package com.box.sdk.retention;

import com.box.sdk.*;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.UUID;

import static com.box.sdk.TestConfig.getAccessToken;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNull;

public class RetentionPolicyTest {

	@Test
	@Category(UnitTest.class)
	public void createIndefiniteRetentionPolicyReturnsCreatedPolicy() throws Exception {
		final String name = "non-empty name";
		final RetentionPolicyType type = RetentionPolicyType.indefinite;
		final RetentionPolicyDispositionAction dispositionAction = RetentionPolicyDispositionAction.remove_retention;
		final String id = "12345";
		final RetentionPolicyStatus status = RetentionPolicyStatus.active;

		final JsonObject fakeJSONResponse = new JsonObject()
				.add("policy_name", name)
				.add("policy_type", type.toString())
				.add("disposition_action", dispositionAction.toString())
				.add("id", id)
				.add("status", status.toString());

		BoxAPIConnection api = new BoxAPIConnection("");

		api.setRequestInterceptor(new JSONRequestInterceptor() {
			@Override
			protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
				assertEquals(name, json.get("policy_name").asString());
				assertEquals(type.toString(), json.get("policy_type").asString());
				assertEquals(dispositionAction.toString(), json.get("disposition_action").asString());

				return new BoxAPIResponse() {
					@Override
					public InputStream getBody() {
						return new ByteArrayInputStream(fakeJSONResponse.toString().getBytes());
					}
				};
			}
		});

		RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(api, name, type, dispositionAction);

		RetentionPolicy.Info expected = new RetentionPolicy.Info(name, type, status, id, dispositionAction, null);

		assertThat(response, is(expected));
	}

	@Test
	@Category(UnitTest.class)
	public void createFiniteRetentionPolicySendsJSONWithNameTypeDispositionActionAndLength() throws Exception {
		final String name = "non-empty name";
		final RetentionPolicyType type = RetentionPolicyType.finite;
		final String retentionLength = "30";
		final RetentionPolicyDispositionAction dispositionAction = RetentionPolicyDispositionAction.remove_retention;
		final String id = "12345";
		final RetentionPolicyStatus status = RetentionPolicyStatus.active;

		final JsonObject fakeJSONResponse = new JsonObject()
				.add("policy_name", name)
				.add("policy_type", type.toString())
				.add("retention_length", retentionLength)
				.add("disposition_action", dispositionAction.toString())
				.add("id", id)
				.add("status", status.toString());

		BoxAPIConnection api = new BoxAPIConnection("");

		api.setRequestInterceptor(new JSONRequestInterceptor() {
			@Override
			protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
				assertEquals(name, json.get("policy_name").asString());
				assertEquals(type.toString(), json.get("policy_type").asString());
				assertEquals(retentionLength, json.get("retention_length").asString());
				assertEquals(dispositionAction.toString(), json.get("disposition_action").asString());

				return new BoxAPIResponse() {
					@Override
					public InputStream getBody() {
						return new ByteArrayInputStream(fakeJSONResponse.toString().getBytes());
					}
				};
			}
		});

		RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(api, name, type, retentionLength, dispositionAction);

		RetentionPolicy.Info expected = new RetentionPolicy.Info(name, type, status, id, dispositionAction, retentionLength);

		assertThat(response, is(expected));
	}

	@Test
	@Category(UnitTest.class)
	public void createFiniteRetentionPolicyRetreiveWithIndependentCallObjectsEqual() throws MalformedURLException {

		final String name = "non-empty name";
		final RetentionPolicyType type = RetentionPolicyType.finite;
		final String retentionLength = "30";
		final RetentionPolicyDispositionAction dispositionAction = RetentionPolicyDispositionAction.remove_retention;
		final String id = "12345";
		final RetentionPolicyStatus status = RetentionPolicyStatus.active;

		final JsonObject fakeJSONResponse = new JsonObject()
				.add("policy_name", name)
				.add("policy_type", type.toString())
				.add("retention_length", retentionLength)
				.add("disposition_action", dispositionAction.toString())
				.add("id", id)
				.add("status", status.toString());

		BoxAPIConnection api = new BoxAPIConnection("");

		api.setRequestInterceptor(new RequestInterceptor() {

			@Override
			public BoxAPIResponse onRequest(BoxAPIRequest request) {
				return new BoxAPIResponse() {
					@Override
					public InputStream getBody() {
						return new ByteArrayInputStream(fakeJSONResponse.toString().getBytes());
					}
				};
			}
		});

		RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(api, name, type, retentionLength, dispositionAction);

		RetentionPolicy.Info getIndependentResponse = RetentionPolicy.getRetentionPolicy(api, response.getId());

		assertThat(response, is(getIndependentResponse));
	}

	@Test
	@Category(UnitTest.class)
	public void createRetentionPolicyAssignmentApplyToEmptyFolder() throws Exception {

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

				return new BoxAPIResponse() {
					@Override
					public InputStream getBody() {
						return new ByteArrayInputStream(fakeJSONResponse.toString().getBytes());
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
	public void createsIndefiniteRetentionPolicySuccessfully() throws Exception {
		final String expectedName = UUID.randomUUID().toString();
		BoxAPIConnection api = new BoxAPIConnection(getAccessToken());

		RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(api, expectedName,
				RetentionPolicyType.indefinite, RetentionPolicyDispositionAction.remove_retention);

		assertThat(response.getId(), not(isNull()));
		assertThat(response.getPolicyName(), is(expectedName));
		assertThat(response.getDispositionAction(), is(RetentionPolicyDispositionAction.remove_retention));
		assertThat(response.getType(), is(RetentionPolicyType.indefinite));
		assertThat(response.getStatus(), is(RetentionPolicyStatus.active));
		assertThat(response.getRetentionLength(), is("indefinite"));
	}

	@Test
	@Category(IntegrationTest.class)
	public void createsDefiniteRetentionPolicySuccessfully() throws Exception {
		final String expectedName = UUID.randomUUID().toString();
		BoxAPIConnection api = new BoxAPIConnection(getAccessToken());

		RetentionPolicy.Info response = RetentionPolicy.createRetentionPolicy(api, expectedName,
				RetentionPolicyType.finite, "30", RetentionPolicyDispositionAction.remove_retention);

		assertThat(response.getId(), not(isNull()));
		assertThat(response.getPolicyName(), is(expectedName));
		assertThat(response.getDispositionAction(), is(RetentionPolicyDispositionAction.remove_retention));
		assertThat(response.getType(), is(RetentionPolicyType.finite));
		assertThat(response.getStatus(), is(RetentionPolicyStatus.active));
		assertThat(response.getRetentionLength(), is("30"));
	}

	@Test
	@Category(IntegrationTest.class)
	public void createIndefiniteRetentionPolicyAndRetreiveIndependently() throws Exception {
		final String expectedName = UUID.randomUUID().toString();
		BoxAPIConnection api = new BoxAPIConnection(getAccessToken());

		RetentionPolicy.Info createResponse = RetentionPolicy.createRetentionPolicy(api, expectedName,
				RetentionPolicyType.indefinite, RetentionPolicyDispositionAction.remove_retention);

		RetentionPolicy.Info getResponse = RetentionPolicy.getRetentionPolicy(api, createResponse.getId());

		assertThat(getResponse, is(createResponse));

	}

}

