package com.box.sdk;


import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxFileRequest} related integration and unit tests.
 */
public class BoxFileRequestTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = new BoxAPIConnection("");

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void getFileRequestSucceeds() throws IOException {
        final String fileRequestID = "42037322";
        final String fileRequestURL = "/2.0/file_requests/" + fileRequestID;

        String result = TestUtils.getFixture("BoxFileRequest/GetFileRequest200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileRequestURL))
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
        final String fileRequestURL = "/2.0/file_requests/" + fileRequestID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(fileRequestURL))
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
        final String fileRequestURL = "/2.0/file_requests/" + fileRequestID + "/copy";

        JsonObject folderBody = new JsonObject()
            .add("id", folderID)
            .add("type", "folder");

        JsonObject body = new JsonObject()
            .add("description", description)
            .add("is_description_required", true)
            .add("status", status.toJSONString())
            .add("folder", folderBody);

        String result = TestUtils.getFixture("BoxFileRequest/CopyFileRequest200");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileRequestURL))
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
        final String fileRequestURL = "/2.0/file_requests/" + fileRequestID;

        JsonObject body = new JsonObject()
            .add("description", description)
            .add("is_description_required", true)
            .add("status", status.toJSONString());

        String result = TestUtils.getFixture("BoxFileRequest/UpdateFileRequest200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileRequestURL))
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

