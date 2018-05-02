package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

/**
 * {@link BoxWatermark} related tests.
 */
public class BoxWatermarkTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testCreateWatermarkOnFolderSucceedsSendsCorrectJson() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String watermarkURL = "/folders/" + folderID + "/watermark";
        JsonObject innerObject = new JsonObject()
                .add("imprint", "default");
        JsonObject watermarkObject = new JsonObject()
                .add("watermark", innerObject);

        result = TestConfig.getFixture("BoxWatermark/CreateWatermarkOnFolder200");

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
    @Category(UnitTest.class)
    public void testGetWatermarkOnFolderSucceeds() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String watermarkURL = "/folders/" + folderID + "/watermark";

        result = TestConfig.getFixture("BoxWatermark/CreateWatermarkOnFolder200");

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
    @Category(UnitTest.class)
    public void testDeleteWaterOnFolderSucceeds() throws IOException {
        String result = "";
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
    @Category(UnitTest.class)
    public void testCreateWatermarkOnFileSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String fileID = "12345";
        final String fileWatermarkURL = "/files/" + fileID + "/watermark";
        JsonObject innerObject = new JsonObject()
                .add("imprint", "default");
        JsonObject watermarkObject = new JsonObject()
                .add("watermark", innerObject);

        result = TestConfig.getFixture("BoxWatermark/CreateWatermarkOnFile200");

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
    @Category(UnitTest.class)
    public void testGetWatermarkOnFileSucceeds() throws IOException {
        String result = "";
        final String fileID = "12345";
        final String watermarkURL = "/files/" + fileID + "/watermark";

        result = TestConfig.getFixture("BoxWatermark/CreateWatermarkOnFile200");

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
    @Category(UnitTest.class)
    public void testDeleteWaterOnFileSucceeds() throws IOException {
        String result = "";
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
