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
 * {@link BoxRetentionPolicyAssignment} related unit tests.
 */
public class BoxRetentionPolicyAssignmentTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testGetPolicyAssignmentForEnterpriseSucceeds() throws IOException {
        String result = "";
        final String assignmentID = "12345";
        final String policyID = "1111";
        final String getAssignmentsURL = "/retention_policies/" + policyID + "/assignments";

        result = TestConfig.getFixture("BoxRetentionPolicy/GetRetentionPolicyAssignment200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAssignmentsURL))
                .withQueryParam("type", WireMock.containing("enterprise"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        Iterator<BoxRetentionPolicyAssignment.Info> enterpriseAssignments =
                policy.getEnterpriseAssignments().iterator();

        BoxRetentionPolicyAssignment.Info assignmentInfo = enterpriseAssignments.next();

        Assert.assertEquals(assignmentID, assignmentInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetPolicyAssignmentForFolderSucceeds() throws IOException {
        String result = "";
        final String assignmentID = "12345";
        final String policyID = "1111";
        final String getAssignmentsURL = "/retention_policies/" + policyID + "/assignments";

        result = TestConfig.getFixture("BoxRetentionPolicy/GetRetentionPolicyAssignment200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAssignmentsURL))
                .withQueryParam("type", WireMock.containing("folder"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        Iterator<BoxRetentionPolicyAssignment.Info> folderAssignments =
                policy.getFolderAssignments().iterator();

        BoxRetentionPolicyAssignment.Info assignmentInfo = folderAssignments.next();

        Assert.assertEquals(assignmentID, assignmentInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllPolicyAssignmentsSucceeds() throws IOException {
        String result = "";
        final String policyID = "1111";
        final String getAssignmentsURL = "/retention_policies/" + policyID + "/assignments";
        final String firstAssignmentID = "12345";
        final String secondAssignmentID = "42342";

        result = TestConfig.getFixture("BoxRetentionPolicy/GetAllRetentionPolicyAssignments200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAssignmentsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        Iterator<BoxRetentionPolicyAssignment.Info> assignments =
                policy.getEnterpriseAssignments().iterator();


        BoxRetentionPolicyAssignment.Info firstAssignmentInfo = assignments.next();

        Assert.assertEquals(firstAssignmentID, firstAssignmentInfo.getID());

        BoxRetentionPolicyAssignment.Info secondAssignmentInfo = assignments.next();

        Assert.assertEquals(secondAssignmentID, secondAssignmentInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateRetentionPolicyAssignmentSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String assignmentID = "12345";
        final String assignmentURL = "/retention_policy_assignments";
        final String policyID = "1111";
        final String policyName = "A Retention Policy";
        final String assignedToID = "2222";
        final String assignedByLogin = "test@user.com";

        JsonObject assignToObject = new JsonObject()
                .add("type", "enterprise");

        JsonObject assignmentObject = new JsonObject()
                .add("policy_id", policyID)
                .add("assign_to", assignToObject);

        result = TestConfig.getFixture("BoxRetentionPolicy/CreateRetentionPolicyAssignmentForEnterprise201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(assignmentURL))
                .withRequestBody(WireMock.equalToJson(assignmentObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        BoxRetentionPolicyAssignment.Info enterpriseAssignmentInfo = policy.assignToEnterprise();

        Assert.assertEquals(assignmentID, enterpriseAssignmentInfo.getID());
        Assert.assertEquals(policyName, enterpriseAssignmentInfo.getRetentionPolicy().getPolicyName());
        Assert.assertEquals(assignedByLogin, enterpriseAssignmentInfo.getAssignedBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetRetentionPolicyAssignmentInfoSucceeds() throws IOException {
        String result = "";
        final String assignmentID = "12345";
        String assignmentURL = "/retention_policy_assignments/" + assignmentID;
        final String retentionPolicyID = "1111";
        final String assignedByLogin = "test@user.com";

        result = TestConfig.getFixture("BoxRetentionPolicy/GetRetentionPolicyAssignmentInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicyAssignment assignment = new BoxRetentionPolicyAssignment(this.api, assignmentID);
        BoxRetentionPolicyAssignment.Info assignmentInfo = assignment.getInfo("assigned_to");

        Assert.assertEquals(assignmentID, assignmentInfo.getID());
        Assert.assertEquals(retentionPolicyID, assignmentInfo.getRetentionPolicy().getID());
        Assert.assertEquals(assignedByLogin, assignmentInfo.getAssignedBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllFileVersionRetentionsSucceeds() throws IOException {
        String result = "";
        String versionRetentionURL = "/file_version_retentions";
        final String firstRetentionID = "12345";
        final String secondRetentionID = "32442";

        result = TestConfig.getFixture("BoxRetentionPolicy/GetAllFileVersionRetentions200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionRetentionURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFileVersionRetention.QueryFilter filter = new BoxFileVersionRetention.QueryFilter();
        Iterator<BoxFileVersionRetention.Info> retentions
            = BoxFileVersionRetention.getRetentions(this.api, filter, "file", "applied_at").iterator();

        BoxFileVersionRetention.Info firstRetention = retentions.next();

        Assert.assertEquals(firstRetentionID, firstRetention.getID());

        BoxFileVersionRetention.Info secondRetention = retentions.next();

        Assert.assertEquals(secondRetentionID, secondRetention.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetFileRetentionInfoSucceeds() throws IOException {
        String result = "";
        final String retentionID = "12345";
        final String versionRetentionURL = "/file_version_retentions/" + retentionID;
        final String retentionPolicyID = "1111";
        final String retentionPolicyName = "test2";
        final String fileVersionID = "2222";

        result = TestConfig.getFixture("BoxRetentionPolicy/GetFileRetentionInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionRetentionURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFileVersionRetention policy = new BoxFileVersionRetention(this.api, retentionID);
        BoxFileVersionRetention.Info policyInfo = policy.getInfo();

        Assert.assertEquals(retentionID, policyInfo.getID());
        Assert.assertEquals(retentionPolicyID, policyInfo.getWinningPolicy().getID());
        Assert.assertEquals(retentionPolicyName, policyInfo.getWinningPolicy().getPolicyName());
        Assert.assertEquals(fileVersionID, policyInfo.getFileVersion().getID());
    }
}
