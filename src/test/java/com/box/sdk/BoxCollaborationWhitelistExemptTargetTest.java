package com.box.sdk;

import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.ClassRule;
import org.junit.Test;

public class BoxCollaborationWhitelistExemptTargetTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testCreateWhitelistForAUserSucceedsAndSendsCorrectJson() throws IOException {
        final String whitelistURL = "/collaboration_whitelist_exempt_targets";
        final String userToWhitelistID = "1111";
        final String userToWhitelistLogin = "test@user.com";
        final String userToWhitelistName = "Test User";
        final String whitelistType = "collaboration_whitelist_exempt_target";
        final String whitelistID = "12345";

        JsonObject userInnerObject = new JsonObject()
            .add("id", userToWhitelistID)
            .add("type", "user");

        JsonObject userOuterObject = new JsonObject()
            .add("user", userInnerObject);

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/CreateAllowlistForAUser201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(whitelistURL))
            .withRequestBody(WireMock.equalToJson(userOuterObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxCollaborationWhitelistExemptTarget.Info userWhitelistInfo =
            BoxCollaborationWhitelistExemptTarget.create(this.api, userToWhitelistID);

        assertEquals(whitelistType, userWhitelistInfo.getType());
        assertEquals(whitelistID, userWhitelistInfo.getID());
        assertEquals(userToWhitelistID, userWhitelistInfo.getUser().getID());
        assertEquals(userToWhitelistName, userWhitelistInfo.getUser().getName());
        assertEquals(userToWhitelistLogin, userWhitelistInfo.getUser().getLogin());
    }

    @Test
    public void testGetWhitelistInfoForAUser() throws IOException {
        final String whitelistID = "12345";
        final String whitelistURL = "/collaboration_whitelist_exempt_targets/" + whitelistID;
        final String whitelistedUserID = "1111";
        final String whitelistedUserLogin = "test@user.com";
        final String enterpriseID = "2222";
        final String enterpriseName = "Example";

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForAUser200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxCollaborationWhitelistExemptTarget.Info userWhitelistInfo = new
            BoxCollaborationWhitelistExemptTarget(this.api, whitelistID).getInfo();

        assertEquals(whitelistID, userWhitelistInfo.getID());
        assertEquals(whitelistedUserID, userWhitelistInfo.getUser().getID());
        assertEquals(whitelistedUserLogin, userWhitelistInfo.getUser().getLogin());
        assertEquals(enterpriseID, userWhitelistInfo.getEnterprise().getID());
        assertEquals(enterpriseName, userWhitelistInfo.getEnterprise().getName());
    }

    @Test
    public void testGetWhitelistInfoForAllUsers() throws IOException {
        final String whitelistExemptUserURL = "/collaboration_whitelist_exempt_targets";
        final String firstWhitelistType = "collaboration_whitelist_exempt_target";
        final String firstWhitelistID = "1234";

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForAllUsers200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistExemptUserURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxCollaborationWhitelistExemptTarget.Info> whitelistInfo =
            BoxCollaborationWhitelistExemptTarget.getAll(this.api).iterator();

        BoxCollaborationWhitelistExemptTarget.Info firstWhitelistInfo = whitelistInfo.next();

        assertEquals(firstWhitelistType, firstWhitelistInfo.getType());
        assertEquals(firstWhitelistID, firstWhitelistInfo.getID());
    }

    @Test
    public void testDeleteCollaborationWhitelistForUser() {
        final String whitelistID = "12345";
        final String deleteWhitelistURL = "/collaboration_whitelist_exempt_targets/" + whitelistID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteWhitelistURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        new BoxCollaborationWhitelistExemptTarget(this.api, whitelistID).delete();
    }
}
