package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

/**
 * {@link BoxStoragePolicy} related tests.
 */
public class BoxStoragePolicyTest {

	/**
	 * Wiremock
	 */
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(53620);

	@Test
	@Category(UnitTest.class)
	public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
		BoxAPIConnection api = new BoxAPIConnection("");
  		api.setBaseURL("http://localhost:53620/");

		final String storagePolicyID = "1234";
		final String storagePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
		                + "    \"type\": \"storage_policy\",\n"
		                + "    \"id\": \"1234\",\n"
						+ "    \"name\": \"AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews\"\n"
		                + "}");

		stubFor(get(urlEqualTo("/storage_policies/"+storagePolicyID))
	  		.willReturn(aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(fakeJSONResponse.toString())));

		BoxStoragePolicy storagePolicy = new BoxStoragePolicy(api, storagePolicyID);
		BoxStoragePolicy.Info storagePolicyInfo = storagePolicy.getInfo();
		Assert.assertEquals(storagePolicyID, storagePolicyInfo.getID());
		Assert.assertEquals(storagePolicyName, storagePolicyInfo.getStoragePolicyName());
	}

	@Test
	@Category(UnitTest.class)
	public void testGetStoragePoliciesParseAllFieldsCorrectly() throws ParseException {
		BoxAPIConnection api = new BoxAPIConnection("");
  		api.setBaseURL("http://localhost:53620/");

		final String storagePolicyType = "storage_policy";
		final String firstStoragePolicyID = "1234";
		final String firstStoragePolicyName = "AWS Montreal / AWS Dublin";
		final String secondStoragePolicyID = "5678";
		final String secondStoragePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
          + "    \"limit\": 100,\n"
		  + "    \"next_marker\": null,\n"
          + "    \"entries\": [\n"
          + "        {\n"
          + "            \"type\": \"storage_policy\",\n"
          + "            \"id\": \"1234\",\n"
          + "            \"name\": \"AWS Montreal / AWS Dublin\"\n"
          + "        },\n"
          + "        {\n"
          + "            \"type\": \"storage_policy\",\n"
          + "            \"id\": \"5678\",\n"
          + "            \"name\": \"AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews\"\n"
          + "        }\n"
          + "    ]\n"
          + "}");

		stubFor(get(urlEqualTo("/storage_policies?limit=100"))
	  		.willReturn(aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(fakeJSONResponse.toString())));

		Iterator<BoxStoragePolicy.Info> storagePolcies = BoxStoragePolicy.getAll(api).iterator();
		BoxStoragePolicy.Info firstStoragePolicyInfo = storagePolcies.next();
		Assert.assertEquals(firstStoragePolicyID, firstStoragePolicyInfo.getID());
		Assert.assertEquals(firstStoragePolicyName, firstStoragePolicyInfo.getStoragePolicyName());

		BoxStoragePolicy.Info secondStoragePolicyInfo = storagePolcies.next();
		Assert.assertEquals(secondStoragePolicyID, secondStoragePolicyInfo.getID());
		Assert.assertEquals(secondStoragePolicyName, secondStoragePolicyInfo.getStoragePolicyName());
	}
}
