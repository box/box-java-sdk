package com.box.sdk;

import java.io.IOException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxCollaborationWhitelistExemptTargetTest {

    /**
     * Wiremock
     */
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(IntegrationTest.class)
    public void createCollaborationWhitelistForUserSucceeds() {
        final String whitelistType = "collaboration_whitelist_exempt_target";
        final String userID = "275393890";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxCollaborationWhitelistExemptTarget.Info userWhitelist =
                BoxCollaborationWhitelistExemptTarget.create(api, userID);

        assertThat(userWhitelist, is(notNullValue()));
        assertEquals(userWhitelist.getType(), whitelistType);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCollaborationWhitelistInfoForUserSucceeds() {
        final String userWhitelistID = "573619";
        final String whitelistType = "collaboration_whitelist_exempt_target";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxCollaborationWhitelistExemptTarget userCollaborationWhitelist =
                new BoxCollaborationWhitelistExemptTarget(api, userWhitelistID);
        BoxCollaborationWhitelistExemptTarget.Info userWhitelistInfo = userCollaborationWhitelist.getInfo();

        assertThat(userWhitelistInfo, is(notNullValue()));
        assertEquals(userWhitelistInfo.getType(), whitelistType);
        assertEquals(userWhitelistInfo.getID(), userWhitelistID);
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteCollaborationWhitelistForUserSucceeds() {
        final String whitelistID = "573619";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxCollaborationWhitelistExemptTarget userCollaborationWhitelist =
                new BoxCollaborationWhitelistExemptTarget(api, whitelistID);
        userCollaborationWhitelist.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistForUserSucceeds() {
        final String whitelistType = "collaboration_whitelist_exempt_target";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Iterator<BoxCollaborationWhitelistExemptTarget.Info> iterator =
                BoxCollaborationWhitelistExemptTarget.getAll(api).iterator();

        BoxCollaborationWhitelistExemptTarget.Info info = iterator.next();
        assertEquals(whitelistType, info.getType());
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistForUserWithParamsSucceeds() {
        final int whitelistLimit = 3;

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Iterator<BoxCollaborationWhitelistExemptTarget.Info> iterator =
                BoxCollaborationWhitelistExemptTarget.getAll(api, whitelistLimit).iterator();

        iterator.hasNext();
    }

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

        this.wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(whitelistURL))
                .withRequestBody(WireMock.equalToJson(userOuterObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationWhitelistExemptTarget.Info userWhitelistInfo = BoxCollaborationWhitelistExemptTarget.create(api,
                userToWhitelistID);

        Assert.assertEquals(whitelistType, userWhitelistInfo.getType());
        Assert.assertEquals(whitelistID, userWhitelistInfo.getID());
        Assert.assertEquals(userToWhitelistID, userWhitelistInfo.getUser().getID());
        Assert.assertEquals(userToWhitelistName, userWhitelistInfo.getUser().getName());
        Assert.assertEquals(userToWhitelistLogin, userWhitelistInfo.getUser().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetWhitelistInfoForAUser() throws IOException{
        String result = "";
        final String whitelistID = "12345";
        final String whitelistURL = "/collaboration_whitelist_exempt_targets/" + whitelistID;
        final String whitelistType = "collaboration_whitelist_exempt_target";
        final String whitelistedUserID = "1111";
        final String whitelistedUserLogin = "test@user.com";
        final String enterpriseID = "2222";
        final String enterpriseName = "Example";

        result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForAUser200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationWhitelistExemptTarget.Info userWhitelistInfo = new BoxCollaborationWhitelistExemptTarget(api,
                whitelistID).getInfo();

        Assert.assertEquals(whitelistID, userWhitelistInfo.getID());
        Assert.assertEquals(whitelistedUserID, userWhitelistInfo.getUser().getID());
        Assert.assertEquals(whitelistedUserLogin, userWhitelistInfo.getUser().getLogin());
        Assert.assertEquals(enterpriseID, userWhitelistInfo.getEnterprise().getID());
        Assert.assertEquals(enterpriseName, userWhitelistInfo.getEnterprise().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetWhitelistInfoForAllUsers() throws IOException{
        String result = "";
        final String whitelistExemptUserURL = "/collaboration_whitelist_exempt_targets";
        final String firstWhitelistType = "collaboration_whitelist_exempt_target";
        final String firstWhitelistID = "1234";

        result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForAllUsers200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistExemptUserURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxCollaborationWhitelistExemptTarget.Info> whitelistInfo =
                BoxCollaborationWhitelistExemptTarget.getAll(api).iterator();

        BoxCollaborationWhitelistExemptTarget.Info firstWhitelistInfo = whitelistInfo.next();

        Assert.assertEquals(firstWhitelistType, firstWhitelistInfo.getType());
        Assert.assertEquals(firstWhitelistID, firstWhitelistInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteCollaborationWhitelistForUser() {
        final String whitelistID = "12345";
        final String deleteWhitelistURL = "/collaboration_whitelist_exempt_targets/" + whitelistID;

        this.wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteWhitelistURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        new BoxCollaborationWhitelistExemptTarget(api, whitelistID).delete();
    }
}
