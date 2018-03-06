package com.box.sdk;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;


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
	public void CreateAssignmentParseAllFieldsCorrectly() {
		final String storagePolicyAssignmentID = "user_1111";
		final String storagePolicyAssignedToType = "user";
		final String storagePolicyAssignedToID = "5678";
		final String storagePolicyType = "storage_policy";
		final String storagePolicyID = "1234";


		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
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

		BoxAPIConnection api = new BoxAPIConnection("");
		api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

		BoxStoragePolicyAssignment.Info  storagePolicyAssignmentInfo = BoxStoragePolicyAssignment.create(api,
				storagePolicyID, storagePolicyAssignedToID);

		Assert.assertEquals(storagePolicyAssignedToID, storagePolicyAssignmentInfo.getAssignedToID());
		Assert.assertEquals(storagePolicyAssignedToType, storagePolicyAssignmentInfo.getAssignedToType());
		Assert.assertEquals(storagePolicyAssignmentID, storagePolicyAssignmentInfo.getID());

		Assert.assertEquals(storagePolicyType, storagePolicyAssignmentInfo.getStoragePolicyType());
		Assert.assertEquals(storagePolicyID, storagePolicyAssignmentInfo.getStoragePolicyID());
	}

	@Test
	@Category(UnitTest.class)
	public void GetStorageAssignmentInfoParseAllFieldsCorrectly() {
		final String assignmentType = "storage_policy_assignment";
		final String assignmentID = "user_1111";
		final String storagePolicyType = "storage_policy";
		final String storagePolicyID = "1234";
		final String assignedToType = "user";
		final String assignedToID = "5678";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
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
          + "    ],\n"
          + "    \"next_marker\": null,\n"
          + "    \"limit\": 1000\n"
          + "}");

		BoxAPIConnection api = new BoxAPIConnection("");
		api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

		Iterator<BoxStoragePolicyAssignment.Info> assignmentIterator =
				BoxStoragePolicyAssignment.getAssignmentForTarget(api, assignedToType, assignedToID).iterator();
		BoxStoragePolicyAssignment.Info firstAssignmentInfo = assignmentIterator.next();
		Assert.assertEquals(assignmentID, firstAssignmentInfo.getID());
		Assert.assertEquals(storagePolicyType, firstAssignmentInfo.getStoragePolicyType());
		Assert.assertEquals(storagePolicyID, firstAssignmentInfo.getStoragePolicyID());
		Assert.assertEquals(storagePolicyID, firstAssignmentInfo.getStoragePolicyID());
		Assert.assertEquals(storagePolicyType, firstAssignmentInfo.getStoragePolicyType());
	}

	@Test
	@Category(UnitTest.class)
	public void GetStorageAssignmentInfoWithIDParseAllFieldsCorrectly() {
		final String assignmentType = "storage_policy_assignment";
		final String assignmentID = "user_1111";
		final String assignedToType = "user";
		final String assignedToID = "5678";
		final String storagePolicyID = "1234";
		final String storagePolicyType = "storage_policy";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
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

		BoxAPIConnection api = new BoxAPIConnection("");
		api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

		BoxStoragePolicyAssignment storagePolicyAssignment = new BoxStoragePolicyAssignment(api, assignmentID);
		BoxStoragePolicyAssignment.Info assignmentInfo = storagePolicyAssignment.getInfo();
		Assert.assertEquals(assignmentID, assignmentInfo.getID());
		Assert.assertEquals(assignedToType, assignmentInfo.getAssignedToType());
		Assert.assertEquals(assignedToID, assignmentInfo.getAssignedToID());
		Assert.assertEquals(storagePolicyID, assignmentInfo.getStoragePolicyID());
		Assert.assertEquals(storagePolicyType, assignmentInfo.getStoragePolicyType());
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
		final String assignmentID = "user_1111";
		final String assignedToType = "user";
		final String assignedToID = "7777";
		final String storagePolicyType = "storage_policy";
		final String storagePolicyID = "8888";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
          + "    \"type\": \"storage_policy_assignment\",\n"
          + "    \"id\": \"user_1111\",\n"
          + "    \"assigned_to\": {\n"
          + "        \"type\": \"user\",\n"
          + "        \"id\": \"7777\"\n"
          + "    },\n"
          + "    \"storage_policy\": {\n"
          + "        \"type\": \"storage_policy\",\n"
          + "        \"id\": \"8888\"\n"
          + "    }\n"
          + "}");

		BoxAPIConnection api = new BoxAPIConnection("");
		api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
		BoxStoragePolicyAssignment storagePolicyAssignment = new BoxStoragePolicyAssignment(api, assignmentID);
		BoxStoragePolicyAssignment.Info info = storagePolicyAssignment.new Info();
	}

}
