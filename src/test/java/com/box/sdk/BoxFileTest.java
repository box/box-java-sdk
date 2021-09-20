package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.experimental.categories.Category;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.box.sdk.UniqueTestFolder.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.longThat;
import static org.mockito.Mockito.*;

/**
 * {@link BoxFile} related unit tests.
 */
public class BoxFileTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    static final String LARGE_FILE_NAME = "Tamme-Lauri_tamm_suvepäeval.jpg";

    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithRepresentationsIntegrationTestWithSimpleHint() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, "red_100x100.png").getResource();
            List<Representation> representations =
                    uploadedFile.getInfoWithRepresentations("[png]").getRepresentations();
            assertTrue("There should be at least one representation", representations.size() > 0);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithRepresentationsIntegrationTestWithComplexHint() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, "red_100x100.png").getResource();
            List<Representation> representations = uploadedFile.getInfoWithRepresentations(
                    "[jpg,png?dimensions=1024x1024][pdf]").getRepresentations();
            assertTrue("There should be at least one representation", representations.size() > 0);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getRepresentationContentSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "smalltest.pdf";
        BoxFile file = null;
        try {
            file = uploadSampleFileToUniqueFolder(api, fileName).getResource();

            String representationHint = "[jpg?dimensions=32x32]";
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            file.getRepresentationContent(representationHint, outputStream);
            byte[] downloadedRepresentationContent = outputStream.toByteArray();

            assertNotNull(downloadedRepresentationContent);
        } finally {
            this.deleteFile(file);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadAndDownloadFileSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder folder = getUniqueFolder(api);
        String fileName = "red_100x100.png";
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        long fileSize = new File(filePath).length();
        byte[] fileContent = readAllBytes(filePath);
        BoxFile uploadedFile = null;
        try {
            InputStream uploadStream = new FileInputStream(filePath);
            ProgressListener mockUploadListener = mock(ProgressListener.class);
            BoxFile.Info uploadedFileInfo = folder.uploadFile(uploadStream,
                    BoxFileTest.generateString(), fileSize, mockUploadListener);
            uploadedFile = uploadedFileInfo.getResource();

            ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
            ProgressListener mockDownloadListener = mock(ProgressListener.class);
            uploadedFile.download(downloadStream, mockDownloadListener);
            byte[] downloadedFileContent = downloadStream.toByteArray();

            assertThat(downloadedFileContent, is(equalTo(fileContent)));
            assertThat(folder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
            verify(mockUploadListener, atLeastOnce()).onProgressChanged(anyLong(), longThat(is(equalTo(fileSize))));
            verify(mockDownloadListener, atLeastOnce()).onProgressChanged(anyLong(), longThat(is(equalTo(fileSize))));
        } finally {
            this.deleteFile(uploadedFile);
        }

    }

    @Test
    @Category(IntegrationTest.class)
    public void downloadFileRangeSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "red_100x100.png";
        BoxFile uploadedFile = null;
        try {
            BoxFile.Info uplodedFileInfo = uploadSampleFileToUniqueFolder(api, fileName);
            uploadedFile = uplodedFileInfo.getResource();
            long firstHalf = uplodedFileInfo.getSize() / 2;

            ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
            uploadedFile.downloadRange(downloadStream, 0, firstHalf);
            uploadedFile.downloadRange(downloadStream, firstHalf + 1);
            byte[] downloadedFileContent = downloadStream.toByteArray();

            assertThat(downloadedFileContent, is(equalTo(this.readFileContent(fileName))));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheNameField() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[getInfoWithOnlyTheNameField] Test File.txt";
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            BoxFile.Info uploadedFileInfo = uploadedFile.getInfo("name");

            assertThat(uploadedFileInfo.getName(), is(equalTo(fileName)));
            assertThat(uploadedFileInfo.getDescription(), is(nullValue()));
            assertThat(uploadedFileInfo.getSize(), is(equalTo(0L)));
        } finally {
            this.deleteFile(uploadedFile);
        }

    }

    @Test
    @Category(IntegrationTest.class)
    public void fileLockAndUnlockSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[getInfoWithOnlyTheLockField] Test File" + Calendar.getInstance().getTimeInMillis() + ".txt";
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date expiresAt = calendar.getTime();
            uploadedFile.lock(expiresAt, false);

            BoxFile.Info uploadedFileInfo = uploadedFile.getInfo("lock", "created_by");
            BoxLock fileLock = uploadedFileInfo.getLock();

            assertThat(fileLock, is(instanceOf(BoxLock.class)));
            assertThat(fileLock.getExpiresAt().toString(), is(equalTo(expiresAt.toString())));
            assertThat(fileLock.getIsDownloadPrevented(), is(equalTo(false)));
            assertThat(fileLock.getCreatedBy().getID(), is(equalTo(uploadedFileInfo.getCreatedBy().getID())));

            uploadedFile.unlock();

            BoxFile.Info updatedFileInfo = uploadedFile.getInfo("lock");
            assertThat(updatedFileInfo.getLock(), is(nullValue()));

            uploadedFile.lock(true);

            uploadedFileInfo = uploadedFile.getInfo("lock", "created_by");
            fileLock = uploadedFileInfo.getLock();

            assertThat(fileLock, is(instanceOf(BoxLock.class)));
            assertNull(fileLock.getExpiresAt());
            assertThat(fileLock.getIsDownloadPrevented(), is(equalTo(true)));
            assertThat(fileLock.getCreatedBy().getID(), is(equalTo(uploadedFileInfo.getCreatedBy().getID())));

            uploadedFile.unlock();

            uploadedFile.lock();

            uploadedFileInfo = uploadedFile.getInfo("lock", "created_by");
            fileLock = uploadedFileInfo.getLock();

            assertThat(fileLock, is(instanceOf(BoxLock.class)));
            assertNull(fileLock.getExpiresAt());
            assertThat(fileLock.getIsDownloadPrevented(), is(equalTo(false)));
            assertThat(fileLock.getCreatedBy().getID(), is(equalTo(uploadedFileInfo.getCreatedBy().getID())));

            uploadedFile.unlock();
        } finally {
            this.deleteFile(uploadedFile);
        }

    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithAllFields() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[getInfoWithAllFields] Test File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            BoxFile.Info uploadedFileInfo = uploadedFile.getInfo(BoxFile.ALL_FIELDS);

            assertThat(uploadedFileInfo.getName(), is(equalTo(fileName)));
            assertThat(uploadedFileInfo.getVersionNumber(), is(equalTo("1")));
            assertThat(uploadedFileInfo.getCommentCount(), is(equalTo(0L)));
            assertThat(uploadedFileInfo.getExtension(), is(equalTo("txt")));
            assertThat(uploadedFileInfo.getIsPackage(), is(false));
            assertThat(uploadedFileInfo.getItemStatus(), is(equalTo("active")));
            assertThat(uploadedFileInfo.getVersion(), not(nullValue()));
            assertThat(uploadedFileInfo.getVersion().getVersionID(), not(nullValue()));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFileWithSpecialCharsInNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String originalFileName = "[updateFileWithSpecialCharsInNameSucceeds] abc\";def.txt";

        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, originalFileName, "Test file");
            assertThat(uploadedFile.getInfo().getName(), is(equalTo(originalFileName)));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFileInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String originalFileName = "[updateFileInfoSucceeds] Original Name.txt";
        String newFileName = "[updateFileInfoSucceeds] New Name.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, originalFileName, "Test file");

            BoxFile.Info newInfo = uploadedFile.new Info();
            newInfo.setName(newFileName);
            uploadedFile.updateInfo(newInfo);

            assertThat(uploadedFile.getInfo().getName(), is(equalTo(newFileName)));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void renameFileSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String originalFileName = "[renameFileSucceeds] Original Name.txt";
        String newFileName = "[renameFileSucceeds] New Name.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, originalFileName, "Test file");

            uploadedFile.rename(newFileName);
            BoxFile.Info newInfo = uploadedFile.getInfo();

            assertThat(newInfo.getName(), is(equalTo(newFileName)));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(UnitTest.class)
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
    @Category(IntegrationTest.class)
    public void uploadAndDownloadMultipleVersionsSucceeds() throws UnsupportedEncodingException {
        BoxFile uploadedFile = null;
        String fileName = "[uploadAndDownloadMultipleVersionsSucceeds] Multi-version File.txt";
        String version1Content = "Version 1";
        String version1Sha = "db3cbc01da600701b9fe4a497fe328e71fa7022f";
        String version2Content = "Version 2";
        ProgressListener mockUploadListener = mock(ProgressListener.class);
        try {
            uploadedFile = uploadTwoFileVersionsToUniqueFolder(fileName, version1Content,
                    version2Content, mockUploadListener);
            Collection<BoxFileVersion> versions = uploadedFile.getVersions();
            BoxFileVersion previousVersion = versions.iterator().next();

            ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
            ProgressListener mockDownloadListener = mock(ProgressListener.class);
            previousVersion.download(downloadStream, mockDownloadListener);
            String downloadedContent = downloadStream.toString(StandardCharsets.UTF_8.name());

            assertThat(versions, hasSize(1));
            assertThat(previousVersion.getSha1(), is(equalTo(version1Sha)));
            assertThat(downloadedContent, equalTo(version1Content));
            verify(mockDownloadListener, atLeastOnce()).onProgressChanged(anyLong(), anyLong());
            long version1Size = version1Content.getBytes(StandardCharsets.UTF_8).length;
            verify(mockUploadListener, atLeastOnce()).onProgressChanged(anyLong(),
                    longThat(is(equalTo(version1Size))));

        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadNewVersionSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "Multi-version File.txt";
        String updatedFileName = "[uploadNewVersionSucceeds] Multi-version File.txt";
        Date contentModifiedAt = new Date(10000);
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Version 1");
            InputStream uploadStream = this.getFileContent("Version 1");
            BoxFile.Info newVersion = uploadedFile.uploadNewVersion(
                    uploadStream,
                    null,
                    contentModifiedAt,
                    updatedFileName,
                    0,
                    null
            );

            assertEquals(updatedFileName, newVersion.getName());
            assertEquals(contentModifiedAt, newVersion.getContentModifiedAt());
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteVersionSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[deleteVersionSucceeds] Multi-version File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            uploadedFile.uploadNewVersion(this.getFileContent("Version 2"));

            Collection<BoxFileVersion> versions = uploadedFile.getVersions();
            BoxFileVersion previousVersion = versions.iterator().next();
            previousVersion.delete();

            Collection<BoxFileVersion> versionsAfterRemove = uploadedFile.getVersions();
            assertThat(versionsAfterRemove, Matchers.<BoxFileVersion>hasSize(1));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void shouldReturnTrashedAtForADeleteVersion() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[deleteVersionSucceeds] Multi-version File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            uploadedFile.uploadNewVersion(this.getFileContent("Version 2"));
            Collection<BoxFileVersion> versions = uploadedFile.getVersions();
            assertThat(versions, Matchers.<BoxFileVersion>hasSize(1));

            BoxFileVersion version = versions.iterator().next();
            assertThat(version.getTrashedAt(), is(nullValue()));

            version.delete();

            BoxFileVersion trashedVersion = uploadedFile.getVersions().iterator().next();
            assertThat(trashedVersion.getTrashedAt(), is(notNullValue()));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void promoteVersionsSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[promoteVersionsSucceeds] Multi-version File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            uploadedFile.uploadNewVersion(this.getFileContent("Version 1"));
            uploadedFile.uploadNewVersion(this.getFileContent("Version 2"));

            Iterator<BoxFileVersion> iterator = uploadedFile.getVersions().iterator();
            BoxFileVersion version1 = iterator.next();
            version1.promote();

            ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
            uploadedFile.download(downloadStream);
            String downloadedContent = downloadStream.toString(StandardCharsets.UTF_8.name());
            assertThat(downloadedContent, equalTo("Version 1"));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void copyFileSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder uploadFolder = getUniqueFolder(api);
        String originalFileName = "[copyFileSucceeds] Original File.txt";
        String newFileName = "[copyFileSucceeds] New File.txt";
        String fileContent = "Test file";
        BoxFile uploadedFile = null;
        BoxFile copiedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, originalFileName, fileContent);
            copiedFile = uploadedFile.copy(uploadFolder, newFileName).getResource();

            ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
            copiedFile.download(downloadStream);
            String downloadedContent = downloadStream.toString(StandardCharsets.UTF_8.name());
            assertThat(downloadedContent, equalTo(fileContent));
        } finally {
            this.deleteFile(uploadedFile);
            this.deleteFile(copiedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void moveFileSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[moveFileSucceeds] Test File.txt";
        String folderName = "[moveFileSucceeds] Destination Folder";
        BoxFile uploadedFile = null;
        BoxFolder destinationFolder = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");

            destinationFolder = getUniqueFolder(api).createFolder(folderName).getResource();
            uploadedFile.move(destinationFolder);

            assertThat(destinationFolder,
                    hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
            this.deleteFolder(destinationFolder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void createAndUpdateSharedLinkSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[createAndUpdateSharedLinkSucceeds] Test File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
            permissions.setCanDownload(true);
            permissions.setCanPreview(true);
            BoxSharedLink sharedLink = uploadedFile.createSharedLink(BoxSharedLink.Access.OPEN, null, permissions);

            assertThat(sharedLink.getURL(), not(isEmptyOrNullString()));

            sharedLink.getPermissions().setCanDownload(false);
            BoxFile.Info info = uploadedFile.new Info();
            info.setSharedLink(sharedLink);
            uploadedFile.updateInfo(info);

            assertThat(uploadedFile.getInfo().getSharedLink().getPermissions().getCanDownload(), is(false));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCommentSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[addCommentSucceeds] Test File.txt";
        String commentMessage = "Non-empty message";
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            BoxComment.Info addedCommentInfo = uploadedFile.addComment(commentMessage);

            assertThat(addedCommentInfo.getMessage(), is(equalTo(commentMessage)));
            assertThat(uploadedFile.getComments(),
                    hasItem(Matchers.<BoxComment.Info>hasProperty("ID", equalTo(addedCommentInfo.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCommentWithMentionSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[addCommentWithMentionSucceeds] Test File.txt";
        String commentMessage = String.format(
                "Message mentioning @[%s:%s]",
                TestConfig.getCollaboratorID(),
                TestConfig.getCollaborator()
        );
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            BoxComment.Info addedCommentInfo = uploadedFile.addComment(commentMessage);

            String expectedCommentMessage = "Message mentioning " + TestConfig.getCollaborator();
            assertThat(addedCommentInfo.getMessage(), is(equalTo(expectedCommentMessage)));
            assertThat(uploadedFile.getComments(),
                    hasItem(Matchers.<BoxComment.Info>hasProperty("ID", equalTo(addedCommentInfo.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void createMetadataAndGetMetadataOnInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[createMetadataSucceeds] Test File.txt";

        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            uploadedFile.createMetadata(new Metadata().add("/foo", "bar"));

            Metadata metadata = uploadedFile.getMetadata();
            assertNotNull(metadata);
            assertEquals("bar", metadata.getString("/foo"));

            Metadata actualMD = uploadedFile.getInfo("metadata.global.properties")
                    .getMetadata("properties", "global");
            assertNotNull("Metadata should not be null for this file", actualMD);
        } catch (BoxAPIException e) {
            fail("Metadata should have been present on this folder");
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateMetadataSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[updateMetadataSucceeds] Test File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            uploadedFile.createMetadata(new Metadata().add("/foo", "bar"));

            Metadata metadata = uploadedFile.getMetadata();
            assertNotNull(metadata);
            assertEquals("bar", metadata.getString("/foo"));

            uploadedFile.updateMetadata(metadata.replace("/foo", "baz"));

            Metadata updatedMetadata = uploadedFile.getMetadata();
            assertNotNull(updatedMetadata);
            assertEquals("baz", updatedMetadata.getString("/foo"));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void addTaskSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[addTaskSucceeds] Test File " + Calendar.getInstance().getTimeInMillis() + ".txt";
        String taskMessage = "Non-empty message";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date dueAt = new Date(new Date().getTime() + (1000 * 24 * 60 * 60));
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            BoxTask.Info addedTaskInfo = uploadedFile.addTask(BoxTask.Action.REVIEW, taskMessage, dueAt);

            assertThat(addedTaskInfo.getMessage(), is(equalTo(taskMessage)));
            assertThat(dateFormat.format(addedTaskInfo.getDueAt()), is(equalTo(dateFormat.format(dueAt))));
            assertThat(uploadedFile.getTasks(), hasItem(Matchers.<BoxTask.Info>hasProperty("ID",
                    equalTo(addedTaskInfo.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getPreviewLink() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[getPreviewLink] Test File.txt";
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            URL uploadedFilePreviewLink = uploadedFile.getPreviewLink();

            assertThat(uploadedFilePreviewLink, is(notNullValue()));
            assertThat(uploadedFilePreviewLink.toString(), not(isEmptyOrNullString()));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getDownloadURL() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[getPreviewLink] Test File.txt";
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            URL uploadedFileDownloadURL = uploadedFile.getDownloadURL();

            assertThat(uploadedFileDownloadURL, is(notNullValue()));
            assertThat(uploadedFileDownloadURL.toString(), not(isEmptyOrNullString()));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getThumbnail() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[getPreviewLink] Test File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            byte[] thumbnail = uploadedFile.getThumbnail(BoxFile.ThumbnailFileType.PNG, 256, 256, 256, 256);

            assertThat(thumbnail, is(notNullValue()));
            assertNotEquals(thumbnail.length, 0);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void setCollectionsSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[setCollectionsSucceeds] Test File " + Calendar.getInstance().getTimeInMillis() + ".txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(api);
            BoxCollection.Info favoritesInfo = collections.iterator().next();
            BoxFile.Info updatedInfo = uploadedFile.setCollections(favoritesInfo.getResource());

            assertThat(updatedInfo.getCollections(), hasItem(Matchers.<BoxCollection.Info>hasProperty("ID",
                    equalTo(favoritesInfo.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void setCollectionsWithInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[setCollectionsWithInfoSucceeds] Test File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(api);
            BoxCollection.Info favoritesInfo = collections.iterator().next();
            ArrayList<BoxCollection> newCollections = new ArrayList<>();
            newCollections.add(favoritesInfo.getResource());
            BoxFile.Info fileInfo = uploadedFile.new Info();
            fileInfo.setCollections(newCollections);
            uploadedFile.updateInfo(fileInfo);
            BoxFile.Info updatedInfo = uploadedFile.getInfo("collections");

            assertThat(fileInfo.getCollections(), hasItem(Matchers.<BoxCollection.Info>hasProperty("ID",
                    equalTo(favoritesInfo.getID()))));
            assertThat(updatedInfo.getCollections(), hasItem(Matchers.<BoxCollection.Info>hasProperty("ID",
                    equalTo(favoritesInfo.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(UnitTest.class)
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
    @Category(IntegrationTest.class)
    public void uploadSessionCommitFlowSuccess() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder folder = getUniqueFolder(api);

        BoxFile uploadedFile = null;
        try {
            URL fileURL = this.getClass().getResource("/sample-files/" + LARGE_FILE_NAME);
            String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
            File file = new File(filePath);
            long fileSize = file.length();

            //Create the session
            BoxFileUploadSession.Info session =
                    this.createFileUploadSession(folder, BoxFileTest.generateString(), fileSize);

            //Create the parts
            MessageDigest fileDigest = this.uploadParts(session, fileSize, LARGE_FILE_NAME);

            //List the session parts
            List<BoxFileUploadSessionPart> parts = this.listUploadSessionParts(session.getResource());

            byte[] digestBytes = fileDigest.digest();
            String digest = Base64.encode(digestBytes);

            //Verify the delete session
            uploadedFile = this.commitSession(session.getResource(), digest, parts);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadSessionVersionCommitFlowSuccess() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder folder = getUniqueFolder(api);

        BoxFile.Info fileInfo = this.createFile(folder);

        BoxFile uploadedFile = fileInfo.getResource();
        try {
            //Create the session
            BoxFileUploadSession.Info session = this.createFileUploadSession(uploadedFile, fileInfo.getSize());

            //Create the parts
            MessageDigest fileDigest = this.uploadParts(session, fileInfo.getSize(), LARGE_FILE_NAME);

            //List the session parts
            List<BoxFileUploadSessionPart> parts = this.listUploadSessionParts(session.getResource());

            byte[] digestBytes = fileDigest.digest();
            String digest = Base64.encode(digestBytes);

            //Verify the commit session
            uploadedFile = this.commitSession(session.getResource(), digest, parts);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadSessionAbortFlowSuccess() throws Exception {
        URL fileURL = this.getClass().getResource("/sample-files/" + LARGE_FILE_NAME);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        FileInputStream stream = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        stream.read(fileBytes);
        InputStream uploadStream = new ByteArrayInputStream(fileBytes);

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder folder = getUniqueFolder(api);
        BoxFile uploadedFile = folder.uploadFile(uploadStream, BoxFileTest.generateString()).getResource();
        try {
            BoxFileUploadSession.Info sessionInfo = uploadedFile.createUploadSession(fileBytes.length);
            assertNotNull(sessionInfo.getUploadSessionId());
            assertNotNull(sessionInfo.getSessionExpiresAt());

            BoxFileUploadSession.Endpoints endpoints = sessionInfo.getSessionEndpoints();
            assertNotNull(endpoints);
            assertNotNull(endpoints.getUploadPartEndpoint());
            assertNotNull(endpoints.getStatusEndpoint());
            assertNotNull(endpoints.getListPartsEndpoint());
            assertNotNull(endpoints.getCommitEndpoint());
            assertNotNull(endpoints.getAbortEndpoint());

            //Verify the status of the session
            BoxFileUploadSession session = sessionInfo.getResource();
            this.verifySessionExists(session);

            //Verify the delete session
            session.abort();
            this.verifySessionWasAborted(session);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void canUploadLargeFileVersion() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile.Info uploadedFileInfo = null;

        try {
            uploadedFileInfo = uploadSampleFileToUniqueFolder(api, LARGE_FILE_NAME);
            boolean result = uploadedFileInfo.getResource().canUploadVersion("new name");

            assertTrue(result);
        } finally {
            this.deleteFile(uploadedFileInfo.getResource());
        }

    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadLargeFileVersion() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile.Info uploadedFile = null;
        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, LARGE_FILE_NAME);

            URL fileURL = this.getClass().getResource("/sample-files/" + LARGE_FILE_NAME);
            String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
            File file = new File(filePath);
            FileInputStream stream = new FileInputStream(file);
            BoxFile.Info fileVerion = uploadedFile.getResource().uploadLargeFile(stream, file.length());
            assertNotNull(fileVerion);
        } finally {
            this.deleteFile(uploadedFile.getResource());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadLargeFileVersionWithAttributes() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile.Info uploadedFile = null;
        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, LARGE_FILE_NAME);

            Map<String, String> fileAttributes = new HashMap<>();
            fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

            URL fileURL = this.getClass().getResource("/sample-files/" + LARGE_FILE_NAME);
            String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
            File file = new File(filePath);
            FileInputStream stream = new FileInputStream(file);
            BoxFile.Info fileVersion =
                    uploadedFile.getResource().uploadLargeFile(stream, file.length(), fileAttributes);
            assertNotNull(fileVersion);

            assertEquals(1491613088000L, fileVersion.getContentModifiedAt().getTime());
        } finally {
            this.deleteFile(uploadedFile.getResource());
        }
    }

    @Test
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
    public void testDeleteFileSucceeds() {
        final String fileID = "12345";
        final String deleteFileURL = "/files/" + fileID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFileURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxFile file = new BoxFile(this.api, fileID);
        this.deleteFile(file);
    }

    @Test
    @Category(UnitTest.class)
    public void testLockFileSucceedsAndSendsCorrectJson() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        final boolean isDownloadPrevented = true;
        final String createdByLogin = "test@user.com";
        final String createdByName = "Test User";

        JsonObject innerObject = new JsonObject()
                .add("type", "lock")
                .add("is_download_prevented", "true");

        JsonObject lockObject = new JsonObject()
                .add("lock", innerObject);

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
    @Category(UnitTest.class)
    public void testUnlockFileSucceedsAndSendSendsCorrectJson() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        JsonObject unlockObject = new JsonObject()
                .add("lock", JsonObject.NULL);

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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
    public void testSetClassificationThrowsException() {
        final String fileID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/files/" + fileID + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
                .add("op", "replace")
                .add("path", "/Box__Security__Classification__Key")
                .add("value", "Internal");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
                .willReturn(WireMock.aResponse()
                        .withStatus(403)));

        BoxFile file = new BoxFile(this.api, fileID);
        file.setClassification(classificationType);
    }

    @Test
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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
    @Category(UnitTest.class)
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

    protected static byte[] readAllBytes(String fileName) throws IOException {
        RandomAccessFile f = new RandomAccessFile(fileName, "r");
        byte[] b = new byte[(int) f.length()];
        f.read(b);
        return b;
    }

    private BoxFile.Info parallelMuliputUpload(File file, BoxFolder folder, String fileName)
            throws IOException, InterruptedException {
        FileInputStream newStream = new FileInputStream(file);
        return folder.uploadLargeFile(newStream, BoxFileTest.generateString(), file.length());
    }

    protected static String generateString() {
        Random rng = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = 10;
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
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

    private void deleteFile(BoxFile file) {
        if (file != null) {
            file.delete();
        }
    }

    private void deleteFolder(BoxFolder folder) {
        if (folder != null) {
            folder.delete(true);
        }
    }

    private byte[] readFileContent(String fileName) throws IOException {
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        return readAllBytes(filePath);
    }

    private ByteArrayInputStream getFileContent(String fileContent) {
        return new ByteArrayInputStream(fileContent.getBytes(UTF_8));
    }


    private BoxFileUploadSession.Info createFileUploadSession(BoxFolder folder, String fileName, long fileSize) {
        BoxFileUploadSession.Info session = folder.createUploadSession(fileName, fileSize);
        assertNotNull(session.getUploadSessionId());
        assertNotNull(session.getSessionExpiresAt());

        BoxFileUploadSession.Endpoints endpoints = session.getSessionEndpoints();
        assertNotNull(endpoints);
        assertNotNull(endpoints.getUploadPartEndpoint());
        assertNotNull(endpoints.getStatusEndpoint());
        assertNotNull(endpoints.getListPartsEndpoint());
        assertNotNull(endpoints.getCommitEndpoint());
        assertNotNull(endpoints.getAbortEndpoint());

        return session;
    }

    private BoxFileUploadSession.Info createFileUploadSession(BoxFile uploadedFile, long fileSize) {
        BoxFileUploadSession.Info session = uploadedFile.createUploadSession(fileSize);
        assertNotNull(session.getUploadSessionId());
        assertNotNull(session.getSessionExpiresAt());

        BoxFileUploadSession.Endpoints endpoints = session.getSessionEndpoints();
        assertNotNull(endpoints);
        assertNotNull(endpoints.getUploadPartEndpoint());
        assertNotNull(endpoints.getStatusEndpoint());
        assertNotNull(endpoints.getListPartsEndpoint());
        assertNotNull(endpoints.getCommitEndpoint());
        assertNotNull(endpoints.getAbortEndpoint());

        return session;
    }

    private MessageDigest uploadParts(BoxFileUploadSession.Info session, long fileSize, String fileName)
            throws Exception {

        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);

        FileInputStream stream = new FileInputStream(file);
        MessageDigest fileDigest = MessageDigest.getInstance("SHA1");
        DigestInputStream dis = new DigestInputStream(stream, fileDigest);

        long offset = 0;
        long processed = 0;
        boolean canBreak = false;
        do {
            long min = session.getPartSize();
            long diff = fileSize - processed;
            if (diff < min) {
                min = diff;
                canBreak = true;
            }

            BoxFileUploadSessionPart part = session.getResource().uploadPart(dis, offset, (int) min, fileSize);
            assertNotNull(part.getSha1());
            assertNotNull(part.getPartId());
            assertEquals(part.getOffset(), offset);
            assertTrue(part.getSize() <= session.getPartSize());
            offset = offset + session.getPartSize();
            processed += min;
        } while (!canBreak);

        return fileDigest;
    }

    private BoxFile.Info createFile(BoxFolder folder) throws IOException {
        return this.createFile(folder, BoxFileTest.generateString());
    }

    private BoxFile.Info createFile(BoxFolder folder, String fileName) throws IOException {
        URL fileURL = this.getClass().getResource("/sample-files/" + BoxFileTest.LARGE_FILE_NAME);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        long fileSize = file.length();

        byte[] fileBytes = new byte[(int) fileSize];

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        return folder.uploadFile(uploadStream, fileName);
    }

    private List<BoxFileUploadSessionPart> listUploadSessionParts(BoxFileUploadSession session) {
        BoxFileUploadSessionPartList list = session.listParts(0, 100);
        return list.getEntries();
    }

    private BoxFile commitSession(BoxFileUploadSession session, String digest, List<BoxFileUploadSessionPart> parts) {
        BoxFile.Info file = session.commit(digest, parts, null, null, null);
        assertNotNull(file);
        return file.getResource();
    }

    private void verifySessionExists(BoxFileUploadSession session) {
        assertNotNull(session);
        BoxFileUploadSession.Info sessionInfo = session.getStatus();
        assertNotNull(sessionInfo.getUploadSessionId());
        assertNotNull(sessionInfo.getSessionExpiresAt());
    }

    private void verifySessionWasAborted(BoxFileUploadSession session) {
        try {
            session.getStatus();
            //If the session is aborted, this line should not be executed.
            fail("Upload session is not deleted");
        } catch (BoxAPIException apiEx) {
            assertEquals(apiEx.getResponseCode(), 404);
        }
    }
}
