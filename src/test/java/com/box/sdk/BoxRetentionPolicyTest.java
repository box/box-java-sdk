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
 * {@link BoxRetentionPolicy} related unit tests.
 */
public class BoxRetentionPolicyTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testGetAllRetentionPoliciesSucceeds() throws IOException {
        String result = "";
        final String getAllRetentionPoliciesURL = "/retention_policies";
        final String firstRetentionPolicyID = "12345";
        final String firstRetentionPolicyName = "A Retention Policy";
        final String secondRetentionPolicyID = "32421";
        final String secondRetentionPolicyName = "A Retention Policy 2";

        result = TestConfig.getFixture("BoxRetentionPolicy/GetAllRetentionPolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllRetentionPoliciesURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxRetentionPolicy.Info> policies = BoxRetentionPolicy.getAll(this.api).iterator();
        BoxRetentionPolicy.Info firstRetentionPolicy = policies.next();

        Assert.assertEquals(firstRetentionPolicyID, firstRetentionPolicy.getID());
        Assert.assertEquals(firstRetentionPolicyName, firstRetentionPolicy.getPolicyName());

        BoxRetentionPolicy.Info secondRetentionPolicy = policies.next();

        Assert.assertEquals(secondRetentionPolicyID, secondRetentionPolicy.getID());
        Assert.assertEquals(secondRetentionPolicyName, secondRetentionPolicy.getPolicyName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetRetentionPolicyInfoSucceeds() throws IOException {
        String result = "";
        final String policyName = "A Retention Policy";
        final String policyStatus = "active";
        final String dispositionAction = "remove_retention";
        final Boolean areOwnersNotified = true;
        final Boolean canOwnerExtendRetention = true;
        final String retentionPolicyID = "12345";
        final String getRetentionPolicyInfoURL = "/retention_policies/" + retentionPolicyID;

        result = TestConfig.getFixture("BoxRetentionPolicy/GetRetentionPolicyInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getRetentionPolicyInfoURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, retentionPolicyID);
        BoxRetentionPolicy.Info policyInfo = policy.getInfo("policy_name", "status");

        Assert.assertEquals(policyName, policyInfo.getPolicyName());
        Assert.assertEquals(policyStatus, policyInfo.getStatus());
        Assert.assertEquals(retentionPolicyID, policyInfo.getID());
        Assert.assertEquals(dispositionAction, policyInfo.getDispositionAction());
        Assert.assertEquals(areOwnersNotified, policyInfo.getAreOwnersNotified());
        Assert.assertEquals(canOwnerExtendRetention, policyInfo.getCanOwnerExtendRetention());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateRetentionPolicySucceeds() throws IOException {
        String result = "";
        final String policyID = "12345";
        final String policyName = "Test Retention Policy";
        final String policyType = "indefinite";
        final String dispositionAction = "remove_retention";
        final String createRetentionPolicyURL = "/retention_policies";
        final String createdByLogin = "test@user.com";
        final String policyStatus = "active";

        JsonObject retentionPolicyObject = new JsonObject()
                .add("policy_name", policyName)
                .add("policy_type", policyType)
                .add("dispositon_action", dispositionAction);

        result = TestConfig.getFixture("BoxRetentionPolicy/CreateRetentionPolicy201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(createRetentionPolicyURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy.Info policyInfo = BoxRetentionPolicy.createIndefinitePolicy(this.api, policyName);

        Assert.assertEquals(policyName, policyInfo.getPolicyName());
        Assert.assertEquals(policyType, policyInfo.getPolicyType());
        Assert.assertEquals(dispositionAction, policyInfo.getDispositionAction());
        Assert.assertEquals(createdByLogin, policyInfo.getCreatedBy().getLogin());
        Assert.assertEquals(policyID, policyInfo.getID());
        Assert.assertEquals(policyStatus, policyInfo.getStatus());
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateRetentionPolicyInfoSendsCorrectJson() throws IOException {
        String result = "";
        final String policyID = "12345";
        final String updateRetentionPolicyURL = "/retention_policies/" + policyID;
        final String updatedPolicyName = "New Policy Name";
        final String updatedPolicyStatus = "retired";

        JsonObject retentionPolicyObject = new JsonObject()
                .add("policy_name", updatedPolicyName)
                .add("status", updatedPolicyStatus);

        result = TestConfig.getFixture("BoxRetentionPolicy/UpdateRetentionPolicyInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(updateRetentionPolicyURL))
                .withRequestBody(WireMock.equalToJson(retentionPolicyObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxRetentionPolicy policy = new BoxRetentionPolicy(this.api, policyID);
        BoxRetentionPolicy.Info policyInfo = policy.new Info();
        policyInfo.addPendingChange("policy_name", updatedPolicyName);
        policyInfo.addPendingChange("status", updatedPolicyStatus);
        policy.updateInfo(policyInfo);
    }
}
