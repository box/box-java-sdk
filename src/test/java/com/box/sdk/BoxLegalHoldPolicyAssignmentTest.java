package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxLegalHoldAssignment} related unit tests.
 */
public class BoxLegalHoldPolicyAssignmentTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testGetLegalHoldsPolicyAssignmentSucceeds() {
        final String assignmentID = "12345";
        final String assignmentURL = "/2.0/legal_hold_policy_assignments/" + assignmentID;
        final String policyID = "11111";
        final String policyName = "Trial Documents";
        final String assignedByID = "33333";
        final String assignedByLogin = "testuser@example.com";

        String result = TestUtils.getFixture("BoxLegalHold/GetLegalHoldPolicyAssignmentsID200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(this.api, assignmentID);
        BoxLegalHoldAssignment.Info info = assignment.getInfo();

        assertEquals(assignmentID, info.getID());
        assertEquals(policyID, info.getLegalHold().getID());
        assertEquals(policyName, info.getLegalHold().getPolicyName());
        assertEquals(assignedByID, info.getAssignedBy().getID());
        assertEquals(assignedByLogin, info.getAssignedBy().getLogin());
    }

    @Test
    public void testGetAllLegalHoldsPolicyAssignmentsSucceeds() {
        final String policyID = "11111";
        final String assignmentID = "12345";
        final String assignmentURL = "/2.0/legal_hold_policies/" + policyID + "/assignments";

        String result = TestUtils.getFixture("BoxLegalHold/GetLegalHoldPolicyAssignmentsPolicyID200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
            .withQueryParam("limit", WireMock.containing("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, policyID);
        Iterator<BoxLegalHoldAssignment.Info> assignments = policy.getAssignments().iterator();
        BoxLegalHoldAssignment.Info assignmentInfo = assignments.next();

        assertEquals(assignmentID, assignmentInfo.getID());
    }

    @Test
    public void testCreateLegalHoldsPolicyAssignmentSucceedsAndSendsCorrectJson() {
        final String policyID = "11111";
        final String folderID = "55555";
        final String assignmentID = "12345";
        final String assignedByName = "Test User";
        final String assignedByLogin = "testuser@example.com";
        final String assignmentURL = "/2.0/legal_hold_policy_assignments";
        JsonObject innerObject = new JsonObject()
            .add("id", folderID)
            .add("type", "folder");

        JsonObject assignmentObject = new JsonObject()
            .add("policy_id", policyID)
            .add("assign_to", innerObject);

        String result = TestUtils.getFixture("BoxLegalHold/PostLegalHoldPolicyAssignments201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(assignmentURL))
            .withRequestBody(WireMock.equalToJson(assignmentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, policyID);
        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxLegalHoldAssignment.Info assignInfo = policy.assignTo(folder);

        assertEquals(assignmentID, assignInfo.getID());
        assertEquals(policyID, assignInfo.getLegalHold().getID());
        assertEquals(folderID, assignInfo.getAssignedToID());
        assertEquals(assignedByLogin, assignInfo.getAssignedBy().getLogin());
        assertEquals(assignedByName, assignInfo.getAssignedBy().getName());
    }

    @Test
    public void testDeleteLegalHoldsPolicyAssignmentSucceeds() {
        final String assignmentID = "12345";
        final String assignmentURL = "/2.0/legal_hold_policy_assignments/" + assignmentID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(this.api, assignmentID);
        assignment.delete();
    }

    @Test
    public void testGetFileVersionLegalHoldSucceeds() {
        final String legalHoldID = "99999";
        final String versionID = "77777";
        final String fileID = "88888";
        final String versionURL = "/2.0/file_version_legal_holds/" + legalHoldID;

        String result = TestUtils.getFixture("BoxLegalHold/GetFileVersionLegalHoldsID200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFileVersionLegalHold hold = new BoxFileVersionLegalHold(this.api, legalHoldID);
        BoxFileVersionLegalHold.Info legalHold = hold.getInfo();

        assertEquals(legalHoldID, legalHold.getID());
        assertEquals(versionID, legalHold.getFileVersion().getID());
        assertEquals(fileID, legalHold.getFile().getID());
    }

    @Test
    public void testGetAllFileVersionLegalHoldsSucceeds() {
        final String policyID = "99999";
        final String versionURL = "/2.0/file_version_legal_holds";

        String result = TestUtils.getFixture("BoxLegalHold/GetFileVersionLegalHolds200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionURL))
            .withQueryParam("policy_id", WireMock.containing(policyID))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, policyID);
        Iterator<BoxFileVersionLegalHold.Info> fileVersionHolds = policy.getFileVersionHolds().iterator();
        BoxFileVersionLegalHold.Info versionEntry = fileVersionHolds.next();

        assertEquals(policyID, versionEntry.getID());
    }

}
