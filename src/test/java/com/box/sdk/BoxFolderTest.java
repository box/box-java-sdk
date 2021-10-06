package com.box.sdk;

import static com.box.sdk.BoxFolder.SortDirection.DESC;
import static com.box.sdk.BoxSharedLink.Access.OPEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.box.sdk.sharedlink.BoxSharedLinkRequest;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxFolder} related unit tests.
 */
public class BoxFolderTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testChunkedUploadThrows409() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        final String preflightURL = "/files/content";
        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String getResult = TestConfig.getFixture("BoxException/BoxResponseException409");

        JsonObject idObject = new JsonObject()
            .add("id", "12345");

        JsonObject preflightObject = new JsonObject()
            .add("name", "testfile.txt")
            .add("size", 5)
            .add("parent", idObject);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
            .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(getResult)
                .withStatus(409)));

        try {
            BoxFolder folder = new BoxFolder(this.api, "12345");
            folder.uploadLargeFile(stream, "testfile.txt", 5);
            fail("Preflight check for chunked upload did not fail with 409.");
        } catch (BoxAPIException apiEx) {
            assertEquals(apiEx.getResponseCode(), 409);
        }
    }

    @Test
    public void testChunkedUploadWithCorrectPartSizeAndAttributes() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        final String preflightURL = "/files/content";
        final String sessionURL = "/files/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String uploadResult = TestConfig.getFixture("BoxFile/UploadPartOne200");
        String commitResult = TestConfig.getFixture("BoxFile/CommitUploadWithAttributes201");

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

        Map<String, String> fileAttributes = new HashMap<>();
        fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

        JsonObject fileAttributesJson = new JsonObject();
        for (String attrKey : fileAttributes.keySet()) {
            fileAttributesJson.set(attrKey, fileAttributes.get(attrKey));
        }

        JsonObject commitObject = new JsonObject()
            .add("parts", parts)
            .add("attributes", fileAttributesJson);

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
        BoxFile.Info uploadedFile = folder.uploadLargeFile(stream, "testfile.txt", 5, fileAttributes);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
        assertEquals(1491613088000L, uploadedFile.getContentModifiedAt().getTime());
    }

    @Test
    public void testChunkedParallelUploadWithCorrectPartSizeAndAttributes() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        final String preflightURL = "/files/content";
        final String sessionURL = "/files/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String uploadResult = TestConfig.getFixture("BoxFile/UploadPartOne200");
        String commitResult = TestConfig.getFixture("BoxFile/CommitUploadWithAttributes201");

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

        Map<String, String> fileAttributes = new HashMap<>();
        fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

        JsonObject fileAttributesJson = new JsonObject();
        for (String attrKey : fileAttributes.keySet()) {
            fileAttributesJson.set(attrKey, fileAttributes.get(attrKey));
        }

        JsonObject commitObject = new JsonObject()
            .add("parts", parts)
            .add("attributes", fileAttributesJson);

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
        BoxFile.Info uploadedFile = folder.uploadLargeFile(stream, "testfile.txt",
            5, 2, 60, TimeUnit.SECONDS, fileAttributes);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
        assertEquals(1491613088000L, uploadedFile.getContentModifiedAt().getTime());
    }

    @Test
    public void testChunkedUploadWith500Error() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        String responseBody500 = TestConfig.getFixture("BoxException/BoxResponseException500");
        final String preflightURL = "/files/content";
        final String sessionURL = "/files/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String listPartsURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/parts";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";

        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String partsResult = TestConfig.getFixture("BoxFile/ListUploadedPart200");
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
                .withBody(sessionResult)
                .withStatus(201)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(responseBody500)
                .withStatus(500)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(listPartsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(partsResult)
                .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
            .withHeader("Content-Type", WireMock.equalTo("application/json"))
            .withRequestBody(WireMock.containing(commitObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(commitResult)
                .withStatus(201)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        BoxFile.Info uploadedFile = folder.uploadLargeFile(stream, "testfile.txt", 5);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
    }

    @Test
    public void testRetryingChunkedUploadWith500Error() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        String responseBody500 = TestConfig.getFixture("BoxException/BoxResponseException500");
        final String preflightURL = "/files/content";
        final String sessionURL = "/files/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String listPartsURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/parts";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";

        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String uploadResult = TestConfig.getFixture("BoxFile/UploadPartOne200");
        String wrongPartsResult = TestConfig.getFixture("BoxFile/ListUploadedParts200");
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
                .withBody(sessionResult)
                .withStatus(201)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(responseBody500)
                .withStatus(500)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(listPartsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(wrongPartsResult)
                .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(uploadResult)
                .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
            .withHeader("Content-Type", WireMock.equalTo("application/json"))
            .withRequestBody(WireMock.containing(commitObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(commitResult)
                .withStatus(201)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        BoxFile.Info uploadedFile = folder.uploadLargeFile(stream, "testfile.txt", 5);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
    }

    @Test
    public void testGetAllRootFolderItemsSucceeds() throws IOException {
        final String rootFolderItemsURL = "/folders/0/items/";
        final String folderURL = "/folders/0";
        final String ownedByUserLogin = "test@user.com";
        final String modifiedByLogin = "test@user.com";
        final String modifiedByName = "Test User";

        String result = TestConfig.getFixture("BoxFolder/GetAllRootFolderItems200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(rootFolderItemsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder rootFolder = BoxFolder.getRootFolder(this.api);
        BoxFolder.Info rootFolderInfo = rootFolder.getInfo();

        assertEquals(ownedByUserLogin, rootFolderInfo.getOwnedBy().getLogin());
        assertEquals(modifiedByLogin, rootFolderInfo.getModifiedBy().getLogin());
        assertEquals(modifiedByName, rootFolderInfo.getModifiedBy().getName());
    }

    @Test
    public void testGetAllFolderItemsSucceeds() throws IOException {
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID + "/items/";
        final String firstFolderName = "Example.pdf";

        String result = TestConfig.getFixture("BoxFolder/GetAllFolderItems200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderURL))
            .withQueryParam("limit", WireMock.containing("1000"))
            .withQueryParam("offset", WireMock.containing("0"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxItem.Info firstFolderInfo = folder.iterator().next();

        assertEquals(folderID, firstFolderInfo.getID());
        assertEquals(firstFolderName, firstFolderInfo.getName());
    }

    @Test
    public void testGetFolderInfoSucceeds() throws IOException {
        final String folderID = "12345";
        final String folderInfoURL = "/folders/" + folderID;
        final String folderName = "Example Folder";
        final String pathCollectionItemName = "All Files";
        final String createdByLogin = "test@user.com";
        final String modifiedByName = "Test User";
        final String classificationColor = "#00FFFF";
        final String classificationDefinition = "Content that should not be shared outside the company.";
        final String classificationName = "Top Secret";
        List<String> roles = new ArrayList<>();
        roles.add("open");

        String result = TestConfig.getFixture("BoxFolder/GetFolderInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.getInfo();

        assertEquals("folder", info.getType());
        assertEquals(folderID, info.getID());
        assertEquals(folderName, info.getName());
        assertEquals(pathCollectionItemName, info.getPathCollection().get(0).getName());
        assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        assertEquals(modifiedByName, info.getModifiedBy().getName());
        assertEquals(roles, info.getAllowedInviteeRoles());
        assertEquals(roles, info.getAllowedSharedLinkAccessLevels());
        assertTrue(info.getIsExternallyOwned());
        assertEquals(classificationColor, info.getClassification().getColor());
        assertEquals(classificationDefinition, info.getClassification().getDefinition());
        assertEquals(classificationName, info.getClassification().getName());
    }

    @Test
    public void testGetRestrictedCollaborationFieldSucceeds() throws IOException {
        final String folderID = "12345";
        final String folderInfoURL = "/folders/" + folderID;

        String result = TestConfig.getFixture("BoxFolder/GetFolderInfoForCollaborationRestriction200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.getInfo();

        assertTrue(info.getIsCollaborationRestrictedToEnterprise());
    }

    @Test
    public void testSetRestrictedCollaborationFieldSucceeds() throws IOException {
        final String folderID = "12345";
        final String folderInfoURL = "/folders/" + folderID;
        JsonObject jsonObject = new JsonObject()
            .add("is_collaboration_restricted_to_enterprise", true);

        String result = TestConfig.getFixture("BoxFolder/GetFolderInfoForCollaborationRestriction200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(folderInfoURL))
            .withRequestBody(WireMock.equalToJson(jsonObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info folderInfo = folder.getInfo();
        folderInfo.setIsCollaborationRestrictedToEnterprise(true);
        folder.updateInfo(folderInfo);

        assertTrue(folder.getInfo().getIsCollaborationRestrictedToEnterprise());
    }

    @Test
    public void testUpdateFolderInfoSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID;
        final String folderName = "New Folder Name";
        final String folderDescription = "Folder Description";

        JsonObject updateFolderObject = new JsonObject()
            .add("name", folderName)
            .add("description", "Folder Description")
            .add("is_password_enabled", true)
            .add("can_download", false)
            .add("can_preview", false);

        String result = TestConfig.getFixture("BoxFolder/UpdateFolderInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(folderURL))
            .withRequestBody(WireMock.equalToJson(updateFolderObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.new Info();
        info.setName(folderName);
        info.setDescription(folderDescription);
        info.addPendingChange("is_password_enabled", true);
        info.addPendingChange("can_download", false);
        info.addPendingChange("can_preview", false);
        folder.updateInfo(info);

        assertEquals(folderID, info.getID());
        assertEquals(folderName, info.getName());
        assertEquals(folderDescription, info.getDescription());
    }

    @Test
    public void testCreateNewFolderSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "0";
        final String folderURL = "/folders";
        final String folderName = "Example Test Folder";
        final String parentFolderName = "All Files";
        final String ownedByUserName = "Test User";
        final String ownedByUserLogin = "test@user.com";

        JsonObject parentObject = new JsonObject()
            .add("id", "0");

        JsonObject createFolderObject = new JsonObject()
            .add("name", folderName)
            .add("parent", parentObject);

        String result = TestConfig.getFixture("BoxFolder/CreateNewFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderURL))
            .withRequestBody(WireMock.equalToJson(createFolderObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder parentFolder = new BoxFolder(this.api, folderID);
        BoxFolder.Info childFolderInfo = parentFolder.createFolder(folderName);

        assertEquals(folderID, childFolderInfo.getID());
        assertEquals(folderName, childFolderInfo.getName());
        assertEquals(parentFolderName, childFolderInfo.getParent().getName());
        assertEquals(ownedByUserName, childFolderInfo.getOwnedBy().getName());
        assertEquals(ownedByUserLogin, childFolderInfo.getOwnedBy().getLogin());
    }

    @Test
    public void testCopyFolderSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID + "/copy";
        final String newParentID = "12345";

        JsonObject parentObject = new JsonObject()
            .add("id", newParentID);

        JsonObject copyObject = new JsonObject()
            .add("parent", parentObject);

        String result = TestConfig.getFixture("BoxFolder/CopyFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderURL))
            .withRequestBody(WireMock.equalToJson(copyObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder destination = new BoxFolder(this.api, newParentID);
        BoxFolder.Info copiedFolderInfo = folder.copy(destination);

        assertEquals(folderID, copiedFolderInfo.getID());
        assertEquals(newParentID, copiedFolderInfo.getParent().getID());
    }

    @Test
    public void testMoveFolderSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String moveFolderURL = "/folders/" + folderID;
        final String parentID = "2222";

        JsonObject innerObject = new JsonObject()
            .add("id", parentID);

        JsonObject parentObject = new JsonObject()
            .add("parent", innerObject);

        String result = TestConfig.getFixture("BoxFolder/MoveFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(moveFolderURL))
            .withRequestBody(WireMock.equalToJson(parentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder destination = new BoxFolder(this.api, parentID);
        BoxItem.Info folderInfo = folder.move(destination);

        assertEquals(parentID, folderInfo.getParent().getID());
        assertEquals(folderID, folderInfo.getID());
    }

    @Test
    public void testDeleteFolderSendsCorrectJson() {
        final String folderID = "12345";
        final String deleteFolderURL = "/folders/" + folderID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFolderURL))
            .withQueryParam("recursive", WireMock.containing("true"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.delete(true);
    }

    @Test
    public void testCreateSharedLinkForFolderSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID;

        JsonObject accessObject = new JsonObject()
            .add("access", "open");

        JsonObject sharedLinkObject = new JsonObject()
            .add("shared_link", accessObject);

        String result = TestConfig.getFixture("BoxFolder/CreateSharedLinkForFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(folderURL))
            .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxSharedLink sharedLink = new BoxSharedLink();
        sharedLink.setAccess(OPEN);

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.new Info();
        info.setSharedLink(sharedLink);
        folder.updateInfo(info);

        assertEquals(OPEN, info.getSharedLink().getEffectiveAccess());
        assertFalse(info.getSharedLink().getIsPasswordEnabled());
        assertEquals(OPEN, info.getSharedLink().getAccess());
    }

    @Test
    public void testGetAllFolderCollaborationsSucceeds() throws IOException {
        final String folderID = "3333";
        final String folderCollaborationURL = "/folders/" + folderID + "/collaborations";
        final String collaborationID = "12345";
        final String accessiblyByLogin = "Test User";
        final BoxCollaboration.Role collaborationRole = BoxCollaboration.Role.VIEWER;

        String result = TestConfig.getFixture("BoxFolder/GetAllFolderCollaborations200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();
        Iterator<BoxCollaboration.Info> iterator = collaborations.iterator();
        BoxCollaboration.Info collaborationInfo = iterator.next();
        BoxCollaboration.Info collaborationInfo2 = iterator.next();

        assertEquals(collaborationID, collaborationInfo.getID());
        assertEquals(folderID, collaborationInfo.getItem().getID());
        assertEquals(accessiblyByLogin, collaborationInfo.getAccessibleBy().getName());
        assertEquals(collaborationRole, collaborationInfo.getRole());
        assertEquals(BoxCollaborator.CollaboratorType.GROUP, collaborationInfo2.getAccessibleBy().getType());
        assertEquals(BoxCollaborator.GroupType.MANAGED_GROUP,
            collaborationInfo2.getAccessibleBy().getGroupType());
    }

    @Test
    public void testCreateMetadataOnFolderSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata/global/properties";
        final String metadataID = "12345";
        final String parentID = "folder_12345";

        JsonObject metadataObject = new JsonObject()
            .add("foo", "bar");

        String result = TestConfig.getFixture("BoxFolder/CreateMetadataOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Metadata metadataInfo = folder.createMetadata(new Metadata().add("/foo", "bar"));
        assertEquals(metadataID, metadataInfo.getID());
        assertEquals(parentID, metadataInfo.getParentID());
    }

    @Test
    public void testGetMetadataOnFolderSucceds() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata/global/properties";
        final String metadataID = "12345";
        final String parentID = "folder_12345";

        String result = TestConfig.getFixture("BoxFolder/CreateMetadataOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Metadata metadata = folder.getMetadata();

        assertEquals(metadataID, metadata.getID());
        assertEquals(parentID, metadata.getParentID());
    }

    @Test
    public void testGetAllMetadataSucceeds() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata";
        final String metadataID = "12345";
        final String parentID = "folder_12345";
        final String template = "properties";
        final String scope = "global";

        String result = TestConfig.getFixture("BoxFolder/GetAllMetadataOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .withQueryParam("limit", WireMock.containing("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<Metadata> metadataList = folder.getAllMetadata().iterator();
        Metadata metadata = metadataList.next();

        assertEquals(metadataID, metadata.getID());
        assertEquals(parentID, metadata.getParentID());
        assertEquals(template, metadata.getTemplateName());
        assertEquals(scope, metadata.getScope());
    }

    @Test
    public void testAddMetadataCascadePolicySucceedsSendsCorrectJson() throws IOException {
        final String cascadePolicyURL = "/metadata_cascade_policies";
        final String folderID = "22222";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        JsonObject cascadeObject = new JsonObject()
            .add("folder_id", folderID)
            .add("scope", scope)
            .add("templateKey", templateKey);

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/CreateMetadataCascadePolicies201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(cascadePolicyURL))
            .withRequestBody(WireMock.equalToJson(cascadeObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxMetadataCascadePolicy.Info metadataCascadePolicyInfo = folder.addMetadataCascadePolicy(scope, templateKey);

        assertEquals(folderID, metadataCascadePolicyInfo.getParent().getID());
        assertEquals(scope, metadataCascadePolicyInfo.getScope());
        assertEquals(templateKey, metadataCascadePolicyInfo.getTemplateKey());
    }

    @Test
    public void testGetAllMetadataCascadePoliciesOnFolderSucceeds() throws IOException {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
            .withQueryParam("folder_id", WireMock.equalTo(folderID))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<BoxMetadataCascadePolicy.Info> metadataCascadePolicies = folder
            .getMetadataCascadePolicies().iterator();

        BoxMetadataCascadePolicy.Info firstCascadePolicy = metadataCascadePolicies.next();

        assertEquals(folderID, firstCascadePolicy.getParent().getID());
        assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        assertEquals(scope, firstCascadePolicy.getScope());
        assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    public void testGetAllMetadataCascadePoliciesOnFolderWithFieldsSucceeds() throws IOException {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
            .withQueryParam("folder_id", WireMock.equalTo(folderID))
            .withQueryParam("fields", WireMock.equalTo("owner_enterprise"))
            .withQueryParam("limit", WireMock.equalTo("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<BoxMetadataCascadePolicy.Info> metadataCascadePolicies = folder
            .getMetadataCascadePolicies("owner_enterprise").iterator();

        BoxMetadataCascadePolicy.Info firstCascadePolicy = metadataCascadePolicies.next();

        assertEquals(folderID, firstCascadePolicy.getParent().getID());
        assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        assertEquals(scope, firstCascadePolicy.getScope());
        assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    public void testGetAllMetadataCascadePoliciesWithEnterpriseIDSucceeds() throws IOException {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final int limit = 100;
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
            .withQueryParam("folder_id", WireMock.equalTo(folderID))
            .withQueryParam("owner_enterprise_id", WireMock.equalTo(enterpriseID))
            .withQueryParam("fields", WireMock.equalTo("owner_enterprise"))
            .withQueryParam("limit", WireMock.equalTo("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<BoxMetadataCascadePolicy.Info> metadataCascadePolicies = folder
            .getMetadataCascadePolicies(enterpriseID, limit, "owner_enterprise").iterator();

        BoxMetadataCascadePolicy.Info firstCascadePolicy = metadataCascadePolicies.next();

        assertEquals(folderID, firstCascadePolicy.getParent().getID());
        assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        assertEquals(scope, firstCascadePolicy.getScope());
        assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    public void createSharedLinkSucceeds() throws IOException {
        final String folderID = "1111";
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

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo("/folders/" + folderID))
            .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();

        permissions.setCanDownload(true);
        permissions.setCanPreview(true);
        BoxSharedLink sharedLink = folder.createSharedLink(
            new BoxSharedLinkRequest()
                .access(OPEN)
                .permissions(true, true)
                .password(password)
        );

        assertTrue(sharedLink.getIsPasswordEnabled());
    }

    @Test
    public void testAddClassification() throws IOException {
        final String folderID = "12345";
        final String classificationType = "Public";
        final String metadataURL = "/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("Box__Security__Classification__Key", classificationType);

        String result = TestConfig.getFixture("BoxFolder/CreateClassificationOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.addClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    public void testUpdateClassification() throws IOException {
        final String folderID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("op", "replace")
            .add("path", "/Box__Security__Classification__Key")
            .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
            .add(metadataObject);

        String result = TestConfig.getFixture("BoxFolder/UpdateClassificationOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json-patch+json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.updateClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    public void testSetClassification() throws IOException {
        final String folderID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("op", "replace")
            .add("path", "/Box__Security__Classification__Key")
            .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
            .add(metadataObject);

        String result = TestConfig.getFixture("BoxFolder/UpdateClassificationOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withStatus(409)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json-patch+json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.setClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test(expected = BoxAPIResponseException.class)
    public void testSetClassificationThrowsException() {
        final String folderID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withStatus(403)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.setClassification(classificationType);

        WireMock.verify(1, WireMock.postRequestedFor(WireMock.urlPathEqualTo(metadataURL)));
    }

    @Test
    public void testGetClassification() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        String result = TestConfig.getFixture("BoxFolder/CreateClassificationOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.getClassification();

        assertEquals("Public", classification);
    }

    @Test
    public void testDeleteClassification() {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json-patch+json")
                .withStatus(204)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.deleteClassification();

        WireMock.verify(1, WireMock.deleteRequestedFor(WireMock.urlPathEqualTo(metadataURL)));
    }

    @Test
    public void testUploadFileWithDescriptionSucceeds() throws IOException {
        final String folderID = "12345";
        final String fileURL = "/files/content";
        final String fileContent = "Test file";
        final String fileName = "Test File.txt";
        final String fileDescription = "Test Description";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));

        String result = TestConfig.getFixture("BoxFile/CreateFileWithDescription201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json-patch+json")
                .withBody(result)
                .withStatus(201)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFile.Info file = folder.uploadFile(stream, fileName, fileDescription);

        assertEquals(fileDescription, file.getDescription());
    }

    @Test
    public void testGetFolderItemsWithSort() throws IOException {
        final String folderID = "12345";
        final String folderItemsURL = "/folders/" + folderID + "/items/";

        String result = TestConfig.getFixture("BoxFolder/GetFolderItemsWithSort200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderItemsURL))
            .withQueryParam("sort", WireMock.equalTo("name"))
            .withQueryParam("direction", WireMock.equalTo("ASC"))
            .withQueryParam("fields", WireMock.equalTo("name"))
            .withQueryParam("limit", WireMock.equalTo("1000"))
            .withQueryParam("offset", WireMock.equalTo("0"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)
                .withStatus(200)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        Iterator<BoxItem.Info> itemIterator = folder.getChildren("name",
            BoxFolder.SortDirection.ASC, "name").iterator();
        BoxItem.Info boxItem1 = itemIterator.next();
        assertEquals("Test", boxItem1.getName());
        BoxItem.Info boxItem2 = itemIterator.next();
        assertEquals("Test 2", boxItem2.getName());
    }

    @Test
    public void testGetFolderItemsWithOffsetAndLimit() throws IOException {
        final String folderID = "12345";
        final String folderItemsURL = "/folders/" + folderID + "/items/";

        String result = TestConfig.getFixture("BoxFolder/GetFolderItemsWithSort200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderItemsURL))
            .withQueryParam("sort", WireMock.equalTo("name"))
            .withQueryParam("direction", WireMock.equalTo("ASC"))
            .withQueryParam("fields", WireMock.equalTo("name"))
            .withQueryParam("limit", WireMock.equalTo("500"))
            .withQueryParam("offset", WireMock.equalTo("10"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)
                .withStatus(200)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        Iterator<BoxItem.Info> itemIterator = folder.getChildren("name",
            BoxFolder.SortDirection.ASC, 10, 500, "name").iterator();
        BoxItem.Info boxItem1 = itemIterator.next();
        assertEquals("Test", boxItem1.getName());
        BoxItem.Info boxItem2 = itemIterator.next();
        assertEquals("Test 2", boxItem2.getName());
    }

    @Test
    public void testSetMetadataReturnsCorrectly() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata/enterprise/testtemplate";
        ArrayList<String> secondValueArray = new ArrayList<>();
        secondValueArray.add("first");
        secondValueArray.add("second");
        secondValueArray.add("third");

        String postResult = TestConfig.getFixture("/BoxException/BoxResponseException409");
        String putResult = TestConfig.getFixture("/BoxFolder/UpdateMetadataOnFolder200");

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
            .add("value", "text");

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

        BoxFolder folder = new BoxFolder(this.api, "12345");

        Metadata metadata = new Metadata()
            .add("/test1", firstValue)
            .add("/test2", secondValueArray)
            .add("/test3", thirdValue)
            .add("/test4", fourthValue)
            .add("/test5", fifthValue);

        Metadata metadataValues = folder.setMetadata("testtemplate", "enterprise", metadata);

        assertEquals("folder_12345", metadataValues.getParentID());
        assertEquals("testtemplate", metadataValues.getTemplateName());
        assertEquals("enterprise_11111", metadataValues.getScope());
        assertEquals(firstValue, metadataValues.getString("/test1"));
        assertEquals(secondValueJson, metadataValues.getValue("/test2"));
        assertEquals(thirdValue, metadataValues.getDouble("/test3"), 0);
        assertEquals(fourthValue, metadataValues.getDouble("/test4"), 4);
        assertEquals(fifthValue, metadataValues.getDouble("/test5"), 0);
    }

    @Test(expected = BoxDeserializationException.class)
    public void testDeserializationException() throws IOException {
        final String folderID = "12345";
        final String foldersURL = "/folders/" + folderID;

        String result = TestConfig.getFixture("BoxFolder/GetFolderInfoCausesDeserializationException");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(foldersURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder.Info folderInfo = new BoxFolder(this.api, folderID).getInfo();
        assertEquals("12345", folderInfo.getID());
    }

    @Test
    public void createFolderLockSucceeds() throws IOException {
        final String folderID = "12345678";
        final String folderLockURL = "/folder_locks";

        JsonObject folderObject = new JsonObject();
        folderObject.add("type", "folder");
        folderObject.add("id", folderID);

        JsonObject lockedOperations = new JsonObject();
        lockedOperations.add("move", true);
        lockedOperations.add("delete", true);

        JsonObject body = new JsonObject();
        body.add("folder", folderObject);
        body.add("locked_operations", lockedOperations);

        String result = TestConfig.getFixture("BoxFolder/CreateFolderLock200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderLockURL))
            .withRequestBody(WireMock.equalToJson(body.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolderLock.Info folderLock = folder.lock();

        assertEquals("12345678", folderLock.getID());
        assertEquals("11446498", folderLock.getCreatedBy().getID());
        assertEquals("Contracts", folderLock.getFolder().getName());
    }

    @Test
    public void getFolderLocks() throws IOException {
        final String folderID = "12345";
        final String folderLocksURL = "/folder_locks";

        String result = TestConfig.getFixture("BoxFolder/GetFolderLocks200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderLocksURL))
            .withQueryParam("folder_id", WireMock.equalTo(folderID))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)
                .withStatus(200)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<BoxFolderLock.Info> lockIterator = folder.getLocks().iterator();
        BoxFolderLock.Info lock = lockIterator.next();

        assertEquals("12345678", lock.getID());
        assertEquals("Contracts", lock.getFolder().getName());
        assertEquals("freeze", lock.getLockType());
        assertTrue(lock.getLockedOperations().get("move"));
    }

    @Test
    public void deleteFolderLockSucceeds() {
        final String folderLockID = "12345678";
        final String deleteFolderLockURL = "/folder_locks/" + folderLockID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFolderLockURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxFolderLock folderLock = new BoxFolderLock(this.api, folderLockID);
        folderLock.delete();
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
        BoxSharedLinkRequest request = new BoxSharedLinkRequest()
            .vanityName("myCustomName");

        //when
        BoxFolder folder = new BoxFolder(api, "12345");
        folder.createSharedLink(request);
    }

    @Test
    public void iterateWithOffset() {
        this.api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                String query = request.getUrl().getQuery();
                assertThat(query, is("sort=name&direction=DESC&limit=2&offset=3"));
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": [], \"total_count\": 0}";
                    }
                };
            }
        });
        BoxFolder folder = new BoxFolder(this.api, "123456");
        folder.getChildren("name", DESC, 3, 2).iterator().hasNext();
    }
}
