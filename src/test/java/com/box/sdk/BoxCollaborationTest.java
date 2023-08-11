package com.box.sdk;


import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.*;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.Collection;
import java.util.Date;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoxCollaborationTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void testCreateFileCollaborationSucceeds() {
        final String collaborationURL = "/2.0/collaborations";
        final String fileName = "1_1-4_bsp_ball_valve.pdf";

        String result = TestUtils.getFixture("BoxCollaboration/CreateFileCollaboration201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(collaborationURL))
            .withQueryParam("notify", WireMock.containing("false"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxUser collaborator = new BoxUser(this.api, "1111");
        BoxFile file = new BoxFile(this.api, "12345");
        Date expiresAt = new Date(1586289090000L);
        BoxCollaboration.Info collabInfo = file.collaborate(
            collaborator, BoxCollaboration.Role.EDITOR, false, false, expiresAt, true
        );

        assertFalse(collabInfo.getCanViewPath());
        assertEquals(fileName, collabInfo.getItem().getName());
        assertEquals(BoxFile.TYPE, collabInfo.getItem().getType());
        assertEquals(BoxFile.Info.class, collabInfo.getItem().getClass());
        assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
        assertEquals(expiresAt.getTime(), collabInfo.getExpiresAt().getTime());
        assertTrue(collabInfo.getIsAccessOnly());
    }

    @Test
    public void testAcceptPendingCollaborationSendsCorrectJson() {
        final String collabID = "12345";
        final String collaborationURL = "/2.0/collaborations";
        final String acceptCollaborationURL = "/2.0/collaborations/" + collabID;
        JsonObject acceptInvite = new JsonObject()
            .add("status", "accepted");

        String result = TestUtils.getFixture("BoxCollaboration/GetPendingCollaborationInfo200");
        String updatedResult = TestUtils.getFixture("BoxCollaboration/UpdateCollaboration200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(collaborationURL))
            .withQueryParam("status", WireMock.containing("pending"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(acceptCollaborationURL))
            .withRequestBody(WireMock.equalToJson(acceptInvite.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(updatedResult)));

        Collection<BoxCollaboration.Info> pendingCollaborations = BoxCollaboration.getPendingCollaborations(this.api);
        for (BoxCollaboration.Info collabInfo : pendingCollaborations) {
            collabInfo.setStatus(BoxCollaboration.Status.ACCEPTED);
            collabInfo.getResource().updateInfo(collabInfo);
        }
    }

    @Test
    public void testGetPendingCollaborationInfoSucceeds() {
        final String collaborationURL = "/2.0/collaborations";

        String result = TestUtils.getFixture("BoxCollaboration/GetPendingCollaborationInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(collaborationURL))
            .withQueryParam("status", WireMock.containing("pending"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        Collection<BoxCollaboration.Info> pendingCollaborations = BoxCollaboration.getPendingCollaborations(this.api);
        BoxCollaboration.Info pendingCollabInfo = pendingCollaborations.iterator().next();

        assertEquals(BoxCollaboration.Status.PENDING, pendingCollabInfo.getStatus());
        assertEquals(BoxCollaboration.Role.EDITOR, pendingCollabInfo.getRole());
    }

    @Test
    public void testGetPendingCollaborationInfoWithFieldsSucceeds() {
        final String collaborationURL = "/2.0/collaborations?status=pending&fields=id%2Crole%2Cstatus";

        String result = TestUtils.getFixture("BoxCollaboration/GetPendingCollaborationInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlEqualTo(collaborationURL))
            .withQueryParam("status", WireMock.containing("pending"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        Collection<BoxCollaboration.Info> pendingCollaborations =
            BoxCollaboration.getPendingCollaborations(this.api, "id", "role", "status");
        BoxCollaboration.Info pendingCollabInfo = pendingCollaborations.iterator().next();

        assertEquals(BoxCollaboration.Status.PENDING, pendingCollabInfo.getStatus());
        assertEquals(BoxCollaboration.Role.EDITOR, pendingCollabInfo.getRole());
    }

    @Test
    public void testGetCollaborationsOnFolderSucceeds() {
        final String folderID = "12345";
        final String folderName = "Ball Valve Diagram";
        final String getFolderCollaborationURL = "/2.0/folders/" + folderID + "/collaborations";

        String result = TestUtils.getFixture("BoxCollaboration/GetCollaborationOnFolder200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getFolderCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();
        BoxCollaboration.Info firstCollabInfo = collaborations.iterator().next();

        assertEquals(2, collaborations.size());
        assertEquals(BoxCollaboration.Status.ACCEPTED, firstCollabInfo.getStatus());
        assertEquals(BoxCollaboration.Role.EDITOR, firstCollabInfo.getRole());
        assertEquals(folderName, firstCollabInfo.getItem().getName());
        assertEquals(BoxFolder.TYPE, firstCollabInfo.getItem().getType());
        assertEquals(BoxFolder.Info.class, firstCollabInfo.getItem().getClass());
    }

    @Test
    public void testDeletedCollaborationSucceeds() {
        final String collaborationID = "12345";
        final String deleteCollaborationURL = "/2.0/collaborations/" + collaborationID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxCollaboration collaboration = new BoxCollaboration(this.api, collaborationID);
        collaboration.delete();
    }

    @Test
    public void testCreateAndEditCollaborationSucceeds() {
        final String collabID = "12345";
        final String itemName = "Ball Valve Diagram";
        final String createCollaborationURL = "/2.0/collaborations";
        final String editCollaborationURL = "/2.0/collaborations/" + collabID;

        String result = TestUtils.getFixture("BoxCollaboration/CreateCollaboration201");

        String editResult = TestUtils.getFixture("BoxCollaboration/UpdateCollaboration200");

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
            .add("expires_at", BoxDateFormat.format(expiresAt))
            .add("is_access_only", false);

        JsonObject updateBody = new JsonObject()
            .add("role", BoxCollaboration.Role.VIEWER.toJSONString())
            .add("expires_at", BoxDateFormat.format(expiresAt));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(createCollaborationURL))
            .withRequestBody(WireMock.equalToJson(createBody.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(editCollaborationURL))
            .withRequestBody(WireMock.equalToJson(updateBody.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(editResult)));

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(editCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(editResult)));

        BoxCollaboration.Info collabInfo = BoxCollaboration.create(this.api, user, folder, BoxCollaboration.Role.EDITOR,
            false, false, expiresAt, false);

        assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
        assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        assertFalse(collabInfo.getCanViewPath());
        assertEquals(collabID, collabInfo.getID());
        assertEquals(itemName, collabInfo.getItem().getName());
        assertEquals(expiresAt, collabInfo.getExpiresAt());
        assertFalse(collabInfo.getIsAccessOnly());


        BoxCollaboration collaboration = new BoxCollaboration(this.api, collabID);
        collabInfo.setRole(BoxCollaboration.Role.VIEWER);
        collabInfo.setExpiresAt(expiresAt);
        collaboration.updateInfo(collabInfo);
        BoxCollaboration.Info updatedCollabInfo = new BoxCollaboration(this.api, collabID).getInfo();

        assertEquals(BoxCollaboration.Role.VIEWER, updatedCollabInfo.getRole());
        assertEquals(expiresAt, collabInfo.getExpiresAt());
    }

    @Test
    public void testGetCollaborationInfoSucceeds() {
        final String collabID = "12345";
        final String collabItemID = "2222";
        final String collabItemName = "Ball Valve Diagram";
        final String createdByEmail = "testuser@example.com";
        final String getCollaborationURL = "/2.0/collaborations/" + collabID;

        String result = TestUtils.getFixture("BoxCollaboration/GetCollaborationInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxCollaboration.Info collabInfo = new BoxCollaboration(this.api, collabID).getInfo();

        assertEquals(BoxCollaboration.Status.ACCEPTED, collabInfo.getStatus());
        assertEquals(BoxCollaboration.Role.EDITOR, collabInfo.getRole());
        assertFalse(collabInfo.getCanViewPath());
        assertEquals(collabID, collabInfo.getID());
        assertEquals(createdByEmail, collabInfo.getCreatedBy().getLogin());
        assertEquals(collabItemID, collabInfo.getItem().getID());
        assertEquals(collabItemName, collabInfo.getItem().getName());
        assertFalse(collabInfo.getIsAccessOnly());
    }

    @Test
    public void testCanViewPathSendsCorrectJson() {
        final String collabID = "12345";
        final boolean canViewPathOn = true;
        final String collaborationURL = "/2.0/collaborations/" + collabID;

        String result = TestUtils.getFixture("BoxCollaboration/UpdateCollaboration200");

        JsonObject jsonObject = new JsonObject()
            .add("can_view_path", true)
            .add("role", "editor");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(collaborationURL))
            .withRequestBody(WireMock.equalToJson(jsonObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxCollaboration collaboration = new BoxCollaboration(this.api, collabID);
        BoxCollaboration.Info info = collaboration.new Info();
        info.setRole(BoxCollaboration.Role.EDITOR);
        info.setCanViewPath(canViewPathOn);
        collaboration.updateInfo(info);
    }

    @Test
    public void testGetAccessibleLoginSucceeds() {
        final String collabID = "12345";
        final String accessiblyByLogin = "example@test.com";
        final String getCollaborationURL = "/2.0/collaborations/" + collabID;

        String result = TestUtils.getFixture("BoxCollaboration/GetCollaborationInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxCollaboration.Info collabInfo = new BoxCollaboration(this.api, collabID).getInfo();

        assertEquals(accessiblyByLogin, collabInfo.getAccessibleBy().getLogin());
    }

    @Test
    public void testGetInviteEmailSucceeds() {
        final String collabID = "12345";
        final String inviteEmail = "example@test.com";
        final String getCollaborationURL = "/2.0/collaborations/" + collabID;

        String result = TestUtils.getFixture("BoxCollaboration/GetInviteEmailAttributesOnCollaboration200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxCollaboration.Info collabInfo = new BoxCollaboration(this.api, collabID).getInfo("invite_email");

        assertEquals(inviteEmail, collabInfo.getInviteEmail());
    }
}
