package com.box.sdk;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.eclipsesource.json.JsonObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import sun.tools.jconsole.Plotter;

import javax.swing.*;

public class BoxCollaborationTest {

    /**
     * Wiremock
     */
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

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

        Collection<BoxCollaboration.Info> pendingCollabs = BoxCollaboration.getPendingCollaborations(api);
        for (BoxCollaboration.Info collabInfo : pendingCollabs) {
            // Accept the pending collaboration
            collabInfo.setStatus(BoxCollaboration.Status.ACCEPTED);
            collabInfo.getResource().updateInfo(collabInfo);
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testCanViewPathSendsCorrectJson() throws IOException{

        final String collabID = "12345";
        final boolean canViewPathOn = true;
        final String collaborationURL = "/collaborations/" + collabID;
        String result = "";

        result = TestConfig.getFixture("BoxCollaboration/UpdateCollaboration200");

        JsonObject jsonObject = new JsonObject()
                .add("can_view_path", true)
                .add("role", "editor");

        this.wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(collaborationURL))
                .withRequestBody(WireMock.equalToJson(jsonObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaboration collaboration = new BoxCollaboration(api, collabID);
        BoxCollaboration.Info info = collaboration.new Info();
        info.setRole(BoxCollaboration.Role.EDITOR);
        info.setCanViewPath(canViewPathOn);
        collaboration.updateInfo(info);
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateFileCollaborationSucceeds() throws IOException {
        String result = "";
        final String collaborationURL = "/collaborations";
        final String fileName = "1_1-4_bsp_ball_valve.pdf";

        result = TestConfig.getFixture("BoxCollaboration/CreateFileCollaboration201");

        this.wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(collaborationURL))
                .withQueryParam("notify", WireMock.containing("false"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser collaborator = new BoxUser(api, "1111");
        BoxFile file = new BoxFile(api, "12345");
        BoxCollaboration.Info collabInfo = file.collaborate(collaborator, BoxCollaboration.Role.EDITOR,
                false, false);

        Assert.assertFalse(collabInfo.getCanViewPath());
        Assert.assertEquals(fileName, collabInfo.getItem().getName());
        Assert.assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        Assert.assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
    }

    @Test
    @Category(UnitTest.class)
    public void testAcceptPendingCollaborationSendsCorrectJson() throws IOException{
        final String collabID = "12345";
        final String collaborationURL = "/collaborations";
        final String acceptCollaborationURL = "/collaborations/" + collabID;
        String result = "";
        String updatedResult = "";
        JsonObject acceptInvite = new JsonObject()
                .add("status", "accepted");

        result = TestConfig.getFixture("BoxCollaboration/GetPendingCollaborationInfo200");

        try {
            updatedResult = TestConfig.getFixture("BoxCollaboration/UpdateCollaboration200");
        } catch (IOException e){
            System.out.println("Error Getting Fixture:" + e);
        }

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(collaborationURL))
                .withQueryParam("status", WireMock.containing("pending"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        this.wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(acceptCollaborationURL))
                .withRequestBody(WireMock.equalToJson(acceptInvite.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(updatedResult)));

        Collection<BoxCollaboration.Info> pendingCollaborations = BoxCollaboration.getPendingCollaborations(api);
        for (BoxCollaboration.Info collabInfo : pendingCollaborations) {
            collabInfo.setStatus(BoxCollaboration.Status.ACCEPTED);
            collabInfo.getResource().updateInfo(collabInfo);
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testGetPendingCollaborationInfoSucceeds() throws IOException {
        String result = "";
        final String collaborationURL = "/collaborations";

        result = TestConfig.getFixture("BoxCollaboration/GetPendingCollaborationInfo200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(collaborationURL))
                .withQueryParam("status", WireMock.containing("pending"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Collection<BoxCollaboration.Info> pendingCollaborations = BoxCollaboration.getPendingCollaborations(api);
        BoxCollaboration.Info pendingCollabInfo = pendingCollaborations.iterator().next();

        Assert.assertEquals(BoxCollaboration.Status.PENDING, pendingCollabInfo.getStatus());
        Assert.assertEquals(BoxCollaboration.Role.EDITOR, pendingCollabInfo.getRole());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetCollaborationsOnFolderSucceeds() throws IOException{
        String result = "";
        final String folderID = "12345";
        final String folderName = "Ball Valve Diagram";
        final String getFolderCollaborationURL = "/folders/" + folderID + "/collaborations";

        result = TestConfig.getFixture("BoxCollaboration/GetCollaborationOnFolder200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getFolderCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(api, folderID);
        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();
        BoxCollaboration.Info firstCollabInfo = collaborations.iterator().next();

        Assert.assertEquals(2, collaborations.size());
        Assert.assertEquals(BoxCollaboration.Status.ACCEPTED, firstCollabInfo.getStatus());
        Assert.assertEquals(BoxCollaboration.Role.EDITOR, firstCollabInfo.getRole());
        Assert.assertEquals(folderName, firstCollabInfo.getItem().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeletedCollaborationSucceeds() {
        final String collaborationID = "12345";
        final String deleteCollaborationURL = "/collaborations/" + collaborationID;

        this.wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)));

        BoxCollaboration collaboration = new BoxCollaboration(api, collaborationID);
        collaboration.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateAndEditCollaborationSucceeds() throws IOException{
        String result = "";
        String editResult = "";
        final String createdByEmail = "example@user.com";
        final String collabID = "12345";
        final String itemName = "Ball Valve Diagram";
        final String createCollaborationURL = "/collaborations";
        final String editCollaborationURL =  "/collaborations/" + collabID;

        result = TestConfig.getFixture("BoxCollaboration/CreateCollaboration201");

        editResult = TestConfig.getFixture("BoxCollaboration/UpdateCollaboration200");

        this.wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(createCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        this.wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(editCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(editResult)));

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(editCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(editResult)));

        JsonObject user = new JsonObject()
                .add("id", "2222")
                .add("type", "user");
        JsonObject folder = new JsonObject()
                .add("id", "5678")
                .add("type", "folder");

        BoxCollaboration.Info collabInfo = BoxCollaboration.create(api, user, folder, BoxCollaboration.Role.EDITOR, false, false);

        Assert.assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
        Assert.assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        Assert.assertFalse(collabInfo.getCanViewPath());
        Assert.assertEquals(collabID, collabInfo.getID());
        Assert.assertEquals(itemName, collabInfo.getItem().getName());

        BoxCollaboration collaboration = new BoxCollaboration(api, collabID);
        collabInfo.setRole(BoxCollaboration.Role.VIEWER);
        collaboration.updateInfo(collabInfo);
        BoxCollaboration.Info updatedCollabInfo = new BoxCollaboration(api, collabID).getInfo();

        Assert.assertEquals(BoxCollaboration.Role.VIEWER, updatedCollabInfo.getRole());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetCollaborationInfoSucceeds() throws IOException{
        String result = "";
        final String collabID = "12345";
        final String collabItemID = "2222";
        final String collabItemName = "Ball Valve Diagram";
        final String createdByEmail = "testuser@example.com";
        final String getCollaborationURL = "/collaborations/" + collabID;

        result = TestConfig.getFixture("BoxCollaboration/GetCollaborationInfo200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaboration.Info collabInfo = new BoxCollaboration(api, collabID).getInfo();

        Assert.assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
        Assert.assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        Assert.assertFalse(collabInfo.getCanViewPath());
        Assert.assertEquals(collabID, collabInfo.getID());
        Assert.assertEquals(createdByEmail, collabInfo.getCreatedBy().getLogin());
        Assert.assertEquals(collabItemID, collabInfo.getItem().getID());
        Assert.assertEquals(collabItemName, collabInfo.getItem().getName());
    }
}
