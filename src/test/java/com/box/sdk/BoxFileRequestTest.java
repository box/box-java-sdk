package com.box.sdk;


import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {@link BoxFileRequest} related integration and unit tests.
 */
public class BoxFileRequestTest {
	@ClassRule
	public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
	private BoxAPIConnection api = TestConfig.getAPIConnection();

	@Test
	@Category(UnitTest.class)
	public void getFileRequestSucceeds() throws IOException {
		String result = "";
		final String fileRequestID = "42037322";
		final String fileRequestURL = "/file_requests/" + fileRequestID;

		result = TestConfig.getFixture("BoxFileRequest/GetFileRequest200");

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileRequestURL))
			.willReturn(WireMock.aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(result)));

		BoxFileRequest fileRequest = new BoxFileRequest(this.api, fileRequestID);
		BoxFileRequest.Info fileRequestInfo = fileRequest.getInfo();

		Assert.assertEquals("Following documents are requested for your process", fileRequestInfo.getDescription());
		Assert.assertEquals("Please upload documents", fileRequestInfo.getTitle());
	}

	@Test
	@Category(UnitTest.class)
	public void deleteFileRequestSucceeds() throws IOException {
		String result = "";
		final String fileRequestID = "42037322";
		final String fileRequestURL = "/file_requests/" + fileRequestID;

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(fileRequestURL))
			.willReturn(WireMock.aResponse()
				.withHeader("Content-Type", "application/json")
				.withStatus(204)));

		BoxFileRequest fileRequest = new BoxFileRequest(this.api, fileRequestID);
		fileRequest.delete();
	}

	@Test
	@Category(UnitTest.class)
	public void copyFileRequestSucceeds() throws IOException {
		String result = "";
		final String fileRequestID = "42037322";
		final String folderID = "12345";
		final String description = "Following documents are requested for your process";
		final Boolean isDescriptionRequired = true;
		final BoxFileRequest.Status status = BoxFileRequest.Status.ACTIVE;
		final String fileRequestURL = "/file_requests/" + fileRequestID + "/copy";

		JsonObject folderBody = new JsonObject()
			.add("id", folderID)
			.add("type", "folder");

		JsonObject body = new JsonObject()
			.add("description", description)
			.add("is_description_required", true)
			.add("status", status.toJSONString())
			.add("folder", folderBody);

		result = TestConfig.getFixture("BoxFileRequest/CopyFileRequest200");

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileRequestURL))
			.withRequestBody(WireMock.equalToJson(body.toString()))
			.willReturn(WireMock.aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(result)));

		BoxFileRequest fileRequest = new BoxFileRequest(this.api, fileRequestID);
		BoxFileRequest.Info fileRequestInfo = fileRequest.new Info();
		fileRequestInfo.setDescription(description);
		fileRequestInfo.setIsDescriptionRequired(isDescriptionRequired);
		fileRequestInfo.setStatus(status);
		fileRequestInfo = fileRequest.copyInfo(fileRequestInfo, folderID);

		Assert.assertEquals(fileRequestInfo.getDescription(), description);
		Assert.assertEquals(fileRequestInfo.getIsDescriptionRequired(), true);
		Assert.assertEquals(fileRequestInfo.getStatus(), status);
	}

	@Test
	@Category(UnitTest.class)
	public void updateFileRequestSucceeds() throws IOException {
		String result = "";
		final String fileRequestID = "42037322";
		final String description = "Following documents are requested for your process";
		final Boolean isDescriptionRequired = true;
		final BoxFileRequest.Status status = BoxFileRequest.Status.ACTIVE;
		final String fileRequestURL = "/file_requests/" + fileRequestID;

		JsonObject body = new JsonObject()
			.add("description", description)
			.add("is_description_required", true)
			.add("status", status.toJSONString());

		result = TestConfig.getFixture("BoxFileRequest/UpdateFileRequest200");

		WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileRequestURL))
			.withRequestBody(WireMock.equalToJson(body.toString()))
			.willReturn(WireMock.aResponse()
				.withHeader("Content-Type", "application/json")
				.withBody(result)));

		BoxFileRequest fileRequest = new BoxFileRequest(this.api, fileRequestID);
		BoxFileRequest.Info fileRequestInfo = fileRequest.new Info();
		fileRequestInfo.setDescription(description);
		fileRequestInfo.setIsDescriptionRequired(isDescriptionRequired);
		fileRequestInfo.setStatus(status);
		fileRequestInfo = fileRequest.updateInfo(fileRequestInfo);

		Assert.assertEquals(fileRequestInfo.getDescription(), description);
		Assert.assertEquals(fileRequestInfo.getIsDescriptionRequired(), true);
		Assert.assertEquals(fileRequestInfo.getStatus(), status);
	}
}

