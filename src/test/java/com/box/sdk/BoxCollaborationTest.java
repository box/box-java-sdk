package com.box.sdk;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

public class BoxCollaborationTest {
    @Test
    @Category(IntegrationTest.class)
    public void updateInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[addCollaborationToFolderSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role originalRole = BoxCollaboration.Role.VIEWER;
        BoxCollaboration.Role newRole = BoxCollaboration.Role.EDITOR;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();

        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, originalRole);

        assertThat(collabInfo.getRole(), is(equalTo(originalRole)));

        BoxCollaboration collab = collabInfo.getResource();
        collabInfo.setRole(newRole);
        collab.updateInfo(collabInfo);

        assertThat(collabInfo.getRole(), is(equalTo(newRole)));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[deleteSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.EDITOR;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();

        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);
        BoxCollaboration collab = collabInfo.getResource();
        collab.delete();

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTestDebug.class)
    public void singleFileCollabSucceeds() {
        Logger logger = TestConfig.enableLogger("FINE");
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[singleFileCollabSucceeds] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);
        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role originalRole = BoxCollaboration.Role.VIEWER;
        BoxCollaboration.Role newRole = BoxCollaboration.Role.EDITOR;

        BoxCollaboration.Info collabInfo = uploadedFile.collaborate(collaboratorLogin, originalRole);

        assertThat(collabInfo.getRole(), is(equalTo(originalRole)));

        BoxCollaboration collab = collabInfo.getResource();
        collabInfo.setRole(newRole);
        collab.updateInfo(collabInfo);

        assertThat(collabInfo.getRole(), is(equalTo(newRole)));

        uploadedFile.delete();
    }

}
