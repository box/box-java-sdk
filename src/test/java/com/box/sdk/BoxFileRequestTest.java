package com.box.sdk;


import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxFileRequest} related integration and unit tests.
 */
public class BoxFileRequestTest {
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void getFileRequestSucceeds() throws IOException {
        final String fileRequestID = "42037322";
        final String fileRequestURL = "/file_requests/" + fileRequestID;

        String result = TestConfig.getFixture("BoxFileRequest/GetFileRequest200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileRequestURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFileRequest fileRequest = new BoxFileRequest(this.api, fileRequestID);
        BoxFileRequest.Info fileRequestInfo = fileRequest.getInfo();

        assertEquals("Following documents are requested for your process", fileRequestInfo.getDescription());
        assertEquals("Please upload documents", fileRequestInfo.getTitle());
        assertEquals(this.api.getBaseAppUrl()
            + fileRequestInfo.getPath(), fileRequestInfo.getUrl().toString());
    }

    @Test
    public void deleteFileRequestSucceeds() {
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
    public void copyFileRequestSucceeds() throws IOException {
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

        String result = TestConfig.getFixture("BoxFileRequest/CopyFileRequest200");

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

        assertEquals(fileRequestInfo.getDescription(), description);
        assertEquals(fileRequestInfo.getIsDescriptionRequired(), true);
        assertEquals(fileRequestInfo.getStatus(), status);
    }

    @Test
    public void updateFileRequestSucceeds() throws IOException {
        final String fileRequestID = "42037322";
        final String description = "Following documents are requested for your process";
        final Boolean isDescriptionRequired = true;
        final BoxFileRequest.Status status = BoxFileRequest.Status.ACTIVE;
        final String fileRequestURL = "/file_requests/" + fileRequestID;

        JsonObject body = new JsonObject()
            .add("description", description)
            .add("is_description_required", true)
            .add("status", status.toJSONString());

        String result = TestConfig.getFixture("BoxFileRequest/UpdateFileRequest200");

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

        assertEquals(fileRequestInfo.getDescription(), description);
        assertEquals(fileRequestInfo.getIsDescriptionRequired(), true);
        assertEquals(fileRequestInfo.getStatus(), status);
    }
}

