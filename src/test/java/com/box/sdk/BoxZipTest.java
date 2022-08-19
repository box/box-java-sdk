package com.box.sdk;


import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxZip} related unit tests.
 */
public class BoxZipTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void createZipSucceeds() throws IOException, ParseException {
        final String fileID = "466239504569";
        final String folderID = "466239504580";
        final String downloadFileName = "test";

        final String downloadURL = "https://api.box.com/zip_downloads/124hfiowk3fa8kmrwh/content";
        final String statusURL = "https://api.box.com/zip_downloads/124hfiowk3fa8kmrwh/status";
        final Date expiresAt = BoxDateFormat.parse("2018-04-25T11:00:18-07:00");

        ArrayList<BoxZipItem> items = new ArrayList<>();
        JsonArray itemsBody = new JsonArray();
        BoxZipItem file = new BoxZipItem("file", fileID);
        BoxZipItem folder = new BoxZipItem("folder", folderID);
        items.add(file);
        items.add(folder);
        itemsBody.add(file.getJSONObject()).add(folder.getJSONObject());

        JsonObject body = new JsonObject()
            .add("items", itemsBody)
            .add("download_file_name", downloadFileName);

        String result = TestUtils.getFixture("BoxZip/CreateZipFile202");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/zip_downloads"))
            .withRequestBody(WireMock.equalToJson(body.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxZip zip = new BoxZip(this.api);
        BoxZipInfo zipInfo = zip.create(downloadFileName, items);
        BoxZipConflict conflict1 = zipInfo.getNameConflicts().get(0);
        List<BoxZipConflictItem> conflict1Items = conflict1.getItems();

        assertEquals(downloadURL, zipInfo.getDownloadURL().toString());
        assertEquals(statusURL, zipInfo.getStatusURL().toString());
        assertEquals(expiresAt, zipInfo.getExpiresAt());
        assertEquals("100", conflict1Items.get(0).getID());
        assertEquals("salary.pdf", conflict1Items.get(0).getOriginalName());
        assertEquals("aqc823.pdf", conflict1Items.get(0).getDownloadName());
        assertEquals("200", conflict1Items.get(1).getID());
        assertEquals("salary.pdf", conflict1Items.get(1).getOriginalName());
        assertEquals("aci23s.pdf", conflict1Items.get(1).getDownloadName());
    }
}

