package com.box.sdk;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.RequestListener;
import com.github.tomakehurst.wiremock.http.Response;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class EventStreamTest {
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    @Category(IntegrationTest.class)
    public void receiveEventsForFolderCreateAndFolderDelete() throws InterruptedException {
        final LinkedBlockingQueue<BoxEvent> observedEvents = new LinkedBlockingQueue<BoxEvent>();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        EventStream stream = new EventStream(api);
        stream.addListener(new EventListener() {
            @Override
            public void onEvent(BoxEvent event) {
                observedEvents.add(event);
            }

            @Override
            public void onNextPosition(long position) {
                return;
            }

            @Override
            public boolean onException(Throwable e) {
                return true;
            }
        });
        stream.start();

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder("[receiveEventsForFolderCreateAndFolderDelete] Child Folder")
            .getResource();
        String expectedID = childFolder.getID();
        childFolder.delete(false);

        boolean createdEventFound = false;
        boolean deletedEventFound = false;
        int timeouts = 0;
        while (timeouts < 3 && (!createdEventFound || !deletedEventFound)) {
            BoxEvent event = observedEvents.poll(1, TimeUnit.MINUTES);
            if (null == event) {
                timeouts++;
                System.out.println("Time outs: " + timeouts);
                continue;
            }
            BoxResource.Info  sourceInfo = event.getSourceInfo();
            //  Some events may not have sourceInfo
            if (sourceInfo == null) {
                continue;
            }
            BoxResource source = sourceInfo.getResource();
            if (source instanceof BoxFolder) {
                BoxFolder sourceFolder = (BoxFolder) source;
                if (sourceFolder.getID().equals(expectedID)) {
                    if (event.getType() == BoxEvent.Type.ITEM_CREATE) {
                        BoxFolder folder = (BoxFolder) event.getSourceInfo().getResource();
                        final String eventFolderID = folder.getID();
                        final String childFolderID = childFolder.getID();
                        assertThat(eventFolderID, is(equalTo(childFolderID)));
                        createdEventFound = true;
                    }

                    if (event.getType() == BoxEvent.Type.ITEM_TRASH) {
                        BoxFolder folder = (BoxFolder) event.getSourceInfo().getResource();
                        assertThat(folder.getID(), is(equalTo(childFolder.getID())));
                        deletedEventFound = true;
                    }
                }
            }
        }

        assertThat(createdEventFound, is(true));
        assertThat(deletedEventFound, is(true));
        stream.stop();
    }

    @Test
    @Category(UnitTest.class)
    public void canStopStreamWhileWaitingForAPIResponse() throws InterruptedException {
        final long streamPosition = 0;
        final String realtimeServerURL = "/realtimeServer?channel=0";

        stubFor(options(urlEqualTo("/events"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"entries\": [ { \"url\": \"http://localhost:8080" + realtimeServerURL + "\", "
                    + "\"max_retries\": \"3\", \"retry_timeout\": 60000 } ] }")));

        stubFor(get(urlMatching("/events\\?.*stream_position=now.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"next_stream_position\": " + streamPosition + " }")));

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        final EventStream stream = new EventStream(api);
        final Object requestLock = new Object();
        this.wireMockRule.addMockServiceRequestListener(new RequestListener() {
            @Override
            public void requestReceived(Request request, Response response) {
                String streamPositionURL = realtimeServerURL + "&stream_position=" + streamPosition;
                boolean requestUrlMatch = request.getUrl().contains(streamPositionURL);
                if (requestUrlMatch) {
                    stream.stop();

                    synchronized (requestLock) {
                        requestLock.notify();
                    }
                }
            }
        });

        stream.start();
        synchronized (requestLock) {
            requestLock.wait();
        }

        assertThat(stream.isStarted(), is(false));
    }

    @Test
    @Category(UnitTest.class)
    public void duplicateEventsAreNotReported() throws InterruptedException {
        final String realtimeServerURL = "/realtimeServer?channel=0";

        stubFor(options(urlEqualTo("/events"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"entries\": [ { \"url\": \"http://localhost:8080" + realtimeServerURL + "\", "
                    + "\"max_retries\": \"3\", \"retry_timeout\": 60000 } ] }")));

        stubFor(get(urlMatching("/events\\?.*stream_position=now.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"next_stream_position\": 0 }")));

        stubFor(get(urlMatching("/realtimeServer.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"message\": \"new_change\" }")));

        stubFor(get(urlMatching("/events\\?.*stream_position=0"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"next_stream_position\": 1, \"entries\": [ { \"type\": \"event\", "
                    + "\"event_id\": \"1\" } ] }")));

        stubFor(get(urlMatching("/events\\?.*stream_position=1"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"next_stream_position\": -1, \"entries\": [ { \"type\": \"event\", "
                    + "\"event_id\": \"1\" } ] }")));

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        final EventStream stream = new EventStream(api);
        final EventListener eventListener = mock(EventListener.class);
        stream.addListener(eventListener);

        final Object requestLock = new Object();
        this.wireMockRule.addMockServiceRequestListener(new RequestListener() {
            @Override
            public void requestReceived(Request request, Response response) {
                boolean requestUrlMatch = request.getUrl().contains("-1");
                if (requestUrlMatch) {
                    synchronized (requestLock) {
                        requestLock.notify();
                    }
                }
            }
        });

        stream.start();
        synchronized (requestLock) {
            requestLock.wait();
        }

        verify(eventListener).onEvent(any(BoxEvent.class));
    }
}
