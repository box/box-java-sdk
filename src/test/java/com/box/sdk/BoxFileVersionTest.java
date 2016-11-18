package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxFileVersion} related tests.
 */
public class BoxFileVersionTest {

    /**
     * Unit test for {@link BoxFileVersion#delete()}
     */
    @Test
    @Category(UnitTest.class)
    public void testDeleteSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/files/0/versions/1",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, "0");
        BoxFileVersion version = new BoxFileVersion(api, new JsonObject().add("id", "1"), file.getID());
        version.delete();
    }

    /**
     * Unit test for {@link BoxFileVersion#promote()}
     */
    @Test
    @Category(UnitTest.class)
    public void testPromoteSendsCorrectJSON() {
        final String type = "file_version";
        final String id = "1";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/files/0/versions/current", request.getUrl().toString());
                Assert.assertEquals(type, json.get("type").asString());
                Assert.assertEquals(id, json.get("id").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxFileVersion version = new BoxFileVersion(api, new JsonObject().add("id", "1"), "0");
        version.promote();
    }

    /**
     * Unit test for {@link BoxFileVersion#promote()}
     */
    @Test
    @Category(UnitTest.class)
    public void testPromoteParseAllFieldsCorrectly() throws ParseException {
        final String id = "871399";
        final String sha1 = "12039d6dd9a7e6eefc78846802e";
        final String name = "Stark Family Lineage.doc";
        final long size = 11;
        final Date createdAt = BoxDateFormat.parse("2013-11-20T13:20:50-08:00");
        final Date modifiedAt = BoxDateFormat.parse("2013-11-20T13:26:48-08:00");
        final String modifiedById = "13711334";
        final String modifiedByName = "Eddard Stark";
        final String modifiedByLogin = "ned@winterfell.com";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"file_version\",\n"
                + "    \"id\": \"871399\",\n"
                + "    \"sha1\": \"12039d6dd9a7e6eefc78846802e\",\n"
                + "    \"name\": \"Stark Family Lineage.doc\",\n"
                + "    \"size\": 11,\n"
                + "    \"created_at\": \"2013-11-20T13:20:50-08:00\",\n"
                + "    \"modified_at\": \"2013-11-20T13:26:48-08:00\",\n"
                + "    \"modified_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"13711334\",\n"
                + "        \"name\": \"Eddard Stark\",\n"
                + "        \"login\": \"ned@winterfell.com\"\n"
                + "    }\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxFileVersion version = new BoxFileVersion(api, new JsonObject().add("id", id), "0");
        version.promote();
        Assert.assertEquals(id, version.getID());
        Assert.assertEquals(sha1, version.getSha1());
        Assert.assertEquals(name, version.getName());
        Assert.assertEquals(size, version.getSize());
        Assert.assertEquals(createdAt, version.getCreatedAt());
        Assert.assertEquals(modifiedAt, version.getModifiedAt());
        Assert.assertEquals(modifiedById, version.getModifiedBy().getID());
        Assert.assertEquals(modifiedByName, version.getModifiedBy().getName());
        Assert.assertEquals(modifiedByLogin, version.getModifiedBy().getLogin());

    }
}
