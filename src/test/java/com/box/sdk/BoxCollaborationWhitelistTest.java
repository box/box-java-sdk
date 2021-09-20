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

/**
 *
 */
public class BoxCollaborationWhitelistTest {
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
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
    public void testGetWhitelistInfoForAllDomainsSucceeds() throws IOException {
        final String whitelistURL = "/collaboration_whitelist_entries";
        final String firstWhitelistID = "1111";
        final String firstWhitelistDomain = "test.com";
        final String firstWhitelistDirection = "both";

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForAllDomains200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(whitelistURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxCollaborationWhitelist.Info> iterator = BoxCollaborationWhitelist.getAll(this.api).iterator();
        BoxCollaborationWhitelist.Info firstWhitelistInfo = iterator.next();

        assertEquals(firstWhitelistID, firstWhitelistInfo.getID());
        assertEquals(firstWhitelistDomain, firstWhitelistInfo.getDomain());
        assertEquals(firstWhitelistDirection, firstWhitelistInfo.getDirection().toString());
    }

    @Test
    public void testGetWhitelistInfoForADomainSucceeds() throws IOException {
        final String whitelistID = "12345";
        final String getWhitelistInfoURL = "/collaboration_whitelist_entries/" + whitelistID;
        final String whitelistDomain = "example.com";
        final String whitelistDirection = "both";

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/GetAllowlistInfoForADomain200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getWhitelistInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxCollaborationWhitelist.Info whitelistInfo = new BoxCollaborationWhitelist(this.api, whitelistID).getInfo();

        assertEquals(whitelistID, whitelistInfo.getID());
        assertEquals(whitelistDomain, whitelistInfo.getDomain());
        assertEquals(whitelistDirection, whitelistInfo.getDirection().toString());
    }

    @Test
    public void testCreateWhitelistForDomainSucceedsAndSendsCorrectJson() throws IOException {
        final String whitelistURL = "/collaboration_whitelist_entries";
        final String domainToWhitelist = "example.com";
        final String whitelistDirection = "both";
        final String whitelistType = "collaboration_whitelist_entry";
        final String whitelistID = "12345";
        final String whitelistedEnterpriseName = "Example";

        JsonObject whitelistObject = new JsonObject()
            .add("domain", domainToWhitelist)
            .add("direction", whitelistDirection);

        String result = TestConfig.getFixture("BoxCollaborationAllowlist/CreateAllowlistForDomain201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(whitelistURL))
            .withRequestBody(WireMock.equalToJson(whitelistObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxCollaborationWhitelist.Info collabWhitelistInfo = BoxCollaborationWhitelist.create(this.api,
            domainToWhitelist, BoxCollaborationWhitelist.WhitelistDirection.BOTH);

        assertEquals(whitelistType, collabWhitelistInfo.getType());
        assertEquals(whitelistID, collabWhitelistInfo.getID());
        assertEquals(whitelistDirection, collabWhitelistInfo.getDirection().toString());
        assertEquals(domainToWhitelist, collabWhitelistInfo.getDomain());
        assertEquals(whitelistedEnterpriseName, collabWhitelistInfo.getEnterprise().getName());
    }

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
            return Arrays.asList(new Object[][]{
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
}
