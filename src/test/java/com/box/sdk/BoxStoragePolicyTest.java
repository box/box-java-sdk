package com.box.sdk;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.eclipsesource.json.ParseException;
import com.github.tomakehurst.wiremock.common.Json;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

import javax.swing.*;
import java.util.Iterator;

/**
 * {@link BoxStoragePolicy} related tests.
 */
public class BoxStoragePolicyTest {

	@Test
	@Category(UnitTest.class)
	public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
		final String storagePolicyID = "1234";
		final String storagePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
		                + "    \"type\": \"storage_policy\",\n"
		                + "    \"id\": \"1234\",\n"
						+ "    \"name\": \"AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews\"\n"
		                + "}");

		BoxAPIConnection api = new BoxAPIConnection("");
		api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

		BoxStoragePolicy storagePolicy = new BoxStoragePolicy(api, storagePolicyID);
		BoxStoragePolicy.Info storagePolicyInfo = storagePolicy.getInfo();
		Assert.assertEquals(storagePolicyID, storagePolicyInfo.getID());
		Assert.assertEquals(storagePolicyName, storagePolicyInfo.getStoragePolicyName());
	}

	@Test
	@Category(UnitTest.class)
	public void testGetStoragePoliciesParseAllFieldsCorrectly() throws ParseException {
		final String storagePolicyType = "storage_policy";
		final String firstStoragePolicyID = "1234";
		final String firstStoragePolicyName = "AWS Montreal / AWS Dublin";
		final String secondStoragePolicyID = "5678";
		final String secondStoragePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

		final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
          + "    \"limit\": 1000,\n"
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

		BoxAPIConnection api = new BoxAPIConnection("");
		api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

		Iterator<BoxStoragePolicy.Info> storagePolcies = BoxStoragePolicy.getAll(api).iterator();
		BoxStoragePolicy.Info firstStoragePolicyInfo = storagePolcies.next();
		Assert.assertEquals(firstStoragePolicyID, firstStoragePolicyInfo.getID());
		Assert.assertEquals(firstStoragePolicyName, firstStoragePolicyInfo.getStoragePolicyName());

		BoxStoragePolicy.Info secondStoragePolicyInfo = storagePolcies.next();
		Assert.assertEquals(secondStoragePolicyID, secondStoragePolicyInfo.getID());
		Assert.assertEquals(secondStoragePolicyName, secondStoragePolicyInfo.getStoragePolicyName());
	}
}
