package com.box.sdk.retention;

import java.net.MalformedURLException;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.box.sdk.TestConfig.getAccessToken;
import static com.box.sdk.retention.RetentionPolicyTarget.folder;
import com.box.sdk.*;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;


public class RetentionPolicyAssignmentTest {

    private final BoxAPIConnection apiIntercepted = new BoxAPIConnection("");
    private final BoxAPIConnection api = new BoxAPIConnection(getAccessToken());

    @Test
    @Category(UnitTest.class)
    public void createRetentionPolicyAssignmentReturnsPolicyAssignmentOnFakeFolder() throws Exception {
        final String policyId = "9999999999";
        final String folderId = "12345";

        final JsonObject fakeJSONResponse = JsonObject.readFrom(
                "{\"type\":\"retention_policy_assignment\",\"id\":\"3233225\","
                        + "\"retentionPolicy\":{\"type\":\"retentionPolicy\",\"id\":\"32131\","
                        + "\"policy_name\":\"TaxDocuments\"},\"assignedTo\":{\"type\":\"folder\",\"id\":\"99922219\"},"
                        + "\"assignedBy\":{\"type\":\"user\",\"id\":\"123456789\",\"name\":\"Sean\","
                        + "\"login\":\"sean@box.com\"},\"assignedAt\":\"2015-07-20T14:28:09-07:00\"}"
        );

        this.apiIntercepted.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(policyId, json.get("policy_id").asString());
                assertEquals(folder.toString(), json.get("assign_to").asObject().get("type").asString());
                assertEquals(folderId, json.get("assign_to").asObject().get("id").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        RetentionPolicyAssignment.Info response = RetentionPolicyAssignment.createRetentionPolicyAssignment(
                this.apiIntercepted,
                policyId,
                folder,
                folderId);

        RetentionPolicyAssignment.Info expected = new Gson().fromJson(fakeJSONResponse.toString(),
                RetentionPolicyAssignment.Info.class);

        assertThat(expected, is(response));
    }

    @Test
    @Category(UnitTest.class)
    public void retrieveRetentionPolicyAssignmentReturnsExpectedAssignment() throws MalformedURLException {
        final String policyId = "3233225";

        final JsonObject fakeJSONResponse = JsonObject.readFrom(
                "{\"type\":\"retention_policy_assignment\",\"id\":\"" + policyId + "\","
                        + "\"retentionPolicy\":{\"type\":\"retentionPolicy\",\"id\":\"32131\","
                        + "\"policy_name\":\"TaxDocuments\"},\"assignedTo\":{\"type\":\"folder\",\"id\":\"99922219\"},"
                        + "\"assignedBy\":{\"type\":\"user\",\"id\":\"123456789\",\"name\":\"Sean\","
                        + "\"login\":\"sean@box.com\"},\"assignedAt\":\"2015-07-20T14:28:09-07:00\"}"
        );
        final RetentionPolicyAssignment.Info expectedResponse = new Gson().fromJson(fakeJSONResponse.toString(),
                RetentionPolicyAssignment.Info.class);

        this.apiIntercepted.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                assertThat(request.getUrl().getPath(), containsString(policyId));

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        RetentionPolicyAssignment.Info actualResponse = RetentionPolicyAssignment.getRetentionPolicy(this
                .apiIntercepted, policyId);

        assertThat(actualResponse, is(expectedResponse));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createRetentionPolicyAssignmentAndApplyToExistingRetentionPolicy() throws MalformedURLException {
        final String folderName = UUID.randomUUID().toString();
        final String retentionPolicyName = UUID.randomUUID().toString();

        BoxFolder rootFolder = BoxFolder.getRootFolder(this.api);
        BoxFolder childFolder = rootFolder.createFolder(folderName).getResource();

        RetentionPolicy.Info retentionPolicy = RetentionPolicy.createRetentionPolicy(this.api, retentionPolicyName,
                RetentionPolicyType.indefinite, RetentionPolicyDispositionAction.remove_retention);
        RetentionPolicyAssignment.Info createdAssignment = RetentionPolicyAssignment.createRetentionPolicyAssignment(
                this.api, retentionPolicy.getId(), folder, childFolder.getID());

        assertThat(createdAssignment.getRetentionPolicy().get("id").toString(), is(retentionPolicy.getId()));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getRetentionPolicyAssignmentRetreiveSuccessfully() throws MalformedURLException {
        final String folderName = UUID.randomUUID().toString();
        final String retentionPolicyName = UUID.randomUUID().toString();

        BoxFolder rootFolder = BoxFolder.getRootFolder(this.api);
        BoxFolder childFolder = rootFolder.createFolder(folderName).getResource();

        RetentionPolicy.Info retentionPolicy = RetentionPolicy.createRetentionPolicy(this.api, retentionPolicyName,
                RetentionPolicyType.indefinite, RetentionPolicyDispositionAction.remove_retention);

        RetentionPolicyAssignment.Info createdAssignment = RetentionPolicyAssignment.createRetentionPolicyAssignment(
                this.api, retentionPolicy.getId(), folder, childFolder.getID());

        RetentionPolicyAssignment.Info retrievedAssignment = RetentionPolicyAssignment.getRetentionPolicy(this.api,
                createdAssignment.getId());

        assertThat(retrievedAssignment, is(createdAssignment));
    }

}
