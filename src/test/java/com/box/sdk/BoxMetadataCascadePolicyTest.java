package com.box.sdk;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoxMetadataCascadePolicyTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testCreateMetadataCascadePolicySucceedsSendsCorrectJson() throws IOException {
        final String cascadePolicyURL = "/2.0/metadata_cascade_policies";
        final String folderID = "22222";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        JsonObject cascadeObject = new JsonObject()
            .add("folder_id", folderID)
            .add("scope", scope)
            .add("templateKey", templateKey);

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/CreateMetadataCascadePolicies201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(cascadePolicyURL))
            .withRequestBody(WireMock.equalToJson(cascadeObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxMetadataCascadePolicy.Info metadataCascadePolicyInfo = BoxMetadataCascadePolicy.create(this.api, folderID,
            scope, templateKey);

        assertEquals(folderID, metadataCascadePolicyInfo.getParent().getID());
        assertEquals(scope, metadataCascadePolicyInfo.getScope());
        assertEquals(templateKey, metadataCascadePolicyInfo.getTemplateKey());
    }

    @Test
    public void testGetAllMetadataCascadePoliciesSucceeds() throws IOException {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/2.0/metadata_cascade_policies";

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxMetadataCascadePolicy.Info> metadataCascadePolicies =
            BoxMetadataCascadePolicy.getAll(this.api, folderID).iterator();

        BoxMetadataCascadePolicy.Info firstCascadePolicy = metadataCascadePolicies.next();

        assertEquals(folderID, firstCascadePolicy.getParent().getID());
        assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        assertEquals(scope, firstCascadePolicy.getScope());
        assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    public void testGetAMetadataCascadePolicySucceeds() throws IOException {
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String parentID = "22222";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePolicyURL = "/2.0/metadata_cascade_policies/" + cascadePolicyID;

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetMetadataCascadePoliciesID200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePolicyURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxMetadataCascadePolicy metadataCascadePolicy = new BoxMetadataCascadePolicy(this.api, cascadePolicyID);
        BoxMetadataCascadePolicy.Info metadataCascadePolicyInfo = metadataCascadePolicy.getInfo();

        assertEquals(enterpriseID, metadataCascadePolicyInfo.getOwnerEnterprise().getID());
        assertEquals(parentID, metadataCascadePolicyInfo.getParent().getID());
        assertEquals(scope, metadataCascadePolicyInfo.getScope());
        assertEquals(templateKey, metadataCascadePolicyInfo.getTemplateKey());
    }

    @Test
    public void testForceApplyMetadataCascadePolicySucceedsAndSendsCorrectJson() {
        final String conflictResolution = "none";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String forceApplyURL = "/2.0/metadata_cascade_policies/" + cascadePolicyID + "/apply";

        JsonObject policyObject = new JsonObject()
            .add("conflict_resolution", conflictResolution);

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(forceApplyURL))
            .withRequestBody(WireMock.equalToJson(policyObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(202)));

        BoxMetadataCascadePolicy metadataCascadePolicy = new BoxMetadataCascadePolicy(this.api, cascadePolicyID);
        metadataCascadePolicy.forceApply(conflictResolution);
    }

    @Test
    public void testDeleteMetadataCascadePolicySendsCorrectRequest() {
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String cascadePolicyURL = "/2.0/metadata_cascade_policies/" + cascadePolicyID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(cascadePolicyURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxMetadataCascadePolicy metadataCascadePolicy = new BoxMetadataCascadePolicy(this.api, cascadePolicyID);
        metadataCascadePolicy.delete();
    }
}
