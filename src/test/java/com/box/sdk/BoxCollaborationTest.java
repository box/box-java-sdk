package com.box.sdk;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import org.junit.ClassRule;
import org.junit.Test;

public class BoxCollaborationTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
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

        assertFalse(collabInfo.getCanViewPath());
        assertEquals(fileName, collabInfo.getItem().getName());
        assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
    }

    @Test
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

        assertEquals(BoxCollaboration.Status.PENDING, pendingCollabInfo.getStatus());
        assertEquals(BoxCollaboration.Role.EDITOR, pendingCollabInfo.getRole());
    }

    @Test
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

        assertEquals(2, collaborations.size());
        assertEquals(BoxCollaboration.Status.ACCEPTED, firstCollabInfo.getStatus());
        assertEquals(BoxCollaboration.Role.EDITOR, firstCollabInfo.getRole());
        assertEquals(folderName, firstCollabInfo.getItem().getName());
    }

    @Test
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

        assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
        assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        assertFalse(collabInfo.getCanViewPath());
        assertEquals(collabID, collabInfo.getID());
        assertEquals(itemName, collabInfo.getItem().getName());
        assertEquals(expiresAt, collabInfo.getExpiresAt());

        BoxCollaboration collaboration = new BoxCollaboration(this.api, collabID);
        collabInfo.setRole(BoxCollaboration.Role.VIEWER);
        collabInfo.setExpiresAt(expiresAt);
        collaboration.updateInfo(collabInfo);
        BoxCollaboration.Info updatedCollabInfo = new BoxCollaboration(this.api, collabID).getInfo();

        assertEquals(BoxCollaboration.Role.VIEWER, updatedCollabInfo.getRole());
        assertEquals(expiresAt, collabInfo.getExpiresAt());
    }

    @Test
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

        assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
        assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        assertFalse(collabInfo.getCanViewPath());
        assertEquals(collabID, collabInfo.getID());
        assertEquals(createdByEmail, collabInfo.getCreatedBy().getLogin());
        assertEquals(collabItemID, collabInfo.getItem().getID());
        assertEquals(collabItemName, collabInfo.getItem().getName());
    }

    @Test
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

        assertEquals(accessiblyByLogin, collabInfo.getAccessibleBy().getLogin());
    }

    @Test
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

        assertEquals(inviteEmail, collabInfo.getInviteEmail());
    }
}
