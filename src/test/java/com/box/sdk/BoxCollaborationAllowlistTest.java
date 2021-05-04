package com.box.sdk;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public class BoxCollaborationAllowlistTest {
    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @RunWith(Parameterized.class)
    public static class EnumValueChecker {
        private String inputString;
        private BoxCollaborationAllowlist.AllowlistDirection expectedResult;

        public EnumValueChecker(String inputString, BoxCollaborationAllowlist.AllowlistDirection expectedResult) {
            this.inputString = inputString;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static List<Object[]> enumValues() {
            return Arrays.asList(new Object[][] {
                    {"inbound", BoxCollaborationAllowlist.AllowlistDirection.INBOUND},
                    {"outbound", BoxCollaborationAllowlist.AllowlistDirection.OUTBOUND},
                    {"both", BoxCollaborationAllowlist.AllowlistDirection.BOTH}
            });
        }

        @Test
        public void whitelistDirectionTest() {
            assertEquals(this.expectedResult,
                    BoxCollaborationAllowlist.AllowlistDirection.fromDirection(this.inputString));
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void createCollaborationAllowlistSucceeds() {
        final String type = "collaboration_whitelist_entry";
        final String domainName = "test14.com";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        BoxCollaborationAllowlist.Info domainAllowlist =
                BoxCollaborationAllowlist.create(api, domainName,
                        BoxCollaborationAllowlist.AllowlistDirection.BOTH);

        assertThat(domainAllowlist, is(notNullValue()));
        assertEquals(domainAllowlist.getDirection(), BoxCollaborationAllowlist.AllowlistDirection.BOTH);
        assertEquals(domainAllowlist.getType(),  type);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationAllowlistsSucceeds() {
        final String whitelistType = "collaboration_whitelist_entry";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        Iterable<BoxCollaborationAllowlist.Info> whitelists = BoxCollaborationAllowlist.getAll(api);
        List<BoxCollaborationAllowlist.Info> whitelistList = Lists.newArrayList(whitelists);

        for (BoxCollaborationAllowlist.Info whitelistInfo : whitelistList) {
            assertThat(whitelistInfo, is(notNullValue()));
            assertEquals(whitelistInfo.getType(), whitelistType);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationAllowlistsAdditionalParamsSucceeds() {
        final int whitelistSize = 3;

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        Iterator<BoxCollaborationAllowlist.Info> iterator =
                BoxCollaborationAllowlist.getAll(api, whitelistSize).iterator();
        iterator.hasNext();
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteAllowlistForDomainSucceeds() {
        final String whitelistID = "12345";
        final String deleteAllowlistURL = "/collaboration_whitelist_entries/" + whitelistID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteAllowlistURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        new BoxCollaborationAllowlist(this.api, whitelistID).delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllowlistInfoForAllDomainsSucceeds() throws IOException {
        String result = "";
        final String whitelistURL = "/collaboration_whitelist_entries";
        final String firstAllowlistID = "1111";
        final String firstAllowlistDomain = "test.com";
        final String firstAllowlistDirection = "both";

        result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForAllDomains200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxCollaborationAllowlist.Info> iterator = BoxCollaborationAllowlist.getAll(this.api).iterator();
        BoxCollaborationAllowlist.Info firstAllowlistInfo = iterator.next();

        Assert.assertEquals(firstAllowlistID, firstAllowlistInfo.getID());
        Assert.assertEquals(firstAllowlistDomain, firstAllowlistInfo.getDomain());
        Assert.assertEquals(firstAllowlistDirection, firstAllowlistInfo.getDirection().toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllowlistInfoForADomainSucceeds() throws IOException {
        String result = "";
        final String whitelistID = "12345";
        final String getAllowlistInfoURL = "/collaboration_whitelist_entries/" + whitelistID;
        final String whitelistDomain = "example.com";
        final String whitelistDirection = "both";

        result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForADomain200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllowlistInfoURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationAllowlist.Info whitelistInfo = new BoxCollaborationAllowlist(this.api, whitelistID).getInfo();

        Assert.assertEquals(whitelistID, whitelistInfo.getID());
        Assert.assertEquals(whitelistDomain, whitelistInfo.getDomain());
        Assert.assertEquals(whitelistDirection, whitelistInfo.getDirection().toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateAllowlistForDomainSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String whitelistURL = "/collaboration_whitelist_entries";
        final String domainToAllowlist = "example.com";
        final String whitelistDirection = "both";
        final String whitelistType = "collaboration_whitelist_entry";
        final String whitelistID = "12345";
        final String whitelistedEnterpriseName = "Example";

        JsonObject whitelistObject = new JsonObject()
                .add("domain", domainToAllowlist)
                .add("direction", whitelistDirection);

        result = TestConfig.getFixture("BoxCollaborationAllowlist/CreateAllowlistForDomain201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(whitelistURL))
                .withRequestBody(WireMock.equalToJson(whitelistObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationAllowlist.Info collabAllowlistInfo = BoxCollaborationAllowlist.create(this.api,
                domainToAllowlist, BoxCollaborationAllowlist.AllowlistDirection.BOTH);

        Assert.assertEquals(whitelistType, collabAllowlistInfo.getType());
        Assert.assertEquals(whitelistID, collabAllowlistInfo.getID());
        Assert.assertEquals(whitelistDirection, collabAllowlistInfo.getDirection().toString());
        Assert.assertEquals(domainToAllowlist, collabAllowlistInfo.getDomain());
        Assert.assertEquals(whitelistedEnterpriseName, collabAllowlistInfo.getEnterprise().getName());
    }
}
