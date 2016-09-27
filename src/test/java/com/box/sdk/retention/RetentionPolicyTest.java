package com.box.sdk.retention;

import com.box.sdk.*;
import com.eclipsesource.json.JsonObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.MalformedURLException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.isNull;
import static org.mockito.Matchers.notNull;

public class RetentionPolicyTest {

	@Test
	@Category(UnitTest.class)
	public void createsIndefiniteRetentionPolicy() throws Exception {
		RetentionPolicy retentionPolicy =
				new RetentionPolicy("policy_name", RetentionPolicyType.INDEFINITE, RetentionPolicyDispositionAction.REMOVE_RETENTION);

		assertThat(retentionPolicy, not(isNull()));
	}


	@Test
	@Category(UnitTest.class)
	public void createIndefiniteRetentionPolicySendsJSONWithNameTypeAndDispositionAction() throws Exception {
		final String name = "non-empty name";
		final RetentionPolicyType type = RetentionPolicyType.INDEFINITE;
		final RetentionPolicyDispositionAction dispositionAction = RetentionPolicyDispositionAction.REMOVE_RETENTION;

		final JsonObject fakeJSONResponse = new JsonObject()
				.add("policy_name", name)
				.add("policy_type", type.toString())
				.add("disposition_action", dispositionAction.toString());

		BoxAPIConnection api = new BoxAPIConnection("");

		api.setRequestInterceptor(new JSONRequestInterceptor() {
			@Override
			protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
				assertEquals(name, json.get("policy_name").asString());
				assertEquals(type.toString(), json.get("policy_type").asString());
				assertEquals(dispositionAction.toString(), json.get("disposition_action").asString());

				return new BoxJSONResponse() {
					@Override
					public String getJSON() {
						return fakeJSONResponse.toString();
					}
				};
			}
		});

		RetentionPolicy.createRetentionPolicy(api, name, type, dispositionAction);
	}

	@Test
	@Category(UnitTest.class)
	public void createFiniteRetentionPolicySendsJSONWithNameTypeDispositionActionAndLength() throws Exception {
		final String name = "non-empty name";
		final RetentionPolicyType type = RetentionPolicyType.INDEFINITE;
		final Integer retentionLength = 30;
		final RetentionPolicyDispositionAction dispositionAction = RetentionPolicyDispositionAction.REMOVE_RETENTION;

		final JsonObject fakeJSONResponse = new JsonObject()
				.add("policy_name", name)
				.add("policy_type", type.toString())
				.add("retention_length", retentionLength)
				.add("disposition_action", dispositionAction.toString());

		BoxAPIConnection api = new BoxAPIConnection("");

		api.setRequestInterceptor(new JSONRequestInterceptor() {
			@Override
			protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
				assertEquals(name, json.get("policy_name").asString());
				assertEquals(type.toString(), json.get("policy_type").asString());
				assertEquals(retentionLength.toString(), json.get("retention_length").asString());
				assertEquals(dispositionAction.toString(), json.get("disposition_action").asString());

				return new BoxJSONResponse() {
					@Override
					public String getJSON() {
						return fakeJSONResponse.toString();
					}
				};
			}
		});

		RetentionPolicy.createRetentionPolicy(api, name, type, retentionLength, dispositionAction);
	}

}

