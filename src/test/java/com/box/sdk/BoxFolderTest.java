package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxFolderTest {
    @Test
    @Category(UnitTest.class)
    public void foldersWithSameIDAreEqual() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder folder1 = new BoxFolder(api, "1");
        BoxFolder folder2 = new BoxFolder(api, "1");

        assertThat(folder1, equalTo(folder2));
    }

    @Test
    @Category(IntegrationTest.class)
    public void creatingAndDeletingFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder("[creatingAndDeletingFolderSucceeds] Child Folder");

        assertThat(rootFolder, hasItem(childFolder));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(childFolder)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getFolderInfoReturnsCorrectInfo() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser currentUser = BoxUser.getCurrentUser(api);
        final String expectedName = "[getFolderInfoReturnsCorrectInfo] Child Folder";
        final String expectedCreatedByID = currentUser.getID();

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        final String expectedParentFolderID = rootFolder.getID();
        final String expectedParentFolderName = rootFolder.getInfo().getName();

        BoxFolder childFolder = rootFolder.createFolder(expectedName);
        BoxFolder.Info info = childFolder.getInfo();

        String actualName = info.getName();
        String actualCreatedByID = info.getCreatedBy().getID();
        String actualParentFolderID = info.getParent().getID();
        String actualParentFolderName = info.getParent().getName();
        List<BoxFolder> actualPathCollection = info.getPathCollection();

        assertThat(expectedName, equalTo(actualName));
        assertThat(expectedCreatedByID, equalTo(actualCreatedByID));
        assertThat(expectedParentFolderID, equalTo(actualParentFolderID));
        assertThat(expectedParentFolderName, equalTo(actualParentFolderName));
        assertThat(actualPathCollection, hasItem(rootFolder));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(childFolder)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheNameField() {
        final String expectedName = "All Files";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder.Info rootFolderInfo = rootFolder.getInfo("name");
        final String actualName = rootFolderInfo.getName();
        final String actualDescription = rootFolderInfo.getDescription();
        final long actualSize = rootFolderInfo.getSize();

        assertThat(expectedName, equalTo(actualName));
        assertThat(actualDescription, is(nullValue()));
        assertThat(actualSize, is(0L));
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadFileSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        final String fileContent = "Test file";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        BoxFile uploadedFile = rootFolder.uploadFile(stream, "Test File.txt", null, null);

        assertThat(rootFolder, hasItem(uploadedFile));

        uploadedFile.delete();
        assertThat(rootFolder, not(hasItem(uploadedFile)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFolderInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String originalName = "[updateFolderInfoSucceeds] Child Folder";
        final String updatedName = "[updateFolderInfoSucceeds] Updated Child Folder";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder(originalName);
        BoxFolder.Info info = childFolder.getInfo();
        info.setName(updatedName);
        childFolder.updateInfo(info);
        assertThat(info.getName(), equalTo(updatedName));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(childFolder)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void copyFolderToSameDestinationWithNewNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String originalName = "[copyFolderToSameDestinationWithNewNameSucceeds] Child Folder";
        final String newName = "[copyFolderToSameDestinationWithNewNameSucceeds] New Child Folder";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder originalFolder = rootFolder.createFolder(originalName);
        BoxFolder.Info copiedFolderInfo = originalFolder.copy(rootFolder, newName);
        BoxFolder copiedFolder = copiedFolderInfo.getResource();

        assertThat(copiedFolderInfo.getName(), is(equalTo(newName)));
        assertThat(rootFolder, hasItem(originalFolder));
        assertThat(rootFolder, hasItem(copiedFolder));

        originalFolder.delete(false);
        copiedFolder.delete(false);
        assertThat(rootFolder, not(hasItem(originalFolder)));
        assertThat(rootFolder, not(hasItem(copiedFolder)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void moveFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String child1Name = "[moveFolderSucceeds] Child Folder";
        final String child2Name = "[moveFolderSucceeds] Child Folder 2";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder1 = rootFolder.createFolder(child1Name);
        BoxFolder childFolder2 = rootFolder.createFolder(child2Name);

        assertThat(rootFolder, hasItem(childFolder1));
        assertThat(rootFolder, hasItem(childFolder2));

        childFolder2.move(childFolder1);

        assertThat(childFolder1, hasItem(childFolder2));
        assertThat(rootFolder, not(hasItem(childFolder2)));

        childFolder1.delete(true);
        assertThat(rootFolder, not(hasItem(childFolder1)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void renameFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String originalName = "[renameFolderSucceeds] Original Name";
        final String newName = "[renameFolderSucceeds] New Name";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder(originalName);
        childFolder.rename(newName);

        BoxFolder.Info childFolderInfo = childFolder.getInfo();
        assertThat(childFolderInfo.getName(), is(equalTo(newName)));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(childFolder)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCollaboratorSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[addCollaborationToFolderSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName);

        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);

        assertThat(collabInfo.getAccessibleBy().getLogin(), is(equalTo(collaboratorLogin)));
        assertThat(collabInfo.getRole(), is(equalTo(collaboratorRole)));

        folder.delete(false);
    }
}
