package com.box.sdk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * {@link BoxLegalHoldPolicy} related unit tests.
 */
public class BoxLegalHoldPolicyTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testGetLegalHoldPolicySucceeds() throws IOException {
        String result = "";
        final String legalHoldsID = "12345";
        final String legalHoldsURL = "/legal_hold_policies/" + legalHoldsID;
        final String policyName = "Trial Documents";
        final String policyID = "11111";
        final String createdByLogin = "testuser@example.com";
        final String createdByName = "Test User";
        final String createdByID = "33333";

        result = TestConfig.getFixture("BoxLegalHold/GetLegalHoldPoliciesID200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(legalHoldsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
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
    @Category(UnitTest.class)
    public void testGetAllLegalHoldPoliciesSucceeds() throws IOException {
        String result = "";
        final String legalHoldsURL = "/legal_hold_policies";
        final String firstLegalHoldID = "22222";
        final String firstLegalHoldName = "IRS Audit";
        final String secondLegalHoldID = "11111";
        final String secondLegalHoldName = "Trial Documents";

        result = TestConfig.getFixture("BoxLegalHold/GetLegalHoldPolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(legalHoldsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
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
    @Category(UnitTest.class)
    public void testCreateNewLegalHoldPolicySucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String legalHoldsURL = "/legal_hold_policies";
        final String policyID = "11111";
        final String createdByID = "33333";
        final String createdByName = "Test User";
        final String createdByLogin = "testuser@example.com";
        final String policyName = "Trial Documents";


        JsonObject policyObject = new JsonObject()
                .add("policy_name", policyName)
                .add("is_ongoing", true);

        result = TestConfig.getFixture("BoxLegalHold/PostOngoingLegalHoldPolicies201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(legalHoldsURL))
                .withRequestBody(WireMock.equalToJson(policyObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
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
    @Category(UnitTest.class)
    public void testCreateNewLegalHoldPolicyWithDateFilters() throws IOException, ParseException {
        String result = "";
        final String legalHoldsURL = "/legal_hold_policies";
        final String policyID = "11111";
        final String createdByID = "33333";
        final String createdByName = "Test User";
        final String createdByLogin = "testuser@example.com";
        final String policyName = "Trial Documents";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        final String startTimeString = "2018-04-25T23:37:05+0000";
        final String endTimeString = "2020-04-25T23:37:05+0000";
        final Date startTime = dateFormat.parse("2018-04-25T16:37:05-07:00");
        final Date endTime = dateFormat.parse("2020-04-25T16:37:05-07:00");

        JsonObject policyObject = new JsonObject()
                .add("policy_name", policyName)
                .add("filter_started_at", startTimeString)
                .add("filter_ended_at", endTimeString);

        result = TestConfig.getFixture("BoxLegalHold/PostLegalHoldPolicies201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(legalHoldsURL))
                .withRequestBody(WireMock.equalToJson(policyObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
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
    @Category(UnitTest.class)
    public void testCreateOngoingNewLegalHoldPolicySucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String legalHoldsURL = "/legal_hold_policies";
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

        result = TestConfig.getFixture("BoxLegalHold/PostOngoingLegalHoldPolicies201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(legalHoldsURL))
                .withRequestBody(WireMock.equalToJson(policyObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
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
    @Category(UnitTest.class)
    public void testUpdateLegalHoldPolicySucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String legalHoldsID = "11111";
        final String legalHoldsURL = "/legal_hold_policies/" + legalHoldsID;
        final String legalHoldsDescription = "Documents related to our ongoing litigation";

        JsonObject updateObject = new JsonObject()
                .add("description", legalHoldsDescription);

        result = TestConfig.getFixture("BoxLegalHold/PutLegalHoldPoliciesID200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(legalHoldsURL))
                .withRequestBody(WireMock.equalToJson(updateObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, legalHoldsID);
        BoxLegalHoldPolicy.Info policyInfo = policy.new Info();
        policyInfo.addPendingChange("description", legalHoldsDescription);
        policy.updateInfo(policyInfo);

        Assert.assertEquals(legalHoldsDescription, policyInfo.getDescription());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteLegalHoldPolicySucceeds() throws IOException {
        final String legalHoldsID = "11111";
        final String legalHoldsURL = "/legal_hold_policies/" + legalHoldsID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(legalHoldsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(this.api, legalHoldsID);
        policy.delete();
    }
}
