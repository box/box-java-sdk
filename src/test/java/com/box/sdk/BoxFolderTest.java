package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.skyscreamer.jsonassert.JSONCompareMode.LENIENT;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;


/**
 * {@link BoxFolder} related tests.
 */
public class BoxFolderTest {
    @SuppressWarnings("checkstyle:wrongOrder")
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    @Category(UnitTest.class)
    public void foldersWithSameIDAreEqual() {
        BoxAPIConnection api = new BoxAPIConnection("");
        BoxFolder folder1 = new BoxFolder(api, "1");
        BoxFolder folder2 = new BoxFolder(api, "1");

        assertThat(folder1, equalTo(folder2));
    }

    @Test
    @Category(UnitTest.class)
    public void createFolderSendsRequestWithRequiredFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String parentFolderID = rootFolder.getID();
        String createdFolderName = "[createFolderSendsRequestWithRequiredFields] Child Folder";

        stubFor(post(urlMatching("/folders"))
            .withRequestBody(equalToJson("{ \"name\": \"" + createdFolderName + "\", \"parent\": {\"id\": \""
                + parentFolderID + "\"} }", LENIENT))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\": \"0\"}")));

        rootFolder.createFolder(createdFolderName);
    }

    @Test
    @Category(UnitTest.class)
    public void infoParsesMixedPermissionsCorrectly() {
        BoxAPIConnection api = new BoxAPIConnection("");
        String id = "id";

        EnumSet<BoxFolder.Permission> expectedPermissions = EnumSet.of(BoxFolder.Permission.CAN_UPLOAD,
            BoxFolder.Permission.CAN_DELETE, BoxFolder.Permission.CAN_INVITE_COLLABORATOR);

        JsonObject permissionsJSON = new JsonObject();
        permissionsJSON.add("can_download", false);
        permissionsJSON.add("can_upload", true);
        permissionsJSON.add("can_rename", false);
        permissionsJSON.add("can_delete", true);
        permissionsJSON.add("can_share", false);
        permissionsJSON.add("can_invite_collaborator", true);
        permissionsJSON.add("can_set_share_access", false);

        JsonObject folderJSON = new JsonObject();
        folderJSON.add("id", id);
        folderJSON.add("type", "folder");
        folderJSON.add("permissions", permissionsJSON);

        BoxFolder folder = new BoxFolder(api, id);
        BoxFolder.Info info = folder.new Info(folderJSON);

        assertThat(info.getPermissions(), is(equalTo(expectedPermissions)));
    }

    @Test
    @Category(UnitTest.class)
    public void getChildrenRangeRequestsCorrectOffsetLimitAndFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        stubFor(get(urlPathEqualTo("/folders/0/items/"))
            .withQueryParam("offset", WireMock.equalTo("1"))
            .withQueryParam("limit", WireMock.equalTo("2"))
            .withQueryParam("fields", containing("name"))
            .withQueryParam("fields", containing("description"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"total_count\": 3, \"entries\":[]}")));

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        PartialCollection<BoxItem.Info> children = rootFolder.getChildrenRange(1, 2, "name", "description");

        assertThat(children.offset(), is(equalTo(1L)));
        assertThat(children.limit(), is(equalTo(2L)));
        assertThat(children.fullSize(), is(equalTo(3L)));
    }

    @Test
    @Category(UnitTest.class)
    public void collaborateShouldSendCorrectJSONWhenCollaboratingWithAGroup() {
        final String folderID = "1";
        final String groupID = "2";
        final BoxCollaboration.Role role = BoxCollaboration.Role.CO_OWNER;

        final JsonObject fakeJSONResponse = new JsonObject()
            .add("type", "collaboration")
            .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                JsonObject itemJSON = json.get("item").asObject();
                assertEquals(folderID, itemJSON.get("id").asString());

                JsonObject accessibleByJSON = json.get("accessible_by").asObject();
                assertEquals(groupID, accessibleByJSON.get("id").asString());
                assertEquals("group", accessibleByJSON.get("type").asString());
                assertNull(accessibleByJSON.get("login"));

                assertEquals(role.toJSONString(), json.get("role").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxGroup collaborator = new BoxGroup(api, groupID);
        BoxFolder folder = new BoxFolder(api, folderID);
        folder.collaborate(collaborator, BoxCollaboration.Role.CO_OWNER);
    }

    @Test
    @Category(UnitTest.class)
    public void getCollaborationsShouldParseGroupsCorrectly() {
        final String groupID = "non-empty ID";
        final String groupName = "non-empty name";

        final JsonObject fakeJSONResponse = new JsonObject()
            .add("total_count", 1)
            .add("entries", new JsonArray()
                .add(new JsonObject()
                    .add("type", "collaboration")
                    .add("id", "non-empty ID")
                    .add("accessible_by", new JsonObject()
                        .add("type", "group")
                        .add("id", groupID)
                        .add("name", groupName))));

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxFolder folder = new BoxFolder(api, "non-empty ID");
        for (BoxCollaboration.Info collaboration : folder.getCollaborations()) {
            BoxCollaborator.Info collaboratorInfo = collaboration.getAccessibleBy();
            assertTrue(collaboratorInfo instanceof BoxGroup.Info);

            BoxGroup.Info groupInfo = (BoxGroup.Info) collaboratorInfo;
            assertEquals(groupID, groupInfo.getID());
            assertEquals(groupName, groupInfo.getName());
        }
    }

    /**
     * Unit test for {@link BoxFolder#getAllMetadata(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataSendsCorrectRequest() {
        final BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/folders/5010739061/metadata?fields=name%2Csize&limit=100",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        BoxFolder folder = new BoxFolder(api, "5010739061");
        Iterator<Metadata> iterator = folder.getAllMetadata("name", "size").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxFolder#getAllMetadata(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadateParseAllFieldsCorrectly() {
        final String firstEntrycurrentDocumentStage = "Init";
        final String firstEntryType = "documentFlow-452b4c9d-c3ad-4ac7-b1ad-9d5192f2fc5f";
        final String firstEntryParent = "file_5010739061";
        final String firstEntryID = "50ba0dba-0f89-4395-b867-3e057c1f6ed9";
        final int firstEntryVersion = 4;
        final int firstEntryTypeVersion = 2;
        final String firstEntryNeedApprovalFrom = "Smith";
        final String firstEntryTemplate = "documentFlow";
        final String firstEntryScope = "enterprise_12345";
        final String secondEntryType = "productInfo-9d7b6993-b09e-4e52-b197-e42f0ea995b9";
        final String secondEntryParent = "file_5010739061";
        final String secondEntryID = "15d1014a-06c2-47ad-9916-014eab456194";
        final int secondEntryVersion = 2;
        final int secondEntryTypeVersion = 1;
        final int secondEntrySkuNumber = 45334223;
        final String secondEntryDescription = "Watch";
        final String secondEntryTemplate = "productInfo";
        final String secondEntryScope = "enterprise_12345";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"entries\": [\n"
                + "        {\n"
                + "            \"currentDocumentStage\": \"Init\",\n"
                + "            \"$type\": \"documentFlow-452b4c9d-c3ad-4ac7-b1ad-9d5192f2fc5f\",\n"
                + "            \"$parent\": \"file_5010739061\",\n"
                + "            \"$id\": \"50ba0dba-0f89-4395-b867-3e057c1f6ed9\",\n"
                + "            \"$version\": 4,\n"
                + "            \"$typeVersion\": 2,\n"
                + "            \"needsApprovalFrom\": \"Smith\",\n"
                + "            \"$template\": \"documentFlow\",\n"
                + "            \"$scope\": \"enterprise_12345\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"$type\": \"productInfo-9d7b6993-b09e-4e52-b197-e42f0ea995b9\",\n"
                + "            \"$parent\": \"file_5010739061\",\n"
                + "            \"$id\": \"15d1014a-06c2-47ad-9916-014eab456194\",\n"
                + "            \"$version\": 2,\n"
                + "            \"$typeVersion\": 1,\n"
                + "            \"skuNumber\": 45334223,\n"
                + "            \"description\": \"Watch\",\n"
                + "            \"$template\": \"productInfo\",\n"
                + "            \"$scope\": \"enterprise_12345\"\n"
                + "        }\n"
                + "\n"
                + "    ],\n"
                + "    \"limit\": 100\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxFolder folder = new BoxFolder(api, "0");
        Iterator<Metadata> iterator = folder.getAllMetadata().iterator();
        Metadata entry = iterator.next();
        Assert.assertEquals(firstEntrycurrentDocumentStage, entry.get("/currentDocumentStage"));
        Assert.assertEquals(firstEntryType, entry.getTypeName());
        Assert.assertEquals(firstEntryParent, entry.getParentID());
        Assert.assertEquals(firstEntryID, entry.getID());
        Assert.assertEquals(firstEntryVersion, (int) Integer.valueOf(entry.get("/$version")));
        Assert.assertEquals(firstEntryTypeVersion, (int) Integer.valueOf(entry.get("/$typeVersion")));
        Assert.assertEquals(firstEntryNeedApprovalFrom, entry.get("/needsApprovalFrom"));
        Assert.assertEquals(firstEntryTemplate, entry.getTemplateName());
        Assert.assertEquals(firstEntryScope, entry.getScope());
        entry = iterator.next();
        Assert.assertEquals(secondEntryType, entry.getTypeName());
        Assert.assertEquals(secondEntryParent, entry.getParentID());
        Assert.assertEquals(secondEntryID, entry.getID());
        Assert.assertEquals(secondEntryVersion, (int) Integer.valueOf(entry.get("/$version")));
        Assert.assertEquals(secondEntryTypeVersion, (int) Integer.valueOf(entry.get("/$typeVersion")));
        Assert.assertEquals(secondEntrySkuNumber, (int) Integer.valueOf(entry.get("/skuNumber")));
        Assert.assertEquals(secondEntryDescription, entry.get("/description"));
        Assert.assertEquals(secondEntryTemplate, entry.getTemplateName());
        Assert.assertEquals(secondEntryScope, entry.getScope());

    }

    /**
     * Unit test for {@link BoxFolder#getWatermark(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetWatermarkSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/folders/0/watermark",
                    request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        new BoxFolder(api, "0").getWatermark();
    }

    /**
     * Unit test for {@link BoxFolder#getWatermark(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetWatermarkParseAllFieldsCorrectly() throws ParseException {
        final Date createdAt = BoxDateFormat.parse("2016-10-31T15:33:33-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2016-11-31T15:33:33-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
            + "  \"watermark\": {\n"
            + "    \"created_at\": \"2016-10-31T15:33:33-07:00\",\n"
            + "    \"modified_at\": \"2016-11-31T15:33:33-07:00\"\n"
            + "  }\n"
            + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxWatermark watermark = new BoxFolder(api, "0").getWatermark();
        Assert.assertEquals(createdAt, watermark.getCreatedAt());
        Assert.assertEquals(modifiedAt, watermark.getModifiedAt());
    }

    /**
     * Unit test for {@link BoxFolder#applyWatermark()}
     */
    @Test
    @Category(UnitTest.class)
    public void testApplyWatermarkSendsCorrectJson() {
        final String imprint = "default";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/folders/0/watermark",
                    request.getUrl().toString());
                Assert.assertEquals(imprint, json.get("watermark").asObject().get("imprint").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        new BoxFolder(api, "0").applyWatermark();
    }

    /**
     * Unit test for {@link BoxFolder#applyWatermark()}
     */
    @Test
    @Category(UnitTest.class)
    public void testApplyWatermarkParseAllFieldsCorrectly() throws ParseException {
        final Date createdAt = BoxDateFormat.parse("2016-10-31T15:33:33-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2016-11-31T15:33:33-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
            + "  \"watermark\": {\n"
            + "    \"created_at\": \"2016-10-31T15:33:33-07:00\",\n"
            + "    \"modified_at\": \"2016-11-31T15:33:33-07:00\"\n"
            + "  }\n"
            + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxWatermark watermark = new BoxFolder(api, "0").applyWatermark();
        Assert.assertEquals(createdAt, watermark.getCreatedAt());
        Assert.assertEquals(modifiedAt, watermark.getModifiedAt());
    }

    /**
     * Unit test for {@link BoxFolder#removeWatermark()}
     */
    @Test
    @Category(UnitTest.class)
    public void testRemoveWatermarkSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/folders/0/watermark",
                    request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        new BoxFolder(api, "0").removeWatermark();
    }

    /**
     * Unit test for {@link BoxFolder#createWebLink(String, URL, String)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateWeblinkSendsCorrectJsonWithNameAndDescription() throws MalformedURLException {
        final String url = "https://www.box.com/home";
        final String parentFolderID = "0";
        final String name = "non-empty name";
        final String description = "non-empty description";

        final JsonObject fakeJSONResponse = new JsonObject()
            .add("type", "web_link")
            .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/web_links", request.getUrl().toString());
                Scanner body = new Scanner(request.getBody()).useDelimiter("\n");
                JsonObject json = JsonObject.readFrom(body.next());
                body.close();
                Assert.assertEquals(url, json.get("url").asString());
                Assert.assertEquals(parentFolderID, json.get("parent").asObject().get("id").asString());
                Assert.assertEquals(name, json.get("name").asString());
                Assert.assertEquals(description, json.get("description").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        new BoxFolder(api, "0").createWebLink(name, new URL(url), description);
    }

    /**
     * Unit test for {@link BoxFolder#createWebLink(URL)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateWeblinkSendsCorrectJsonWithoutNameAndDescription() throws MalformedURLException {
        final String url = "https://www.box.com/home";
        final String parentFolderID = "0";

        final JsonObject fakeJSONResponse = new JsonObject()
            .add("type", "web_link")
            .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/web_links", request.getUrl().toString());
                JsonObject json = JsonObject.readFrom(new Scanner(request.getBody()).useDelimiter("\n").next());
                assertEquals(url, json.get("url").asString());
                assertEquals(parentFolderID, json.get("parent").asObject().get("id").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        new BoxFolder(api, "0").createWebLink(new URL(url));
    }

    /**
     * Unit test for {@link BoxFolder#createWebLink(URL)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateWeblinkParseAllFieldsCorrectly() throws ParseException, MalformedURLException {
        final String id = "6742981";
        final String sequenceID = "0";
        final String etag = "0";
        final String name = "Box Website";
        final String url = "https://www.box.com";
        final String creatorID = "10523870";
        final String creatorName = "Ted Blosser";
        final String creatorLogin = "ted+demo@box.com";
        final Date createdAt = BoxDateFormat.parse("2015-05-07T14:31:16-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2015-05-07T14:31:16-07:00");
        final String parentID = "848123342";
        final String parentSequenceID = "1";
        final String parentEtag = "1";
        final String parentName = "Documentation";
        final String description = "Cloud Content Management";
        final String itemStatus = "active";
        final Date trashedAt = null;
        final Date purgedAt = null;
        final BoxSharedLink sharedLink = null;
        final String pathID = "848123342";
        final String pathSequenceID = "1";
        final String pathEtag = "1";
        final String pathName = "Documentation";
        final String modifiedID = "10523870";
        final String modifiedName = "Ted Blosser";
        final String modifiedLogin = "ted+demo@box.com";
        final String ownerID = "10523870";
        final String ownerName = "Ted Blosser";
        final String ownerLogin = "ted+demo@box.com";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
            + "    \"type\": \"web_link\",\n"
            + "    \"id\": \"6742981\",\n"
            + "    \"sequence_id\": \"0\",\n"
            + "    \"etag\": \"0\",\n"
            + "    \"name\": \"Box Website\",\n"
            + "    \"url\": \"https://www.box.com\",\n"
            + "    \"created_by\": {\n"
            + "        \"type\": \"user\",\n"
            + "        \"id\": \"10523870\",\n"
            + "        \"name\": \"Ted Blosser\",\n"
            + "        \"login\": \"ted+demo@box.com\"\n"
            + "    },\n"
            + "    \"created_at\": \"2015-05-07T14:31:16-07:00\",\n"
            + "    \"modified_at\": \"2015-05-07T14:31:16-07:00\",\n"
            + "    \"parent\": {\n"
            + "        \"type\": \"folder\",\n"
            + "        \"id\": \"848123342\",\n"
            + "        \"sequence_id\": \"1\",\n"
            + "        \"etag\": \"1\",\n"
            + "        \"name\": \"Documentation\"\n"
            + "    },\n"
            + "    \"description\": \"Cloud Content Management\",\n"
            + "    \"item_status\": \"active\",\n"
            + "    \"trashed_at\": null,\n"
            + "    \"purged_at\": null,\n"
            + "    \"shared_link\": null,\n"
            + "    \"path_collection\": {\n"
            + "        \"total_count\": 1,\n"
            + "        \"entries\": [\n"
            + "            {\n"
            + "                \"type\": \"folder\",\n"
            + "                \"id\": \"848123342\",\n"
            + "                \"sequence_id\": \"1\",\n"
            + "                \"etag\": \"1\",\n"
            + "                \"name\": \"Documentation\"\n"
            + "            }\n"
            + "        ]\n"
            + "    },\n"
            + "    \"modified_by\": {\n"
            + "        \"type\": \"user\",\n"
            + "        \"id\": \"10523870\",\n"
            + "        \"name\": \"Ted Blosser\",\n"
            + "        \"login\": \"ted+demo@box.com\"\n"
            + "    },\n"
            + "    \"owned_by\": {\n"
            + "        \"type\": \"user\",\n"
            + "        \"id\": \"10523870\",\n"
            + "        \"name\": \"Ted Blosser\",\n"
            + "        \"login\": \"ted+demo@box.com\"\n"
            + "    }\n"
            + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxWebLink.Info info = new BoxFolder(api, "0").createWebLink(new URL(url));
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(sequenceID, info.getSequenceID());
        Assert.assertEquals(etag, info.getEtag());
        Assert.assertEquals(name, info.getName());
        Assert.assertEquals(url, info.getLinkURL().toString());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(modifiedAt, info.getModifiedAt());
        Assert.assertEquals(description, info.getDescription());
        Assert.assertEquals(itemStatus, info.getItemStatus());
        Assert.assertEquals(trashedAt, info.getTrashedAt());
        Assert.assertEquals(purgedAt, info.getPurgedAt());
        Assert.assertEquals(sharedLink, info.getSharedLink());
        BoxUser.Info creatorInfo = info.getCreatedBy();
        Assert.assertEquals(creatorID, creatorInfo.getID());
        Assert.assertEquals(creatorName, creatorInfo.getName());
        Assert.assertEquals(creatorLogin, creatorInfo.getLogin());
        BoxUser.Info modifiedInfo = info.getModifiedBy();
        Assert.assertEquals(modifiedID, modifiedInfo.getID());
        Assert.assertEquals(modifiedName, modifiedInfo.getName());
        Assert.assertEquals(modifiedLogin, modifiedInfo.getLogin());
        BoxUser.Info ownerInfo = info.getOwnedBy();
        Assert.assertEquals(ownerID, ownerInfo.getID());
        Assert.assertEquals(ownerName, ownerInfo.getName());
        Assert.assertEquals(ownerLogin, ownerInfo.getLogin());
        BoxFolder.Info parentInfo = info.getParent();
        Assert.assertEquals(parentID, parentInfo.getID());
        Assert.assertEquals(parentSequenceID, parentInfo.getSequenceID());
        Assert.assertEquals(parentEtag, parentInfo.getEtag());
        Assert.assertEquals(parentName, parentInfo.getName());
        BoxFolder.Info pathInfo = info.getPathCollection().get(0);
        Assert.assertEquals(pathID, pathInfo.getID());
        Assert.assertEquals(pathSequenceID, pathInfo.getSequenceID());
        Assert.assertEquals(pathEtag, pathInfo.getEtag());
        Assert.assertEquals(pathName, pathInfo.getName());
    }

    @Test
    @Category(IntegrationTest.class)
    public void creatingAndDeletingFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder("[creatingAndDeletingFolderSucceeds] Ĥȅľľő Ƒŕőďő")
            .getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID()))));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getFolderInfoReturnsCorrectInfo() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser currentUser = BoxUser.getCurrentUser(api);
        final String expectedName = "[getFolderInfoReturnsCorrectInfo] Child Folder";
        final String expectedCreatedByID = currentUser.getID();

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        final String expectedParentFolderID = rootFolder.getID();
        final String expectedParentFolderName = rootFolder.getInfo().getName();

        BoxFolder childFolder = rootFolder.createFolder(expectedName).getResource();
        BoxFolder.Info info = childFolder.getInfo(BoxItem.ALL_FIELDS);

        String actualName = info.getName();
        String actualCreatedByID = info.getCreatedBy().getID();
        String actualParentFolderID = info.getParent().getID();
        String actualParentFolderName = info.getParent().getName();
        List<BoxFolder.Info> actualPathCollection = info.getPathCollection();

        assertThat(expectedName, equalTo(actualName));
        assertThat(expectedCreatedByID, equalTo(actualCreatedByID));
        assertThat(expectedParentFolderID, equalTo(actualParentFolderID));
        assertThat(expectedParentFolderName, equalTo(actualParentFolderName));
        assertThat(actualPathCollection, hasItem(Matchers.<BoxFolder.Info>hasProperty("ID", equalTo("0"))));
        assertThat(info.getPermissions(), is(equalTo(EnumSet.allOf(BoxFolder.Permission.class))));
        assertThat(info.getItemStatus(), is(equalTo("active")));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheNameField() {
        final String expectedName = "All Files";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder.Info rootFolderInfo = rootFolder.getInfo("name");
        final String actualName = rootFolderInfo.getName();
        final String actualDescription = rootFolderInfo.getDescription();
        final long actualSize = rootFolderInfo.getSize();

        assertThat(expectedName, equalTo(actualName));
        assertThat(actualDescription, is(nullValue()));
        assertThat(actualSize, is(0L));
    }

    @Test
    @Category(IntegrationTest.class)
    public void iterateWithOnlyTheNameField() {
        final String expectedName = "[iterateWithOnlyTheNameField] Child Folder";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder.Info rootFolderInfo = rootFolder.getInfo("name");

        BoxFolder childFolder = rootFolder.createFolder(expectedName).getResource();

        Iterable<BoxItem.Info> children = rootFolder.getChildren("name");
        boolean found = false;
        for (BoxItem.Info childInfo : children) {
            if (childInfo.getID().equals(childFolder.getID())) {
                found = true;
                assertThat(childInfo.getName(), is(equalTo(expectedName)));
                assertThat(childInfo.getSize(), is(equalTo(0L)));
                assertThat(childInfo.getDescription(), is(nullValue()));
            }
        }
        assertThat(found, is(true));

        childFolder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadFileSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        final String fileContent = "Test file";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        BoxFile uploadedFile = rootFolder.uploadFile(stream, "Test File.txt").getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));

        uploadedFile.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadFileWithCreatedAndModifiedDatesSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date created = new Date(1415318114);
        Date modified = new Date(1315318114);
        final String fileContent = "Test file";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        FileUploadParams params = new FileUploadParams()
            .setName("[uploadFileWithCreatedAndModifiedDatesSucceeds] Test File.txt").setContent(stream)
            .setModified(modified).setCreated(created);
        BoxFile.Info info = rootFolder.uploadFile(params);
        BoxFile uploadedFile = info.getResource();

        assertThat(dateFormat.format(info.getContentCreatedAt()), is(equalTo(dateFormat.format(created))));
        assertThat(dateFormat.format(info.getContentModifiedAt()), is(equalTo(dateFormat.format(modified))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFolderInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String originalName = "[updateFolderInfoSucceeds] Child Folder";
        final String updatedName = "[updateFolderInfoSucceeds] Updated Child Folder";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder.Info info = rootFolder.createFolder(originalName);
        BoxFolder childFolder = info.getResource();
        info.setName(updatedName);
        childFolder.updateInfo(info);
        assertThat(info.getName(), equalTo(updatedName));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void copyFolderToSameDestinationWithNewNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String originalName = "[copyFolderToSameDestinationWithNewNameSucceeds] Child Folder";
        final String newName = "[copyFolderToSameDestinationWithNewNameSucceeds] New Child Folder";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder originalFolder = rootFolder.createFolder(originalName).getResource();
        BoxFolder.Info copiedFolderInfo = originalFolder.copy(rootFolder, newName);
        BoxFolder copiedFolder = copiedFolderInfo.getResource();

        assertThat(copiedFolderInfo.getName(), is(equalTo(newName)));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(originalFolder.getID()))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(copiedFolder.getID()))));

        originalFolder.delete(false);
        copiedFolder.delete(false);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(originalFolder.getID())))));
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(copiedFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void moveFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String child1Name = "[moveFolderSucceeds] Child Folder";
        final String child2Name = "[moveFolderSucceeds] Child Folder 2";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder1 = rootFolder.createFolder(child1Name).getResource();
        BoxFolder childFolder2 = rootFolder.createFolder(child2Name).getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder1.getID()))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder2.getID()))));

        childFolder2.move(childFolder1);

        assertThat(childFolder1, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder2.getID()))));
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder2.getID())))));

        childFolder1.delete(true);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder1.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void renameFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String originalName = "[renameFolderSucceeds] Original Name";
        final String newName = "[renameFolderSucceeds] New Name";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder(originalName).getResource();
        childFolder.rename(newName);
        BoxFolder.Info childFolderInfo = childFolder.getInfo();

        assertThat(childFolderInfo.getName(), is(equalTo(newName)));

        childFolder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCollaboratorSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[addCollaborationToFolderSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();

        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);
        BoxUser.Info accessibleBy = (BoxUser.Info) collabInfo.getAccessibleBy();

        assertThat(accessibleBy.getLogin(), is(equalTo(collaboratorLogin)));
        assertThat(collabInfo.getRole(), is(equalTo(collaboratorRole)));

        folder.delete(false);
    }
    @Test
    @Category(IntegrationTest.class)
    public void addCollaborationsWithAttributesSucceeds() {
        // Logger logger = TestConfig.enableLogger("FINE");
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[getCollaborationsSucceeds] Test Folder";
        String collaboratorLogin = "karthik2001123@yahoo.com";
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole, true, true);
        String collabID = collabInfo.getID();

        collaboratorRole = BoxCollaboration.Role.VIEWER;
        collaboratorLogin = "davidsmaynard@gmail.com";
        BoxCollaboration.Info collabInfo2 = folder.collaborate(collaboratorLogin, collaboratorRole, true, true);

        collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Info collabInfo3 = folder.collaborate(collaboratorLogin, collaboratorRole, true, true);


        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();

        assertThat(collaborations, hasSize(3));
        assertThat(collaborations, hasItem(Matchers.<BoxCollaboration.Info>hasProperty("ID", equalTo(collabID))));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCollaborationsHasCorrectCollaborations() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[getCollaborationsHasCorrectCollaborations] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);
        String collabID = collabInfo.getID();

        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();

        assertThat(collaborations, hasSize(1));
        assertThat(collaborations, hasItem(Matchers.<BoxCollaboration.Info>hasProperty("ID", equalTo(collabID))));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void setFolderUploadEmailSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[setFolderUploadEmailSucceeds] Test Folder";

        BoxUploadEmail uploadEmail = new BoxUploadEmail();
        uploadEmail.setAccess(BoxUploadEmail.Access.OPEN);

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        BoxFolder.Info info = folder.new Info();
        info.setUploadEmail(uploadEmail);
        folder.updateInfo(info);

        assertThat(uploadEmail.getEmail(), not(isEmptyOrNullString()));
        assertThat(uploadEmail.getAccess(), is(equalTo(BoxUploadEmail.Access.OPEN)));

        info.setUploadEmail(null);
        uploadEmail = info.getUploadEmail();

        assertThat(uploadEmail, is(nullValue()));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getSharedItemAndItsChildrenSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[getSharedItemAndItsChildrenSucceeds] Test Folder";
        String childFolderName = "[getSharedItemAndItsChildrenSucceeds] Child Folder";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        BoxFolder childFolder = folder.createFolder(childFolderName).getResource();
        BoxSharedLink sharedLink = folder.createSharedLink(BoxSharedLink.Access.OPEN, null, null);

        BoxFolder.Info sharedItem = (BoxFolder.Info) BoxItem.getSharedItem(api, sharedLink.getURL());

        assertThat(sharedItem.getID(), is(equalTo(folder.getID())));
        assertThat(sharedItem.getResource(), hasItem(Matchers.<BoxItem.Info>hasProperty("ID",
            equalTo(childFolder.getID()))));

        folder.delete(true);
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink("[createWebLinkSucceeds] Test Web Link",
            new URL("https://api.box.com"), "[createWebLinkSucceeds] Test Web Link").getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkNoNameSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink(new URL("https://api.box.com"),
            "[createWebLinkSucceeds] Test Web Link").getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkNoDescriptionSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink("[createWebLinkSucceeds] Test Web Link",
            new URL("https://api.box.com")).getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkNoNameOrDescriptionSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink(new URL("https://api.box.com")).getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }


    @Test
    @Category(IntegrationTest.class)
    public void createPropertiesMetadataSucceeds() {
        final String key = "/testKey";
        final String value = "testValue";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Metadata md = new Metadata();
        md.add(key, value);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder("[createPropertiesMetadataSucceeds] Metadata Folder").getResource();
        Metadata createdMD = folder.createMetadata(md);

        assertThat(createdMD.get(key), is(equalTo(value)));
        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void deletePropertiesMetadataSucceeds() {
        final String key = "/testKey";
        final String value = "testValue";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Metadata md = new Metadata();
        md.add(key, value);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder("[createPropertiesMetadataSucceeds] Metadata Folder").getResource();
        folder.createMetadata(md);
        folder.deleteMetadata();

        try {
            Metadata actualMD = folder.getMetadata();
            fail();
        } catch (BoxAPIException e) {
            assertThat(e.getResponseCode(), is(equalTo(404)));
        } finally {
            folder.delete(false);
        }
    }

    /**
     * Verifies the fix for issue #325
     */
    @Test
    @Category(IntegrationTest.class)
    public void sharedLinkInfoHasEffectiveAccess() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder("[sharedLinkInfoHasEffectiveAccess] Test Folder").getResource();
        BoxSharedLink sharedLink = folder.createSharedLink(BoxSharedLink.Access.OPEN, null, null);

        assertThat(sharedLink, Matchers.<BoxSharedLink>hasProperty("effectiveAccess"));
        assertThat(sharedLink.getEffectiveAccess(), equalTo(BoxSharedLink.Access.OPEN));

        folder.delete(true);
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadSessionAbortFlowSuccess() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        String fileName = "Tamme-Lauri_tamm_suvepäeval.jpg";
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        long fileSize = file.length();

        FileInputStream stream = new FileInputStream(file);

        byte[] fileBytes = new byte[(int) file.length()];
        stream.read(fileBytes);
        InputStream uploadStream = new ByteArrayInputStream(fileBytes);

        BoxFileUploadSession.Info session = rootFolder.createUploadSession(
                "Tamme-Lauri_tamm_suvepäeval.jpg", fileBytes.length);
        Assert.assertNotNull(session.getUploadSessionId());
        Assert.assertNotNull(session.getSessionExpiresAt());
        Assert.assertNotNull(session.getPartSize());

        BoxFileUploadSession.Endpoints endpoints = session.getSessionEndpoints();
        Assert.assertNotNull(endpoints);
        Assert.assertNotNull(endpoints.getUploadPartEndpoint());
        Assert.assertNotNull(endpoints.getStatusEndpoint());
        Assert.assertNotNull(endpoints.getListPartsEndpoint());
        Assert.assertNotNull(endpoints.getCommitEndpoint());
        Assert.assertNotNull(endpoints.getAbortEndpoint());

        //Verify the status of the session
        this.getUploadSessionStatus(session.getResource());

        //Verify the delete session
        this.abortUploadSession(session.getResource());
    }

    private void getUploadSessionStatus(BoxFileUploadSession session) {
        BoxFileUploadSession.Info sessionInfo = session.getStatus();
        Assert.assertNotNull(sessionInfo.getSessionExpiresAt());
        Assert.assertNotNull(sessionInfo.getPartSize());
        Assert.assertNotNull(sessionInfo.getTotalParts());
        Assert.assertNotNull(sessionInfo.getPartsProcessed());
    }

    private void abortUploadSession(BoxFileUploadSession session) {
        session.abort();

        try {
            BoxFileUploadSession.Info sessionInfo = session.getStatus();

            //If the session is aborted, this line should not be executed.
            Assert.assertFalse("Upload session is not deleted", true);
        } catch (BoxAPIException apiEx) {
            Assert.assertEquals(apiEx.getResponseCode(), 404);
        }
    }
}
