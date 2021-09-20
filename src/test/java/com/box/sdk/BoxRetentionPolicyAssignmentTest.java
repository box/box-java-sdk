package com.box.sdk;

import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxRetentionPolicyAssignment} related unit tests.
 */
public class BoxRetentionPolicyAssignmentTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testGetPolicyAssignmentForEnterpriseSucceeds() throws IOException {
        final String assignmentID = "12345";
        final String policyID = "1111";
        final String getAssignmentsURL = "/retention_policies/" + policyID + "/assignments";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetRetentionPolicyAssignment200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAssignmentsURL))
            .withQueryParam("type", WireMock.containing("enterprise"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        Iterator<BoxRetentionPolicyAssignment.Info> enterpriseAssignments =
            policy.getEnterpriseAssignments().iterator();

        BoxRetentionPolicyAssignment.Info assignmentInfo = enterpriseAssignments.next();

        assertEquals(assignmentID, assignmentInfo.getID());
    }

    @Test
    public void testGetPolicyAssignmentForFolderSucceeds() throws IOException {
        final String assignmentID = "12345";
        final String policyID = "1111";
        final String getAssignmentsURL = "/retention_policies/" + policyID + "/assignments";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetRetentionPolicyAssignment200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAssignmentsURL))
            .withQueryParam("type", WireMock.containing("folder"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        Iterator<BoxRetentionPolicyAssignment.Info> folderAssignments =
            policy.getFolderAssignments().iterator();

        BoxRetentionPolicyAssignment.Info assignmentInfo = folderAssignments.next();

        assertEquals(assignmentID, assignmentInfo.getID());
    }

    @Test
    public void testGetAllPolicyAssignmentsSucceeds() throws IOException {
        final String policyID = "1111";
        final String getAssignmentsURL = "/retention_policies/" + policyID + "/assignments";
        final String firstAssignmentID = "12345";
        final String secondAssignmentID = "42342";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetAllRetentionPolicyAssignments200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAssignmentsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        Iterator<BoxRetentionPolicyAssignment.Info> assignments =
            policy.getEnterpriseAssignments().iterator();


        BoxRetentionPolicyAssignment.Info firstAssignmentInfo = assignments.next();

        assertEquals(firstAssignmentID, firstAssignmentInfo.getID());

        BoxRetentionPolicyAssignment.Info secondAssignmentInfo = assignments.next();

        assertEquals(secondAssignmentID, secondAssignmentInfo.getID());
    }

    @Test
    public void testCreateRetentionPolicyAssignmentSucceedsAndSendsCorrectJson() throws IOException {
        final String assignmentID = "12345";
        final String assignmentURL = "/retention_policy_assignments";
        final String policyID = "1111";
        final String policyName = "A Retention Policy";
        final String assignedByLogin = "test@user.com";

        JsonObject assignToObject = new JsonObject()
            .add("type", "enterprise");

        JsonObject assignmentObject = new JsonObject()
            .add("policy_id", policyID)
            .add("assign_to", assignToObject);

        String result = TestConfig.getFixture("BoxRetentionPolicy/CreateRetentionPolicyAssignmentForEnterprise201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(assignmentURL))
            .withRequestBody(WireMock.equalToJson(assignmentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        BoxRetentionPolicyAssignment.Info enterpriseAssignmentInfo = policy.assignToEnterprise();

        assertEquals(assignmentID, enterpriseAssignmentInfo.getID());
        assertEquals(policyName, enterpriseAssignmentInfo.getRetentionPolicy().getPolicyName());
        assertEquals(assignedByLogin, enterpriseAssignmentInfo.getAssignedBy().getLogin());
    }

    @Test
    public void testGetRetentionPolicyAssignmentInfoSucceeds() throws IOException {
        final String assignmentID = "12345";
        String assignmentURL = "/retention_policy_assignments/" + assignmentID;
        final String retentionPolicyID = "1111";
        final String assignedByLogin = "test@user.com";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetRetentionPolicyAssignmentInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicyAssignment assignment = new BoxRetentionPolicyAssignment(this.api, assignmentID);
        BoxRetentionPolicyAssignment.Info assignmentInfo = assignment.getInfo("assigned_to");

        assertEquals(assignmentID, assignmentInfo.getID());
        assertEquals(retentionPolicyID, assignmentInfo.getRetentionPolicy().getID());
        assertEquals(assignedByLogin, assignmentInfo.getAssignedBy().getLogin());
    }

    @Test
    public void testGetAllFileVersionRetentionsSucceeds() throws IOException {
        String versionRetentionURL = "/file_version_retentions";
        final String firstRetentionID = "12345";
        final String secondRetentionID = "32442";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetAllFileVersionRetentions200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionRetentionURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFileVersionRetention.QueryFilter filter = new BoxFileVersionRetention.QueryFilter();
        Iterator<BoxFileVersionRetention.Info> retentions
            = BoxFileVersionRetention.getRetentions(this.api, filter, "file", "applied_at").iterator();

        BoxFileVersionRetention.Info firstRetention = retentions.next();

        assertEquals(firstRetentionID, firstRetention.getID());

        BoxFileVersionRetention.Info secondRetention = retentions.next();

        assertEquals(secondRetentionID, secondRetention.getID());
    }

    @Test
    public void testGetFileRetentionInfoSucceeds() throws IOException {
        final String retentionID = "12345";
        final String versionRetentionURL = "/file_version_retentions/" + retentionID;
        final String retentionPolicyID = "1111";
        final String retentionPolicyName = "test2";
        final String fileVersionID = "2222";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetFileRetentionInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionRetentionURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFileVersionRetention policy = new BoxFileVersionRetention(this.api, retentionID);
        BoxFileVersionRetention.Info policyInfo = policy.getInfo();

        assertEquals(retentionID, policyInfo.getID());
        assertEquals(retentionPolicyID, policyInfo.getWinningPolicy().getID());
        assertEquals(retentionPolicyName, policyInfo.getWinningPolicy().getPolicyName());
        assertEquals(fileVersionID, policyInfo.getFileVersion().getID());
    }

    @Test
    public void testGetFilesUnderRetentionSucceeds() throws IOException {
        final String retentionAssignmentID = "12345";
        final String filesUnderRetentionURL = "/retention_policy_assignments/"
            + retentionAssignmentID + "/files_under_retention";
        final String fileId = "12345";
        final String fileName = "Contract.pdf";
        final String fileVersionID = "123456";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetFilesUnderRetention200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(filesUnderRetentionURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicyAssignment retetionAssignment =
            new BoxRetentionPolicyAssignment(this.api, retentionAssignmentID);
        Iterator<BoxFile.Info> filesUnderRetention = retetionAssignment.getFilesUnderRetention().iterator();

        BoxFile.Info firstFileUnderRetention = filesUnderRetention.next();

        assertEquals(fileId, firstFileUnderRetention.getID());
        assertEquals(fileName, firstFileUnderRetention.getName());
        assertEquals(fileVersionID, firstFileUnderRetention.getVersion().getVersionID());
    }

    @Test
    public void testGetFileVersionsUnderRetentionSucceeds() throws IOException {
        final String retentionAssignmentID = "12345";
        final String filesUnderRetentionURL = "/retention_policy_assignments/"
            + retentionAssignmentID + "/file_versions_under_retention";
        final String fileId = "123456";
        final String fileName = "Contract.pdf";
        final String fileVersionID = "1234567";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetFileVersionsUnderRetention200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(filesUnderRetentionURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicyAssignment retetionAssignment =
            new BoxRetentionPolicyAssignment(this.api, retentionAssignmentID);
        Iterator<BoxFileVersion> fileVersionsUnderRetention =
            retetionAssignment.getFileVersionsUnderRetention().iterator();

        BoxFileVersion firstFileVersionUnderRetention = fileVersionsUnderRetention.next();

        assertEquals(fileId, firstFileVersionUnderRetention.getID());
        assertEquals(fileName, firstFileVersionUnderRetention.getName());
        assertEquals(fileVersionID, firstFileVersionUnderRetention.getFileVersion().getVersionID());
    }
}
