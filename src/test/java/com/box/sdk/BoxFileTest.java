package com.box.sdk;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assume;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxFile} related unit tests.
 */
public class BoxFileTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    static final String LARGE_FILE_NAME = "Tamme-Lauri_tamm_suvepäeval.jpg";

    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void getAndSetTags() {
        JsonObject fakeResponse = new JsonObject();
        fakeResponse.add("type", "file");
        fakeResponse.add("id", "1234");
        JsonArray tagsJSON = new JsonArray();
        tagsJSON.add("foo");
        tagsJSON.add("bar");
        fakeResponse.add("tags", tagsJSON);

        BoxAPIConnection api = new BoxAPIConnection("");
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

        BoxAPIConnection api = new BoxAPIConnection("");
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

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"collaboration\",\"id\":\"98763245\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, fileID);
        file.collaborate(collaboratorLogin, collaboratorRole, true, true);
    }

    @Test
    public void testGetFileInfoSucceeds() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
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

        String result = TestConfig.getFixture("BoxFile/GetFileInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
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
    }

    @Test(expected = BoxDeserializationException.class)
    public void testDeserializationException() throws IOException {
        final String fileID = "12345";
        final String filesURL = "/files/" + fileID;

        String result = TestConfig.getFixture("BoxFile/GetFileInfoCausesDeserializationException");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(filesURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile.Info fileInfo = new BoxFile(this.api, fileID).getInfo();
        assertEquals("12345", fileInfo.getID());
    }

    @Test
    public void testRemoveSharedLink() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        JsonObject jsonObject = new JsonObject()
            .add("shared_link", (String) null);

        String putResult = TestConfig.getFixture("BoxFile/GetFileInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withRequestBody(WireMock.containing(jsonObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(putResult)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info info = file.new Info();
        info.removeSharedLink();
        file.updateInfo(info);

        assertNull("Shared Link was not removed", info.getSharedLink());
    }

    @Test
    public void testGetTasksWithFields() throws IOException {
        final String fileID = "12345";
        final String tasksURL = "/files/" + fileID + "/tasks";

        String result = TestConfig.getFixture("BoxFile/GetFileTasksInfoWithFields200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(tasksURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        List<BoxTask.Info> tasks = file.getTasks("is_completed");
        for (BoxTask.Info task : tasks) {
            assertTrue(task.isCompleted());
        }
    }

    @Test
    public void testUpdateFileInformationSucceedsAndSendsCorrectJson() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        final String newFileName = "New File Name";
        JsonObject updateObject = new JsonObject()
            .add("name", newFileName);

        String result = TestConfig.getFixture("BoxFile/UpdateFileInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withRequestBody(WireMock.equalToJson(updateObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info info = file.new Info();
        info.setName(newFileName);
        file.updateInfo(info);

        assertEquals(newFileName, info.getName());
    }

    @Test
    public void testCopyFileSucceedsAndSendsCorrectJson() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID + "/copy";
        final String parentID = "0";
        final String parentName = "All Files";
        JsonObject innerObject = new JsonObject()
            .add("id", "0");
        JsonObject parentObject = new JsonObject()
            .add("parent", innerObject);

        String result = TestConfig.getFixture("BoxFile/CopyFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileURL))
            .withRequestBody(WireMock.equalToJson(parentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder rootFolder = BoxFolder.getRootFolder(this.api);
        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info copiedFileInfo = file.copy(rootFolder);

        assertEquals(parentID, copiedFileInfo.getParent().getID());
        assertEquals(parentName, copiedFileInfo.getParent().getName());
    }

    @Test
    public void testMoveFileSucceedsAndSendsCorrectJson() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        final String newParentID = "1111";
        final String newParentName = "Another Move Folder";
        JsonObject moveObject = new JsonObject()
            .add("id", newParentID);
        JsonObject parentObject = new JsonObject()
            .add("parent", moveObject);

        String result = TestConfig.getFixture("BoxFile/MoveFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withRequestBody(WireMock.equalToJson(parentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
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
        final String deleteFileURL = "/files/" + fileID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.delete();
    }

    @Test
    public void testLockFileSucceedsAndSendsCorrectJson() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        final boolean isDownloadPrevented = true;
        final String createdByLogin = "test@user.com";
        final String createdByName = "Test User";

        JsonObject innerObject = new JsonObject()
            .add("type", "lock")
            .add("is_download_prevented", "true");

        new JsonObject().add("lock", innerObject);

        String result = TestConfig.getFixture("BoxFile/LockFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withQueryParam("fields", WireMock.containing("lock"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxLock fileLock = file.lock(true);

        assertEquals(isDownloadPrevented, fileLock.getIsDownloadPrevented());
        assertEquals(createdByLogin, fileLock.getCreatedBy().getLogin());
        assertEquals(createdByName, fileLock.getCreatedBy().getName());
    }

    @Test
    public void testUnlockFileSucceedsAndSendSendsCorrectJson() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        JsonObject unlockObject = new JsonObject().add("lock", Json.NULL);

        String fileResult = TestConfig.getFixture("BoxFile/GetFileInfo200");

        String result = TestConfig.getFixture("BoxFile/UnlockFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(fileResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
            .withQueryParam("fields", WireMock.containing("lock"))
            .withRequestBody(WireMock.equalToJson(unlockObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.unlock();

        assertEquals(fileID, file.getID());
        assertNull(file.getInfo().getLock());
    }

    @Test
    public void testDeleteMetadataOnFileSucceeds() {
        final String fileID = "12345";
        final String metadataURL = "/files/" + fileID + "/metadata/global/properties";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.deleteMetadata();
    }

    @Test
    public void testGetThumbnailSucceeds() {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID + "/thumbnail.jpg";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(200)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.getThumbnail(BoxFile.ThumbnailFileType.JPG, 256, 256, 256, 256);
    }

    @Test
    public void testDeletePreviousFileVersionSucceeds() throws IOException {
        final String versionID = "12345";
        final String fileID = "1111";
        final String fileURL = "/files/" + fileID + "/versions";
        final String versionURL = "/files/" + fileID + "/versions/" + versionID;

        String result = TestConfig.getFixture("BoxFile/GetFileInfo200");

        String versionResult = TestConfig.getFixture("BoxFile/GetAllFileVersions200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(versionResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(versionURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        Collection<BoxFileVersion> versions = file.getVersions();
        BoxFileVersion firstVersion = versions.iterator().next();
        firstVersion.delete();
    }

    @Test
    public void testCreateMetadataOnFileSucceeds() throws IOException {
        final String metadataID = "12345";
        final String fileID = "12345";
        final String scope = "global";
        final String metadataURL = "/files/" + fileID + "/metadata/global/properties";
        JsonObject metadataObject = new JsonObject()
            .add("foo", "bar");

        String result = TestConfig.getFixture("BoxFile/CreateMetadataOnFile201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        Metadata info = file.createMetadata(new Metadata().add("/foo", "bar"));

        assertEquals(metadataID, info.getID());
        assertEquals(scope, info.getScope());
    }

    @Test
    public void testGetMetadataOnFileSucceeds() throws IOException {
        final String fileID = "12345";
        final String metadataID = "12345";
        final String parent = "file_1111";
        final String template = "properties";
        final String scope = "global";
        final String metadataURL = "/files/" + fileID + "/metadata/global/properties";

        String result = TestConfig.getFixture("BoxFile/GetMetadataOnFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        Metadata metadata = file.getMetadata();

        assertEquals(metadataID, metadata.getID());
        assertEquals(parent, metadata.getParentID());
        assertEquals(template, metadata.getTemplateName());
        assertEquals(scope, metadata.getScope());
    }

    @Test
    public void testUploadNewVersionReturnsCorrectInfo() throws IOException {
        String fileID = "11111";
        String fileName = "test.txt";
        byte[] bytes = new byte[]{1, 2, 3};
        InputStream fileContents = new ByteArrayInputStream(bytes);

        String result = TestConfig.getFixture("BoxFile/UploadNewVersion201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo("/files/" + fileID + "/content"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);

        BoxFile.Info info = file.uploadNewVersion(fileContents);

        assertEquals(fileID, info.getID());
        assertEquals(fileName, info.getName());
    }

    @Test
    public void createSharedLinkSucceeds() throws IOException {
        final String fileID = "1111";
        final String password = "test1";

        JsonObject permissionsObject = new JsonObject()
            .add("can_download", true)
            .add("can_preview", true);

        JsonObject innerObject = new JsonObject()
            .add("password", password)
            .add("access", "open")
            .add("permissions", permissionsObject);

        JsonObject sharedLinkObject = new JsonObject()
            .add("shared_link", innerObject);

        String result = TestConfig.getFixture("BoxSharedLink/CreateSharedLink201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo("/files/" + fileID))
            .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();

        permissions.setCanDownload(true);
        permissions.setCanPreview(true);
        BoxSharedLink sharedLink = file.createSharedLink(BoxSharedLink.Access.OPEN, null, permissions,
            password);
        assertTrue(sharedLink.getIsPasswordEnabled());
    }

    @Test
    public void testAddClassification() throws IOException {
        final String fileID = "12345";
        final String classificationType = "Public";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("Box__Security__Classification__Key", classificationType);

        String result = TestConfig.getFixture("BoxFile/CreateClassificationOnFile201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.addClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    public void testUpdateClassification() throws IOException {
        final String fileID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("op", "add")
            .add("path", "/Box__Security__Classification__Key")
            .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
            .add(metadataObject);

        String result = TestConfig.getFixture("BoxFile/UpdateClassificationOnFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json-patch+json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.updateClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    public void testSetClassification() throws IOException {
        final String fileID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("op", "replace")
            .add("path", "/Box__Security__Classification__Key")
            .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
            .add(metadataObject);

        String result = TestConfig.getFixture("BoxFile/UpdateClassificationOnFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withStatus(409)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json-patch+json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.setClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test(expected = BoxAPIResponseException.class)
    public void testSetClassificationThrowsException() {
        final String fileID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withStatus(403)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.setClassification(classificationType);
    }

    @Test
    public void testGetClassification() throws IOException {
        final String fileID = "12345";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        String result = TestConfig.getFixture("BoxFile/CreateClassificationOnFile201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.getClassification();

        assertEquals("Public", classification);
    }

    @Test
    public void testGetClassificationReturnsNone() throws IOException {
        final String fileID = "12345";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        String getResult = TestConfig.getFixture("BoxException/BoxResponseException404");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(getResult)
                .withStatus(404)));

        BoxFile file = new BoxFile(this.api, fileID);
        String classification = file.getClassification();

        assertNull(classification);
    }

    @Test(expected = BoxAPIException.class)
    public void testGetClassificationThrows() throws IOException {
        final String fileID = "12345";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        String getResult = TestConfig.getFixture("BoxException/BoxResponseException403");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(getResult)
                .withStatus(403)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.getClassification();
    }

    @Test
    public void testDeleteClassification() {
        final String fileID = "12345";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json-patch+json")
                .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.deleteClassification();
    }

    @Test
    public void testSetMetadataReturnsCorrectly() throws IOException {
        final String fileID = "12345";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/testtemplate";
        ArrayList<String> secondValueArray = new ArrayList<>();
        secondValueArray.add("first");
        secondValueArray.add("second");
        secondValueArray.add("third");

        String postResult = TestConfig.getFixture("/BoxException/BoxResponseException409");
        String putResult = TestConfig.getFixture("/BoxFile/UpdateMetadataOnFile200");

        final String firstValue = "text";
        JsonArray secondValueJson = new JsonArray()
            .add("first")
            .add("second")
            .add("third");
        final int thirdValue = 2;
        final float fourthValue = 1234567890f;
        final double fifthValue = 233333333333333340.0;

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

        JsonObject fifthAttribute = new JsonObject()
            .add("op", "add")
            .add("path", "/test5")
            .add("value", fifthValue);

        JsonArray jsonArray = new JsonArray()
            .add(firstAttribute)
            .add(secondAttribute)
            .add(thirdAttribute)
            .add(fourthAttribute)
            .add(fifthAttribute);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(postResult)
                .withStatus(409)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(jsonArray.toString()))
            .withHeader("Content-Type", WireMock.equalTo("application/json-patch+json"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json-patch+json")
                .withBody(putResult)
                .withStatus(200)));

        BoxFile file = new BoxFile(this.api, "12345");

        Metadata metadata = new Metadata()
            .add("/test1", firstValue)
            .add("/test2", secondValueArray)
            .add("/test3", thirdValue)
            .add("/test4", fourthValue)
            .add("/test5", fifthValue);

        Metadata metadataValues = file.setMetadata("testtemplate", "enterprise", metadata);

        assertEquals("file_12345", metadataValues.getParentID());
        assertEquals("testtemplate", metadataValues.getTemplateName());
        assertEquals("enterprise_11111", metadataValues.getScope());
        assertEquals(firstValue, metadataValues.getString("/test1"));
        assertEquals(secondValueJson, metadataValues.getValue("/test2"));
        assertEquals(thirdValue, metadataValues.getDouble("/test3"), 0);
        assertEquals(fourthValue, metadataValues.getDouble("/test4"), 4);
        assertEquals(fifthValue, metadataValues.getDouble("/test5"), 0);
    }

    @Test
    public void testChunkedUploadWithCorrectPartSize() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        final String preflightURL = "/files/content";
        final String sessionURL = "/files/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        FakeStream stream = new FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String uploadResult = TestConfig.getFixture("BoxFile/UploadPartOne200");
        String commitResult = TestConfig.getFixture("BoxFile/CommitUpload201");

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

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
            .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
            .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(sessionResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(uploadResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
            .withHeader("Content-Type", WireMock.equalTo("application/json"))
            .withRequestBody(WireMock.containing(commitObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
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
        final String sessionURL = "/files/1111111/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        FakeStream stream = new FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String uploadResult = TestConfig.getFixture("BoxFile/UploadPartOne200");
        String commitResult = TestConfig.getFixture("BoxFile/CommitUploadWithAttributes201");

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

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
            .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(sessionResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(uploadResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
            .withHeader("Content-Type", WireMock.equalTo("application/json"))
            .withRequestBody(WireMock.containing(commitObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
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
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(
            new RequestInterceptor() {
                @Override
                public BoxAPIResponse onRequest(BoxAPIRequest request) {
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
            }
        );
        BoxSharedLink sharedLink = new BoxSharedLink();
        sharedLink.setVanityName("myCustomName");

        //when
        BoxFile file = new BoxFile(api, "12345");
        file.createSharedLink(sharedLink);
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
}
