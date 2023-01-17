package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoxCollaborationAllowlistExemptTargetTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void testCreateAllowlistForAUserSucceedsAndSendsCorrectJson() {
        final String allowlistURL = "/2.0/collaboration_whitelist_exempt_targets";
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

        String result = TestUtils.getFixture("BoxCollaborationAllowlist/CreateAllowlistForAUser201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(allowlistURL))
            .withRequestBody(WireMock.equalToJson(userOuterObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetAllowlistInfoForAUser() {
        final String allowlistID = "12345";
        final String allowlistURL = "/2.0/collaboration_whitelist_exempt_targets/" + allowlistID;
        final String allowlistedUserID = "1111";
        final String allowlistedUserLogin = "test@user.com";
        final String enterpriseID = "2222";
        final String enterpriseName = "Example";

        String result = TestUtils.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForAUser200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(allowlistURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetAllowlistInfoForAllUsers() {
        final String allowlistExemptUserURL = "/2.0/collaboration_whitelist_exempt_targets";
        final String firstAllowlistType = "collaboration_whitelist_exempt_target";
        final String firstAllowlistID = "1234";

        String result = TestUtils.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForAllUsers200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(allowlistExemptUserURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
        final String deleteAllowlistURL = "/2.0/collaboration_whitelist_exempt_targets/" + allowlistID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteAllowlistURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        new BoxCollaborationAllowlistExemptTarget(this.api, allowlistID).delete();
    }
}
