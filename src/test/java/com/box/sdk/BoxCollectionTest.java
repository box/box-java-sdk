package com.box.sdk;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoxCollectionTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testGetItemsRequestCorrectFields() {

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo("/collections/0/items/"))
            .withQueryParam("fields", WireMock.containing("name"))
            .withQueryParam("fields", WireMock.containing("description"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{}")));

        BoxCollection collection = new BoxCollection(api, "0");
        collection.getItems("name", "description");
    }

    @Test
    public void testGetItemsRangeRequestsCorrectOffsetLimitAndFields() {

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo("/collections/0/items/"))
            .withQueryParam("offset", WireMock.equalTo("0"))
            .withQueryParam("limit", WireMock.equalTo("2"))
            .withQueryParam("fields", WireMock.containing("name"))
            .withQueryParam("fields", WireMock.containing("description"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"total_count\": 3, \"entries\":[]}")));

        BoxCollection collection = new BoxCollection(this.api, "0");
        PartialCollection<BoxItem.Info> items = collection.getItemsRange(0, 2, "name", "description");
        assertEquals(3L, items.fullSize());
        assertEquals(0, items.offset());
        assertEquals(2L, items.limit());
    }

    @Test
    public void addItemToCollectionSucceeds() throws IOException {
        final String folderId = "12345";
        final String addItemURL = "/folders/" + folderId + "?([a-z]*)";
        final String collectionURL = "/collections/?limit=100&offset=0";
        BoxCollection favorites = null;

        String collectionsResults = TestConfig.getFixture("BoxCollection/GetCollections200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlEqualTo(collectionURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(collectionsResults)));

        String result = TestConfig.getFixture("BoxCollection/AddItemToCollection200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathMatching(addItemURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(this.api);

        for (BoxCollection.Info info : collections) {
            favorites = info.getResource();
        }

        BoxFolder folder = new BoxFolder(this.api, "12345");
        BoxFolder.Info folderInfo = folder.setCollections(favorites);

        assertThat(favorites, is(notNullValue()));
        assertEquals("Ball Valve Diagram", folderInfo.getName());
        assertEquals(75256, folderInfo.getSize());
        assertEquals("12345", folderInfo.getID());
    }

    @Test
    public void getCollectionSucceeds() throws IOException {
        final String collectionURL = "/collections/?limit=100&offset=0";

        String result = TestConfig.getFixture("BoxCollection/GetCollections200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlEqualTo(collectionURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(this.api);

        BoxCollection.Info favorites = null;
        for (BoxCollection.Info info : collections) {
            favorites = info;
        }

        assertThat(favorites, is(notNullValue()));
        assertEquals("Favorites", favorites.getName());
        assertEquals("123456", favorites.getID());
        assertEquals("favorites", favorites.getCollectionType());
    }

    @Test
    public void testGetItemsParsesFieldsCorrectly() throws IOException {
        final String collectionID = "12345";
        final String collectionID2 = "123456";
        final String collectionItemsURL = "/collections/12345/items/";
        final String collectionName = "Simple Contract Final.pdf";
        final String collectionName2 = "google.com";

        String result = TestConfig.getFixture("BoxCollection/GetCollectionItems200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(collectionItemsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxCollection collection = new BoxCollection(this.api, collectionID);
        Iterator<BoxItem.Info> iterator = collection.getItems().iterator();
        BoxFile.Info info = (BoxFile.Info) iterator.next();
        BoxWebLink.Info info2 = (BoxWebLink.Info) iterator.next();

        assertEquals(collectionID, info.getID());
        assertEquals(collectionName, info.getName());
        assertEquals(collectionID2, info2.getID());
        assertEquals(collectionName2, info2.getName());
        assertNull(info2.getLinkURL());
    }
}
