package com.box.sdk;

import java.io.IOException;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxCollaborationWhitelistExemptTargetTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testCreateWhitelistForAUserSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
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

        result = TestConfig.getFixture("BoxCollaborationWhitelist/CreateWhitelistForAUser201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(whitelistURL))
                .withRequestBody(WireMock.equalToJson(userOuterObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationWhitelistExemptTarget.Info userWhitelistInfo =
                BoxCollaborationWhitelistExemptTarget.create(this.api, userToWhitelistID);

        Assert.assertEquals(whitelistType, userWhitelistInfo.getType());
        Assert.assertEquals(whitelistID, userWhitelistInfo.getID());
        Assert.assertEquals(userToWhitelistID, userWhitelistInfo.getUser().getID());
        Assert.assertEquals(userToWhitelistName, userWhitelistInfo.getUser().getName());
        Assert.assertEquals(userToWhitelistLogin, userWhitelistInfo.getUser().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetWhitelistInfoForAUser() throws IOException {
        String result = "";
        final String whitelistID = "12345";
        final String whitelistURL = "/collaboration_whitelist_exempt_targets/" + whitelistID;
        final String whitelistType = "collaboration_whitelist_exempt_target";
        final String whitelistedUserID = "1111";
        final String whitelistedUserLogin = "test@user.com";
        final String enterpriseID = "2222";
        final String enterpriseName = "Example";

        result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForAUser200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationWhitelistExemptTarget.Info userWhitelistInfo = new
                BoxCollaborationWhitelistExemptTarget(this.api, whitelistID).getInfo();

        Assert.assertEquals(whitelistID, userWhitelistInfo.getID());
        Assert.assertEquals(whitelistedUserID, userWhitelistInfo.getUser().getID());
        Assert.assertEquals(whitelistedUserLogin, userWhitelistInfo.getUser().getLogin());
        Assert.assertEquals(enterpriseID, userWhitelistInfo.getEnterprise().getID());
        Assert.assertEquals(enterpriseName, userWhitelistInfo.getEnterprise().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetWhitelistInfoForAllUsers() throws IOException {
        String result = "";
        final String whitelistExemptUserURL = "/collaboration_whitelist_exempt_targets";
        final String firstWhitelistType = "collaboration_whitelist_exempt_target";
        final String firstWhitelistID = "1234";

        result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForAllUsers200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistExemptUserURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxCollaborationWhitelistExemptTarget.Info> whitelistInfo =
                BoxCollaborationWhitelistExemptTarget.getAll(this.api).iterator();

        BoxCollaborationWhitelistExemptTarget.Info firstWhitelistInfo = whitelistInfo.next();

        Assert.assertEquals(firstWhitelistType, firstWhitelistInfo.getType());
        Assert.assertEquals(firstWhitelistID, firstWhitelistInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
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
