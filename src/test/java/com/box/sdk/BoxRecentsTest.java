package com.box.sdk;

import static com.box.sdk.UniqueTestFolder.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * {@link BoxRecents} related tests.
 */
public class BoxRecentsTest {
    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getRecentsWorkWithoutFields() {
        BoxFile uploadedFile = null;
        try {
            BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

            //Create a file to check if it comes up in recents
            String fileName = "[recentItemTest] Multi-version File.txt";
            String version1Content = "Version 1";
            String version2Content = "Version 2";
            uploadedFile = uploadTwoFileVersionsToUniqueFolder(fileName, version1Content, version2Content, null);

            BoxResourceIterable<BoxRecentItem> recentItems = BoxRecents.getRecentItems(api, 100);
            assertNotNull("Should receive a response", recentItems);
            Iterator<BoxRecentItem> recentItemIterator = recentItems.iterator();
            if (recentItemIterator.hasNext()) {
                BoxRecentItem recentItem = recentItemIterator.next();
                assertNotNull("type should be not be null", recentItem.getType());
                assertNotNull("interactedAt date should not be null", recentItem.getInteractedAt());
                assertNotNull("interactionType should not be null", recentItem.getInteractionType());
                assertNotNull("item should not be null", recentItem.getItem());
            }
        } catch (Exception e) {
            assertNull("There should have been no exception", e);
        } finally {
            this.deleteFile(uploadedFile);
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
            String version2Content = "Version 2";
            uploadedFile = uploadTwoFileVersionsToUniqueFolder(fileName, version1Content, version2Content, null);

            BoxResourceIterable<BoxRecentItem> recentItems = BoxRecents.getRecentItems(api, 100, "created_at");
            assertNotNull("Should receive a response", recentItems);
            Iterator<BoxRecentItem> recentItemIterator = recentItems.iterator();
            if (recentItemIterator.hasNext()) {
                BoxRecentItem recentItem = recentItemIterator.next();
                assertNotNull("type should be not be null", recentItem.getType());
                assertNotNull("interactedAt date should not be null", recentItem.getInteractedAt());
                assertNotNull("interactionType should not be null", recentItem.getInteractionType());
                assertNotNull("item should not be null", recentItem.getItem());
                assertNotNull("item's created_at should not be null", recentItem.getItem().getCreatedAt());
            }
        } catch (Exception e) {
            assertNull("There should have been no exception", e);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    private void deleteFile(BoxFile uploadedFile) {
        if (uploadedFile != null) {
            uploadedFile.delete();
        }
    }
}
