package com.box.sdk;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxRetentionPolicy} related unit tests.
 */
public class BoxRetentionPolicyTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testGetAllRetentionPoliciesSucceeds() throws IOException {
        final String getAllRetentionPoliciesURL = "/2.0/retention_policies";
        final String firstRetentionPolicyID = "12345";
        final String firstRetentionPolicyName = "A Retention Policy";
        final String secondRetentionPolicyID = "32421";
        final String secondRetentionPolicyName = "A Retention Policy 2";

        String result = TestUtils.getFixture("BoxRetentionPolicy/GetAllRetentionPolicies200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllRetentionPoliciesURL))
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
        final String getRetentionPolicyInfoURL = "/2.0/retention_policies/" + retentionPolicyID;
        final String description = "description";

        String result = TestUtils.getFixture("BoxRetentionPolicy/GetRetentionPolicyInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getRetentionPolicyInfoURL))
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
        final String createRetentionPolicyURL = "/2.0/retention_policies";
        final String createdByLogin = "test@user.com";
        final String policyStatus = "active";
        final String description = "description";

        String result = TestUtils.getFixture("BoxRetentionPolicy/CreateRetentionPolicy201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(createRetentionPolicyURL))
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
        final String updateRetentionPolicyURL = "/2.0/retention_policies/" + policyID;
        final String updatedPolicyName = "New Policy Name";
        final String updatedPolicyStatus = "retired";
        final String updatedDescription = "updated description";


        JsonObject retentionPolicyObject = new JsonObject()
            .add("policy_name", updatedPolicyName)
            .add("status", updatedPolicyStatus)
            .add("description", updatedDescription);

        String result = TestUtils.getFixture("BoxRetentionPolicy/UpdateRetentionPolicyInfo200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(updateRetentionPolicyURL))
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
