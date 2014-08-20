package com.box.sdk;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EventStreamTest {
    @Test
    @Category(IntegrationTest.class)
    public void receiveEventsForFolderCreateAndFolderDelete() throws InterruptedException {
        final LinkedBlockingQueue<BoxEvent> observedEvents = new LinkedBlockingQueue<BoxEvent>();

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAuthToken());
        EventStream stream = new EventStream(api);
        stream.addListener(new EventListener() {
            public void onEvent(BoxEvent event) {
                observedEvents.add(event);
            }
        });
        stream.start();

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder("[receiveEventsForFolderCreateAndFolderDelete] Child Folder");
        String expectedID = childFolder.getID();
        childFolder.delete(false);

        boolean createdEventFound = false;
        boolean deletedEventFound = false;
        while (!createdEventFound || !deletedEventFound) {
            BoxEvent event = observedEvents.poll(1, TimeUnit.MINUTES);
            BoxResource source = event.getSource();
            if (source instanceof BoxFolder) {
                BoxFolder sourceFolder = (BoxFolder) source;
                if (sourceFolder.getID().equals(expectedID)) {
                    if (event.getType() == BoxEvent.Type.ITEM_CREATE) {
                        createdEventFound = true;
                    }

                    if (event.getType() == BoxEvent.Type.ITEM_TRASH) {
                        deletedEventFound = true;
                    }
                }
            }
        }

        assertTrue(createdEventFound);
        assertTrue(deletedEventFound);
        stream.stop();
    }
}
