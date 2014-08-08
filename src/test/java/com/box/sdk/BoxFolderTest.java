package com.box.sdk;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxFolderTest {
    @Test
    @Category(UnitTest.class)
    public void foldersWithSameIDAreEqual() {
        BoxFolder folder1 = new BoxFolder(null, "1");
        BoxFolder folder2 = new BoxFolder(null, "1");

        assertTrue(folder1.equals(folder2));
    }

    @Test
    @Category(IntegrationTest.class)
    public void creatingAndDeletingFolderSucceeds() {
        OAuthSession session = new OAuthSession(TestConfig.getAuthToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(session);
        BoxFolder childFolder = rootFolder.createFolder("[createAndDeleteFolder] Child Folder");

        assertThat(rootFolder, hasItem(childFolder));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(childFolder)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getFolderInfoReturnsCorrectInfo() throws InterruptedException {
        final String expectedName = "[getFolderInfo] Child Folder";
        OAuthSession session = new OAuthSession(TestConfig.getAuthToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(session);
        BoxFolder childFolder = rootFolder.createFolder(expectedName);
        BoxFolder.Info info = childFolder.getInfo();
        String actualName = info.getName();

        assertThat(expectedName, equalTo(actualName));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(childFolder)));
    }
}
