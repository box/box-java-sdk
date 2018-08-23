package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Iterator;

/**
 *
 */
public class BoxMetadataCascadePolicyTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testCreateMetadataCascadePolicySucceedsSendsCorrectJson() throws IOException {
        String result = "";
        final String cascadePolicyURL = "/metadata_cascade_policies";
        final String folderID = "22222";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        JsonObject cascadeObject = new JsonObject()
                .add("folder_id", folderID)
                .add("scope", scope)
                .add("templateKey", templateKey);

        result = TestConfig.getFixture("BoxMetadataCascadePolicy/CreateMetadataCascadePolicies201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(cascadePolicyURL))
                .withRequestBody(WireMock.equalToJson(cascadeObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxMetadataCascadePolicy.Info metadataCascadePolicyInfo = BoxMetadataCascadePolicy.create(this.api, folderID,
                scope, templateKey);

        Assert.assertEquals(folderID, metadataCascadePolicyInfo.getParent().getID());
        Assert.assertEquals(scope, metadataCascadePolicyInfo.getScope());
        Assert.assertEquals(templateKey, metadataCascadePolicyInfo.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataCascadePoliciesSucceeds() throws IOException {
        String result = "";
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxMetadataCascadePolicy.Info> metadataCascadePolicies =
                BoxMetadataCascadePolicy.getAll(this.api, folderID).iterator();

        BoxMetadataCascadePolicy.Info firstCascadePolicy = metadataCascadePolicies.next();

        Assert.assertEquals(folderID, firstCascadePolicy.getParent().getID());
        Assert.assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        Assert.assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        Assert.assertEquals(scope, firstCascadePolicy.getScope());
        Assert.assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAMetadataCascadePolicySucceeds() throws IOException {
        String result = "";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String parentID = "22222";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePolicyURL = "/metadata_cascade_policies/" + cascadePolicyID;

        result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetMetadataCascadePoliciesID200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePolicyURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxMetadataCascadePolicy metadataCascadePolicy = new BoxMetadataCascadePolicy(this.api, cascadePolicyID);
        BoxMetadataCascadePolicy.Info metadataCascadePolicyInfo = metadataCascadePolicy.getInfo();

        Assert.assertEquals(enterpriseID, metadataCascadePolicyInfo.getOwnerEnterprise().getID());
        Assert.assertEquals(parentID, metadataCascadePolicyInfo.getParent().getID());
        Assert.assertEquals(scope, metadataCascadePolicyInfo.getScope());
        Assert.assertEquals(templateKey, metadataCascadePolicyInfo.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void testForceApplyMetadataCascadePolicySucceedsAndSendsCorrectJson() throws IOException {
        final String conflictResolution = "none";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String forceApplyURL = "/metadata_cascade_policies/" + cascadePolicyID + "/apply";

        JsonObject policyObject = new JsonObject()
                .add("conflict_resolution", conflictResolution);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(forceApplyURL))
                .withRequestBody(WireMock.equalToJson(policyObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(202)));

        BoxMetadataCascadePolicy metadataCascadePolicy = new BoxMetadataCascadePolicy(this.api, cascadePolicyID);
        metadataCascadePolicy.forceApply(conflictResolution);
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteMetadataCascadePolicySendsCorrectRequest() {
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String cascadePolicyURL = "/metadata_cascade_policies/" + cascadePolicyID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(cascadePolicyURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxMetadataCascadePolicy metadataCascadePolicy = new BoxMetadataCascadePolicy(this.api, cascadePolicyID);
        metadataCascadePolicy.delete();
    }
}
