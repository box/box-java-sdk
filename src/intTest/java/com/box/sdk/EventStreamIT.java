package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.UniqueTestFolder.randomizeName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class EventStreamIT {

    @Test
    public void receiveEventsForFolderCreateAndFolderDelete() throws InterruptedException {
        final LinkedBlockingQueue<BoxEvent> observedEvents = new LinkedBlockingQueue<>();
        BoxAPIConnection api = jwtApiForServiceAccount();
        EventStream stream = new EventStream(api);
        stream.addListener(new EventListener() {
            @Override
            public void onEvent(BoxEvent event) {
                observedEvents.add(event);
            }

            @Override
            public void onNextPosition(long position) {
            }

            @Override
            public boolean onException(Throwable e) {
                return true;
            }
        });
        stream.start();

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder(
            randomizeName("[receiveEventsForFolderCreateAndFolderDelete] Child Folder")
        ).getResource();
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
            BoxResource.Info sourceInfo = event.getSourceInfo();
            //  Some events may not have sourceInfo
            if (sourceInfo == null) {
                continue;
            }
            BoxResource source = sourceInfo.getResource();
            if (source instanceof BoxFolder) {
                BoxFolder sourceFolder = (BoxFolder) source;
                if (sourceFolder.getID().equals(expectedID)) {
                    if (event.getEventType() == BoxEvent.EventType.ITEM_CREATE) {
                        BoxFolder folder = (BoxFolder) event.getSourceInfo().getResource();
                        final String eventFolderID = folder.getID();
                        final String childFolderID = childFolder.getID();
                        assertThat(eventFolderID, is(equalTo(childFolderID)));
                        createdEventFound = true;
                    }

                    if (event.getEventType() == BoxEvent.EventType.ITEM_TRASH) {
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
}
