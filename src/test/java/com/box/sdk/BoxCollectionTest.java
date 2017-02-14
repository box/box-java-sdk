package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoxCollectionTest {

    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    @Category(UnitTest.class)
    public void testGetCollectionsParsesAllFieldsCorrectly() {
        final String id = "405151";
        final String name = "Favorites";
        final String collectionType = "favorites";

        JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"total_count\": 1,\n"
                + "    \"entries\": [\n"
                + "        {\n"
                + "            \"type\": \"collection\",\n"
                + "            \"id\": \"405151\",\n"
                + "            \"name\": \"Favorites\",\n"
                + "            \"collection_type\": \"favorites\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"limit\": 100,\n"
                + "    \"offset\": 0\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxCollection.Info> iterator = BoxCollection.getAllCollections(api).iterator();
        BoxCollection.Info collection = iterator.next();
        Assert.assertEquals(id, collection.getID());
        Assert.assertEquals(name, collection.getName());
        Assert.assertEquals(collectionType, collection.getCollectionType());
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetItemsParsesFieldsCorrectly() {
        final String idFirst = "192429928";
        final String sequenceIDFirst = "1";
        final String etagFirst = "1";
        final String nameFirst = "Stephen Curry Three Pointers";
        final String idSecond = "818853862";
        final String sequenceIDSecond = "0";
        final String etagSecond = "0";
        final String nameSecond = "Warriors.jpg";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/collections/0/items/"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                                + "    \"total_count\": 24,\n"
                                + "    \"entries\": [\n"
                                + "        {\n"
                                + "            \"type\": \"folder\",\n"
                                + "            \"id\": \"192429928\",\n"
                                + "            \"sequence_id\": \"1\",\n"
                                + "            \"etag\": \"1\",\n"
                                + "            \"name\": \"Stephen Curry Three Pointers\"\n"
                                + "        },\n"
                                + "        {\n"
                                + "            \"type\": \"file\",\n"
                                + "            \"id\": \"818853862\",\n"
                                + "            \"sequence_id\": \"0\",\n"
                                + "            \"etag\": \"0\",\n"
                                + "            \"name\": \"Warriors.jpg\"\n"
                                + "        }\n"
                                + "    ],\n"
                                + "    \"offset\": 0,\n"
                                + "    \"limit\": 2\n"
                                + "}")));

        BoxCollection collection = new BoxCollection(api, "0");
        Iterator<BoxItem.Info> iterator = collection.getItems().iterator();
        BoxItem.Info info = iterator.next();
        Assert.assertEquals(idFirst, info.getID());
        Assert.assertEquals(nameFirst, info.getName());
        Assert.assertEquals(sequenceIDFirst, info.getSequenceID());
        Assert.assertEquals(etagFirst, info.getEtag());
        info = iterator.next();
        Assert.assertEquals(idSecond, info.getID());
        Assert.assertEquals(nameSecond, info.getName());
        Assert.assertEquals(sequenceIDSecond, info.getSequenceID());
        Assert.assertEquals(etagSecond, info.getEtag());
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetItemsRequestCorrectFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

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
        api.setBaseURL("http://localhost:8080/");

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
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
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
    @Category(IntegrationTest.class)
    public void getCollectionItemsSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getCollectionItemsSucceeds] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);
        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        BoxCollection favorites = null;
        for (BoxCollection.Info info : BoxCollection.getAllCollections(api)) {
            if (info.getCollectionType().equals("favorites")) {
                favorites = info.getResource();
                break;
            }
        }
        assertThat(favorites, is(notNullValue()));

        uploadedFile.setCollections(favorites);
        assertThat(favorites, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
        uploadedFile.delete();
    }
}
