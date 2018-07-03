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

public class BoxCollaborationWhitelistTest {
    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
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
    @Category(IntegrationTest.class)
    public void createCollaborationWhitelistSucceeds() {
        final String type = "collaboration_whitelist_entry";
        final String domainName = "test14.com";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        BoxCollaborationWhitelist.Info domainWhitelist =
                BoxCollaborationWhitelist.create(api, domainName,
                        BoxCollaborationWhitelist.WhitelistDirection.BOTH);

        assertThat(domainWhitelist, is(notNullValue()));
        assertEquals(domainWhitelist.getDirection(), BoxCollaborationWhitelist.WhitelistDirection.BOTH);
        assertEquals(domainWhitelist.getType(),  type);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollaborationWhitelistsSucceeds() {
        final String whitelistType = "collaboration_whitelist_entry";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

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

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        Iterator<BoxCollaborationWhitelist.Info> iterator =
                BoxCollaborationWhitelist.getAll(api, whitelistSize).iterator();
        iterator.hasNext();
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteWhitelistForDomainSucceeds() {
        final String whitelistID = "12345";
        final String deleteWhitelistURL = "/collaboration_whitelist_entries/" + whitelistID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteWhitelistURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        new BoxCollaborationWhitelist(this.api, whitelistID).delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetWhitelistInfoForAllDomainsSucceeds() throws IOException {
        String result = "";
        final String whitelistURL = "/collaboration_whitelist_entries";
        final String firstWhitelistID = "1111";
        final String firstWhitelistDomain = "test.com";
        final String firstWhitelistDirection = "both";

        result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForAllDomains200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxCollaborationWhitelist.Info> iterator = BoxCollaborationWhitelist.getAll(this.api).iterator();
        BoxCollaborationWhitelist.Info firstWhitelistInfo = iterator.next();

        Assert.assertEquals(firstWhitelistID, firstWhitelistInfo.getID());
        Assert.assertEquals(firstWhitelistDomain, firstWhitelistInfo.getDomain());
        Assert.assertEquals(firstWhitelistDirection, firstWhitelistInfo.getDirection().toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetWhitelistInfoForADomainSucceeds() throws IOException {
        String result = "";
        final String whitelistID = "12345";
        final String getWhitelistInfoURL = "/collaboration_whitelist_entries/" + whitelistID;
        final String whitelistDomain = "example.com";
        final String whitelistDirection = "both";

        result = TestConfig.getFixture("BoxCollaborationWhitelist/GetWhitelistInfoForADomain200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getWhitelistInfoURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationWhitelist.Info whitelistInfo = new BoxCollaborationWhitelist(this.api, whitelistID).getInfo();

        Assert.assertEquals(whitelistID, whitelistInfo.getID());
        Assert.assertEquals(whitelistDomain, whitelistInfo.getDomain());
        Assert.assertEquals(whitelistDirection, whitelistInfo.getDirection().toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateWhitelistForDomainSucceedsAndSendsCorrectJson() throws IOException {
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

        result = TestConfig.getFixture("BoxCollaborationWhitelist/CreateWhitelistForDomain201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(whitelistURL))
                .withRequestBody(WireMock.equalToJson(whitelistObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxCollaborationWhitelist.Info collabWhitelistInfo = BoxCollaborationWhitelist.create(this.api,
                domainToWhitelist, BoxCollaborationWhitelist.WhitelistDirection.BOTH);

        Assert.assertEquals(whitelistType, collabWhitelistInfo.getType());
        Assert.assertEquals(whitelistID, collabWhitelistInfo.getID());
        Assert.assertEquals(whitelistDirection, collabWhitelistInfo.getDirection().toString());
        Assert.assertEquals(domainToWhitelist, collabWhitelistInfo.getDomain());
        Assert.assertEquals(whitelistedEnterpriseName, collabWhitelistInfo.getEnterprise().getName());
    }
}
