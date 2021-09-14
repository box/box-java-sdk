package com.box.sdk;


import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import static com.box.sdk.BoxCollaborationAllowlist.AllowlistDirection.INBOUND;
import static com.box.sdk.UniqueTestFolder.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BoxCollaborationTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

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
    public void updateInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
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
    @Category(IntegrationTest.class)
    public void deleteSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
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

            assertThat(folder.getCollaborations(), Matchers.<BoxCollaboration.Info>hasSize(0));
        } finally {
            deleteFolder(folder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void singleFileCollabSucceeds() {
        HashMap<String, BoxCollaboration.Info> collabsMap = new HashMap<>();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
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
                assertEquals(fileCollabInfo.getAccessibleBy().getName(), localFileCollabInfor.getAccessibleBy().getName());

                assertEquals(fileCollabInfo.getRole(), localFileCollabInfor.getRole());
                assertEquals(fileCollabInfo.getStatus(), localFileCollabInfor.getStatus());
            }

            assertEquals(2, numCollabs);
        } finally {
            deleteFile(uploadedFile);
            if (allowList != null) {
                allowList.delete();
            }
        }
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
    @Category(UnitTest.class)
    public void testCreateFileCollaborationSucceeds() throws IOException {
        final String collaborationURL = "/collaborations";
        final String fileName = "1_1-4_bsp_ball_valve.pdf";

        String result = TestConfig.getFixture("BoxCollaboration/CreateFileCollaboration201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(collaborationURL))
                .withQueryParam("notify", WireMock.containing("false"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxUser collaborator = new BoxUser(this.api, "1111");
        BoxFile file = new BoxFile(this.api, "12345");
        BoxCollaboration.Info collabInfo = file.collaborate(collaborator, BoxCollaboration.Role.EDITOR,
                false, false);

        Assert.assertFalse(collabInfo.getCanViewPath());
        Assert.assertEquals(fileName, collabInfo.getItem().getName());
        Assert.assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        Assert.assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
    }

    @Test
    @Category(UnitTest.class)
    public void testAcceptPendingCollaborationSendsCorrectJson() throws IOException {
        final String collabID = "12345";
        final String collaborationURL = "/collaborations";
        final String acceptCollaborationURL = "/collaborations/" + collabID;
        String updatedResult = "";
        JsonObject acceptInvite = new JsonObject()
                .add("status", "accepted");

        String result = TestConfig.getFixture("BoxCollaboration/GetPendingCollaborationInfo200");

        try {
            updatedResult = TestConfig.getFixture("BoxCollaboration/UpdateCollaboration200");
        } catch (IOException e) {
            System.out.println("Error Getting Fixture:" + e);
        }

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(collaborationURL))
                .withQueryParam("status", WireMock.containing("pending"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(acceptCollaborationURL))
                .withRequestBody(WireMock.equalToJson(acceptInvite.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(updatedResult)));

        Collection<BoxCollaboration.Info> pendingCollaborations = BoxCollaboration.getPendingCollaborations(this.api);
        for (BoxCollaboration.Info collabInfo : pendingCollaborations) {
            collabInfo.setStatus(BoxCollaboration.Status.ACCEPTED);
            collabInfo.getResource().updateInfo(collabInfo);
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testGetPendingCollaborationInfoSucceeds() throws IOException {
        final String collaborationURL = "/collaborations";

        String result = TestConfig.getFixture("BoxCollaboration/GetPendingCollaborationInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(collaborationURL))
                .withQueryParam("status", WireMock.containing("pending"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Collection<BoxCollaboration.Info> pendingCollaborations = BoxCollaboration.getPendingCollaborations(this.api);
        BoxCollaboration.Info pendingCollabInfo = pendingCollaborations.iterator().next();

        Assert.assertEquals(BoxCollaboration.Status.PENDING, pendingCollabInfo.getStatus());
        Assert.assertEquals(BoxCollaboration.Role.EDITOR, pendingCollabInfo.getRole());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetCollaborationsOnFolderSucceeds() throws IOException {
        final String folderID = "12345";
        final String folderName = "Ball Valve Diagram";
        final String getFolderCollaborationURL = "/folders/" + folderID + "/collaborations";

        String result = TestConfig.getFixture("BoxCollaboration/GetCollaborationOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getFolderCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
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

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)));

        BoxCollaboration collaboration = new BoxCollaboration(this.api, collaborationID);
        collaboration.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateAndEditCollaborationSucceeds() throws IOException {
        final String collabID = "12345";
        final String itemName = "Ball Valve Diagram";
        final String createCollaborationURL = "/collaborations";
        final String editCollaborationURL = "/collaborations/" + collabID;

        String result = TestConfig.getFixture("BoxCollaboration/CreateCollaboration201");

        String editResult = TestConfig.getFixture("BoxCollaboration/UpdateCollaboration200");

        Date expiresAt = new Date(1586289090000L);
        JsonObject user = new JsonObject()
                .add("id", "2222")
                .add("type", "user");
        JsonObject folder = new JsonObject()
                .add("id", "5678")
                .add("type", "folder");

        JsonObject createBody = new JsonObject()
                .add("accessible_by", user)
                .add("item", folder)
                .add("role", BoxCollaboration.Role.EDITOR.toJSONString())
                .add("can_view_path", false)
                .add("expires_at", BoxDateFormat.format(expiresAt));

        JsonObject updateBody = new JsonObject()
                .add("role", BoxCollaboration.Role.VIEWER.toJSONString())
                .add("expires_at", BoxDateFormat.format(expiresAt));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(createCollaborationURL))
                .withRequestBody(WireMock.equalToJson(createBody.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(editCollaborationURL))
                .withRequestBody(WireMock.equalToJson(updateBody.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(editResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(editCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(editResult)));

        BoxCollaboration.Info collabInfo = BoxCollaboration.create(this.api, user, folder, BoxCollaboration.Role.EDITOR,
                false, false, expiresAt);

        Assert.assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
        Assert.assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        Assert.assertFalse(collabInfo.getCanViewPath());
        Assert.assertEquals(collabID, collabInfo.getID());
        Assert.assertEquals(itemName, collabInfo.getItem().getName());
        Assert.assertEquals(expiresAt, collabInfo.getExpiresAt());

        BoxCollaboration collaboration = new BoxCollaboration(this.api, collabID);
        collabInfo.setRole(BoxCollaboration.Role.VIEWER);
        collabInfo.setExpiresAt(expiresAt);
        collaboration.updateInfo(collabInfo);
        BoxCollaboration.Info updatedCollabInfo = new BoxCollaboration(this.api, collabID).getInfo();

        Assert.assertEquals(BoxCollaboration.Role.VIEWER, updatedCollabInfo.getRole());
        Assert.assertEquals(expiresAt, collabInfo.getExpiresAt());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetCollaborationInfoSucceeds() throws IOException {
        final String collabID = "12345";
        final String collabItemID = "2222";
        final String collabItemName = "Ball Valve Diagram";
        final String createdByEmail = "testuser@example.com";
        final String getCollaborationURL = "/collaborations/" + collabID;

        String result = TestConfig.getFixture("BoxCollaboration/GetCollaborationInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaboration.Info collabInfo = new BoxCollaboration(this.api, collabID).getInfo();

        Assert.assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
        Assert.assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        Assert.assertFalse(collabInfo.getCanViewPath());
        Assert.assertEquals(collabID, collabInfo.getID());
        Assert.assertEquals(createdByEmail, collabInfo.getCreatedBy().getLogin());
        Assert.assertEquals(collabItemID, collabInfo.getItem().getID());
        Assert.assertEquals(collabItemName, collabInfo.getItem().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testCanViewPathSendsCorrectJson() throws IOException {
        final String collabID = "12345";
        final boolean canViewPathOn = true;
        final String collaborationURL = "/collaborations/" + collabID;

        String result = TestConfig.getFixture("BoxCollaboration/UpdateCollaboration200");

        JsonObject jsonObject = new JsonObject()
                .add("can_view_path", true)
                .add("role", "editor");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(collaborationURL))
                .withRequestBody(WireMock.equalToJson(jsonObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaboration collaboration = new BoxCollaboration(this.api, collabID);
        BoxCollaboration.Info info = collaboration.new Info();
        info.setRole(BoxCollaboration.Role.EDITOR);
        info.setCanViewPath(canViewPathOn);
        collaboration.updateInfo(info);
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAccessibleLoginSucceeds() throws IOException {
        final String collabID = "12345";
        final String accessiblyByLogin = "example@test.com";
        final String getCollaborationURL = "/collaborations/" + collabID;

        String result = TestConfig.getFixture("BoxCollaboration/GetCollaborationInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaboration.Info collabInfo = new BoxCollaboration(this.api, collabID).getInfo();

        Assert.assertEquals(accessiblyByLogin, collabInfo.getAccessibleBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetInviteEmailSucceeds() throws IOException {
        final String collabID = "12345";
        final String inviteEmail = "example@test.com";
        final String getCollaborationURL = "/collaborations/" + collabID;

        String result = TestConfig.getFixture("BoxCollaboration/GetInviteEmailAttributesOnCollaboration200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaboration.Info collabInfo = new BoxCollaboration(this.api, collabID).getInfo("invite_email");

        Assert.assertEquals(inviteEmail, collabInfo.getInviteEmail());
    }

    private void deleteFolder(BoxFolder folder) {
        if (folder != null) {
            folder.delete(false);
        }
    }

    private void deleteFile(BoxFile uploadedFile) {
        if (uploadedFile != null) {
            uploadedFile.delete();
        }
    }
}
