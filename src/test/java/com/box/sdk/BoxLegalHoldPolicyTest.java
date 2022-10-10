package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxLegalHoldPolicy} related unit tests.
 */
public class BoxLegalHoldPolicyTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testGetLegalHoldPolicySucceeds() {
        final String legalHoldsID = "12345";
        final String legalHoldsURL = "/2.0/legal_hold_policies/" + legalHoldsID;
        final String policyName = "Trial Documents";
        final String createdByLogin = "testuser@example.com";
        final String createdByName = "Test User";
        final String createdByID = "33333";

        String result = TestUtils.getFixture("BoxLegalHold/GetLegalHoldPoliciesID200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(legalHoldsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, legalHoldsID);
        BoxLegalHoldPolicy.Info policyInfo = policy.getInfo();

        Assert.assertEquals(legalHoldsID, policyInfo.getID());
        Assert.assertEquals(policyName, policyInfo.getPolicyName());
        Assert.assertEquals(createdByLogin, policyInfo.getCreatedBy().getLogin());
        Assert.assertEquals(createdByName, policyInfo.getCreatedBy().getName());
        Assert.assertEquals(createdByID, policyInfo.getCreatedBy().getID());
    }

    @Test
    public void testGetAllLegalHoldPoliciesSucceeds() {
        final String legalHoldsURL = "/2.0/legal_hold_policies";
        final String firstLegalHoldID = "22222";
        final String firstLegalHoldName = "IRS Audit";
        final String secondLegalHoldID = "11111";
        final String secondLegalHoldName = "Trial Documents";

        String result = TestUtils.getFixture("BoxLegalHold/GetLegalHoldPolicies200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(legalHoldsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        Iterator<BoxLegalHoldPolicy.Info> policies = BoxLegalHoldPolicy.getAll(this.api).iterator();
        BoxLegalHoldPolicy.Info firstPolicyInfo = policies.next();

        Assert.assertEquals(firstLegalHoldID, firstPolicyInfo.getID());
        Assert.assertEquals(firstLegalHoldName, firstPolicyInfo.getPolicyName());

        BoxLegalHoldPolicy.Info secondPolicyInfo = policies.next();

        Assert.assertEquals(secondLegalHoldID, secondPolicyInfo.getID());
        Assert.assertEquals(secondLegalHoldName, secondPolicyInfo.getPolicyName());
    }

    @Test
    public void testCreateNewLegalHoldPolicySucceedsAndSendsCorrectJson() {
        final String legalHoldsURL = "/2.0/legal_hold_policies";
        final String policyID = "11111";
        final String createdByID = "33333";
        final String createdByName = "Test User";
        final String createdByLogin = "testuser@example.com";
        final String policyName = "Trial Documents";


        JsonObject policyObject = new JsonObject()
            .add("policy_name", policyName)
            .add("is_ongoing", true);

        String result = TestUtils.getFixture("BoxLegalHold/PostOngoingLegalHoldPolicies201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(legalHoldsURL))
            .withRequestBody(WireMock.equalToJson(policyObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxLegalHoldPolicy.Info policyInfo = BoxLegalHoldPolicy.create(this.api, policyName);

        Assert.assertEquals(policyID, policyInfo.getID());
        Assert.assertEquals(createdByID, policyInfo.getCreatedBy().getID());
        Assert.assertEquals(createdByName, policyInfo.getCreatedBy().getName());
        Assert.assertEquals(createdByLogin, policyInfo.getCreatedBy().getLogin());
        Assert.assertEquals(policyName, policyInfo.getPolicyName());
        Assert.assertTrue(policyInfo.getIsOngoing());
    }

    @Test
    public void testCreateNewLegalHoldPolicyWithDateFilters() throws ParseException {
        final String legalHoldsURL = "/2.0/legal_hold_policies";
        final String policyID = "11111";
        final String createdByID = "33333";
        final String createdByName = "Test User";
        final String createdByLogin = "testuser@example.com";
        final String policyName = "Trial Documents";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        final String startTimeString = "2018-04-25T23:37:05Z";
        final String endTimeString = "2020-04-25T23:37:05Z";
        final Date startTime = dateFormat.parse("2018-04-25T16:37:05-07:00");
        final Date endTime = dateFormat.parse("2020-04-25T16:37:05-07:00");

        JsonObject policyObject = new JsonObject()
            .add("policy_name", policyName)
            .add("filter_started_at", startTimeString)
            .add("filter_ended_at", endTimeString);

        String result = TestUtils.getFixture("BoxLegalHold/PostLegalHoldPolicies201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(legalHoldsURL))
            .withRequestBody(WireMock.equalToJson(policyObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxLegalHoldPolicy.Info policyInfo = BoxLegalHoldPolicy.create(this.api, policyName, null,
            startTime, endTime);

        Assert.assertEquals(policyID, policyInfo.getID());
        Assert.assertEquals(createdByID, policyInfo.getCreatedBy().getID());
        Assert.assertEquals(createdByName, policyInfo.getCreatedBy().getName());
        Assert.assertEquals(createdByLogin, policyInfo.getCreatedBy().getLogin());
        Assert.assertEquals(policyName, policyInfo.getPolicyName());
        Assert.assertEquals(startTime, policyInfo.getFilterStartedAt());
        Assert.assertEquals(endTime, policyInfo.getFilterEndedAt());
    }

    @Test
    public void testCreateOngoingNewLegalHoldPolicySucceedsAndSendsCorrectJson() {
        final String legalHoldsURL = "/2.0/legal_hold_policies";
        final String policyID = "11111";
        final String createdByID = "33333";
        final String createdByName = "Test User";
        final String createdByLogin = "testuser@example.com";
        final String policyName = "Trial Documents";
        final String description = "This is a description.";

        JsonObject policyObject = new JsonObject()
            .add("policy_name", policyName)
            .add("is_ongoing", true)
            .add("description", description);

        String result = TestUtils.getFixture("BoxLegalHold/PostOngoingLegalHoldPolicies201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(legalHoldsURL))
            .withRequestBody(WireMock.equalToJson(policyObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxLegalHoldPolicy.Info policyInfo = BoxLegalHoldPolicy.createOngoing(this.api, policyName, description);

        Assert.assertEquals(policyID, policyInfo.getID());
        Assert.assertEquals(createdByID, policyInfo.getCreatedBy().getID());
        Assert.assertEquals(createdByName, policyInfo.getCreatedBy().getName());
        Assert.assertEquals(createdByLogin, policyInfo.getCreatedBy().getLogin());
        Assert.assertEquals(policyName, policyInfo.getPolicyName());
        Assert.assertEquals(description, policyInfo.getDescription());
        Assert.assertTrue(policyInfo.getIsOngoing());
    }


    @Test
    public void testUpdateLegalHoldPolicySucceedsAndSendsCorrectJson() {
        final String legalHoldsID = "11111";
        final String legalHoldsURL = "/2.0/legal_hold_policies/" + legalHoldsID;
        final String legalHoldsDescription = "Documents related to our ongoing litigation";
        final String legalHoldsPolicyName = "Trial Documents";

        JsonObject updateObject = new JsonObject()
            .add("description", legalHoldsDescription)
            .add("policy_name", legalHoldsPolicyName);

        String result = TestUtils.getFixture("BoxLegalHold/PutLegalHoldPoliciesID200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(legalHoldsURL))
            .withRequestBody(WireMock.equalToJson(updateObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, legalHoldsID);
        BoxLegalHoldPolicy.Info policyInfo = policy.new Info();
        policyInfo.setDescription(legalHoldsDescription);
        policyInfo.setPolicyName(legalHoldsPolicyName);
        policy.updateInfo(policyInfo);

        Assert.assertEquals(legalHoldsDescription, policyInfo.getDescription());
        Assert.assertEquals(legalHoldsPolicyName, policyInfo.getPolicyName());
    }

    @Test
    public void testDeleteLegalHoldPolicySucceeds() {
        final String legalHoldsID = "11111";
        final String legalHoldsURL = "/2.0/legal_hold_policies/" + legalHoldsID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(legalHoldsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, legalHoldsID);
        policy.delete();
    }
}
