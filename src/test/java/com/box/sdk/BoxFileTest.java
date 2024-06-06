package com.box.sdk;

import static com.box.sdk.BoxSharedLink.Access.OPEN;
import static com.box.sdk.TestUtils.getFixture;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.box.sdk.http.ContentType.APPLICATION_JSON_PATCH;
import static com.box.sdk.http.ContentType.APPLICATION_OCTET_STREAM;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.box.sdk.sharedlink.BoxSharedLinkRequest;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.EqualToJsonPattern;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxFile} related unit tests.
 */
public class BoxFileTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
        api.setBaseUploadURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void getAndSetTags() {
        JsonObject fakeResponse = new JsonObject();
        fakeResponse.add("type", "file");
        fakeResponse.add("id", "1234");
        JsonArray tagsJSON = new JsonArray();
        tagsJSON.add("foo");
        tagsJSON.add("bar");
        fakeResponse.add("tags", tagsJSON);

        BoxAPIConnection api = new BoxAPIConnectionForTests("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeResponse));

        BoxFile file = new BoxFile(api, "1234");
        BoxFile.Info info = file.getInfo();
        List<String> tags = info.getTags();
        assertEquals("foo", tags.get(0));
        assertEquals("bar", tags.get(1));

        tags.add("baz");
        info.setTags(tags);

        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals("foo", json.get("tags").asArray().get(0).asString());
                assertEquals("bar", json.get("tags").asArray().get(1).asString());
                assertEquals("baz", json.get("tags").asArray().get(2).asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        file.updateInfo(info);
    }

    @Test
    public void collaborateWithOptionalParamsSendsCorrectRequest() {

        final String fileID = "983745";
        final String collaboratorLogin = "boxer@example.com";
        final BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.VIEWER;

        BoxAPIConnection api = new BoxAPIConnectionForTests("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            public BoxJSONResponse onJSONRequest(BoxJSONRequest request, JsonObject body) {
                assertEquals(
                    "https://api.box.com/2.0/collaborations?notify=true",
                    request.getUrl().toString());
                assertEquals("POST", request.getMethod());

                assertEquals(fileID, body.get("item").asObject().get("id").asString());
                assertEquals("file", body.get("item").asObject().get("type").asString());
                assertEquals(collaboratorLogin, body.get("accessible_by").asObject().get("login").asString());
                assertEquals("user", body.get("accessible_by").asObject().get("type").asString());
                assertEquals(collaboratorRole.toJSONString(), body.get("role").asString());
                assertEquals("2020-04-07T19:51:30Z", body.get("expires_at").asString());
                assertTrue(body.get("is_access_only").asBoolean());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"collaboration\",\"id\":\"98763245\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, fileID);
        Date expiresAt = new Date(1586289090000L);
        file.collaborate(collaboratorLogin, collaboratorRole, true, true, expiresAt, true);
    }

    @Test
    public void testGetFileInfoSucceeds() throws ParseException {
        final String fileID = "12345";
        final String fileURL = "/2.0/files/" + fileID;
        final String fileName = "Example.pdf";
        final String pathCollectionName = "All Files";
        final String createdByLogin = "test@user.com";
        final String uploaderDisplayName = "Test User";
        final String modifiedByName = "Test User";
        final String ownedByID = "1111";
        final String classificationColor = "#00FFFF";
        final String classificationDefinition = "Content that should not be shared outside the company.";
        final String classificationName = "Top Secret";
        List<String> roles = new ArrayList<>();
        roles.add("open");

        String result = getFixture("BoxFile/GetFileInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info info = file.getInfo();

        assertEquals("file", info.getType());
        assertEquals(fileID, info.getID());
        assertEquals(fileName, info.getName());
        assertEquals(pathCollectionName, info.getPathCollection().get(0).getName());
        assertEquals(uploaderDisplayName, info.getUploaderDisplayName());
        assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        assertEquals(modifiedByName, info.getModifiedBy().getName());
        assertEquals(ownedByID, info.getOwnedBy().getID());
        assertEquals(roles, info.getAllowedInviteeRoles());
        assertEquals(classificationColor, info.getClassification().getColor());
        assertEquals(classificationDefinition, info.getClassification().getDefinition());
        assertEquals(classificationName, info.getClassification().getName());
        assertTrue(info.getIsExternallyOwned());
        assertTrue(info.getHasCollaborations());
        assertEquals(info.getDispositionAt(), BoxDateFormat.parse("2012-12-12T18:53:43Z"));
        assertTrue(info.getIsAccessibleViaSharedLink());
    }

    @Test(expected = BoxDeserializationException.class)
    public void testDeserializationException() {
        final String fileID = "12345";
        final String filesURL = "/2.0/files/" + fileID;

        String result = getFixture("BoxFile/GetFileInfoCausesDeserializationException");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(filesURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile.Info fileInfo = new BoxFile(this.api, fileID).getInfo();
        assertEquals("12345", fileInfo.getID());
    }

    @Test
    public void testRemoveSharedLink() {
        final String fileID = "12345";
        final String fileURL = "/2.0/files/" + fileID;
        JsonObject jsonObject = new JsonObject()
            .add("shared_link", (String) null);

        String putResult = getFixture("BoxFile/GetFileInfo200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withRequestBody(WireMock.containing(jsonObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(putResult)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info info = file.new Info();
        info.removeSharedLink();
        file.updateInfo(info);

        assertNull("Shared Link was not removed", info.getSharedLink());
    }

    @Test
    public void testGetTasksWithFields() {
        final String fileID = "12345";
        final String tasksURL = "/2.0/files/" + fileID + "/tasks";

        String result = getFixture("BoxFile/GetFileTasksInfoWithFields200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(tasksURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        List<BoxTask.Info> tasks = file.getTasks("is_completed");
        for (BoxTask.Info task : tasks) {
            assertTrue(task.isCompleted());
        }
    }

    @Test
    public void testUpdateFileInformationSucceedsAndSendsCorrectJson() {
        final String fileID = "12345";
        final String fileURL = "/2.0/files/" + fileID;
        final String newFileName = "New File Name";
        JsonObject updateObject = new JsonObject()
            .add("name", newFileName);

        String result = getFixture("BoxFile/UpdateFileInfo200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withRequestBody(WireMock.equalToJson(updateObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info info = file.new Info();
        info.setName(newFileName);
        file.updateInfo(info);

        assertEquals(newFileName, info.getName());
    }

    @Test
    public void testCopyFileSucceedsAndSendsCorrectJson() {
        final String fileID = "12345";
        final String fileURL = "/2.0/files/" + fileID + "/copy";
        final String parentID = "0";
        final String parentName = "All Files";
        JsonObject innerObject = new JsonObject()
            .add("id", "0");
        JsonObject parentObject = new JsonObject()
            .add("parent", innerObject);

        String result = getFixture("BoxFile/CopyFile200");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileURL))
            .withRequestBody(WireMock.equalToJson(parentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder rootFolder = BoxFolder.getRootFolder(this.api);
        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info copiedFileInfo = file.copy(rootFolder);

        assertEquals(parentID, copiedFileInfo.getParent().getID());
        assertEquals(parentName, copiedFileInfo.getParent().getName());
    }

    @Test
    public void testMoveFileSucceedsAndSendsCorrectJson() {
        final String fileID = "12345";
        final String fileURL = "/2.0/files/" + fileID;
        final String newParentID = "1111";
        final String newParentName = "Another Move Folder";
        JsonObject moveObject = new JsonObject()
            .add("id", newParentID);
        JsonObject parentObject = new JsonObject()
            .add("parent", moveObject);

        String result = getFixture("BoxFile/MoveFile200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withRequestBody(WireMock.equalToJson(parentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxFolder destinationFolder = new BoxFolder(this.api, newParentID);
        BoxItem.Info fileInfo = file.move(destinationFolder);

        assertEquals(newParentID, fileInfo.getParent().getID());
        assertEquals(fileID, fileInfo.getID());
        assertEquals(newParentName, fileInfo.getParent().getName());
    }

    @Test
    public void testDeleteFileSucceeds() {
        final String fileID = "12345";
        final String deleteFileURL = "/2.0/files/" + fileID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.delete();
    }

    @Test
    public void testLockFileSucceedsAndSendsCorrectJson() {
        final String fileID = "12345";
        final String fileURL = "/2.0/files/" + fileID;
        final boolean isDownloadPrevented = true;
        final String createdByLogin = "test@user.com";
        final String createdByName = "Test User";

        JsonObject innerObject = new JsonObject()
            .add("type", "lock")
            .add("is_download_prevented", "true");

        new JsonObject().add("lock", innerObject);

        String result = getFixture("BoxFile/LockFile200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withQueryParam("fields", WireMock.containing("lock"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxLock fileLock = file.lock(true);

        assertEquals(isDownloadPrevented, fileLock.getIsDownloadPrevented());
        assertEquals(createdByLogin, fileLock.getCreatedBy().getLogin());
        assertEquals(createdByName, fileLock.getCreatedBy().getName());
    }

    @Test
    public void testUnlockFileSucceedsAndSendSendsCorrectJson() {
        final String fileID = "12345";
        final String fileURL = "/2.0/files/" + fileID;
        JsonObject unlockObject = new JsonObject().add("lock", Json.NULL);

        String fileResult = getFixture("BoxFile/GetFileInfo200");

        String result = getFixture("BoxFile/UnlockFile200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(fileResult)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withQueryParam("fields", WireMock.containing("lock"))
            .withRequestBody(WireMock.equalToJson(unlockObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.unlock();

        assertEquals(fileID, file.getID());
        assertNull(file.getInfo().getLock());
    }

    @Test
    public void testDeleteMetadataOnFileSucceeds() {
        final String fileID = "12345";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/global/properties";

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.deleteMetadata();
    }

    @Test
    public void testGetThumbnailSucceeds() {
        final String fileID = "12345";
        wireMockRule.stubFor(
            WireMock.get(WireMock.urlPathEqualTo("/2.0/files/" + fileID))
                .withQueryParam("fields", WireMock.equalTo("representations"))
                .willReturn(WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(getFixture("BoxFile/GetFileRepresentations200", wireMockRule.httpsPort()))
                    .withStatus(200))
        );
        wireMockRule.stubFor(
            WireMock.get(WireMock.urlPathEqualTo(
                "/2.0/internal_files/12345/versions/1116420931563/representations/jpg_thumb_32x32")
                )
                .willReturn(WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(getFixture("BoxFile/GetFileRepresentation200", wireMockRule.httpsPort()))
                    .withStatus(200))
        );
        wireMockRule.stubFor(
            WireMock.get(WireMock.urlPathEqualTo(
                "/2.0/internal_files/1030335435441/versions/1116437417841/representations/jpg_thumb_32x32/content/"
                ))
                .willReturn(WireMock.aResponse()
                    .withHeader("Content-Type", "image/jpg")
                    .withBody("This is a JPG")
                    .withStatus(200))
        );

        BoxFile file = new BoxFile(this.api, fileID);
        OutputStream output = new ByteArrayOutputStream();
        file.getRepresentationContent("[jpg?dimensions=32x32]", output);
        assertThat(output.toString(), equalTo("This is a JPG"));
    }

    @Test
    public void testGetRepresentationContentThrowsWhenExceedingMaxRetries() {
        final String fileID = "12345";
        wireMockRule.stubFor(
            WireMock.get(WireMock.urlPathEqualTo("/2.0/files/" + fileID))
                .withQueryParam("fields", WireMock.equalTo("representations"))
                .willReturn(WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(getFixture("BoxFile/GetFileRepresentations200", wireMockRule.httpsPort()))
                    .withStatus(200))
        );
        wireMockRule.stubFor(
            WireMock.get(WireMock.urlPathEqualTo(
                    "/2.0/internal_files/12345/versions/1116420931563/representations/jpg_thumb_32x32")
                )
                .willReturn(WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(getFixture("BoxFile/GetFileRepresentation200WithPending", wireMockRule.httpsPort()))
                    .withStatus(200))
        );

        try {
            BoxFile file = new BoxFile(this.api, fileID);
            OutputStream output = new ByteArrayOutputStream();
            file.getRepresentationContent("[jpg?dimensions=32x32]", "", output, 5);
            fail("getRepresentationContent did not fail with BoxAPIException due to pending status");
            assertThat(output.toString(), equalTo("This is a JPG"));
        } catch (BoxAPIException apiException) {
            assertEquals(
                apiException.getMessage(),
                "Representation did non have a success status allowing it to be retrieved after 5 attempts"
            );
        }
    }

    @Test
    public void testGetRepresentationContentSuccess() {
        final String fileID = "12345";
        wireMockRule.stubFor(
            WireMock.get(WireMock.urlPathEqualTo("/2.0/files/" + fileID))
                .withQueryParam("fields", WireMock.equalTo("representations"))
                .willReturn(WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(getFixture("BoxFile/GetFileRepresentations200", wireMockRule.httpsPort()))
                    .withStatus(200))
        );
        wireMockRule.stubFor(
            WireMock.get(WireMock.urlPathEqualTo(
                    "/2.0/internal_files/12345/versions/1116420931563/representations/jpg_thumb_32x32")
                )
                .inScenario("Get file representation status info")
                .willSetStateTo("pending status")
                .willReturn(WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(getFixture("BoxFile/GetFileRepresentation200WithPending", wireMockRule.httpsPort()))
                    .withStatus(200))
        );
        wireMockRule.stubFor(
            WireMock.get(WireMock.urlPathEqualTo(
                    "/2.0/internal_files/12345/versions/1116420931563/representations/jpg_thumb_32x32")
                )
                .inScenario("Get file representation status info")
                .whenScenarioStateIs("pending status")
                .willReturn(WireMock.aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(getFixture("BoxFile/GetFileRepresentation200", wireMockRule.httpsPort()))
                    .withStatus(200))
        );

        wireMockRule.stubFor(
            WireMock.get(WireMock.urlPathEqualTo(
                    "/2.0/internal_files/1030335435441/versions/1116437417841/representations/jpg_thumb_32x32/content/"
                ))
                .willReturn(WireMock.aResponse()
                    .withHeader("Content-Type", "image/jpg")
                    .withBody("This is a JPG")
                    .withStatus(200))
        );

        BoxFile file = new BoxFile(this.api, fileID);
        OutputStream output = new ByteArrayOutputStream();
        file.getRepresentationContent("[jpg?dimensions=32x32]", output);
        assertThat(output.toString(), equalTo("This is a JPG"));
    }

    @Test
    public void testDeletePreviousFileVersionSucceeds() {
        final String versionID = "12345";
        final String fileID = "1111";
        final String fileURL = "/2.0/files/" + fileID + "/versions";
        final String versionURL = "/2.0/files/" + fileID + "/versions/" + versionID;

        String result = getFixture("BoxFile/GetFileInfo200");

        String versionResult = getFixture("BoxFile/GetAllFileVersions200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(versionResult)));

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(versionURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        Collection<BoxFileVersion> versions = file.getVersions();
        BoxFileVersion firstVersion = versions.iterator().next();
        firstVersion.delete();
    }

    @Test
    public void testCreateMetadataOnFileSucceeds() {
        final String metadataID = "12345";
        final String fileID = "12345";
        final String scope = "global";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/global/properties";
        JsonObject metadataObject = new JsonObject()
            .add("foo", "bar");

        String result = getFixture("BoxFile/CreateMetadataOnFile201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        Metadata info = file.createMetadata(new Metadata().add("/foo", "bar"));

        assertEquals(metadataID, info.getID());
        assertEquals(scope, info.getScope());
    }

    @Test
    public void testGetMetadataOnFileSucceeds() {
        final String fileID = "12345";
        final String metadataID = "12345";
        final String parent = "file_1111";
        final String template = "properties";
        final String scope = "global";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/global/properties";

        String result = getFixture("BoxFile/GetMetadataOnFile200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        Metadata metadata = file.getMetadata();

        assertEquals(metadataID, metadata.getID());
        assertEquals(parent, metadata.getParentID());
        assertEquals(template, metadata.getTemplateName());
        assertEquals(scope, metadata.getScope());
    }

    @Test
    public void testUploadNewVersionReturnsCorrectInfo() {
        String fileID = "11111";
        String fileName = "test.txt";
        byte[] bytes = new byte[]{1, 2, 3};
        InputStream fileContents = new ByteArrayInputStream(bytes);

        String result = getFixture("BoxFile/UploadNewVersion201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo("/2.0/files/" + fileID + "/content"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);

        BoxFile.Info info = file.uploadNewVersion(fileContents);

        assertEquals(fileID, info.getID());
        assertEquals(fileName, info.getName());
    }

    @Test
    public void createSharedLinkSucceeds() {
        final String fileID = "1111";
        final String password = "test1";

        JsonObject permissionsObject = new JsonObject()
            .add("can_download", true)
            .add("can_preview", true)
            .add("can_edit", false);

        JsonObject innerObject = new JsonObject()
            .add("password", password)
            .add("access", "open")
            .add("permissions", permissionsObject);

        JsonObject sharedLinkObject = new JsonObject()
            .add("shared_link", innerObject);

        String result = getFixture("BoxSharedLink/CreateSharedLink201");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo("/2.0/files/" + fileID))
            .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();

        permissions.setCanDownload(true);
        permissions.setCanPreview(true);
        BoxSharedLink sharedLink = file.createSharedLink(
            new BoxSharedLinkRequest().access(OPEN)
                .permissions(true, true)
                .password(password)
        );
        assertTrue(sharedLink.getIsPasswordEnabled());
    }

    @Test
    public void createEditableSharedLinkSucceeds() {
        final String fileID = "1111";
        final String password = "test1";

        JsonObject permissionsObject = new JsonObject()
            .add("can_download", true)
            .add("can_preview", true)
            .add("can_edit", true);

        JsonObject innerObject = new JsonObject()
            .add("password", password)
            .add("access", "open")
            .add("permissions", permissionsObject);

        JsonObject sharedLinkObject = new JsonObject()
            .add("shared_link", innerObject);

        String result = getFixture("BoxSharedLink/CreateEditableSharedLink201");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo("/2.0/files/" + fileID))
            .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);

        BoxSharedLink sharedLink = file.createSharedLink(
            new BoxSharedLinkRequest().access(OPEN)
                .permissions(true, true, true)
                .password(password)
        );
        assertTrue(sharedLink.getIsPasswordEnabled());
        assertTrue(sharedLink.getPermissions().getCanEdit());
    }

    @Test
    public void testAddClassification() {
        final String fileID = "12345";
        final String classificationType = "Public";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("Box__Security__Classification__Key", classificationType);

        String result = getFixture("BoxFile/CreateClassificationOnFile201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.addClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    public void testUpdateClassification() {
        final String fileID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("op", "add")
            .add("path", "/Box__Security__Classification__Key")
            .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
            .add(metadataObject);

        String result = getFixture("BoxFile/UpdateClassificationOnFile200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON_PATCH)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.updateClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    public void testSetClassification() {
        final String fileID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("op", "replace")
            .add("path", "/Box__Security__Classification__Key")
            .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
            .add(metadataObject);

        String result = getFixture("BoxFile/UpdateClassificationOnFile200");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withBody("{}")
                .withStatus(409)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
            .withHeader("Content-Type", WireMock.equalTo(APPLICATION_JSON_PATCH))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.setClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test(expected = BoxAPIResponseException.class)
    public void testSetClassificationThrowsException() {
        final String fileID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withBody("{}")
                .withStatus(403)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.setClassification(classificationType);
    }

    @Test
    public void testGetClassification() {
        final String fileID = "12345";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        String result = getFixture("BoxFile/CreateClassificationOnFile201");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.getClassification();

        assertEquals("Public", classification);
    }

    @Test
    public void testGetClassificationReturnsNone() {
        final String fileID = "12345";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        String getResult = getFixture("BoxException/BoxResponseException404");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(getResult)
                .withStatus(404)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.getClassification();

        assertNull(classification);
    }

    @Test(expected = BoxAPIException.class)
    public void testGetClassificationThrows() {
        final String fileID = "12345";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        String getResult = getFixture("BoxException/BoxResponseException403");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(getResult)
                .withStatus(403)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.getClassification();
    }

    @Test
    public void testDeleteClassification() {
        final String fileID = "12345";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON_PATCH)
                .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.deleteClassification();
    }

    @Test
    public void testSetMetadataReturnsCorrectly() {
        final String fileID = "12345";
        final String metadataURL = "/2.0/files/" + fileID + "/metadata/enterprise/testtemplate";
        ArrayList<String> secondValueArray = new ArrayList<>();
        secondValueArray.add("first");
        secondValueArray.add("second");
        secondValueArray.add("third");

        String postResult = getFixture("/BoxException/BoxResponseException409");
        String putResult = getFixture("/BoxFile/UpdateMetadataOnFile200");

        final String firstValue = "text";
        JsonArray secondValueJson = new JsonArray()
            .add("first")
            .add("second")
            .add("third");
        final int thirdValue = 2;
        final double fourthValue = 233333333333333340.0;

        JsonObject firstAttribute = new JsonObject()
            .add("op", "add")
            .add("path", "/test1")
            .add("value", firstValue);

        JsonObject secondAttribute = new JsonObject()
            .add("op", "add")
            .add("path", "/test2")
            .add("value", secondValueJson);

        JsonObject thirdAttribute = new JsonObject()
            .add("op", "add")
            .add("path", "/test3")
            .add("value", thirdValue);

        JsonObject fourthAttribute = new JsonObject()
            .add("op", "add")
            .add("path", "/test4")
            .add("value", fourthValue);

        JsonArray jsonArray = new JsonArray()
            .add(firstAttribute)
            .add(secondAttribute)
            .add(thirdAttribute)
            .add(fourthAttribute);

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(postResult)
                .withStatus(409)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(jsonArray.toString()))
            .withHeader("Content-Type", WireMock.equalTo(APPLICATION_JSON_PATCH))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(putResult)
                .withStatus(200)));

        BoxFile file = new BoxFile(this.api, fileID);

        Metadata metadata = new Metadata()
            .add("/test1", firstValue)
            .add("/test2", secondValueArray)
            .add("/test3", thirdValue)
            .add("/test4", fourthValue);

        Metadata metadataValues = file.setMetadata("testtemplate", "enterprise", metadata);

        assertEquals("file_12345", metadataValues.getParentID());
        assertEquals("testtemplate", metadataValues.getTemplateName());
        assertEquals("enterprise_11111", metadataValues.getScope());
        assertEquals(firstValue, metadataValues.getString("/test1"));
        assertEquals(secondValueJson, metadataValues.getValue("/test2"));
        assertEquals(thirdValue, metadataValues.getDouble("/test3"), 0);
        assertEquals(fourthValue, metadataValues.getDouble("/test4"), 4);
    }

    @Test
    public void testChunkedUploadWithCorrectPartSize() throws IOException, InterruptedException {
        final String preflightURL = "/2.0/files/content";
        final String sessionURL = "/2.0/files/upload_sessions";
        final String uploadURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        FakeStream stream = new FakeStream("aaaaa");

        String sessionResult = getFixture("BoxFile/CreateUploadSession201", wireMockRule.httpsPort());
        String uploadResult = getFixture("BoxFile/UploadPartOne200");
        String commitResult = getFixture("BoxFile/CommitUpload201");
        String canUploadResult = getFixture("BoxFile/CanUploadFile200");

        JsonObject idObject = new JsonObject()
            .add("id", "12345");

        JsonObject preflightObject = new JsonObject()
            .add("name", "testfile.txt")
            .add("size", 5)
            .add("parent", idObject);

        JsonObject sessionObject = new JsonObject()
            .add("folder_id", "12345")
            .add("file_size", 5)
            .add("file_name", "testfile.txt");

        JsonObject partOne = new JsonObject()
            .add("part_id", "CFEB5BA9")
            .add("offset", 0)
            .add("size", 5);

        JsonArray parts = new JsonArray()
            .add(partOne);

        JsonObject commitObject = new JsonObject()
            .add("parts", parts);

        wireMockRule.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
            .withHeader("Content-Type", WireMock.equalTo(APPLICATION_JSON))
            .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(200)
                .withBody(canUploadResult)));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
            .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(sessionResult)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing(APPLICATION_OCTET_STREAM))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(uploadResult)));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
            .withHeader("Content-Type", WireMock.equalTo(APPLICATION_JSON))
            .withRequestBody(WireMock.containing(commitObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(commitResult)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        BoxFile.Info uploadedFile = folder.uploadLargeFile(stream, "testfile.txt", 5);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
    }

    @Test
    public void testChunkedVersionUploadWithCorrectPartSizeAndAttributes() throws IOException, InterruptedException {
        final String sessionURL = "/2.0/files/1111111/upload_sessions";
        final String uploadURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        FakeStream stream = new FakeStream("aaaaa");

        String sessionResult = getFixture("BoxFile/CreateUploadSession201", wireMockRule.httpsPort());
        String uploadResult = getFixture("BoxFile/UploadPartOne200");
        String commitResult = getFixture("BoxFile/CommitUploadWithAttributes201");

        JsonObject sessionObject = new JsonObject()
            .add("file_size", 5);

        JsonObject partOne = new JsonObject()
            .add("part_id", "CFEB5BA9")
            .add("offset", 0)
            .add("size", 5);

        JsonArray parts = new JsonArray()
            .add(partOne);

        Map<String, String> fileAttributes = new HashMap<>();
        fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

        JsonObject fileAttributesJson = new JsonObject();
        for (String attrKey : fileAttributes.keySet()) {
            fileAttributesJson.set(attrKey, fileAttributes.get(attrKey));
        }

        JsonObject commitObject = new JsonObject()
            .add("parts", parts)
            .add("attributes", fileAttributesJson);

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
            .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(sessionResult)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing(APPLICATION_OCTET_STREAM))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(uploadResult)));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
            .withHeader("Content-Type", WireMock.equalTo(APPLICATION_JSON))
            .withRequestBody(WireMock.containing(commitObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(commitResult)));

        BoxFile file = new BoxFile(this.api, "1111111");
        BoxFile.Info uploadedFile = file.uploadLargeFile(stream, 5, fileAttributes);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
        assertEquals(1491613088000L, uploadedFile.getContentModifiedAt().getTime());
    }

    @Test
    public void setsVanityUrlOnASharedLink() {
        //given
        BoxAPIConnection api = new BoxAPIConnectionForTests("");
        api.setRequestInterceptor(
            request -> {
                //then
                JsonObject responseJson = Json.parse(request.bodyToString()).asObject();
                JsonObject sharedLinkJson = responseJson.get("shared_link").asObject();
                assertThat(sharedLinkJson.get("vanity_name").asString(), is("myCustomName"));
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        );
        BoxSharedLinkRequest sharedLink = new BoxSharedLinkRequest()
            .vanityName("myCustomName");

        //when
        BoxFile file = new BoxFile(api, "12345");
        file.createSharedLink(sharedLink);
    }

    @Test
    public void createDefaultSharedLink() {
        //given
        BoxAPIConnection api = new BoxAPIConnectionForTests("");
        api.setRequestInterceptor(
            request -> {
                //then
                String requestString = request.bodyToString();
                assertThat(requestString, is("{\"shared_link\":{}}"));
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        );
        BoxSharedLinkRequest sharedLink = new BoxSharedLinkRequest();

        //when
        BoxFile file = new BoxFile(api, "12345");
        file.createSharedLink(sharedLink);
    }

    @Test
    public void setMetadataWorksWhenNoChangesSubmittedAndConflictOccured() {
        // given
        BoxAPIConnection api = new BoxAPIConnectionForTests("");
        BoxFile file = new BoxFile(api, "someFile");
        final AtomicInteger postCounter = new AtomicInteger(0);
        final AtomicInteger getCounter = new AtomicInteger(0);
        api.setRequestInterceptor(
            request -> {
                if (request.getMethod().equals("POST")) {
                    postCounter.incrementAndGet();
                    throw new BoxAPIException("Conflict", 409, "{}");
                }
                if (request.getMethod().equals("GET")) {
                    getCounter.incrementAndGet();
                }
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        );

        // when
        file.setMetadata("template1", "enterprise-1", new Metadata());

        // then
        assertThat(postCounter.get(), is(1));
        assertThat(getCounter.get(), is(1));
    }

    @Test
    public void getVersionsWithSpecificFieldsAndDefaultLimit() {
        // given
        BoxAPIConnection api = new BoxAPIConnectionForTests("");
        BoxFile file = new BoxFile(api, "6543");

        // then
        api.setRequestInterceptor(
            request -> {
                try {
                    String query = URLDecoder.decode(request.getUrl().getQuery(), "UTF-8");
                    assertThat(query, containsString("limit=1000&offset=0&fields=name,version_number"));
                    return new BoxJSONResponse() {
                        @Override
                        public String getJSON() {
                            return "{\"entries\": [], \"total_count\": 100}";
                        }
                    };
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        );

        // when
        file.getVersions("name", "version_number");
    }

    @Test
    public void getVersionsWithLimitAndOffset() {
        // given
        BoxAPIConnection api = new BoxAPIConnectionForTests("");
        BoxFile file = new BoxFile(api, "6543");

        // then
        api.setRequestInterceptor(
                request -> {
                    try {
                        String query = URLDecoder.decode(request.getUrl().getQuery(), "UTF-8");
                        assertThat(query, containsString("limit=10&offset=0&fields=name,version_number"));
                        return new BoxJSONResponse() {
                            @Override
                            public String getJSON() {
                                return "{\"entries\": [], \"total_count\": 100}";
                            }
                        };
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        // when
        file.getVersionsRange(0, 10, "name", "version_number");
    }

    @Test
    public void setsDispositionAt() throws ParseException {
        //given
        String fileId = "12345";
        final String fileURL = "/2.0/files/" + fileId;
        String dispositionAtString = "2012-12-12T18:53:43Z";
        String result = getFixture("BoxFile/GetFileInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));
        api.setRequestInterceptor(
            request -> {
                //then
                if (request.getMethod().equals("PUT")) {
                    JsonObject requestJson = Json.parse(request.bodyToString()).asObject();
                    String dispositionAt = requestJson.get("disposition_at").asString();
                    assertThat(dispositionAt, is(dispositionAtString));
                }
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return result;
                    }
                };
            }
        );

        //when
        BoxFile file = new BoxFile(api, fileId);
        BoxFile.Info info = file.getInfo();
        info.setDispositionAt(BoxDateFormat.parse(dispositionAtString));
        file.updateInfo(info);
    }

    /**
     * Fake stream class used in testing in uploadLargeFile() if part size is populated correctly.
     */
    public static class FakeStream extends InputStream {
        String value;
        int pointer;

        public FakeStream(String value) {
            this.value = value;
            this.pointer = 0;
        }

        @Override
        public int read() {
            char a = this.value.toCharArray()[this.pointer];
            this.pointer += 1;
            return a;
        }

        @Override
        public int read(byte[] b, int offset, int len) {
            b[offset] = this.value.getBytes(UTF_8)[offset];
            this.pointer += 1;
            return 1;
        }
    }

    @Test
    public void renameFile() {
        final String fileID = "12345";
        final String metadataURL = "/2.0/files/" + fileID;
        String result = getFixture("BoxFile/GetFileInfo200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(new EqualToJsonPattern("{\"name\": \"New Name\"}", false, false))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)
                .withStatus(200)));

        new BoxFile(this.api, fileID).rename("New Name");
    }
}
