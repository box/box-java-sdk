package com.box.sdk;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sun.tools.jconsole.Plotter;

public class BoxCollaborationWhitelistTest {
    /**
     * Wiremock
     */
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @RunWith(Parameterized.class)
    public static class EnumValueChecker {
        private String inputString;
        private BoxCollaborationWhitelist.WhitelistDirection expectedResult;

        public EnumValueChecker(String inputString, BoxCollaborationWhitelist.WhitelistDirection expectedResult) {
            this.inputString = inputString;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static List<Object[]> enumValues() {
            return Arrays.asList(new Object[][] {
                    {"inbound", BoxCollaborationWhitelist.WhitelistDirection.INBOUND},
                    {"outbound", BoxCollaborationWhitelist.WhitelistDirection.OUTBOUND},
                    {"both", BoxCollaborationWhitelist.WhitelistDirection.BOTH}
            });
        }

        @Test
        public void whitelistDirectionTest() {
            assertEquals(this.expectedResult,
                    BoxCollaborationWhitelist.WhitelistDirection.fromDirection(this.inputString));
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllWhitelistingForUserWithParamsParseAllFieldsCorrectly() {
        final String firstEntryType = "collaboration_whitelist_entry";
        final String firstEntryID = "558459";
        final int collaborationWhitelistLimit = 4;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"entries\": ["
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"558459\",\n"
                + "            \"domain\": \"test1.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"567889\",\n"
                + "            \"domain\": \"test2.com\",\n"
                + "            \"direction\": \"inbound\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"554354\",\n"
                + "            \"domain\": \"test3.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        },"
                + "        {"
                + "            \"type\": \"collaboration_whitelist_entry\",\n"
                + "            \"id\": \"543244\",\n"
                + "            \"domain\": \"test4.com\",\n"
                + "            \"direction\": \"both\"\n"
                + "        }"
                + "    ],"
                + "    \"next_marker\": \"eyJ0eXBlIjoiaWQiLCJkaXIiOiJuZXh0IiwidGFpbCI6IjU1ODIyMyJ9\","
                + "    \"limit\": \"4\""
                + "}");

        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxCollaborationWhitelist.Info> whitelists =
                BoxCollaborationWhitelist.getAll(api, collaborationWhitelistLimit).iterator();

        BoxCollaborationWhitelist.Info entry = whitelists.next();
        Assert.assertEquals(firstEntryType, entry.getType());
        Assert.assertEquals(firstEntryID, entry.getID());
    }

    @Test
    @Category(IntegrationTest.class)
    public void createCollaborationWhitelistSucceeds() {
        final String type = "collaboration_whitelist_entry";
        final String domainName = "test14.com";

        BoxCollaborationWhitelist.Info domainWhitelist =
                BoxCollaborationWhitelist.create(api, domainName,
                        BoxCollaborationWhitelist.WhitelistDirection.BOTH);

        assertThat(domainWhitelist, is(notNullValue()));
        assertEquals(domainWhitelist.getDirection(), BoxCollaborationWhitelist.WhitelistDirection.BOTH);
        assertEquals(domainWhitelist.getType(),  type);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCollaborationWhitelistInfoSucceeds() {
        final String whitelistID = "34290";

        BoxCollaborationWhitelist collaborationWhitelist = new BoxCollaborationWhitelist(api, whitelistID);
        BoxCollaborationWhitelist.Info whitelistInfo = collaborationWhitelist.getInfo();

        assertThat(whitelistInfo, is(notNullValue()));
        assertEquals(whitelistInfo.getID(), whitelistID);
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteCollaborationWhitelistSucceeds() {
        final String whitelistID = "34290";

        BoxCollaborationWhitelist collaborationWhitelist = new BoxCollaborationWhitelist(api, whitelistID);
        collaborationWhitelist.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistsSucceeds() {
        final String whitelistType = "collaboration_whitelist_entry";

        Iterable<BoxCollaborationWhitelist.Info> whitelists = BoxCollaborationWhitelist.getAll(api);
        List<BoxCollaborationWhitelist.Info> whitelistList = Lists.newArrayList(whitelists);

        for (BoxCollaborationWhitelist.Info whitelistInfo : whitelistList) {
            assertThat(whitelistInfo, is(notNullValue()));
            assertEquals(whitelistInfo.getType(), whitelistType);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistsAdditionalParamsSucceeds() {
        final int whitelistSize = 3;

        Iterator<BoxCollaborationWhitelist.Info> iterator =
                BoxCollaborationWhitelist.getAll(api, whitelistSize).iterator();
        iterator.hasNext();
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

    @Test
    @Category(UnitTest.class)
    public void testGetWhitelistInfoForAllUsers() {
        String result = "";
        final String whitelistExemptUserURL = "/collaboration_whitelist_exempt_targets";
        final String firstWhitelistType = "collaboration_whitelist_exempt_target";
        final String firstWhitelistID = "1234";

        try {
            result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForAllUsers200");
        } catch (IOException e){
            System.out.println("Error Getting Fixture:" + e);
        }

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
    public void testGetWhitelistInfoForAUser() {
        String result = "";
        final String whitelistID = "12345";
        final String whitelistURL = "/collaboration_whitelist_exempt_targets/" + whitelistID;
        final String whitelistType = "collaboration_whitelist_exempt_target";
        final String whitelistedUserID = "1111";
        final String whitelistedUserLogin = "test@user.com";
        final String enterpriseID = "2222";
        final String enterpriseName = "Example";

        try {
            result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForAUser200");
        } catch (IOException e){
            System.out.println("Error Getting Fixture:" + e);
        }

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
    public void testCreateWhitelistForAUserSucceedsAndSendsCorrectJson() {
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

        try {
            result = TestConfig.getFixture("BoxCollaborationWhitelist/CreateWhitelistForAUser200");
        } catch (IOException e){
            System.out.println("Error Getting Fixture:" + e);
        }

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
    public void testDeleteWhitelistForDomainSucceeds() {
        final String whitelistID = "12345";
        final String deleteWhitelistURL = "/collaboration_whitelist_entries/" + whitelistID;

        this.wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteWhitelistURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        new BoxCollaborationWhitelist(api, whitelistID).delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetWhitelistInfoForAllDomainsSucceeds() {
        String result = "";
        final String whitelistURL = "/collaboration_whitelist_entries";
        final String firstWhitelistID = "1111";
        final String firstWhitelistDomain = "test.com";
        final String firstWhitelistDirection = "both";

        try {
            result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForAllDomains200");
        } catch (IOException e){
            System.out.println("Error Getting Fixture:" + e);
        }

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxCollaborationWhitelist.Info> iterator = BoxCollaborationWhitelist.getAll(api).iterator();
        BoxCollaborationWhitelist.Info firstWhitelistInfo = iterator.next();

        Assert.assertEquals(firstWhitelistID, firstWhitelistInfo.getID());
        Assert.assertEquals(firstWhitelistDomain, firstWhitelistInfo.getDomain());
        Assert.assertEquals(firstWhitelistDirection, firstWhitelistInfo.getDirection().toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetWhitelistInfoForADomainSucceeds() {
        String result = "";
        final String whitelistID = "12345";
        final String getWhitelistInfoURL = "/collaboration_whitelist_entries/" + whitelistID;
        final String whitelistDomain = "example.com";
        final String whitelistDirection = "both";

        try {
            result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForADomain200");
        } catch (IOException e){
            System.out.println("Error Getting Fixture:" + e);
        }

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getWhitelistInfoURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationWhitelist.Info whitelistInfo = new BoxCollaborationWhitelist(api, whitelistID).getInfo();

        Assert.assertEquals(whitelistID, whitelistInfo.getID());
        Assert.assertEquals(whitelistDomain, whitelistInfo.getDomain());
        Assert.assertEquals(whitelistDirection, whitelistInfo.getDirection().toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateWhitelistForDomainSucceedsAndSendsCorrectJson() {
        String result = "";
        final String whitelistURL = "/collaboration_whitelist_entries";
        final String domainToWhitelist = "example.com";
        final String whitelistDirection = "both";
        final String whitelistType = "collaboration_whitelist_entry";
        final String whitelistID = "12345";
        final String whitelistedEnterpriseName = "Example";

        JsonObject whitelistObject = new JsonObject()
                .add("domain", domainToWhitelist)
                .add("direction", whitelistDirection);

        try {
            result = TestConfig.getFixture("BoxCollaborationWhitelist/CreateWhitelistForDomain200");
        } catch (IOException e){
            System.out.println("Error Getting Fixture:" + e);
        }

        this.wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(whitelistURL))
                .withRequestBody(WireMock.equalToJson(whitelistObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationWhitelist.Info collabWhitelistInfo = BoxCollaborationWhitelist.create(api, domainToWhitelist, BoxCollaborationWhitelist.WhitelistDirection.BOTH);

        Assert.assertEquals(whitelistType, collabWhitelistInfo.getType());
        Assert.assertEquals(whitelistID, collabWhitelistInfo.getID());
        Assert.assertEquals(whitelistDirection, collabWhitelistInfo.getDirection().toString());
        Assert.assertEquals(domainToWhitelist, collabWhitelistInfo.getDomain());
        Assert.assertEquals(whitelistedEnterpriseName, collabWhitelistInfo.getEnterprise().getName());
    }
}
