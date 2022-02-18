package com.box.sdk;

import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public class BoxCollaborationAllowlistTest {
    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
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
    public void testGetAllowlistInfoForAllDomainsSucceeds() throws IOException {
        final String whitelistURL = "/collaboration_whitelist_entries";
        final String firstAllowlistID = "1111";
        final String firstAllowlistDomain = "test.com";
        final String firstAllowlistDirection = "both";

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForAllDomains200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxCollaborationAllowlist.Info> iterator = BoxCollaborationAllowlist.getAll(this.api).iterator();
        BoxCollaborationAllowlist.Info firstAllowlistInfo = iterator.next();

        assertEquals(firstAllowlistID, firstAllowlistInfo.getID());
        assertEquals(firstAllowlistDomain, firstAllowlistInfo.getDomain());
        assertEquals(firstAllowlistDirection, firstAllowlistInfo.getDirection().toString());
    }

    @Test
    public void testGetAllowlistInfoForADomainSucceeds() throws IOException {
        final String whitelistID = "12345";
        final String getAllowlistInfoURL = "/collaboration_whitelist_entries/" + whitelistID;
        final String whitelistDomain = "example.com";
        final String whitelistDirection = "both";

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForADomain200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllowlistInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxCollaborationAllowlist.Info whitelistInfo = new BoxCollaborationAllowlist(this.api, whitelistID).getInfo();

        assertEquals(whitelistID, whitelistInfo.getID());
        assertEquals(whitelistDomain, whitelistInfo.getDomain());
        assertEquals(whitelistDirection, whitelistInfo.getDirection().toString());
    }

    @Test
    public void testCreateAllowlistForDomainSucceedsAndSendsCorrectJson() throws IOException {
        final String whitelistURL = "/collaboration_whitelist_entries";
        final String domainToAllowlist = "example.com";
        final String whitelistDirection = "both";
        final String whitelistType = "collaboration_whitelist_entry";
        final String whitelistID = "12345";
        final String whitelistedEnterpriseName = "Example";

        JsonObject whitelistObject = new JsonObject()
            .add("domain", domainToAllowlist)
            .add("direction", whitelistDirection);

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/CreateAllowlistForDomain201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(whitelistURL))
            .withRequestBody(WireMock.equalToJson(whitelistObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxCollaborationAllowlist.Info collabAllowlistInfo = BoxCollaborationAllowlist.create(this.api,
            domainToAllowlist, BoxCollaborationAllowlist.AllowlistDirection.BOTH);

        assertEquals(whitelistType, collabAllowlistInfo.getType());
        assertEquals(whitelistID, collabAllowlistInfo.getID());
        assertEquals(whitelistDirection, collabAllowlistInfo.getDirection().toString());
        assertEquals(domainToAllowlist, collabAllowlistInfo.getDomain());
        assertEquals(whitelistedEnterpriseName, collabAllowlistInfo.getEnterprise().getName());
    }

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
            return Arrays.asList(new Object[][]{
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
}