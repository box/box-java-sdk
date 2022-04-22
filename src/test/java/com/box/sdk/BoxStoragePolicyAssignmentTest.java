package com.box.sdk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxStoragePolicyAssignment} related tests
 */
public class BoxStoragePolicyAssignmentTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    BoxAPIConnection api = TestConfig.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testCreateAssignmentParseAllFieldsCorrectly() {

        final String storagePolicyAssignmentID = "user_1111";
        final String storagePolicyAssignedToType = "user";
        final String storagePolicyAssignedToID = "5678";
        final String storagePolicyType = "storage_policy";
        final String storagePolicyID = "1234";

        JsonObject assignedToObject = new JsonObject()
            .add("type", storagePolicyAssignedToType)
            .add("id", storagePolicyAssignedToID);

        JsonObject storagePolicyObject = new JsonObject()
            .add("type", storagePolicyType)
            .add("id", storagePolicyID);

        JsonObject mockJSON = new JsonObject()
            .add("type", storagePolicyAssignedToType)
            .add("id", storagePolicyAssignmentID)
            .add("assigned_to", assignedToObject)
            .add("storage_policy", storagePolicyObject);

        wireMockRule.stubFor(post(urlEqualTo("/2.0/storage_policy_assignments"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(mockJSON.toString())));

        BoxStoragePolicyAssignment.Info assignmentInfo =
            BoxStoragePolicyAssignment.create(this.api, storagePolicyID, storagePolicyAssignedToID);
        assertThat(assignmentInfo.getID(), is(equalTo(mockJSON.get("id").asString())));
        assertThat(assignmentInfo.getStoragePolicyID(),
            is(equalTo(mockJSON.get("storage_policy").asObject().get("id").asString())));
        assertThat(assignmentInfo.getAssignedToType(),
            is(equalTo(mockJSON.get("assigned_to").asObject().get("type").asString())));
    }

    @Test
    public void testGetStorageAssignmentInfoParseAllFieldsCorrectly() throws IOException {
        final String assignmentID = "12345";
        final String storagePolicyType = "storage_policy";
        final String storagePolicyID = "11";
        final String assignedToType = "user";
        final String assignedToID = "22";

        String result = TestConfig.getFixture("BoxStoragePolicy/Get_Storage_Policy_Assignments_200");

        wireMockRule.stubFor(get(urlPathEqualTo("/2.0/storage_policy_assignments"))
            .withQueryParam("resolved_for_id", WireMock.equalTo(assignedToID))
            .withQueryParam("resolved_for_type", WireMock.equalTo(assignedToType))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxStoragePolicyAssignment.Info info = BoxStoragePolicyAssignment.getAssignmentForTarget(this.api,
            assignedToType, assignedToID);

        assertEquals(assignmentID, info.getID());
        assertEquals(storagePolicyType, info.getStoragePolicyType());
        assertEquals(storagePolicyID, info.getStoragePolicyID());
        assertEquals(storagePolicyID, info.getStoragePolicyID());
        assertEquals(storagePolicyType, info.getStoragePolicyType());
    }

    @Test
    public void testGetStorageAssignmentInfoWithIDParseAllFieldsCorrectly() {
        final String assignmentType = "storage_policy_assignment";
        final String assignmentID = "user_1111";
        final String assignedToType = "user";
        final String assignedToID = "5678";
        final String storagePolicyID = "1234";
        final String storagePolicyType = "storage_policy";

        JsonObject assignedToObject = new JsonObject()
            .add("type", assignedToType)
            .add("id", assignedToID);

        JsonObject storagePolicyObject = new JsonObject()
            .add("type", storagePolicyType)
            .add("id", storagePolicyID);

        JsonObject mockJSON = new JsonObject()
            .add("type", assignmentType)
            .add("id", assignmentID)
            .add("assigned_to", assignedToObject)
            .add("storage_policy", storagePolicyObject);

        wireMockRule.stubFor(get(urlEqualTo("/2.0/storage_policy_assignments/" + assignmentID))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(mockJSON.toString())));

        BoxStoragePolicyAssignment assignment = new BoxStoragePolicyAssignment(this.api, assignmentID);
        BoxStoragePolicyAssignment.Info assignmentInfo = assignment.getInfo();
        assertThat(assignmentInfo.getID(), is(equalTo(mockJSON.get("id").asString())));
        assertThat(assignmentInfo.getStoragePolicyID(),
            is(equalTo(mockJSON.get("storage_policy").asObject().get("id").asString())));
        assertThat(assignmentInfo.getAssignedToType(),
            is(equalTo(mockJSON.get("assigned_to").asObject().get("type").asString())));
    }

    @Test
    public void testDeleteStorageAssignmentSendsCorrectRequest() {

        final String assignmentID = "user_1111";
        final String assignmentURL = "/2.0/storage_policy_assignments/" + assignmentID;

        stubFor(delete(urlEqualTo(assignmentURL)).willReturn(aResponse().withStatus(204)));

        BoxStoragePolicyAssignment assignment = new BoxStoragePolicyAssignment(this.api, assignmentID);
        assignment.delete();

        verify(deleteRequestedFor(urlEqualTo(assignmentURL)).withRequestBody(WireMock.absent()));
    }

    @Test
    public void testUpdateStorageAssignmentInfoParseAllFieldsCorrectly() {
        final String assignmentID = "user_1111";
        final String assignmentType = "storage_policy_assignment";
        final String assignedToType = "user";
        final String assignedToID = "7777";
        final String storagePolicyType = "storage_policy";
        final String storagePolicyID = "12345";

        JsonObject assignedToObject = new JsonObject()
            .add("type", assignedToType)
            .add("id", assignedToID);

        JsonObject storagePolicyObject = new JsonObject()
            .add("type", storagePolicyType)
            .add("id", storagePolicyID);

        JsonObject mockJSON = new JsonObject()
            .add("type", assignmentType)
            .add("id", assignmentID)
            .add("assigned_to", assignedToObject)
            .add("storage_policy", storagePolicyObject);

        wireMockRule.stubFor(put(urlEqualTo("/2.0/storage_policy_assignments/" + assignmentID))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(mockJSON.toString())));

        BoxStoragePolicyAssignment storagePolicyAssignment = new BoxStoragePolicyAssignment(this.api, assignmentID);
        BoxStoragePolicyAssignment.Info info = storagePolicyAssignment.new Info();
        info.setStoragePolicyID(storagePolicyID);
        storagePolicyAssignment.updateInfo(info);

        assertThat(info.getID(), is(equalTo(mockJSON.get("id").asString())));
        assertThat(info.getStoragePolicyID(),
            is(equalTo(mockJSON.get("storage_policy").asObject().get("id").asString())));
    }

    @Test
    public void testAssignStorageAssignmentInfoParseAllFieldsCorrectly() throws IOException {
        final String assignedToType = "user";
        final String assignedToID = "22";
        final String storagePolicyID = "11";

        String result = TestConfig.getFixture("BoxStoragePolicy/Get_Storage_Policy_Assignments_200");

        wireMockRule.stubFor(get(urlPathEqualTo("/2.0/storage_policy_assignments"))
            .withQueryParam("resolved_for_id", WireMock.equalTo(assignedToID))
            .withQueryParam("resolved_for_type", WireMock.equalTo(assignedToType))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxStoragePolicyAssignment.Info assignmentInfo =
            BoxStoragePolicyAssignment.assign(this.api, storagePolicyID, assignedToID);

        assertEquals(assignmentInfo.getStoragePolicyID(), storagePolicyID);
    }

    @Test
    public void testAssignStorageAssignmentInfoIsEnterpriseParseAllFieldsCorrectly() throws IOException {
        final String assignedToType = "enterprise";
        final String assignedToID = "22";
        final String storagePolicyID = "11";

        String policyResult = TestConfig.getFixture("BoxStoragePolicy/Get_Storage_Policy_Assignments_200");

        wireMockRule.stubFor(get(urlPathEqualTo("/2.0/storage_policy_assignments"))
            .withQueryParam("resolved_for_id", WireMock.equalTo(assignedToID))
            .withQueryParam("resolved_for_type", WireMock.equalTo("user"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(policyResult)));

        String assignResult = TestConfig.getFixture("BoxStoragePolicy/Get_Storage_Policy_Assignments_200");

        wireMockRule.stubFor(post(urlPathEqualTo("/2.0/storage_policy_assignments"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(assignResult)));

        BoxStoragePolicyAssignment.Info assignmentInfo =
            BoxStoragePolicyAssignment.assign(this.api, storagePolicyID, assignedToID);

        assertEquals(assignedToType, assignmentInfo.getAssignedToType());
    }
}
