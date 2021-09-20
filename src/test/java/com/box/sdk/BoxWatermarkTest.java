package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxWatermark} related tests.
 */
public class BoxWatermarkTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testCreateWatermarkOnFolderSucceedsSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String watermarkURL = "/folders/" + folderID + "/watermark";
        JsonObject innerObject = new JsonObject()
            .add("imprint", "default");
        JsonObject watermarkObject = new JsonObject()
            .add("watermark", innerObject);

        String result = TestConfig.getFixture("BoxWatermark/CreateWatermarkOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(watermarkURL))
            .withRequestBody(WireMock.equalToJson(watermarkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxWatermark watermark = folder.applyWatermark();

        Assert.assertNotNull(watermark);
        Assert.assertNotNull(watermark.getCreatedAt());
        Assert.assertNotNull(watermark.getModifiedAt());
    }

    @Test
    public void testGetWatermarkOnFolderSucceeds() throws IOException {
        final String folderID = "12345";
        final String watermarkURL = "/folders/" + folderID + "/watermark";

        String result = TestConfig.getFixture("BoxWatermark/CreateWatermarkOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(watermarkURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxWatermark watermark = folder.getWatermark();

        Assert.assertNotNull(watermark);
        Assert.assertNotNull(watermark.getCreatedAt());
        Assert.assertNotNull(watermark.getModifiedAt());
    }

    @Test
    public void testDeleteWaterOnFolderSucceeds() {
        final String folderID = "12345";
        final String deleteWatermarkURL = "/folders/" + folderID + "/watermark";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteWatermarkURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.removeWatermark();
    }

    @Test
    public void testCreateWatermarkOnFileSucceedsAndSendsCorrectJson() throws IOException {
        final String fileID = "12345";
        final String fileWatermarkURL = "/files/" + fileID + "/watermark";
        JsonObject innerObject = new JsonObject()
            .add("imprint", "default");
        JsonObject watermarkObject = new JsonObject()
            .add("watermark", innerObject);

        String result = TestConfig.getFixture("BoxWatermark/CreateWatermarkOnFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileWatermarkURL))
            .withRequestBody(WireMock.equalToJson(watermarkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxWatermark watermark = file.applyWatermark();

        Assert.assertNotNull(watermark);
        Assert.assertNotNull(watermark.getCreatedAt());
        Assert.assertNotNull(watermark.getModifiedAt());
    }

    @Test
    public void testGetWatermarkOnFileSucceeds() throws IOException {
        final String fileID = "12345";
        final String watermarkURL = "/files/" + fileID + "/watermark";

        String result = TestConfig.getFixture("BoxWatermark/CreateWatermarkOnFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(watermarkURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxWatermark watermark = file.getWatermark();

        Assert.assertNotNull(watermark);
        Assert.assertNotNull(watermark.getCreatedAt());
        Assert.assertNotNull(watermark.getModifiedAt());
    }

    @Test
    public void testDeleteWaterOnFileSucceeds() {
        final String fileID = "12345";
        final String deleteWatermarkURL = "/files/" + fileID + "/watermark";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteWatermarkURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.removeWatermark();
    }

}
