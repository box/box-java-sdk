package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.eclipsesource.json.ParseException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import sun.tools.jconsole.Plotter;

public class BoxCollectionTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);

    @Test
    @Category(UnitTest.class)
    public void testGetItemsRequestCorrectFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:53620/");

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/collections/0/items/"))
                .withQueryParam("fields", WireMock.containing("name"))
                .withQueryParam("fields", WireMock.containing("description"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}")));

        BoxCollection collection = new BoxCollection(api, "0");
        collection.getItems("name", "description");
    }

    @Test
    @Category(UnitTest.class)
    public void testGetItemsRangeRequestsCorrectOffsetLimitAndFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:53620/");

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/collections/0/items/"))
                .withQueryParam("offset", WireMock.equalTo("0"))
                .withQueryParam("limit", WireMock.equalTo("2"))
                .withQueryParam("fields", WireMock.containing("name"))
                .withQueryParam("fields", WireMock.containing("description"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"total_count\": 3, \"entries\":[]}")));

        BoxCollection collection = new BoxCollection(api, "0");
        PartialCollection<BoxItem.Info> items = collection.getItemsRange(0, 2, "name", "description");
        Assert.assertEquals(3L, items.fullSize());
        Assert.assertEquals(0, items.offset());
        Assert.assertEquals(2L, items.limit());
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllCollectionsReturnsFavorites() {
        BoxAPIConnection api = TestConfig.getAPIConnection();
        ArrayList<BoxCollection.Info> collectionList = new ArrayList<BoxCollection.Info>();
        for (BoxCollection.Info info : BoxCollection.getAllCollections(api)) {
            collectionList.add(info);
        }

        assertThat(collectionList.size(), is(equalTo(1)));
        BoxCollection.Info firstCollection = collectionList.get(0);
        assertThat(firstCollection.getName(), is(equalTo("Favorites")));
        assertThat(firstCollection.getCollectionType(), is(equalTo("favorites")));
    }

    @Test
    @Category(UnitTest.class)
    public void addItemToCollectionSucceeds() throws IOException{
        String result = "";
        String collectionsResults = "";
        final String folderId = "12345";
        final String addItemURL = "/folders/" + folderId + "?fields=type%2Cid%2Csequence_id%2Cetag%2Cname%2Ccreated_at%2Cmodified_at%2Cdescription%2Csize%2Cpath_collection%2Ccreated_by%2Cmodified_by%2Ctrashed_at%2Cpurged_at%2Ccontent_created_at%2Ccontent_modified_at%2Cowned_by%2Cshared_link%2Cfolder_upload_email%2Cparent%2Citem_status%2Citem_collection%2Csync_state%2Chas_collaborations%2Cpermissions%2Ctags%2Ccan_non_owners_invite%2Ccollections%2Cwatermark_info%2Cmetadata";
        final String collectionURL = "/collections/?limit=100&offset=0";
        BoxCollection favorites = null;

        collectionsResults = TestConfig.getFixture("BoxCollection/GetCollections200");

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(collectionURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(collectionsResults)));

        result = TestConfig.getFixture("BoxCollection/AddItemToCollection200");

        WireMock.stubFor(WireMock.put(WireMock.urlEqualTo(addItemURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxAPIConnection api = TestConfig.getAPIConnection();
        Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(api);

        for (BoxCollection.Info info : collections) {
            favorites = info.getResource();
        }

        BoxFolder folder = new BoxFolder(api, "12345");
        BoxFolder.Info folderInfo = folder.setCollections(favorites);

        assertThat(favorites, is(notNullValue()));
        Assert.assertEquals("Ball Valve Diagram", folderInfo.getName());
        Assert.assertEquals(75256, folderInfo.getSize());
        Assert.assertEquals("12345", folderInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void getCollectionSucceeds() throws IOException{
        final String collectionURL = "/collections/?limit=100&offset=0";
        String result = "";

        result = TestConfig.getFixture("BoxCollection/GetCollections200");

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(collectionURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxAPIConnection api = TestConfig.getAPIConnection();
        Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(api);

        BoxCollection.Info favorites = null;
        for (BoxCollection.Info info : collections) {
            favorites = info;
        }

        assertThat(favorites, is(notNullValue()));
        Assert.assertEquals("Favorites", favorites.getName());
        Assert.assertEquals("123456", favorites.getID());
        Assert.assertEquals("favorites", favorites.getCollectionType());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetItemsParsesFieldsCorrectly() throws IOException{
        String result = "";
        final String collectionID = "12345";
        final String collectionItemsURL = "/collections/12345/items/";
        final String collectionName = "Simple Contract Final.pdf";

        result = TestConfig.getFixture("BoxCollection/GetCollectionItems200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(collectionItemsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxAPIConnection api = TestConfig.getAPIConnection();

        BoxCollection collection = new BoxCollection(api, collectionID);
        Iterator<BoxItem.Info> iterator = collection.getItems().iterator();
        BoxItem.Info info = iterator.next();

        Assert.assertEquals(collectionID, info.getID());
        Assert.assertEquals(collectionName, info.getName());
    }
    
}
