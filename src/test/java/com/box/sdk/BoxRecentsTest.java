package com.box.sdk;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * {@link BoxRecents} related tests.
 */
public class BoxRecentsTest {

    @Test
    @Category(IntegrationTest.class)
    public void getRecentsWorkWithoutFields() {
        BoxFile uploadedFile = null;
        try {
            BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

            //Create a file to check if it comes up in recents
            String fileName = "[recentItemTest] Multi-version File.txt";
            String version1Content = "Version 1";
            String version1Sha = "db3cbc01da600701b9fe4a497fe328e71fa7022f";
            String version2Content = "Version 2";
            uploadedFile = BoxFileTest.createAndUpdateFileHelper(fileName, version1Content, version2Content, null);

            BoxResourceIterable<BoxRecentItem> recentItems = BoxRecents.getRecentItems(api, 100);
            Iterator<BoxRecentItem> recentItemIterator = recentItems.iterator();
            if (recentItemIterator.hasNext()) {
                BoxRecentItem recentItem = recentItemIterator.next();
                assertNotNull("type should be not be null", recentItem.getType());
                assertNotNull("interactedAt date should not be null", recentItem.getInteractedAt());
                assertNotNull("interactionType should not be null", recentItem.getInteractionType());
                assertNotNull("item should not be null", recentItem.getItem());
            }
            assertNotNull("Should receive a response", recentItems);
        } catch (Exception e) {
            assertNull("There should have been no exception", e);
        } finally {
            if (uploadedFile != null) {
                uploadedFile.delete();
            }
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getRecentsWorkWithFields() {
        BoxFile uploadedFile = null;
        try {
            BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

            //Create a file to check if it comes up in recents
            String fileName = "[recentItemTest] Multi-version File.txt";
            String version1Content = "Version 1";
            String version1Sha = "db3cbc01da600701b9fe4a497fe328e71fa7022f";
            String version2Content = "Version 2";
            uploadedFile = BoxFileTest.createAndUpdateFileHelper(fileName, version1Content, version2Content, null);

            BoxResourceIterable<BoxRecentItem> recentItems = BoxRecents.getRecentItems(api, 100, "created_at");
            Iterator<BoxRecentItem> recentItemIterator = recentItems.iterator();
            if (recentItemIterator.hasNext()) {
                BoxRecentItem recentItem = recentItemIterator.next();
                assertNotNull("type should be not be null", recentItem.getType());
                assertNotNull("interactedAt date should not be null", recentItem.getInteractedAt());
                assertNotNull("interactionType should not be null", recentItem.getInteractionType());
                assertNotNull("item should not be null", recentItem.getItem());
                assertNotNull("item's created_at should not be null", recentItem.getItem().getCreatedAt());
            }
            assertNotNull("Should receive a response", recentItems);
        } catch (Exception e) {
            assertNull("There should have been no exception", e);
        } finally {
            if (uploadedFile != null) {
                uploadedFile.delete();
            }
        }
    }
}
