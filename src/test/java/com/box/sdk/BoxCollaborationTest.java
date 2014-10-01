package com.box.sdk;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

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
        BoxFolder folder = rootFolder.createFolder(folderName);

        BoxCollaboration.Info collabInfo = folder.addCollaborator(collaboratorLogin, originalRole);

        assertThat(collabInfo.getRole(), is(equalTo(originalRole)));

        BoxCollaboration collab = collabInfo.getResource();
        collabInfo.setRole(newRole);
        collab.updateInfo(collabInfo);

        assertThat(collabInfo.getRole(), is(equalTo(newRole)));

        folder.delete(false);
    }
}
