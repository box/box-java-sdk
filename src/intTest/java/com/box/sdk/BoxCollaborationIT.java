package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.BoxCollaborationAllowlist.AllowlistDirection.INBOUND;
import static com.box.sdk.CleanupTools.deleteFolder;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolderWithSomeContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.eclipsesource.json.JsonObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoxCollaborationIT {

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    @Test
    public void updateInfoSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String folderName = "[addCollaborationToFolderSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role originalRole = BoxCollaboration.Role.VIEWER;
        BoxCollaboration.Role newRole = BoxCollaboration.Role.EDITOR;
        BoxFolder rootFolder = getUniqueFolder(api);
        BoxFolder folder = null;
        try {
            folder = rootFolder.createFolder(folderName).getResource();
            BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, originalRole);

            assertThat(collabInfo.getRole(), is(equalTo(originalRole)));
            assertNotNull(collabInfo.getIsAccessOnly());

            BoxCollaboration collab = collabInfo.getResource();
            collabInfo.setRole(newRole);
            collab.updateInfo(collabInfo);

            assertThat(collabInfo.getRole(), is(equalTo(newRole)));
            Collection<BoxCollaboration.Info> collabCollection = folder.getCollaborations();

            assertEquals(collabCollection.size(), 1);

            Iterator<BoxCollaboration.Info> collabs = collabCollection.iterator();
            BoxCollaboration.Info remoteCollab = collabs.next();
            assertThat(remoteCollab.getRole(), is(equalTo(newRole)));
        } finally {
            deleteFolder(folder);
        }
    }

    @Test
    public void deleteSucceeds() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        String folderName = "[deleteSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.EDITOR;
        BoxFolder rootFolder = UniqueTestFolder.getUniqueFolder(api);
        BoxFolder folder = null;
        try {
            folder = rootFolder.createFolder(folderName).getResource();
            BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);
            BoxCollaboration collab = collabInfo.getResource();

            collab.delete();

            assertThat(folder.getCollaborations(), Matchers.hasSize(0));
        } finally {
            deleteFolder(folder);
        }
    }

    @Test
    public void singleFileCollabSucceeds() {
        HashMap<String, BoxCollaboration.Info> collabsMap = new HashMap<>();
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName = "[singleFileCollabSucceeds] Test File.txt";
        BoxFile uploadedFile = null;
        BoxCollaborationAllowlist allowList = null;

        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);

            String collaboratorLogin = TestConfig.getCollaborator();
            BoxCollaboration.Role originalRole = BoxCollaboration.Role.VIEWER;
            BoxCollaboration.Role newRole = BoxCollaboration.Role.EDITOR;

            BoxCollaboration.Info collabInfo =
                uploadedFile.collaborate(collaboratorLogin, originalRole, true, false);

            collabsMap.put(collabInfo.getID(), collabInfo);

            assertThat(collabInfo.getRole(), is(equalTo(originalRole)));

            BoxCollaboration collab = collabInfo.getResource();
            collabInfo.setRole(newRole);
            collab.updateInfo(collabInfo);

            assertThat(collabInfo.getRole(), is(equalTo(newRole)));

            BoxCollaboration remoteCollab = new BoxCollaboration(api, collab.getID());
            BoxCollaboration.Info remoteInfo = remoteCollab.getInfo();
            assertThat(remoteInfo.getRole(), is(equalTo(newRole)));
            assertThat(remoteInfo.getCreatedBy().getID(), is(collabInfo.getCreatedBy().getID()));


            allowList = BoxCollaborationAllowlist.create(api, "gmail.com", INBOUND).getResource();
            BoxCollaboration.Info collab2Info =
                uploadedFile.collaborate("davidsmaynard@gmail.com", originalRole, false, false);

            collabsMap.put(collab2Info.getID(), collab2Info);

            BoxResourceIterable<BoxCollaboration.Info> collabs = uploadedFile.getAllFileCollaborations();
            Iterator<BoxCollaboration.Info> collabIterator = collabs.iterator();
            int numCollabs = 0;

            while (collabIterator.hasNext()) {
                numCollabs++;
                BoxCollaboration.Info fileCollabInfo = collabIterator.next();
                BoxCollaboration.Info localFileCollabInfor = collabsMap.get(fileCollabInfo.getID());

                assertEquals(fileCollabInfo.getID(), localFileCollabInfor.getID());
                assertEquals(fileCollabInfo.getCreatedBy().getID(), localFileCollabInfor.getCreatedBy().getID());
                assertEquals(fileCollabInfo.getCreatedBy().getName(), localFileCollabInfor.getCreatedBy().getName());

                assertEquals(fileCollabInfo.getAccessibleBy().getID(), localFileCollabInfor.getAccessibleBy().getID());
                assertEquals(fileCollabInfo.getAccessibleBy().getName(),
                    localFileCollabInfor.getAccessibleBy().getName());

                assertEquals(fileCollabInfo.getRole(), localFileCollabInfor.getRole());
                assertEquals(fileCollabInfo.getStatus(), localFileCollabInfor.getStatus());
            }

            assertEquals(2, numCollabs);
        } finally {
            CleanupTools.deleteFile(uploadedFile);
            if (allowList != null) {
                allowList.delete();
            }
        }
    }

    @Test
    public void acceptPendingCollaboration() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        Collection<BoxCollaboration.Info> pendingCollabs = BoxCollaboration.getPendingCollaborations(api);
        for (BoxCollaboration.Info collabInfo : pendingCollabs) {
            // Accept the pending collaboration
            collabInfo.setStatus(BoxCollaboration.Status.ACCEPTED);
            collabInfo.getResource().updateInfo(collabInfo);
        }
    }

    @Test
    public void singleFileCollabWithAccessOnlySucceeds() {
        HashMap<String, BoxCollaboration.Info> collabsMap = new HashMap<>();
        BoxAPIConnection api = jwtApiForServiceAccount();
        String fileName = "[singleFileCollabSucceeds] Test File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
            JsonObject user = new JsonObject()
                    .add("login", TestConfig.getCollaborator())
                    .add("type", "user");
            JsonObject file = new JsonObject()
                    .add("id", uploadedFile.getID())
                    .add("type", "file");

            BoxCollaboration.Role originalRole = BoxCollaboration.Role.VIEWER;
            BoxCollaboration.Info collabInfo = BoxCollaboration.create(api, user, file, originalRole,
                    false, false, null, true);

            assertThat(collabInfo.getRole(), is(equalTo(originalRole)));
            assertTrue(collabInfo.getIsAccessOnly());
        } finally {
            CleanupTools.deleteFile(uploadedFile);
        }
    }
}
