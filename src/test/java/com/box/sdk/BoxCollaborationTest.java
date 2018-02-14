package com.box.sdk;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.eclipsesource.json.JsonObject;
import org.junit.Assert;
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
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();

        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, originalRole);

        assertThat(collabInfo.getRole(), is(equalTo(originalRole)));

        BoxCollaboration collab = collabInfo.getResource();
        collabInfo.setRole(newRole);
        collab.updateInfo(collabInfo);

        assertThat(collabInfo.getRole(), is(equalTo(newRole)));
        Collection<BoxCollaboration.Info> collabCollection = folder.getCollaborations();

        assertEquals(collabCollection.size(), 1);

        Iterator<BoxCollaboration.Info> collabs = collabCollection.iterator();
        BoxCollaboration.Info remoteCollab = collabs.next();
        assertThat(remoteCollab.getRole(), is(equalTo(newRole)));


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
    @Category(IntegrationTest.class)
    public void singleFileCollabSucceeds() {
        HashMap<String, BoxCollaboration.Info> collabsMap = new HashMap<String, BoxCollaboration.Info>();
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

        BoxCollaboration.Info collabInfo = uploadedFile.collaborate(collaboratorLogin, originalRole, true, false);

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


        BoxCollaboration.Info collab2Info = uploadedFile.collaborate("davidsmaynard@gmail.com", originalRole,
                true, false);

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
            assertEquals(fileCollabInfo.getAccessibleBy().getName(), localFileCollabInfor.getAccessibleBy().getName());

            assertEquals(fileCollabInfo.getRole(), localFileCollabInfor.getRole());
            assertEquals(fileCollabInfo.getStatus(), localFileCollabInfor.getStatus());
        }

        assertEquals(collabIterator.hasNext(), false);
        assertEquals(2, numCollabs);
        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void acceptPendingCollaboration() {

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        Collection<BoxCollaboration.Info> pendingCollabs = BoxCollaboration.getPendingCollaborations(api);
        for (BoxCollaboration.Info collabInfo : pendingCollabs) {
            // Accept the pending collaboration
            collabInfo.setStatus(BoxCollaboration.Status.ACCEPTED);
            collabInfo.getResource().updateInfo(collabInfo);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void testCanViewPathSucceeds() {

        final String collabID = "12345";
        final Boolean canViewPathOn = true;
        final String collabRole = "previewer";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/collaborations/" + collabID,
                        request.getUrl().toString());
                Assert.assertEquals(canViewPathOn, json.get("can_view_path").asBoolean());
                Assert.assertEquals(collabRole, json.get("role").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxCollaboration collaboration = new BoxCollaboration(api, collabID);
        BoxCollaboration.Info info = collaboration.new Info();
        info.setRole(BoxCollaboration.Role.PREVIEWER);
        info.setCanViewPath(canViewPathOn);
        collaboration.updateInfo(info);
    }
}
