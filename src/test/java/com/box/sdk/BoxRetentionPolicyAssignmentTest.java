package com.box.sdk;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import com.eclipsesource.json.JsonArray;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
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
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testCreateAssignmentToMetadataMakesCorrectRequest() {

        final String policyID = "1234";
        final String templateID = "9b80ed1d-df05-41db-840a-38fc77c5fe0c";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            public BoxJSONResponse onJSONRequest(BoxJSONRequest request, JsonObject body) {
                Assert.assertEquals("https://api.box.com/2.0/retention_policy_assignments",
                        request.getUrl().toString());
                Assert.assertEquals("POST", request.getMethod());

                JsonArray filterFields = body.get("filter_fields").asArray();
                Assert.assertEquals("9b80ed1d-df05-41db-840a-38fc77c5fe0c",
                        filterFields.get(0).asObject().get("field").asString());
                Assert.assertEquals("f67f1029-4c38-43f7-8daf-86db2bf601ce",
                        filterFields.get(0).asObject().get("value").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\", \"type\": \"retention_policy_assignment\"}";
                    }
                };
            }
        });


        MetadataFieldFilter filter1 = new MetadataFieldFilter("9b80ed1d-df05-41db-840a-38fc77c5fe0c",
                "f67f1029-4c38-43f7-8daf-86db2bf601ce");
        BoxRetentionPolicyAssignment.Info info = BoxRetentionPolicyAssignment.createAssignmentToMetadata(api, policyID,
                templateID, filter1);
    }

    @Test
    @Category(UnitTest.class)
    public void testGetPolicyAssignmentForEnterpriseSucceeds() throws IOException {
        String result = "";
        final String assignmentID = "12345";
        final String policyID = "1111";
        final String getAssignmentsURL = "/retention_policies/" + policyID + "/assignments";

        result = TestConfig.getFixture("BoxRetentionPolicy/GetRetentionPolicyAssignment200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAssignmentsURL))
                .withQueryParam("type", WireMock.containing("enterprise"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(api, policyID);
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

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAssignmentsURL))
                .withQueryParam("type", WireMock.containing("folder"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(api, policyID);
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

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAssignmentsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(api, policyID);
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

        result = TestConfig.getFixture("BoxRetentionPolicy/CreateRetentionPolicyAssignmentForEnterprise200");

        this.wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(assignmentURL))
                .withRequestBody(WireMock.equalToJson(assignmentObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(api, policyID);
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

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicyAssignment assignment = new BoxRetentionPolicyAssignment(api, assignmentID);
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

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionRetentionURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFileVersionRetention.QueryFilter filter = new BoxFileVersionRetention.QueryFilter();
        Iterator<BoxFileVersionRetention.Info> retentions
                        = BoxFileVersionRetention.getRetentions(api, filter,"file", "applied_at").iterator();

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

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(versionRetentionURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFileVersionRetention policy = new BoxFileVersionRetention(api, retentionID);
        BoxFileVersionRetention.Info policyInfo = policy.getInfo();

        Assert.assertEquals(retentionID, policyInfo.getID());
        Assert.assertEquals(retentionPolicyID, policyInfo.getWinningPolicy().getID());
        Assert.assertEquals(retentionPolicyName, policyInfo.getWinningPolicy().getPolicyName());
        Assert.assertEquals(fileVersionID, policyInfo.getFileVersion().getID());
    }
}
