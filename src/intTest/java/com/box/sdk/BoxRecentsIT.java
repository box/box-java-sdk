package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.CleanupTools.deleteFile;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadTwoFileVersionsToUniqueFolder;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link BoxRecents} related integration tests.
 */
public class BoxRecentsIT {
    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    @Test
    public void getRecentsWorkWithoutFields() {
        BoxFile uploadedFile = null;
        try {
            BoxAPIConnection api = jwtApiForServiceAccount();
            api.asUser(TestConfig.getCollaboratorID());

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
        } finally {
            deleteFile(uploadedFile);
        }
    }

    @Test
    public void getRecentsWorkWithFields() {
        BoxFile uploadedFile = null;
        try {
            BoxAPIConnection api = jwtApiForServiceAccount();
            api.asUser(TestConfig.getCollaboratorID());

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
        } finally {
            deleteFile(uploadedFile);
        }
    }

}
