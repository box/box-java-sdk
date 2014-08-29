package com.box.sdk;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxFolderTest {
    @Test
    @Category(UnitTest.class)
    public void foldersWithSameIDAreEqual() {
        BoxFolder folder1 = new BoxFolder(null, "1");
        BoxFolder folder2 = new BoxFolder(null, "1");

        assertThat(folder1, equalTo(folder2));
    }

    @Test
    @Category(IntegrationTest.class)
    public void creatingAndDeletingFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAuthToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder("[creatingAndDeletingFolderSucceeds] Child Folder");

        assertThat(rootFolder, hasItem(childFolder));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(childFolder)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getFolderInfoReturnsCorrectInfo() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAuthToken());
        BoxUser currentUser = BoxUser.getCurrentUser(api);
        final String expectedName = "[getFolderInfoReturnsCorrectInfo] Child Folder";
        final String expectedCreatedByID = currentUser.getID();

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder(expectedName);
        BoxFolder.Info info = childFolder.getInfo();

        String actualName = info.getName();
        String actualCreatedByID = info.getCreatedBy().getID();
        List<BoxFolder> actualPathCollection = info.getPathCollection();

        assertThat(expectedName, equalTo(actualName));
        assertThat(expectedCreatedByID, equalTo(actualCreatedByID));
        assertThat(actualPathCollection, hasItem(rootFolder));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(childFolder)));
    }
}
