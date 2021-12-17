package com.box.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxRetentionPolicy} related unit tests.
 */
public class BoxRetentionPolicyTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testGetAllRetentionPoliciesSucceeds() throws IOException {
        final String getAllRetentionPoliciesURL = "/retention_policies";
        final String firstRetentionPolicyID = "12345";
        final String firstRetentionPolicyName = "A Retention Policy";
        final String secondRetentionPolicyID = "32421";
        final String secondRetentionPolicyName = "A Retention Policy 2";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetAllRetentionPolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllRetentionPoliciesURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxRetentionPolicy.Info> policies = BoxRetentionPolicy.getAll(this.api).iterator();
        BoxRetentionPolicy.Info firstRetentionPolicy = policies.next();

        assertEquals(firstRetentionPolicyID, firstRetentionPolicy.getID());
        assertEquals(firstRetentionPolicyName, firstRetentionPolicy.getPolicyName());

        BoxRetentionPolicy.Info secondRetentionPolicy = policies.next();

        assertEquals(secondRetentionPolicyID, secondRetentionPolicy.getID());
        assertEquals(secondRetentionPolicyName, secondRetentionPolicy.getPolicyName());
    }

    @Test
    public void testGetRetentionPolicyInfoSucceeds() throws IOException {
        final String policyName = "A Retention Policy";
        final String policyStatus = "active";
        final String dispositionAction = "remove_retention";
        final String retentionPolicyID = "12345";
        final String getRetentionPolicyInfoURL = "/retention_policies/" + retentionPolicyID;
        final String description = "description";

        String result = TestConfig.getFixture("BoxRetentionPolicy/GetRetentionPolicyInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getRetentionPolicyInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, retentionPolicyID);
        BoxRetentionPolicy.Info policyInfo = policy.getInfo("policy_name", "status");

        assertEquals(policyName, policyInfo.getPolicyName());
        assertEquals(policyStatus, policyInfo.getStatus());
        assertEquals(retentionPolicyID, policyInfo.getID());
        assertEquals(dispositionAction, policyInfo.getDispositionAction());
        assertEquals(description, policyInfo.getDescription());
        assertTrue(policyInfo.getAreOwnersNotified());
        assertTrue(policyInfo.getCanOwnerExtendRetention());
    }

    @Test
    public void testCreateRetentionPolicySucceeds() throws IOException {
        final String policyID = "12345";
        final String policyName = "Test Retention Policy";
        final String policyType = "indefinite";
        final String dispositionAction = "remove_retention";
        final String createRetentionPolicyURL = "/retention_policies";
        final String createdByLogin = "test@user.com";
        final String policyStatus = "active";
        final String description = "description";

        String result = TestConfig.getFixture("BoxRetentionPolicy/CreateRetentionPolicy201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(createRetentionPolicyURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicy.Info policyInfo = BoxRetentionPolicy.createIndefinitePolicy(this.api, policyName);

        assertEquals(policyName, policyInfo.getPolicyName());
        assertEquals(policyType, policyInfo.getPolicyType());
        assertEquals(dispositionAction, policyInfo.getDispositionAction());
        assertEquals(createdByLogin, policyInfo.getCreatedBy().getLogin());
        assertEquals(policyID, policyInfo.getID());
        assertEquals(policyStatus, policyInfo.getStatus());
        assertEquals(description, policyInfo.getDescription());
    }

    @Test
    public void testUpdateRetentionPolicyInfoSendsCorrectJson() throws IOException {
        final String policyID = "12345";
        final String updateRetentionPolicyURL = "/retention_policies/" + policyID;
        final String updatedPolicyName = "New Policy Name";
        final String updatedPolicyStatus = "retired";
        final String updatedDescription = "updated description";


        JsonObject retentionPolicyObject = new JsonObject()
            .add("policy_name", updatedPolicyName)
            .add("status", updatedPolicyStatus)
            .add("description", updatedDescription);

        String result = TestConfig.getFixture("BoxRetentionPolicy/UpdateRetentionPolicyInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(updateRetentionPolicyURL))
            .withRequestBody(WireMock.equalToJson(retentionPolicyObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        BoxRetentionPolicy.Info policyInfo = policy.new Info();
        policyInfo.setPolicyName(updatedPolicyName);
        policyInfo.setStatus(updatedPolicyStatus);
        policyInfo.setDescription(updatedDescription);
        policy.updateInfo(policyInfo);
    }
}
