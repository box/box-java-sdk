package com.box.sdk.retention;

import com.box.sdk.*;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.MalformedURLException;
import java.util.UUID;

import static com.box.sdk.TestConfig.getAccessToken;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RetentionPolicyAssignmentTest {


	@Test
	@Category(UnitTest.class)
	public void createRetentionPolicyAssignmentReturnsPolicyOnFakeFolder() throws Exception {

		final String id = "9999999999";
		final String folder_id = "12345";


		final RetentionPolicyTarget type = RetentionPolicyTarget.folder;

		final JsonObject fakeJSONRequest = new JsonObject()
				.add("policy_id", id)
				.add("assign_to", (new JsonObject()).add("type", type.toString()).add("id", folder_id));

		final JsonObject fakeJSONResponse = new JsonObject().readFrom(
				"{\"type\":\"retention_policy_assignment\",\"id\":\"3233225\",\"retention_policy\":{\"type\":\"retention_policy\",\"id\":\"32131\",\"policy_name\":\"TaxDocuments\"},\"assigned_to\":{\"type\":\"folder\",\"id\":\"99922219\"},\"assigned_by\":{\"type\":\"user\",\"id\":\"123456789\",\"name\":\"Sean\",\"login\":\"sean@box.com\"},\"assigned_at\":\"2015-07-20T14:28:09-07:00\"}"
		);

		BoxAPIConnection api = new BoxAPIConnection("");

		api.setRequestInterceptor(new JSONRequestInterceptor() {
			@Override
			protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
				assertEquals(id, json.get("policy_id").asString());
				assertEquals(type.toString(), json.get("assign_to").asObject().get("type").asString());
				assertEquals(folder_id, json.get("assign_to").asObject().get("id").asString());

				return new BoxJSONResponse() {
					@Override
					public String getJSON() {
						return fakeJSONResponse.toString();
					}
				};
			}
		});

		RetentionPolicyAssignment.Info response = RetentionPolicyAssignment.createRetentionPolicyAssignment(
				api,
				id,
				RetentionPolicyTarget.folder,
				folder_id);

		RetentionPolicyAssignment.Info expected = new Gson().fromJson(fakeJSONResponse.toString(), RetentionPolicyAssignment.Info.class);

		assertThat(expected, is(response));

	}


	@Test
	@Category(IntegrationTest.class)
	public void createRetentionPolicyAssignmentAndApplyToExistingRetentionPolicy() throws MalformedURLException {

		BoxAPIConnection api = new BoxAPIConnection(getAccessToken());

		final String folderName = UUID.randomUUID().toString();
		final String retentionPolicyName = UUID.randomUUID().toString();

		BoxFolder rootFolder = BoxFolder.getRootFolder(api);
		BoxFolder childFolder = rootFolder.createFolder(folderName).getResource();

		RetentionPolicy.Info rp = RetentionPolicy.createRetentionPolicy(api, retentionPolicyName, RetentionPolicyType.indefinite, RetentionPolicyDispositionAction.remove_retention);
		RetentionPolicyAssignment.Info rpa = RetentionPolicyAssignment.createRetentionPolicyAssignment(api, rp.getId(), RetentionPolicyTarget.folder, childFolder.getID());


		assertNotNull(rpa);
		assertThat(rpa.getRetention_policy().get("id").toString(), is(rp.getId()));
	}


	@Test
	@Category(UnitTest.class)
	public void createRetentionPolicyAssignmentRetreiveWithIndependentCallObjectsEqual() throws MalformedURLException {

		final JsonObject fakeJSONResponse = new JsonObject().readFrom(
				"{\"type\":\"retention_policy_assignment\",\"id\":\"3233225\",\"retention_policy\":{\"type\":\"retention_policy\",\"id\":\"32131\",\"policy_name\":\"TaxDocuments\"},\"assigned_to\":{\"type\":\"folder\",\"id\":\"99922219\"},\"assigned_by\":{\"type\":\"user\",\"id\":\"123456789\",\"name\":\"Sean\",\"login\":\"sean@box.com\"},\"assigned_at\":\"2015-07-20T14:28:09-07:00\"}"
		);
		final RetentionPolicyAssignment.Info expectedResponse = new Gson().fromJson(fakeJSONResponse.toString(), RetentionPolicyAssignment.Info.class);

		BoxAPIConnection api = new BoxAPIConnection("");

		api.setRequestInterceptor(new RequestInterceptor() {
			@Override
			public BoxAPIResponse onRequest(BoxAPIRequest request) {

				assertThat(request.getUrl().getPath().contains(expectedResponse.getId()), is(true));

				return new BoxJSONResponse() {
					@Override
					public String getJSON() {
						return fakeJSONResponse.toString();
					}
				};
			}
		});

		RetentionPolicyAssignment.Info getIndependentResponse = RetentionPolicyAssignment.getRetentionPolicy(api, expectedResponse.getId());

		assertThat(expectedResponse, is(getIndependentResponse));
	}


	@Test
	@Category(IntegrationTest.class)
	public void getRetentionPolicyAssignmentRetreiveSuccessfully() throws MalformedURLException {

		BoxAPIConnection api = new BoxAPIConnection(getAccessToken());

		final String folderName = UUID.randomUUID().toString();
		final String retentionPolicyName = UUID.randomUUID().toString();

		BoxFolder rootFolder = BoxFolder.getRootFolder(api);
		BoxFolder childFolder = rootFolder.createFolder(folderName).getResource();

		RetentionPolicy.Info rp = RetentionPolicy.createRetentionPolicy(api, retentionPolicyName, RetentionPolicyType.indefinite, RetentionPolicyDispositionAction.remove_retention);
		RetentionPolicyAssignment.Info rpa = RetentionPolicyAssignment.createRetentionPolicyAssignment(api, rp.getId(), RetentionPolicyTarget.folder, childFolder.getID());

		RetentionPolicyAssignment.Info getIndependentResponse = RetentionPolicyAssignment.getRetentionPolicy(api, rpa.getId());

		assertThat(rpa, is(getIndependentResponse));
	}

}
