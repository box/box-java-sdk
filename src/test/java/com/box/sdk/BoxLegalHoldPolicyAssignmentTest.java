package com.box.sdk;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxLegalHoldAssignment} related unit tests.
 */
public class BoxLegalHoldPolicyAssignmentTest {

    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();


    /**
     * Unit test for {@link BoxLegalHoldAssignment#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/legal_hold_policy_assignments/0?fields=assigned_by",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(api, "0");
        assignment.getInfo("assigned_by");
    }

    /**
     * Unit test for {@link BoxLegalHoldAssignment#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "255473";
        final String policyID = "166757";
        final String policyName = "Bug Bash 5-12 Policy 3 updated";
        final String assignedToType = "user";
        final String assignedToID = "2030388321";
        final String assignedByID = "2030388322";
        final String assignedByName = "Steve Boxuser";
        final String assignedByLogin = "sboxuser@box.com";
        final Date assignedAt = BoxDateFormat.parse("2016-05-18T10:32:19-07:00");
        final Date deletedAt = null;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"type\": \"legal_hold_policy_assignment\",\n"
                + "  \"id\": \"255473\",\n"
                + "  \"legal_hold_policy\": {\n"
                + "    \"type\": \"legal_hold_policy\",\n"
                + "    \"id\": \"166757\",\n"
                + "    \"policy_name\": \"Bug Bash 5-12 Policy 3 updated\"\n"
                + "  },\n"
                + "  \"assigned_to\": {\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"2030388321\"\n"
                + "  },\n"
                + "  \"assigned_by\": {\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"2030388322\",\n"
                + "    \"name\": \"Steve Boxuser\",\n"
                + "    \"login\": \"sboxuser@box.com\"\n"
                + "  },\n"
                + "  \"assigned_at\": \"2016-05-18T10:32:19-07:00\",\n"
                + "  \"deleted_at\": null\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(api, id);
        BoxLegalHoldAssignment.Info info = assignment.getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(policyID, info.getLegalHold().getID());
        Assert.assertEquals(policyName, info.getLegalHold().getPolicyName());
        Assert.assertEquals(assignedToType, info.getAssignedToType());
        Assert.assertEquals(assignedToID, info.getAssignedToID());
        Assert.assertEquals(assignedByID, info.getAssignedBy().getID());
        Assert.assertEquals(assignedByName, info.getAssignedBy().getName());
        Assert.assertEquals(assignedByLogin, info.getAssignedBy().getLogin());
        Assert.assertEquals(assignedAt, info.getAssignedAt());
        Assert.assertEquals(deletedAt, info.getDeletedAt());
    }

    /**
     * Unit test for {@link BoxLegalHoldAssignment#create(BoxAPIConnection, String, String, String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateSendsCorrectJSON() {
        final String policyID = "0";
        final String resourceType = BoxLegalHoldAssignment.TYPE_FILE_VERSION;
        final String resourceID = "1";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/legal_hold_policy_assignments",
                        request.getUrl().toString());
                Assert.assertEquals(policyID, json.get("policy_id").asString());
                Assert.assertEquals(resourceType, json.get("assign_to").asObject().get("type").asString());
                Assert.assertEquals(resourceID, json.get("assign_to").asObject().get("id").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxLegalHoldAssignment.create(api, policyID, resourceType, resourceID);
    }

    /**
     * Unit test for {@link BoxLegalHoldAssignment#create(BoxAPIConnection, String, String, String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateParseAllFieldsCorrectly() throws ParseException {
        final String id = "255613";
        final String policyID = "166757";
        final String policyName = "Bug Bash 5-12 Policy 3 updated";
        final String assignedToType = BoxLegalHoldAssignment.TYPE_FILE;
        final String assignedToID = "5025127885";
        final String assignedByID = "2030388321";
        final String assignedByName = "Steve Boxuser";
        final String assignedByLogin = "sboxuser@box.com";
        final Date assignedAt = BoxDateFormat.parse("2016-05-18T17:38:03-07:00");
        final Date deletedAt = null;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"type\": \"legal_hold_policy_assignment\",\n"
                + "  \"id\": \"255613\",\n"
                + "  \"legal_hold_policy\": {\n"
                + "    \"type\": \"legal_hold_policy\",\n"
                + "    \"id\": \"166757\",\n"
                + "    \"policy_name\": \"Bug Bash 5-12 Policy 3 updated\"\n"
                + "  },\n"
                + "  \"assigned_to\": {\n"
                + "    \"type\": \"file\",\n"
                + "    \"id\": \"5025127885\"\n"
                + "  },\n"
                + "  \"assigned_by\": {\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"2030388321\",\n"
                + "    \"name\": \"Steve Boxuser\",\n"
                + "    \"login\": \"sboxuser@box.com\"\n"
                + "  },\n"
                + "  \"assigned_at\": \"2016-05-18T17:38:03-07:00\",\n"
                + "  \"deleted_at\": null\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxLegalHoldAssignment.Info info = BoxLegalHoldAssignment.create(api, policyID, assignedToType, assignedToID);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(policyID, info.getLegalHold().getID());
        Assert.assertEquals(policyName, info.getLegalHold().getPolicyName());
        Assert.assertEquals(assignedToType, info.getAssignedToType());
        Assert.assertEquals(assignedToID, info.getAssignedToID());
        Assert.assertEquals(assignedByID, info.getAssignedBy().getID());
        Assert.assertEquals(assignedByName, info.getAssignedBy().getName());
        Assert.assertEquals(assignedByLogin, info.getAssignedBy().getLogin());
        Assert.assertEquals(assignedAt, info.getAssignedAt());
        Assert.assertEquals(deletedAt, info.getDeletedAt());
    }

    /**
     * Unit test for {@link BoxLegalHoldAssignment#delete()}
     */
    @Test
    @Category(UnitTest.class)
    public void testDeleteSendsCorrectJSON() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/legal_hold_policy_assignments/0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(api, "0");
        assignment.delete();
    }

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

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(api, assignmentID);
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

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(assignmentURL))
           .withQueryParam("limit", WireMock.containing("100"))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, policyID);
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

        this.wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(assignmentURL))
           .withRequestBody(WireMock.equalToJson(assignmentObject.toString()))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, policyID);
        BoxFolder folder = new BoxFolder(api, folderID);
        BoxLegalHoldAssignment.Info assignInfo =  policy.assignTo(folder);

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

        this.wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(assignmentURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withStatus(204)));

        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(api, assignmentID);
        assignment.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetFileVersionLegalHoldSucceeds() throws IOException {
        String result = "";
        final String assignedToID = "55555";
        final String legalHoldID = "12345";
        final String versionURL = "/file_version_legal_holds/" + legalHoldID;

        result = TestConfig.getFixture("BoxLegalHold/GetFileVersionLegalHoldsID200");

        this.wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(versionURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));


    }

}
