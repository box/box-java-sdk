package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxWebHook} related tests.
 */
public class BoxWebHookTest {

    /**
     * Unit test for {@link BoxWebHook#create(BoxResource, URL, BoxWebHook.Trigger...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateSendsCorrectJson() throws MalformedURLException {
        final String targetID = "1";
        final String targetType = "folder";
        final String address = "http://box.com";
        final String trigger = "FILE.UPLOADED";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/webhooks",
                        request.getUrl().toString());
                Assert.assertEquals(targetID, json.get("target").asObject().get("id").asString());
                Assert.assertEquals(targetType, json.get("target").asObject().get("type").asString());
                Assert.assertEquals(address, json.get("address").asString());
                Assert.assertEquals(trigger, json.get("triggers").asArray().get(0).asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxWebHook.create(new BoxFolder(api, "1"), new URL(address), BoxWebHook.Trigger.FILE_UPLOADED);
    }

    /**
     * Unit test for {@link BoxWebHook#create(BoxResource, URL, BoxWebHook.Trigger...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateParseAllFieldsCorrectly() throws ParseException, MalformedURLException {
        final String id = "4165";
        final String targetID = "5016243669";
        final String targetType = "file";
        final String createdByID = "2030392653";
        final String createdByName = "John Q. Developer";
        final String createdByLogin = "johnq@dev.name";
        final Date createdAt = BoxDateFormat.parse("2016-05-09T17:41:27-07:00");
        final URL address = new URL("https://dev.name/actions/file_changed");
        final BoxWebHook.Trigger firstTrigger = BoxWebHook.Trigger.FILE_DOWNLOADED;
        final BoxWebHook.Trigger secondTrigger = BoxWebHook.Trigger.FILE_UPLOADED;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"id\": \"4165\",\n"
                + "  \"type\": \"webhook\",\n"
                + "  \"target\": {\n"
                + "    \"id\": \"5016243669\",\n"
                + "    \"type\": \"file\"\n"
                + "  },\n"
                + "  \"created_by\": {\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"2030392653\",\n"
                + "    \"name\": \"John Q. Developer\",\n"
                + "    \"login\": \"johnq@dev.name\"\n"
                + "  },\n"
                + "  \"created_at\": \"2016-05-09T17:41:27-07:00\",\n"
                + "  \"address\": \"https://dev.name/actions/file_changed\",\n"
                + "  \"triggers\": [\n"
                + "    \"FILE.DOWNLOADED\",\n"
                + "    \"FILE.UPLOADED\"\n"
                + "  ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxWebHook.Info info = BoxWebHook.create(new BoxFile(api, "0"), address);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(targetID, info.getTarget().getId());
        Assert.assertEquals(targetType, info.getTarget().getType());
        Assert.assertEquals(createdByID, info.getCreatedBy().getID());
        Assert.assertEquals(createdByName, info.getCreatedBy().getName());
        Assert.assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(address, info.getAddress());
        Assert.assertEquals(true, info.getTriggers().contains(firstTrigger));
        Assert.assertEquals(true, info.getTriggers().contains(secondTrigger));

    }

    /**
     * Unit test for {@link BoxWebHook#getInfo())}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequestWithoutFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/webhooks/0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxWebHook hook = new BoxWebHook(api, "0");
        hook.getInfo();
    }

    /**
     * Unit test for {@link BoxWebHook#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/webhooks/0?fields=created_at",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxWebHook hook = new BoxWebHook(api, "0");
        hook.getInfo("created_at");
    }

    /**
     * Unit test for {@link BoxWebHook#getInfo()}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException, MalformedURLException {
        final String id = "4137";
        final String targetID = "5018848529";
        final String targetType = "file";
        final String createdByID = "2030392653";
        final String createdByName = "John Q. Developer";
        final String createdByLogin = "johnq@example.net";
        final Date createdAt = BoxDateFormat.parse("2016-05-04T18:51:45-07:00");
        final URL address = new URL("https://example.net/actions/file_changed");
        final BoxWebHook.Trigger trigger = BoxWebHook.Trigger.FILE_PREVIEWED;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"id\": \"4137\",\n"
                + "  \"type\": \"webhook\",\n"
                + "  \"target\": {\n"
                + "    \"id\": \"5018848529\",\n"
                + "    \"type\": \"file\"\n"
                + "  },\n"
                + "  \"created_by\": {\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"2030392653\",\n"
                + "    \"name\": \"John Q. Developer\",\n"
                + "    \"login\": \"johnq@example.net\"\n"
                + "  },\n"
                + "  \"created_at\": \"2016-05-04T18:51:45-07:00\",\n"
                + "  \"address\": \"https://example.net/actions/file_changed\",\n"
                + "  \"triggers\": [\n"
                + "    \"FILE.PREVIEWED\"\n"
                + "  ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxWebHook.Info info = new BoxWebHook(api, id).getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(targetID, info.getTarget().getId());
        Assert.assertEquals(targetType, info.getTarget().getType());
        Assert.assertEquals(createdByID, info.getCreatedBy().getID());
        Assert.assertEquals(createdByName, info.getCreatedBy().getName());
        Assert.assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(address, info.getAddress());
        Assert.assertEquals(trigger, info.getTriggers().toArray()[0]);
    }

    /**
     * Unit test for {@link BoxWebHook#delete()}
     */
    @Test
    @Category(UnitTest.class)
    public void testDeleteSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/webhooks/0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxWebHook hook = new BoxWebHook(api, "0");
        hook.delete();
    }

    /**
     * Unit test for {@link BoxWebHook#all(BoxAPIConnection)}
     */
    @Test
    @Category(UnitTest.class)
    public void testAllSendsCorrectRequestWithoutParams() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/webhooks?limit=64",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        Iterator<BoxWebHook.Info> iterator = BoxWebHook.all(api).iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxWebHook#all(BoxAPIConnection, String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testAllSendsCorrectRequestWithFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/webhooks?fields=created_at%2Ccreated_by&limit=64",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        Iterator<BoxWebHook.Info> iterator = BoxWebHook.all(api, "created_at", "created_by").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxWebHook#all(BoxAPIConnection)}
     */
    @Test
    @Category(UnitTest.class)
    public void testAllParseAllFieldsCorrectly() {
        final String firstID = "4161";
        final String firstTargetID = "5018326685";
        final String firstTargetType = "folder";
        final String secondID = "4165";
        final String secondTargetID = "5016243669";
        final String secondTargerType = "file";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"entries\": [\n"
                + "    {\n"
                + "      \"id\": \"4161\",\n"
                + "      \"type\": \"webhook\",\n"
                + "      \"target\": {\n"
                + "        \"id\": \"5018326685\",\n"
                + "        \"type\": \"folder\"\n"
                + "      }\n"
                + "    },\n"
                + "    {\n"
                + "      \"id\": \"4165\",\n"
                + "      \"type\": \"webhook\",\n"
                + "      \"target\": {\n"
                + "        \"id\": \"5016243669\",\n"
                + "        \"type\": \"file\"\n"
                + "      }\n"
                + "    }\n"
                + "  ],\n"
                + "  \"limit\": 3\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxWebHook.Info> iterator = BoxWebHook.all(api).iterator();
        BoxWebHook.Info info = iterator.next();
        Assert.assertEquals(firstID, info.getID());
        Assert.assertEquals(firstTargetID, info.getTarget().getId());
        Assert.assertEquals(firstTargetType, info.getTarget().getType());
        info = iterator.next();
        Assert.assertEquals(secondID, info.getID());
        Assert.assertEquals(secondTargetID, info.getTarget().getId());
        Assert.assertEquals(secondTargerType, info.getTarget().getType());
        Assert.assertEquals(false, iterator.hasNext());

    }

    /**
     * Unit test for {@link BoxWebHook#updateInfo(BoxWebHook.Info)}
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateSendCorrectJSON() {
        final String address = "";
        final String firstTrigger = "FILE.PREVIEWED";
        final String secondTrigger = "FILE.DOWNLOADED";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"id\": \"4137\",\n"
                + "  \"type\": \"webhook\",\n"
                + "  \"target\": {\n"
                + "    \"id\": \"5018848529\",\n"
                + "    \"type\": \"file\"\n"
                + "  }\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxWebHook hook = new BoxWebHook(api, "0");
        BoxWebHook.Info info = hook.new Info();

        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/webhooks/0",
                        request.getUrl().toString());
                Assert.assertEquals(address, json.get("address").asString());
                Assert.assertEquals(firstTrigger, json.get("triggers").asArray().get(0).asString());
                Assert.assertEquals(secondTrigger, json.get("triggers").asArray().get(1).asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        info.addPendingChange("address", address);
        info.addPendingChange("triggers", new JsonArray().add(firstTrigger).add(secondTrigger));
        hook.updateInfo(info);
    }

    /**
     * Unit test for {@link BoxWebHook#updateInfo(BoxWebHook.Info)}
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateParseAllFieldsCorrectly() throws ParseException, MalformedURLException {
        final String id = "4133";
        final String targetID = "1000605797";
        final String targetType = "folder";
        final String createdByID = "2030392653";
        final String createdByName = "John Q. Developer";
        final String createdByLogin = "john2@example.net";
        final Date createdAt = BoxDateFormat.parse("2016-05-04T18:51:17-07:00");
        final URL address = new URL("https://notification.example.net");
        final BoxWebHook.Trigger firstTrigger = BoxWebHook.Trigger.FILE_PREVIEWED;
        final BoxWebHook.Trigger secondTrigger = BoxWebHook.Trigger.FILE_DOWNLOADED;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"id\": \"4133\",\n"
                + "  \"type\": \"webhook\",\n"
                + "  \"target\": {\n"
                + "    \"id\": \"1000605797\",\n"
                + "    \"type\": \"folder\"\n"
                + "  },\n"
                + "  \"created_by\": {\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"2030392653\",\n"
                + "    \"name\": \"John Q. Developer\",\n"
                + "    \"login\": \"john2@example.net\"\n"
                + "  },\n"
                + "  \"created_at\": \"2016-05-04T18:51:17-07:00\",\n"
                + "  \"address\": \"https://notification.example.net\",\n"
                + "  \"triggers\": [\n"
                + "    \"FILE.PREVIEWED\", \"FILE.DOWNLOADED\"\n"
                + "  ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxWebHook hook = new BoxWebHook(api, id);
        BoxWebHook.Info info = hook.new Info();
        info.addPendingChange("address", "fake pending change");
        hook.updateInfo(info);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(targetID, info.getTarget().getId());
        Assert.assertEquals(targetType, info.getTarget().getType());
        Assert.assertEquals(createdByID, info.getCreatedBy().getID());
        Assert.assertEquals(createdByName, info.getCreatedBy().getName());
        Assert.assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(address, info.getAddress());
        Assert.assertEquals(true, info.getTriggers().contains(firstTrigger));
        Assert.assertEquals(true, info.getTriggers().contains(secondTrigger));

    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebHookFileSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[createWebhook] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        try {
            URL address = new URL("https://www.google.com");
            BoxWebHook.Info info = BoxWebHook.create(uploadedFile, address,
                    BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED);

            assertThat(info.getID(), is(notNullValue()));
            assertThat(info.getAddress(), is(equalTo(address)));
            assertThat(info.getTarget().getType(), is(equalTo(BoxResource.getResourceType(BoxFile.class))));
            assertThat(info.getTarget().getId(), is(equalTo(uploadedFile.getID())));
            assertThat(info.getTriggers(), is(equalTo(this.toSet(
                    new BoxWebHook.Trigger[] {BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED }))));

            info.getResource().delete();
        } finally {
            uploadedFile.delete();
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebHookFolderSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String folderName = "[createWebhook] Folder";
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();

        try {
            URL address = new URL("https://www.google.com");
            BoxWebHook.Info info = BoxWebHook.create(folder, address,
                    BoxWebHook.Trigger.FOLDER_DOWNLOADED, BoxWebHook.Trigger.FOLDER_COPIED);

            assertThat(info.getID(), is(notNullValue()));
            assertThat(info.getAddress(), is(equalTo(address)));
            assertThat(info.getTarget().getType(), is(equalTo(BoxResource.getResourceType(BoxFolder.class))));
            assertThat(info.getTarget().getId(), is(equalTo(folder.getID())));
            assertThat(info.getTriggers(), is(equalTo(this.toSet(new BoxWebHook.Trigger[] {
                BoxWebHook.Trigger.FOLDER_COPIED, BoxWebHook.Trigger.FOLDER_DOWNLOADED }))));

            info.getResource().delete();
        } finally {
            folder.delete(true);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void listWebHooksSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[listWebhooks] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        try {
            URL address = new URL("https://www.google.com");
            BoxWebHook.Info info = BoxWebHook.create(uploadedFile, address, BoxWebHook.Trigger.FILE_PREVIEWED);
            Iterable<BoxWebHook.Info> webhooks = BoxWebHook.all(api);

            assertThat(webhooks, hasItem(Matchers.<BoxWebHook.Info>hasProperty("ID", equalTo(info.getID()))));

            info.getResource().delete();

            webhooks = BoxWebHook.all(api);
            assertThat(webhooks, not(hasItem(Matchers.<BoxWebHook.Info>hasProperty("ID", equalTo(info.getID())))));
        } finally {
            uploadedFile.delete();
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateWebHookInfoSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[updateWebHookInfoSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        try {
            URL address = new URL("https://www.google.com");
            BoxWebHook webHook = BoxWebHook.create(uploadedFile, address,
                    BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED).getResource();

            URL newAddress = new URL("https://www.yahoo.com");

            BoxWebHook.Info newInfo = webHook.new Info();
            newInfo.setTriggers(BoxWebHook.Trigger.FILE_UNLOCKED);
            newInfo.setAddress(newAddress);

            webHook.updateInfo(newInfo);

            assertThat(newInfo.getAddress(), is(equalTo(newAddress)));
            assertThat(newInfo.getTriggers(), is(equalTo(
                    this.toSet(new BoxWebHook.Trigger[]{BoxWebHook.Trigger.FILE_UNLOCKED})
            )));

            webHook.delete();
        } finally {
            uploadedFile.delete();
        }
    }

    private <T> Set<T> toSet(T[] values) {
        return new HashSet<T>(Arrays.asList(values));
    }
}
