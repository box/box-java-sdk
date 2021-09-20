package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class BoxCollaborationAllowlistExemptTargetTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testCreateAllowlistForAUserSucceedsAndSendsCorrectJson() throws IOException {
        final String allowlistURL = "/collaboration_whitelist_exempt_targets";
        final String userToAllowlistID = "1111";
        final String userToAllowlistLogin = "test@user.com";
        final String userToAllowlistName = "Test User";
        final String allowlistType = "collaboration_whitelist_exempt_target";
        final String allowlistID = "12345";

        JsonObject userInnerObject = new JsonObject()
            .add("id", userToAllowlistID)
            .add("type", "user");

        JsonObject userOuterObject = new JsonObject()
            .add("user", userInnerObject);

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/CreateAllowlistForAUser201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(allowlistURL))
            .withRequestBody(WireMock.equalToJson(userOuterObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxCollaborationAllowlistExemptTarget.Info userAllowlistInfo =
            BoxCollaborationAllowlistExemptTarget.create(this.api, userToAllowlistID);

        Assert.assertEquals(allowlistType, userAllowlistInfo.getType());
        Assert.assertEquals(allowlistID, userAllowlistInfo.getID());
        Assert.assertEquals(userToAllowlistID, userAllowlistInfo.getUser().getID());
        Assert.assertEquals(userToAllowlistName, userAllowlistInfo.getUser().getName());
        Assert.assertEquals(userToAllowlistLogin, userAllowlistInfo.getUser().getLogin());
    }

    @Test
    public void testGetAllowlistInfoForAUser() throws IOException {
        final String allowlistID = "12345";
        final String allowlistURL = "/collaboration_whitelist_exempt_targets/" + allowlistID;
        final String allowlistedUserID = "1111";
        final String allowlistedUserLogin = "test@user.com";
        final String enterpriseID = "2222";
        final String enterpriseName = "Example";

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForAUser200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(allowlistURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxCollaborationAllowlistExemptTarget.Info userAllowlistInfo = new
            BoxCollaborationAllowlistExemptTarget(this.api, allowlistID).getInfo();

        Assert.assertEquals(allowlistID, userAllowlistInfo.getID());
        Assert.assertEquals(allowlistedUserID, userAllowlistInfo.getUser().getID());
        Assert.assertEquals(allowlistedUserLogin, userAllowlistInfo.getUser().getLogin());
        Assert.assertEquals(enterpriseID, userAllowlistInfo.getEnterprise().getID());
        Assert.assertEquals(enterpriseName, userAllowlistInfo.getEnterprise().getName());
    }

    @Test
    public void testGetAllowlistInfoForAllUsers() throws IOException {
        final String allowlistExemptUserURL = "/collaboration_whitelist_exempt_targets";
        final String firstAllowlistType = "collaboration_whitelist_exempt_target";
        final String firstAllowlistID = "1234";

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForAllUsers200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(allowlistExemptUserURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxCollaborationAllowlistExemptTarget.Info> allowlistInfo =
            BoxCollaborationAllowlistExemptTarget.getAll(this.api).iterator();

        BoxCollaborationAllowlistExemptTarget.Info firstAllowlistInfo = allowlistInfo.next();

        Assert.assertEquals(firstAllowlistType, firstAllowlistInfo.getType());
        Assert.assertEquals(firstAllowlistID, firstAllowlistInfo.getID());
    }

    @Test
    public void testDeleteCollaborationAllowlistForUser() {
        final String allowlistID = "12345";
        final String deleteAllowlistURL = "/collaboration_whitelist_exempt_targets/" + allowlistID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteAllowlistURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        new BoxCollaborationWhitelistExemptTarget(this.api, allowlistID).delete();
    }
}
