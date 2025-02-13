package com.box.sdk;

import static com.box.sdk.BoxFolder.SortDirection.DESC;
import static com.box.sdk.BoxSharedLink.Access.OPEN;
import static com.box.sdk.PagingParameters.marker;
import static com.box.sdk.SortParameters.ascending;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.box.sdk.http.ContentType.APPLICATION_JSON_PATCH;
import static com.box.sdk.http.ContentType.APPLICATION_OCTET_STREAM;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.box.sdk.sharedlink.BoxSharedLinkRequest;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.EqualToJsonPattern;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.MultipartValuePatternBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxFolder} related unit tests.
 */
public class BoxFolderTest {

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
    public void testChunkedUploadThrows409() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        final String preflightURL = "/2.0/files/content";
        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String getResult = TestUtils.getFixture("BoxException/BoxResponseException409");

        JsonObject idObject = new JsonObject()
            .add("id", "12345");

        JsonObject preflightObject = new JsonObject()
            .add("name", "testfile.txt")
            .add("size", 5)
            .add("parent", idObject);

        wireMockRule.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
            .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
        final String preflightURL = "/2.0/files/content";
        final String sessionURL = "/2.0/files/upload_sessions";
        final String uploadURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestUtils.getFixture("BoxFile/CreateUploadSession201", wireMockRule.httpsPort());
        String uploadResult = TestUtils.getFixture("BoxFile/UploadPartOne200");
        String commitResult = TestUtils.getFixture("BoxFile/CommitUploadWithAttributes201");
        String canUploadResult = TestUtils.getFixture("BoxFile/CanUploadFile200");

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

        wireMockRule.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
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
        final String preflightURL = "/2.0/files/content";
        final String sessionURL = "/2.0/files/upload_sessions";
        final String uploadURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestUtils.getFixture("BoxFile/CreateUploadSession201", wireMockRule.httpsPort());
        String uploadResult = TestUtils.getFixture("BoxFile/UploadPartOne200");
        String commitResult = TestUtils.getFixture("BoxFile/CommitUploadWithAttributes201");
        String canUploadResult = TestUtils.getFixture("BoxFile/CanUploadFile200");

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

        wireMockRule.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
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
        String responseBody500 = TestUtils.getFixture("BoxException/BoxResponseException500");
        final String preflightURL = "/2.0/files/content";
        final String sessionURL = "/2.0/files/upload_sessions";
        final String uploadURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String listPartsURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/parts";
        final String commitURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";

        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestUtils.getFixture("BoxFile/CreateUploadSession201", wireMockRule.httpsPort());
        String partsResult = TestUtils.getFixture("BoxFile/ListUploadedPart200");
        String commitResult = TestUtils.getFixture("BoxFile/CommitUpload201");
        String canUploadResult = TestUtils.getFixture("BoxFile/CanUploadFile200");

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
            .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(200)
                .withBody(canUploadResult)));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
            .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(sessionResult)
                .withStatus(201)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing(APPLICATION_OCTET_STREAM))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(responseBody500)
                .withStatus(500)));

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(listPartsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(partsResult)
                .withStatus(200)));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
            .withHeader("Content-Type", WireMock.equalTo(APPLICATION_JSON))
            .withRequestBody(WireMock.containing(commitObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
        String responseBody500 = TestUtils.getFixture("BoxException/BoxResponseException500");
        final String preflightURL = "/2.0/files/content";
        final String sessionURL = "/2.0/files/upload_sessions";
        final String uploadURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String listPartsURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/parts";
        final String commitURL = "/2.0/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";

        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestUtils.getFixture("BoxFile/CreateUploadSession201", wireMockRule.httpsPort());
        String uploadResult = TestUtils.getFixture("BoxFile/UploadPartOne200");
        String wrongPartsResult = TestUtils.getFixture("BoxFile/ListUploadedParts200");
        String commitResult = TestUtils.getFixture("BoxFile/CommitUpload201");
        String canUploadResult = TestUtils.getFixture("BoxFile/CanUploadFile200");

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
            .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(200)
                .withBody(canUploadResult)));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
            .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(sessionResult)
                .withStatus(201)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing(APPLICATION_OCTET_STREAM))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(responseBody500)
                .withStatus(500)));

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(listPartsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(wrongPartsResult)
                .withStatus(200)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
            .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
            .withHeader("Content-Type", WireMock.containing(APPLICATION_OCTET_STREAM))
            .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
            .withRequestBody(WireMock.equalTo("aaaaa"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(uploadResult)
                .withStatus(200)));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
            .withHeader("Content-Type", WireMock.equalTo(APPLICATION_JSON))
            .withRequestBody(WireMock.containing(commitObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetAllRootFolderItemsSucceeds() {
        final String rootFolderItemsURL = "/2.0/folders/0/items/";
        final String folderURL = "/2.0/folders/0";
        final String ownedByUserLogin = "test@user.com";
        final String modifiedByLogin = "test@user.com";
        final String modifiedByName = "Test User";

        String items = TestUtils.getFixture("BoxFolder/GetAllRootFolderItems200");
        String info = TestUtils.getFixture("BoxFolder/GetFolderInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(rootFolderItemsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(200).withBody(items)));

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(info)
                .withStatus(200)));

        BoxFolder rootFolder = BoxFolder.getRootFolder(this.api);
        BoxFolder.Info rootFolderInfo = rootFolder.getInfo();

        assertEquals(ownedByUserLogin, rootFolderInfo.getOwnedBy().getLogin());
        assertEquals(modifiedByLogin, rootFolderInfo.getModifiedBy().getLogin());
        assertEquals(modifiedByName, rootFolderInfo.getModifiedBy().getName());
        assertEquals(rootFolderInfo.getPermissions(), EnumSet.allOf(BoxFolder.Permission.class));
    }

    @Test
    public void testGetAllFolderItemsSucceeds() {
        final String folderID = "12345";
        final String folderURL = "/2.0/folders/" + folderID + "/items/";
        final String firstFolderName = "Example.pdf";

        String result = TestUtils.getFixture("BoxFolder/GetAllFolderItems200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderURL))
            .withQueryParam("limit", WireMock.containing("1000"))
            .withQueryParam("usemarker", WireMock.containing("true"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxItem.Info firstFolderInfo = folder.iterator().next();

        assertEquals(folderID, firstFolderInfo.getID());
        assertEquals(firstFolderName, firstFolderInfo.getName());
    }

    @Test
    public void testGetFolderInfoSucceeds() {
        final String folderID = "12345";
        final String folderInfoURL = "/2.0/folders/" + folderID;
        final String folderName = "Example Folder";
        final String pathCollectionItemName = "All Files";
        final String createdByLogin = "test@user.com";
        final String modifiedByName = "Test User";
        final String classificationColor = "#00FFFF";
        final String classificationDefinition = "Content that should not be shared outside the company.";
        final String classificationName = "Top Secret";
        List<String> roles = new ArrayList<>();
        roles.add("open");

        String result = TestUtils.getFixture("BoxFolder/GetFolderInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
        assertTrue(info.getIsAccessibleViaSharedLink());
        assertTrue(info.getCanNonOwnersViewCollaborators());
    }

    @Test
    public void testGetRestrictedCollaborationFieldSucceeds() {
        final String folderID = "12345";
        final String folderInfoURL = "/2.0/folders/" + folderID;

        String result = TestUtils.getFixture("BoxFolder/GetFolderInfoForCollaborationRestriction200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.getInfo();

        assertTrue(info.getIsCollaborationRestrictedToEnterprise());
    }

    @Test
    public void testSetRestrictedCollaborationFieldSucceeds() {
        final String folderID = "12345";
        final String folderInfoURL = "/2.0/folders/" + folderID;
        JsonObject jsonObject = new JsonObject()
            .add("is_collaboration_restricted_to_enterprise", true);

        String result = TestUtils.getFixture("BoxFolder/GetFolderInfoForCollaborationRestriction200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(folderInfoURL))
            .withRequestBody(WireMock.equalToJson(jsonObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info folderInfo = folder.getInfo();
        folderInfo.setIsCollaborationRestrictedToEnterprise(true);
        folder.updateInfo(folderInfo);

        assertTrue(folder.getInfo().getIsCollaborationRestrictedToEnterprise());
    }

    @Test
    public void testUpdateFolderInfoSucceedsAndSendsCorrectJson() {
        final String folderID = "12345";
        final String folderURL = "/2.0/folders/" + folderID;
        final String folderName = "New Folder Name";
        final String folderDescription = "Folder Description";

        JsonObject updateFolderObject = new JsonObject()
            .add("name", folderName)
            .add("description", "Folder Description")
            .add("is_password_enabled", true)
            .add("can_download", false)
            .add("can_preview", false);

        String result = TestUtils.getFixture("BoxFolder/UpdateFolderInfo200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(folderURL))
            .withRequestBody(WireMock.equalToJson(updateFolderObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testCreateNewFolderSucceedsAndSendsCorrectJson() {
        final String folderID = "0";
        final String folderURL = "/2.0/folders";
        final String folderName = "Example Test Folder";
        final String parentFolderName = "All Files";
        final String ownedByUserName = "Test User";
        final String ownedByUserLogin = "test@user.com";

        JsonObject parentObject = new JsonObject()
            .add("id", "0");

        JsonObject createFolderObject = new JsonObject()
            .add("name", folderName)
            .add("parent", parentObject);

        String result = TestUtils.getFixture("BoxFolder/CreateNewFolder201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderURL))
            .withRequestBody(WireMock.equalToJson(createFolderObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testCopyFolderSucceedsAndSendsCorrectJson() {
        final String folderID = "12345";
        final String folderURL = "/2.0/folders/" + folderID + "/copy";
        final String newParentID = "12345";

        JsonObject parentObject = new JsonObject()
            .add("id", newParentID);

        JsonObject copyObject = new JsonObject()
            .add("parent", parentObject);

        String result = TestUtils.getFixture("BoxFolder/CopyFolder201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderURL))
            .withRequestBody(WireMock.equalToJson(copyObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder destination = new BoxFolder(this.api, newParentID);
        BoxFolder.Info copiedFolderInfo = folder.copy(destination);

        assertEquals(folderID, copiedFolderInfo.getID());
        assertEquals(newParentID, copiedFolderInfo.getParent().getID());
    }

    @Test
    public void testMoveFolderSucceedsAndSendsCorrectJson() {
        final String folderID = "12345";
        final String moveFolderURL = "/2.0/folders/" + folderID;
        final String parentID = "2222";

        JsonObject innerObject = new JsonObject()
            .add("id", parentID);

        JsonObject parentObject = new JsonObject()
            .add("parent", innerObject);

        String result = TestUtils.getFixture("BoxFolder/MoveFolder200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(moveFolderURL))
            .withRequestBody(WireMock.equalToJson(parentObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
        final String deleteFolderURL = "/2.0/folders/" + folderID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFolderURL))
            .withQueryParam("recursive", WireMock.containing("true"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.delete(true);
    }

    @Test
    public void testCreateSharedLinkForFolderSucceedsAndSendsCorrectJson() {
        final String folderID = "12345";
        final String folderURL = "/2.0/folders/" + folderID;

        JsonObject accessObject = new JsonObject()
            .add("access", "open");

        JsonObject sharedLinkObject = new JsonObject()
            .add("shared_link", accessObject);

        String result = TestUtils.getFixture("BoxFolder/CreateSharedLinkForFolder200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(folderURL))
            .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetAllFolderCollaborationsSucceeds() {
        final String folderID = "3333";
        final String folderCollaborationURL = "/2.0/folders/" + folderID + "/collaborations";
        final String collaborationID = "12345";
        final String accessiblyByLogin = "Test User";
        final BoxCollaboration.Role collaborationRole = BoxCollaboration.Role.VIEWER;

        String result = TestUtils.getFixture("BoxFolder/GetAllFolderCollaborations200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetAllFolderCollaborationsWithFieldsSucceeds() {
        final String folderID = "3333";
        final String folderCollaborationURL = "/2.0/folders/" + folderID
            + "/collaborations?fields=id%2Ctype%2Ccan_view_path";

        String result = TestUtils.getFixture("BoxFolder/GetAllFolderCollaborations200WithFields");

        wireMockRule.stubFor(WireMock.get(WireMock.urlEqualTo(folderCollaborationURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations("id", "type", "can_view_path");

        Iterator<BoxCollaboration.Info> iterator = collaborations.iterator();

        BoxCollaboration.Info collaborationInfo = iterator.next();
        assertEquals("12345", collaborationInfo.getID());
        assertFalse(collaborationInfo.getCanViewPath());

        BoxCollaboration.Info collaborationInfo2 = iterator.next();
        assertEquals("124783", collaborationInfo2.getID());
        assertTrue(collaborationInfo2.getCanViewPath());
    }

    @Test
    public void testCreateMetadataOnFolderSucceedsAndSendsCorrectJson() {
        final String folderID = "12345";
        final String metadataURL = "/2.0/folders/" + folderID + "/metadata/global/properties";
        final String metadataID = "12345";
        final String parentID = "folder_12345";

        JsonObject metadataObject = new JsonObject()
            .add("foo", "bar");

        String result = TestUtils.getFixture("BoxFolder/CreateMetadataOnFolder201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Metadata metadataInfo = folder.createMetadata(new Metadata().add("/foo", "bar"));
        assertEquals(metadataID, metadataInfo.getID());
        assertEquals(parentID, metadataInfo.getParentID());
    }

    @Test
    public void testGetMetadataOnFolderSucceds() {
        final String folderID = "12345";
        final String metadataURL = "/2.0/folders/" + folderID + "/metadata/global/properties";
        final String metadataID = "12345";
        final String parentID = "folder_12345";

        String result = TestUtils.getFixture("BoxFolder/CreateMetadataOnFolder201");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Metadata metadata = folder.getMetadata();

        assertEquals(metadataID, metadata.getID());
        assertEquals(parentID, metadata.getParentID());
    }

    @Test
    public void testGetAllMetadataSucceeds() {
        final String folderID = "12345";
        final String metadataURL = "/2.0/folders/" + folderID + "/metadata";
        final String metadataID = "12345";
        final String parentID = "folder_12345";
        final String template = "properties";
        final String scope = "global";

        String result = TestUtils.getFixture("BoxFolder/GetAllMetadataOnFolder200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .withQueryParam("limit", WireMock.containing("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testAddMetadataCascadePolicySucceedsSendsCorrectJson() {
        final String cascadePolicyURL = "/2.0/metadata_cascade_policies";
        final String folderID = "22222";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        JsonObject cascadeObject = new JsonObject()
            .add("folder_id", folderID)
            .add("scope", scope)
            .add("templateKey", templateKey);

        String result = TestUtils.getFixture("BoxMetadataCascadePolicy/CreateMetadataCascadePolicies201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(cascadePolicyURL))
            .withRequestBody(WireMock.equalToJson(cascadeObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxMetadataCascadePolicy.Info metadataCascadePolicyInfo = folder.addMetadataCascadePolicy(scope, templateKey);

        assertEquals(folderID, metadataCascadePolicyInfo.getParent().getID());
        assertEquals(scope, metadataCascadePolicyInfo.getScope());
        assertEquals(templateKey, metadataCascadePolicyInfo.getTemplateKey());
    }

    @Test
    public void testGetAllMetadataCascadePoliciesOnFolderSucceeds() {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/2.0/metadata_cascade_policies";

        String result = TestUtils.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
            .withQueryParam("folder_id", WireMock.equalTo(folderID))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetAllMetadataCascadePoliciesOnFolderWithFieldsSucceeds() {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/2.0/metadata_cascade_policies";

        String result = TestUtils.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
            .withQueryParam("folder_id", WireMock.equalTo(folderID))
            .withQueryParam("fields", WireMock.equalTo("owner_enterprise"))
            .withQueryParam("limit", WireMock.equalTo("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetAllMetadataCascadePoliciesWithEnterpriseIDSucceeds() {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final int limit = 100;
        final String cascadePoliciesURL = "/2.0/metadata_cascade_policies";

        String result = TestUtils.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
            .withQueryParam("folder_id", WireMock.equalTo(folderID))
            .withQueryParam("owner_enterprise_id", WireMock.equalTo(enterpriseID))
            .withQueryParam("fields", WireMock.equalTo("owner_enterprise"))
            .withQueryParam("limit", WireMock.equalTo("100"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void createSharedLinkSucceeds() {
        final String folderID = "1111";
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

        String result = TestUtils.getFixture("BoxSharedLink/CreateSharedLink201");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo("/2.0/folders/" + folderID))
            .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);

        BoxSharedLink sharedLink = folder.createSharedLink(
            new BoxSharedLinkRequest()
                .access(OPEN)
                .permissions(true, true)
                .password(password)
        );

        assertTrue(sharedLink.getIsPasswordEnabled());
    }

    @Test
    public void createSharedLinkChangesCanEditPermissionToFalse() {
        final String folderID = "1111";
        final String password = "test1";

        JsonObject permissionsObject = new JsonObject()
            .add("can_download", true)
            .add("can_preview", true)
            .add("can_edit", false);

        JsonObject sharedLinkJson = new JsonObject()
            .add("password", password)
            .add("access", "open")
            .add("permissions", permissionsObject);

        JsonObject sharedLinkObject = new JsonObject()
            .add("shared_link", sharedLinkJson);

        String result = TestUtils.getFixture("BoxSharedLink/CreateSharedLink201");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo("/2.0/folders/" + folderID))
            .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.createSharedLink(
            new BoxSharedLinkRequest()
                .access(OPEN)
                .permissions(true, true, true)
                .password(password)
        );
    }

    @Test
    public void testAddClassification() {
        final String folderID = "12345";
        final String classificationType = "Public";
        final String metadataURL = "/2.0/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("Box__Security__Classification__Key", classificationType);

        String result = TestUtils.getFixture("BoxFolder/CreateClassificationOnFolder201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.addClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    public void testUpdateClassification() {
        final String folderID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/2.0/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("op", "replace")
            .add("path", "/Box__Security__Classification__Key")
            .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
            .add(metadataObject);

        String result = TestUtils.getFixture("BoxFolder/UpdateClassificationOnFolder200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON_PATCH)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.updateClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    public void testSetClassification() {
        final String folderID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/2.0/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
            .add("op", "replace")
            .add("path", "/Box__Security__Classification__Key")
            .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
            .add(metadataObject);

        String result = TestUtils.getFixture("BoxFolder/UpdateClassificationOnFolder200");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withBody("{}")
                .withStatus(409)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON_PATCH)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.setClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test(expected = BoxAPIResponseException.class)
    public void testSetClassificationThrowsException() {
        final String folderID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/2.0/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withBody("{}")
                .withStatus(403)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.setClassification(classificationType);

        WireMock.verify(1, WireMock.postRequestedFor(WireMock.urlPathEqualTo(metadataURL)));
    }

    @Test
    public void testGetClassification() {
        final String folderID = "12345";
        final String metadataURL = "/2.0/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        String result = TestUtils.getFixture("BoxFolder/CreateClassificationOnFolder201");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.getClassification();

        assertEquals("Public", classification);
    }

    @Test
    public void testDeleteClassification() {
        final String folderID = "12345";
        final String metadataURL = "/2.0/folders/" + folderID
            + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(metadataURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON_PATCH)
                .withStatus(204)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.deleteClassification();

        WireMock.verify(1, WireMock.deleteRequestedFor(WireMock.urlPathEqualTo(metadataURL)));
    }

    @Test
    public void testUploadFileWithCorrectMultipartOrder() {
        final String folderID = "0";
        final String fileURL = "/2.0/files/content";
        final String fileContent = "Test file";
        final String fileName = "Test File.txt";
        final String fileDescription = "Test Description";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        final String expectedAttributesJson =
                "{\"name\":\"Test File.txt\",\"parent\":{\"id\": \"0\"},\"description\":\"Test Description\"}";

        String result = TestUtils.getFixture("BoxFile/CreateFileWithDescription201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileURL))
                .withRequestBody(WireMock.matching(".*attributes.*file.*")) // Check that attributes comes before file
                .withMultipartRequestBody(
                        new MultipartValuePatternBuilder()
                                .withName("attributes")
                                .withBody(new EqualToJsonPattern(expectedAttributesJson, false, false))
                ).withMultipartRequestBody(
                        new MultipartValuePatternBuilder()
                                .withName("file")
                                .withBody(new EqualToPattern(fileContent))
                )
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON_PATCH)
                        .withBody(result)
                        .withStatus(201)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFile.Info file = folder.uploadFile(stream, fileName, fileDescription);

        assertEquals(fileDescription, file.getDescription());
        assertEquals(folderID, file.getParent().getID());
        assertEquals(fileName, file.getName());
    }

    @Test
    public void testUploadFileWithDescriptionSucceeds() {
        final String folderID = "12345";
        final String fileURL = "/2.0/files/content";
        final String fileContent = "Test file";
        final String fileName = "Test File.txt";
        final String fileDescription = "Test Description";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));

        String result = TestUtils.getFixture("BoxFile/CreateFileWithDescription201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON_PATCH)
                .withBody(result)
                .withStatus(201)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFile.Info file = folder.uploadFile(stream, fileName, fileDescription);

        assertEquals(fileDescription, file.getDescription());
    }

    @Test
    public void testUploadFileSucceedsAfter500InTheFirstAttempt() throws IOException {
        final String folderID = "12345";
        final String fileURL = "/2.0/files/content";
        final String fileContent = "Test file";
        final String fileName = "Test File.txt";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));

        String result = TestUtils.getFixture("BoxFile/CreateFileWithDescription201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileURL))
                .inScenario("Retry Scenario")
                .whenScenarioStateIs(STARTED)
                .willReturn(WireMock.aResponse()
                    .withStatus(500))
                .willSetStateTo("Retry"));

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileURL))
                .inScenario("Retry Scenario")
                .whenScenarioStateIs("Retry")
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON_PATCH)
                        .withBody(result)
                        .withStatus(201))
                .willSetStateTo("Success"));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFile.Info file = folder.uploadFile(stream, fileName);

        WireMock.verify(2, WireMock.postRequestedFor(WireMock.urlEqualTo("/2.0/files/content")));

        assertEquals(fileName, file.getName());
    }

    @Test
    public void testGetFolderItemsWithSortAndOffset() {
        final String folderID = "12345";
        final String folderItemsURL = "/2.0/folders/" + folderID + "/items/";

        String result = TestUtils.getFixture("BoxFolder/GetFolderItemsWithSort200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderItemsURL))
            .withQueryParam("sort", WireMock.equalTo("name"))
            .withQueryParam("direction", WireMock.equalTo("ASC"))
            .withQueryParam("fields", WireMock.equalTo("name"))
            .withQueryParam("limit", WireMock.equalTo("1000"))
            .withQueryParam("offset", WireMock.equalTo("0"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetFolderItemsWithOffsetAndLimit() {
        final String folderID = "12345";
        final String folderItemsURL = "/2.0/folders/" + folderID + "/items/";

        String result = TestUtils.getFixture("BoxFolder/GetFolderItemsWithSort200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderItemsURL))
            .withQueryParam("sort", WireMock.equalTo("name"))
            .withQueryParam("direction", WireMock.equalTo("ASC"))
            .withQueryParam("fields", WireMock.equalTo("name"))
            .withQueryParam("limit", WireMock.equalTo("500"))
            .withQueryParam("offset", WireMock.equalTo("10"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetFolderItemsWithSortAndMarkerBasedPagingFails() {
        BoxFolder folder = new BoxFolder(this.api, "12345");
        assertThrows(
            "Sorting is not supported when using marker based pagination.",
            IllegalArgumentException.class,
            () -> folder.getChildren(ascending("name"), marker(100))
        );
    }

    @Test
    public void testSetMetadataReturnsCorrectly() {
        final String folderID = "12345";
        final String metadataURL = "/2.0/folders/" + folderID + "/metadata/enterprise/testtemplate";
        ArrayList<String> secondValueArray = new ArrayList<>();
        secondValueArray.add("first");
        secondValueArray.add("second");
        secondValueArray.add("third");

        String postResult = TestUtils.getFixture("/BoxException/BoxResponseException409");
        String putResult = TestUtils.getFixture("/BoxFolder/UpdateMetadataOnFolder200");

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

        BoxFolder folder = new BoxFolder(this.api, "12345");

        Metadata metadata = new Metadata()
            .add("/test1", firstValue)
            .add("/test2", secondValueArray)
            .add("/test3", thirdValue)
            .add("/test4", fourthValue);

        Metadata metadataValues = folder.setMetadata("testtemplate", "enterprise", metadata);

        assertEquals("folder_12345", metadataValues.getParentID());
        assertEquals("testtemplate", metadataValues.getTemplateName());
        assertEquals("enterprise_11111", metadataValues.getScope());
        assertEquals(firstValue, metadataValues.getString("/test1"));
        assertEquals(secondValueJson, metadataValues.getValue("/test2"));
        assertEquals(thirdValue, metadataValues.getDouble("/test3"), 0);
        assertEquals(fourthValue, metadataValues.getDouble("/test4"), 4);
    }

    @Test(expected = BoxDeserializationException.class)
    public void testDeserializationException() {
        final String folderID = "12345";
        final String foldersURL = "/2.0/folders/" + folderID;

        String result = TestUtils.getFixture("BoxFolder/GetFolderInfoCausesDeserializationException");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(foldersURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder.Info folderInfo = new BoxFolder(this.api, folderID).getInfo();
        assertEquals("12345", folderInfo.getID());
    }

    @Test
    public void createFolderLockSucceeds() {
        final String folderID = "12345678";
        final String folderLockURL = "/2.0/folder_locks";

        JsonObject folderObject = new JsonObject();
        folderObject.add("type", "folder");
        folderObject.add("id", folderID);

        JsonObject lockedOperations = new JsonObject();
        lockedOperations.add("move", true);
        lockedOperations.add("delete", true);

        JsonObject body = new JsonObject();
        body.add("folder", folderObject);
        body.add("locked_operations", lockedOperations);

        String result = TestUtils.getFixture("BoxFolder/CreateFolderLock200");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderLockURL))
            .withRequestBody(WireMock.equalToJson(body.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolderLock.Info folderLock = folder.lock();

        assertEquals("12345678", folderLock.getID());
        assertEquals("11446498", folderLock.getCreatedBy().getID());
        assertEquals("Contracts", folderLock.getFolder().getName());
    }

    @Test
    public void getFolderLocks() {
        final String folderID = "12345";
        final String folderLocksURL = "/2.0/folder_locks";

        String result = TestUtils.getFixture("BoxFolder/GetFolderLocks200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderLocksURL))
            .withQueryParam("folder_id", WireMock.equalTo(folderID))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
        final String deleteFolderLockURL = "/2.0/folder_locks/" + folderLockID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFolderLockURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxFolderLock folderLock = new BoxFolderLock(this.api, folderLockID);
        folderLock.delete();
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
        BoxSharedLinkRequest request = new BoxSharedLinkRequest()
            .vanityName("myCustomName");

        //when
        BoxFolder folder = new BoxFolder(api, "12345");
        folder.createSharedLink(request);
    }

    @Test
    public void iterateWithOffset() {
        this.api.setRequestInterceptor(request -> {
            String query = request.getUrl().getQuery();
            assertThat(query, is("sort=name&direction=DESC&limit=2&offset=3"));
            return new BoxJSONResponse() {
                @Override
                public String getJSON() {
                    return "{\"entries\": [], \"total_count\": 0}";
                }
            };
        });
        BoxFolder folder = new BoxFolder(this.api, "123456");
        assertFalse(folder.getChildren("name", DESC, 3, 2).iterator().hasNext());
    }

    @Test
    public void startIteratingWithMarker() {
        this.api.setRequestInterceptor(request -> {
            String query = request.getUrl().getQuery();
            assertThat(query, CoreMatchers.is("limit=2&usemarker=true"));
            return new BoxJSONResponse() {
                @Override
                public String getJSON() {
                    return "{\"entries\": [], \"next_marker\": \"\"}";
                }
            };
        });
        BoxFolder folder = new BoxFolder(this.api, "123456");
        assertFalse(folder.getChildren(
            SortParameters.none(), marker(2)).iterator().hasNext()
        );
    }

    @Test
    public void setMetadataWorksWhenNoChangesSubmittedAndConflictOccured() {
        // given
        BoxAPIConnection api = new BoxAPIConnectionForTests("");
        BoxFolder folder = new BoxFolder(api, "someFolder");
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
        folder.setMetadata("template1", "enterprise-1", new Metadata());

        // then
        assertThat(postCounter.get(), is(1));
        assertThat(getCounter.get(), is(1));
    }

    @Test
    public void renameFolder() {
        final String folderId = "12345";
        final String metadataURL = "/2.0/folders/" + folderId;
        String result = TestUtils.getFixture("BoxFolder/GetFolderInfo200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
            .withRequestBody(new EqualToJsonPattern("{\"name\": \"New Name\"}", false, false))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)
                .withStatus(200)));

        new BoxFolder(this.api, folderId).rename("New Name");
    }

    @Test
    public void collaborateWithOptionalParamsSendsCorrectRequest() {

        final String folderId = "983745";
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

                assertEquals(folderId, body.get("item").asObject().get("id").asString());
                assertEquals("folder", body.get("item").asObject().get("type").asString());
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

        BoxFolder folder = new BoxFolder(api, folderId);
        Date expiresAt = new Date(1586289090000L);
        folder.collaborate(collaboratorLogin, collaboratorRole, true, true, expiresAt, true);
    }
}
