package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;


/**
 * {@link BoxStoragePolicyAssignment} related tests
 */
public class BoxStoragePolicyAssignmentTest {

	/**
	 * Wiremock
	 */
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(53620);

	@Test
	@Category(UnitTest.class)
	public void CreateAssignmentParseAllFieldsCorrectly() throws ParseException {
		BoxAPIConnection api = new BoxAPIConnection("");
  		api.setBaseURL("http://localhost:53620/");

		final String storagePolicyAssignmentID = "user_1111";
		final String storagePolicyAssignedToType = "user";
		final String storagePolicyAssignedToID = "5678";
		final String storagePolicyType = "storage_policy";
		final String storagePolicyID = "1234";

		JsonObject assignedToObject = new JsonObject()
			.add("type", storagePolicyAssignedToType)
			.add("id", storagePolicyAssignedToID);

		JsonObject storagePolicyObject = new JsonObject()
			.add("type", storagePolicyType)
			.add("id", storagePolicyID);

		JsonObject mockJSON = new JsonObject()
			.add("type", storagePolicyAssignedToType)
			.add("id", storagePolicyAssignmentID)
			.add("assigned_to", assignedToObject)
			.add("storage_policy", storagePolicyObject);

		stubFor(post(urlEqualTo("/storage_policy_assignments"))
			.willReturn(aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(mockJSON.toString())));

		BoxStoragePolicyAssignment.Info assignmentInfo =
				BoxStoragePolicyAssignment.create(api, storagePolicyID, storagePolicyAssignedToID);
		assertThat(assignmentInfo.getID(), is(equalTo(mockJSON.get("id").asString())));
		assertThat(assignmentInfo.getStoragePolicyID(),
				is(equalTo(mockJSON.get("storage_policy").asObject().get("id").asString())));
		assertThat(assignmentInfo.getAssignedToType(),
				is(equalTo(mockJSON.get("assigned_to").asObject().get("type").asString())));
	}

	@Test
	@Category(UnitTest.class)
	public void GetStorageAssignmentInfoParseAllFieldsCorrectly() {
		BoxAPIConnection api = new BoxAPIConnection("");
		api.setBaseURL("http://localhost:53620/");

		final String assignmentType = "storage_policy_assignment";
		final String assignmentID = "user_1111";
		final String storagePolicyType = "storage_policy";
		final String storagePolicyID = "1234";
		final String assignedToType = "user";
		final String assignedToID = "5678";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
          + "    \"limit\": 1000,\n"
		  + "    \"next_marker\": null,\n"
          + "    \"entries\": [\n"
          + "        {\n"
          + "            \"type\": \"storage_policy_assignment\",\n"
          + "            \"id\": \"user_1111\",\n"
		  + "            \"storage_policy\": {\n"
	      + "                \"type\": \"storage_policy\",\n"
		  + "                \"id\": \"1234\"\n"
		  + "            },\n"
		  + "            \"assigned_to\": {\n"
	      + "                \"type\": \"user\",\n"
		  + "                \"id\": \"5678\"\n"
		  + "            }\n"
          + "        }\n"
          + "    ]\n"
          + "}");

		stubFor(get(urlPathEqualTo("/storage_policy_assignments"))
			.withQueryParam("resolved_for_id", WireMock.equalTo(assignedToID))
			.withQueryParam("resolved_for_type", WireMock.equalTo(assignedToType))
			.willReturn(aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(fakeJSONResponse.toString())));

		BoxStoragePolicyAssignment.Info info = BoxStoragePolicyAssignment.getAssignmentForTarget(api,
				assignedToType, assignedToID);

		Assert.assertEquals(assignmentID, info.getID());
		Assert.assertEquals(storagePolicyType, info.getStoragePolicyType());
		Assert.assertEquals(storagePolicyID, info.getStoragePolicyID());
		Assert.assertEquals(storagePolicyID, info.getStoragePolicyID());
		Assert.assertEquals(storagePolicyType, info.getStoragePolicyType());
	}

	@Test
	@Category(UnitTest.class)
	public void GetStorageAssignmentInfoWithIDParseAllFieldsCorrectly() {
		BoxAPIConnection api = new BoxAPIConnection("");
  		api.setBaseURL("http://localhost:53620/");

		final String assignmentType = "storage_policy_assignment";
		final String assignmentID = "user_1111";
		final String assignedToType = "user";
		final String assignedToID = "5678";
		final String storagePolicyID = "1234";
		final String storagePolicyType = "storage_policy";

		JsonObject assignedToObject = new JsonObject()
			.add("type", assignedToType)
			.add("id", assignedToID);

		JsonObject storagePolicyObject = new JsonObject()
			.add("type", storagePolicyType)
			.add("id", storagePolicyID);

		JsonObject mockJSON = new JsonObject()
			.add("type", assignmentType)
			.add("id", assignmentID)
			.add("assigned_to", assignedToObject)
			.add("storage_policy", storagePolicyObject);

		stubFor(get(urlEqualTo("/storage_policy_assignments/" + assignmentID))
			.willReturn(aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(mockJSON.toString())));

		BoxStoragePolicyAssignment assignment = new BoxStoragePolicyAssignment(api, assignmentID);
		BoxStoragePolicyAssignment.Info assignmentInfo = assignment.getInfo();
		assertThat(assignmentInfo.getID(), is(equalTo(mockJSON.get("id").asString())));
		assertThat(assignmentInfo.getStoragePolicyID(),
				is(equalTo(mockJSON.get("storage_policy").asObject().get("id").asString())));
		assertThat(assignmentInfo.getAssignedToType(),
				is(equalTo(mockJSON.get("assigned_to").asObject().get("type").asString())));
	}

	@Test
	@Category(UnitTest.class)
	public void DeleteStorageAssignmentSendsCorrectRequest(){
		BoxAPIConnection api = new BoxAPIConnection("");
		api.setBaseURL("http://localhost:53620/");

		final String assignmentID = "user_1111";
		final String assignmentURL = "/storage_policy_assignments/" + assignmentID;

		stubFor(delete(urlEqualTo(assignmentURL))
			.willReturn(aResponse()
				.withStatus(204)));

		BoxStoragePolicyAssignment assignment = new BoxStoragePolicyAssignment(api, assignmentID);
		assignment.delete();

		verify(deleteRequestedFor(urlEqualTo(assignmentURL))
			.withRequestBody(WireMock.equalTo("")));
	}

	@Test
	@Category(UnitTest.class)
	public void UpdateStorageAssignmentInfoParseAllFieldsCorrectly() {
		BoxAPIConnection api = new BoxAPIConnection("");
  		api.setBaseURL("http://localhost:53620/");

		final String assignmentID = "user_1111";
		final String assignmentType = "storage_policy_assignment";
		final String assignedToType = "user";
		final String assignedToID = "7777";
		final String storagePolicyType = "storage_policy";
		final String storagePolicyID = "12345";

		JsonObject assignedToObject = new JsonObject()
			.add("type", assignedToType)
			.add("id", assignedToID);

		JsonObject storagePolicyObject = new JsonObject()
			.add("type", storagePolicyType)
			.add("id", storagePolicyID);

		JsonObject mockJSON = new JsonObject()
			.add("type", assignmentType)
			.add("id", assignmentID)
			.add("assigned_to", assignedToObject)
			.add("storage_policy", storagePolicyObject);

		stubFor(put(urlEqualTo("/storage_policy_assignments"))
			.willReturn(aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(mockJSON.toString())));

		BoxStoragePolicyAssignment storagePolicyAssignment = new BoxStoragePolicyAssignment(api, assignmentID);
		BoxStoragePolicyAssignment.Info info = storagePolicyAssignment.new Info();
		info.setStoragePolicyID(storagePolicyID);
		storagePolicyAssignment.updateInfo(info);

		assertThat(info.getID(), is(equalTo(mockJSON.get("id").asString())));
		assertThat(info.getStoragePolicyID(),
				is(equalTo(mockJSON.get("storage_policy").asObject().get("id").asString())));
	}

	@Test
	@Category(UnitTest.class)
	public void AssignStorageAssignmentInfoParseAllFieldsCorrectly() {
		BoxAPIConnection api = new BoxAPIConnection("");
		api.setBaseURL("http://localhost:53620/");

		final String assignmentID = "user_1111";
		final String assignedToType = "user";
		final String assignedToID = "5678";
		final String storagePolicyType = "storage_policy";
		final String storagePolicyID = "1234";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
          + "    \"limit\": 1000,\n"
		  + "    \"next_marker\": null,\n"
          + "    \"entries\": [\n"
          + "        {\n"
          + "            \"type\": \"storage_policy_assignment\",\n"
          + "            \"id\": \"user_1111\",\n"
		  + "            \"storage_policy\": {\n"
	      + "                \"type\": \"storage_policy\",\n"
		  + "                \"id\": \"1234\"\n"
		  + "            },\n"
		  + "            \"assigned_to\": {\n"
	      + "                \"type\": \"user\",\n"
		  + "                \"id\": \"5678\"\n"
		  + "            }\n"
          + "        }\n"
          + "    ]\n"
          + "}");

		stubFor(get(urlPathEqualTo("/storage_policy_assignments"))
			.withQueryParam("resolved_for_id", WireMock.equalTo(assignedToID))
			.withQueryParam("resolved_for_type", WireMock.equalTo(assignedToType))
			.willReturn(aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(fakeJSONResponse.toString())));

		api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
		BoxStoragePolicyAssignment.Info assignmentInfo = BoxStoragePolicyAssignment.assign(api, storagePolicyID, assignedToID);

		Assert.assertEquals(assignmentInfo.getStoragePolicyID(), storagePolicyID);
	}

	@Test
	@Category(UnitTest.class)
	public void AssignStorageAssignmentInfoIsEnterpriseParseAllFieldsCorrectly() {
		BoxAPIConnection api = new BoxAPIConnection("");
		api.setBaseURL("http://localhost:53620/");

		final String assignmentID = "user_1111";
		final String assignedToType = "enterprise";
		final String assignedToID = "5678";
		final String storagePolicyType = "storage_policy";
		final String storagePolicyID = "5555";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
          + "    \"limit\": 1000,\n"
		  + "    \"next_marker\": null,\n"
          + "    \"entries\": [\n"
          + "        {\n"
          + "            \"type\": \"storage_policy_assignment\",\n"
          + "            \"id\": \"user_1111\",\n"
		  + "            \"storage_policy\": {\n"
	      + "                \"type\": \"storage_policy\",\n"
		  + "                \"id\": \"1234\"\n"
		  + "            },\n"
		  + "            \"assigned_to\": {\n"
	      + "                \"type\": \"enterprise\",\n"
		  + "                \"id\": \"5678\"\n"
		  + "            }\n"
          + "        }\n"
          + "    ]\n"
          + "}");

		stubFor(get(urlPathEqualTo("/storage_policy_assignments"))
			.withQueryParam("resolved_for_id", WireMock.equalTo(assignedToID))
			.withQueryParam("resolved_for_type", WireMock.equalTo("user"))
			.willReturn(aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(fakeJSONResponse.toString())));

		final JsonObject fakeJSONResponseCreate = JsonObject.readFrom("{\n"
          + "    \"type\": \"storage_policy_assignment\",\n"
          + "    \"id\": \"user_1111\",\n"
          + "    \"assigned_to\": {\n"
          + "        \"type\": \"user\",\n"
          + "        \"id\": \"5678\"\n"
          + "    },\n"
          + "    \"storage_policy\": {\n"
          + "        \"type\": \"storage_policy\",\n"
          + "        \"id\": \"1234\"\n"
          + "    }\n"
          + "}");

		stubFor(post(urlPathEqualTo("/storage_policy_assignments"))
			.willReturn(aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(fakeJSONResponseCreate.toString())));

		BoxStoragePolicyAssignment.Info assignmentInfo = BoxStoragePolicyAssignment.assign(api, storagePolicyID, assignedToID);

		Assert.assertEquals(assignmentInfo.getAssignedToType(), "user");
	}

	@Test
	@Category(IntegrationTest.class)
	public void AssignStoragePolicySucceeds() {
		BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
		BoxStoragePolicyAssignment.assign(api, "", "");
	}
}
