package com.box.sdk;

import java.io.IOException;
import java.util.Iterator;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxLegalHoldAssignment} related unit tests.
 */
public class BoxLegalHoldPolicyAssignmentTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testGetLegalHoldsPolicyAssignmentSucceeds() throws IOException {
        String result = "";
        final String assignmentID = "12345";
        final String assignmentURL = "/legal_hold_policy_assignments/" + assignmentID;
        final String policyID = "11111";
        final String policyName = "Trial Documents";
        final String asignedToID = "55555";
        final String assignedByID = "33333";
        final String assignedByLogin = "testuser@example.com";

        result = TestConfig.getFixture("BoxLegalHold/GetLegalHoldPolicyAssignmentsID200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(this.api, assignmentID);
        BoxLegalHoldAssignment.Info info = assignment.getInfo();

        Assert.assertEquals(assignmentID, info.getID());
        Assert.assertEquals(policyID, info.getLegalHold().getID());
        Assert.assertEquals(policyName, info.getLegalHold().getPolicyName());
        Assert.assertEquals(assignedByID, info.getAssignedBy().getID());
        Assert.assertEquals(assignedByLogin, info.getAssignedBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllLegalHoldsPolicyAssignmentsSucceeds() throws IOException {
        String result = "";
        final String policyID = "11111";
        final String assignmentID = "12345";
        final String assignmentURL = "/legal_hold_policies/" + policyID + "/assignments";

        result = TestConfig.getFixture("BoxLegalHold/GetLegalHoldPolicyAssignmentsPolicyID200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
                .withQueryParam("limit", WireMock.containing("100"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, policyID);
        Iterator<BoxLegalHoldAssignment.Info> assignments = policy.getAssignments().iterator();
        BoxLegalHoldAssignment.Info assignmentInfo = assignments.next();

        Assert.assertEquals(assignmentID, assignmentInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateLegalHoldsPolicyAssignmentSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String policyID = "11111";
        final String folderID = "55555";
        final String assignmentID = "12345";
        final String assignedByName = "Test User";
        final String assignedByLogin = "testuser@example.com";
        final String assignmentURL = "/legal_hold_policy_assignments";
        JsonObject innerObject = new JsonObject()
                .add("id", folderID)
                .add("type", "folder");

        JsonObject assignmentObject = new JsonObject()
                .add("policy_id", policyID)
                .add("assign_to", innerObject);

        result = TestConfig.getFixture("BoxLegalHold/PostLegalHoldPolicyAssignments201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(assignmentURL))
                .withRequestBody(WireMock.equalToJson(assignmentObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, policyID);
        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxLegalHoldAssignment.Info assignInfo = policy.assignTo(folder);

        Assert.assertEquals(assignmentID, assignInfo.getID());
        Assert.assertEquals(policyID, assignInfo.getLegalHold().getID());
        Assert.assertEquals(folderID, assignInfo.getAssignedToID());
        Assert.assertEquals(assignedByLogin, assignInfo.getAssignedBy().getLogin());
        Assert.assertEquals(assignedByName, assignInfo.getAssignedBy().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteLegalHoldsPolicyAssignmentSucceeds() throws IOException {
        final String assignmentID = "12345";
        final String assignmentURL = "/legal_hold_policy_assignments/" + assignmentID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(assignmentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(this.api, assignmentID);
        assignment.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetFileVersionLegalHoldSucceeds() throws IOException {
        String result = "";
        final String legalHoldID = "99999";
        final String assignmentID = "12345";
        final String versionID = "77777";
        final String fileID = "88888";
        final String versionURL = "/file_version_legal_holds/" + legalHoldID;

        result = TestConfig.getFixture("BoxLegalHold/GetFileVersionLegalHoldsID200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFileVersionLegalHold hold = new BoxFileVersionLegalHold(this.api, legalHoldID);
        BoxFileVersionLegalHold.Info legalHold = hold.getInfo();

        Assert.assertEquals(legalHoldID, legalHold.getID());
        Assert.assertEquals(versionID, legalHold.getFileVersion().getID());
        Assert.assertEquals(fileID, legalHold.getFile().getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllFileVersionLegalHoldsSucceeds() throws IOException {
        String result = "";
        final String policyID = "99999";
        final String versionURL = "/file_version_legal_holds";

        result = TestConfig.getFixture("BoxLegalHold/GetFileVersionLegalHolds200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionURL))
                .withQueryParam("policy_id", WireMock.containing(policyID))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, policyID);
        Iterator<BoxFileVersionLegalHold.Info> fileVersionHolds = policy.getFileVersionHolds().iterator();
        BoxFileVersionLegalHold.Info versionEntry = fileVersionHolds.next();

        Assert.assertEquals(policyID, versionEntry.getID());
    }

}
