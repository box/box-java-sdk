package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.options;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class EventStreamTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void canStopStreamWhileWaitingForAPIResponse() throws InterruptedException {
        final long streamPosition = 123456;
        final String realtimeServerURL = "/2.0/realtimeServer?channel=0";
        String eventsURL = "/2.0/events";

        stubFor(options(urlEqualTo(eventsURL))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"entries\": [ { \"url\": \"https://localhost:" + wireMockRule.httpsPort()
                    + realtimeServerURL + "\", \"max_retries\": \"3\", \"retry_timeout\": 60000 } ] }")));

        stubFor(get(urlPathMatching(eventsURL))
            .withQueryParam("stream_position", WireMock.equalTo("now"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"next_stream_position\": " + streamPosition + ",\"entries\":[] }")));

        stubFor(get(urlMatching("/2.0/realtimeServer.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"message\": \"new_change\" }")));


        final EventStream stream = new EventStream(api);
        final Object requestLock = new Object();
        this.wireMockRule.addMockServiceRequestListener((request, response) -> {
            String streamPositionURL = realtimeServerURL + "&stream_position=" + streamPosition;
            boolean requestUrlMatch = request.getUrl().contains(streamPositionURL);
            if (requestUrlMatch) {
                stream.stop();

                synchronized (requestLock) {
                    requestLock.notify();
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
    public void duplicateEventsAreNotReported() throws InterruptedException {
        final String realtimeServerURL = "/2.0/realtimeServer?channel=0";
        String eventsURL = "/2.0/events";

        stubFor(options(urlEqualTo(eventsURL))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"entries\": [ { \"url\": \"https://localhost:" + wireMockRule.httpsPort()
                    + realtimeServerURL + "\", \"max_retries\": \"3\", \"retry_timeout\": 60000 } ] }")));

        stubFor(get(urlMatching("/2.0/events\\?.*stream_position=now.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"next_stream_position\": 0 }")));

        stubFor(get(urlMatching("/2.0/events\\?.*stream_position=0"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"next_stream_position\": 1, \"entries\": [ { \"type\": \"event\", "
                    + "\"event_id\": \"1\" } ] }")));

        stubFor(get(urlMatching("/2.0/events\\?.*stream_position=1"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"next_stream_position\": -1, \"entries\": [ { \"type\": \"event\", "
                    + "\"event_id\": \"1\" } ] }")));

        stubFor(get(urlMatching("/2.0/events\\?.*stream_position=-1"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"next_stream_position\": -1, \"entries\": [] }")));

        stubFor(get(urlMatching("/2.0/realtimeServer.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"message\": \"new_change\" }")));

        final EventStream stream = new EventStream(api);
        final EventListener eventListener = mock(EventListener.class);
        stream.addListener(eventListener);

        final Object requestLock = new Object();
        this.wireMockRule.addMockServiceRequestListener((request, response) -> {
            boolean requestUrlMatch = request.getUrl().contains("-1");
            if (requestUrlMatch) {
                synchronized (requestLock) {
                    requestLock.notify();
                }
            }
        });

        stream.start();
        synchronized (requestLock) {
            requestLock.wait();
        }

        verify(eventListener).onEvent(any(BoxEvent.class));
    }

    @Test
    public void delayBetweenCalls() throws InterruptedException {

        final String realtimeServerURL = "/2.0/realtimeServer?channel=0";
        final int delay = 1000;
        final long[] times = new long[2];

        stubFor(options(urlEqualTo("/2.0/events"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"entries\": [ { \"url\": \"https://localhost:" + wireMockRule.httpsPort()
                    + realtimeServerURL + "\", \"max_retries\": \"3\", \"retry_timeout\": 60000 } ] }")));

        stubFor(get(urlMatching("/2.0/events\\?.*stream_position=now.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"next_stream_position\": 123 }")));

        stubFor(get(urlMatching("/2.0/realtimeServer.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"message\": \"new_change\" }")));

        stubFor(get(urlMatching("/2.0/events\\?.*stream_position=123.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"next_stream_position\": 456, \"entries\": [ { \"type\": \"event\", "
                    + "\"event_id\": \"1\" } ] }")));

        stubFor(get(urlMatching("/2.0/events\\?.*stream_position=456.*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{ \"next_stream_position\": 789, \"entries\": [ { \"type\": \"event\", "
                    + "\"event_id\": \"1\" } ] }")));

        final EventStream stream = new EventStream(api, -1, delay);
        final EventListener eventListener = mock(EventListener.class);
        stream.addListener(eventListener);

        final CountDownLatch latch = new CountDownLatch(3);
        this.wireMockRule.addMockServiceRequestListener((request, response) -> {
            if (request.getUrl().contains("stream_position=123")) {
                times[0] = System.currentTimeMillis();
                latch.countDown();
            }
            if (request.getUrl().contains("stream_position=456")) {
                times[1] = System.currentTimeMillis();
                latch.countDown();
            }
            if (request.getUrl().contains("stream_position=789")) {
                latch.countDown();
            }
        });

        stream.start();
        assertTrue("EventStream was interuppted", latch.await(5, TimeUnit.SECONDS));
        stream.stop();

        assertTrue("Calls should be be 1s apart", times[1] - times[0] >= delay);
    }
}
