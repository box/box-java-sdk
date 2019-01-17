package com.box.sdk;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.longThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.eclipsesource.json.JsonArray;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.ClassRule;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxFile} related unit tests.
 */
public class BoxFileTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    static final String LARGE_FILE_NAME = "oversize_pdf_test_0.pdf";

    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithRepresentationsIntegrationTestWithSimpleHint() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile file = new BoxFile(api, "135907614435");
        List<Representation> representations = file.getInfoWithRepresentations("[png]").getRepresentations();
        Assert.assertTrue("There should be at least one representation", representations.size() > 0);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithRepresentationsIntegrationTestWithComplexHint() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile file = new BoxFile(api, "135907614435");
        List<Representation> representations = file.getInfoWithRepresentations(
                "[jpg,png?dimensions=1024x1024][pdf]").getRepresentations();
        Assert.assertTrue("There should be at least one representation", representations.size() > 0);
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadAndDownloadFileSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "Tamme-Lauri_tamm_suvepäeval.jpg";
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        long fileSize = new File(filePath).length();
        byte[] fileContent = readAllBytes(filePath);

        InputStream uploadStream = new FileInputStream(filePath);
        ProgressListener mockUploadListener = mock(ProgressListener.class);
        BoxFile.Info uploadedFileInfo = rootFolder.uploadFile(uploadStream,
                BoxFileTest.generateString(), fileSize, mockUploadListener);
        BoxFile uploadedFile = uploadedFileInfo.getResource();

        ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
        ProgressListener mockDownloadListener = mock(ProgressListener.class);
        uploadedFile.download(downloadStream, mockDownloadListener);
        byte[] downloadedFileContent = downloadStream.toByteArray();

        assertThat(downloadedFileContent, is(equalTo(fileContent)));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
        verify(mockUploadListener, atLeastOnce()).onProgressChanged(anyLong(), longThat(is(equalTo(fileSize))));
        verify(mockDownloadListener, atLeastOnce()).onProgressChanged(anyLong(), longThat(is(equalTo(fileSize))));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void downloadFileRangeSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "Tamme-Lauri_tamm_suvepäeval.jpg";
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        long fileSize = new File(filePath).length();
        byte[] fileContent = readAllBytes(filePath);

        InputStream uploadStream = new FileInputStream(filePath);
        ProgressListener mockUploadListener = mock(ProgressListener.class);
        BoxFile.Info uploadedFileInfo = rootFolder.uploadFile(uploadStream, fileName, fileSize, mockUploadListener);
        BoxFile uploadedFile = uploadedFileInfo.getResource();
        long firstHalf = uploadedFileInfo.getSize() / 2;

        ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
        uploadedFile.downloadRange(downloadStream, 0, firstHalf);
        uploadedFile.downloadRange(downloadStream, firstHalf + 1);
        byte[] downloadedFileContent = downloadStream.toByteArray();

        assertThat(downloadedFileContent, is(equalTo(fileContent)));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
        verify(mockUploadListener, atLeastOnce()).onProgressChanged(anyLong(), longThat(is(equalTo(fileSize))));

        uploadedFile.delete();
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
            uploadedFile = BoxFileTest.createAndUpdateFileHelper(fileName, version1Content,
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
            if (uploadedFile != null) {
                uploadedFile.delete();
            }
        }
    }

    protected static BoxFile createAndUpdateFileHelper(String fileName, String version1Content,
                                                       String version2Content, ProgressListener mockUploadListener) {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        byte[] version1Bytes = version1Content.getBytes(StandardCharsets.UTF_8);


        byte[] version2Bytes = version2Content.getBytes(StandardCharsets.UTF_8);
        long version2Size = version1Bytes.length;

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        uploadStream = new ByteArrayInputStream(version2Bytes);
        uploadedFile.uploadNewVersion(uploadStream, null, version2Size, mockUploadListener);
        return uploadedFile;
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheNameField() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getInfoWithOnlyTheNameField] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxFile.Info uploadedFileInfo = uploadedFile.getInfo("name");

        assertThat(uploadedFileInfo.getName(), is(equalTo(fileName)));
        assertThat(uploadedFileInfo.getDescription(), is(nullValue()));
        assertThat(uploadedFileInfo.getSize(), is(equalTo(0L)));

        uploadedFileInfo.getResource().delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void fileLockAndUnlockSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getInfoWithOnlyTheLockField] Test File" + Calendar.getInstance().getTimeInMillis() + ".txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

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

        updatedFileInfo.getResource().delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithAllFields() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getInfoWithAllFields] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxFile.Info uploadedFileInfo = uploadedFile.getInfo(BoxFile.ALL_FIELDS);

        assertThat(uploadedFileInfo.getName(), is(equalTo(fileName)));
        assertThat(uploadedFileInfo.getVersionNumber(), is(equalTo("1")));
        assertThat(uploadedFileInfo.getCommentCount(), is(equalTo(0L)));
        assertThat(uploadedFileInfo.getExtension(), is(equalTo("txt")));
        assertThat(uploadedFileInfo.getIsPackage(), is(false));
        assertThat(uploadedFileInfo.getItemStatus(), is(equalTo("active")));
        assertThat(uploadedFileInfo.getVersion(), not(nullValue()));
        assertThat(uploadedFileInfo.getVersion().getVersionID(), not(nullValue()));

        uploadedFileInfo.getResource().delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFileWithSpecialCharsInNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String originalFileName = "[updateFileWithSpecialCharsInNameSucceeds] abc\";def.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile.Info uploadedFileInfo = rootFolder.uploadFile(uploadStream, originalFileName);
        BoxFile uploadedFile = uploadedFileInfo.getResource();

        assertThat(uploadedFileInfo.getName(), is(equalTo(originalFileName)));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFileInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String originalFileName = "[updateFileInfoSucceeds] Original Name.txt";
        String newFileName = "[updateFileInfoSucceeds] New Name.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile.Info uploadedFileInfo = rootFolder.uploadFile(uploadStream, originalFileName);
        BoxFile uploadedFile = uploadedFileInfo.getResource();

        BoxFile.Info newInfo = uploadedFile.new Info();
        newInfo.setName(newFileName);
        uploadedFile.updateInfo(newInfo);

        assertThat(newInfo.getName(), is(equalTo(newFileName)));

        uploadedFile.delete();
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
        Assert.assertEquals("foo", tags.get(0));
        Assert.assertEquals("bar", tags.get(1));

        tags.add("baz");
        info.setTags(tags);

        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("foo", json.get("tags").asArray().get(0).asString());
                Assert.assertEquals("bar", json.get("tags").asArray().get(1).asString());
                Assert.assertEquals("baz", json.get("tags").asArray().get(2).asString());
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
    public void deleteVersionSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[deleteVersionSucceeds] Multi-version File.txt";
        byte[] version1Bytes = "Version 1".getBytes(StandardCharsets.UTF_8);
        byte[] version2Bytes = "Version 2".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        uploadStream = new ByteArrayInputStream(version2Bytes);
        uploadedFile.uploadNewVersion(uploadStream);

        Collection<BoxFileVersion> versions = uploadedFile.getVersions();
        BoxFileVersion previousVersion = versions.iterator().next();
        previousVersion.delete();

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void shouldReturnTrashedAtForADeleteVersion() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[deleteVersionSucceeds] Multi-version File.txt";
        byte[] version1Bytes = "Version 1".getBytes(StandardCharsets.UTF_8);
        byte[] version2Bytes = "Version 2".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        uploadStream = new ByteArrayInputStream(version2Bytes);
        uploadedFile.uploadNewVersion(uploadStream);

        Collection<BoxFileVersion> versions = uploadedFile.getVersions();
        BoxFileVersion previousVersion = versions.iterator().next();

        assertThat(previousVersion.getTrashedAt(), is(nullValue()));

        previousVersion.delete();
        versions = uploadedFile.getVersions();
        previousVersion = versions.iterator().next();

        assertThat(previousVersion.getTrashedAt(), is(notNullValue()));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void promoteVersionsSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[promoteVersionsSucceeds] Multi-version File " + Calendar.getInstance().getTimeInMillis()
                + ".txt";
        String version1Content = "Version 1";
        byte[] version1Bytes = version1Content.getBytes(StandardCharsets.UTF_8);
        byte[] version2Bytes = "Version 2".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        uploadStream = new ByteArrayInputStream(version2Bytes);
        uploadedFile.uploadNewVersion(uploadStream);

        Collection<BoxFileVersion> versions = uploadedFile.getVersions();
        BoxFileVersion previousVersion = versions.iterator().next();
        previousVersion.promote();

        ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
        uploadedFile.download(downloadStream);
        String downloadedContent = downloadStream.toString(StandardCharsets.UTF_8.name());
        assertThat(downloadedContent, equalTo(version1Content));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void copyFileSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String originalFileName = "[copyFileSucceeds] Original File.txt";
        String newFileName = "[copyFileSucceeds] New File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, originalFileName).getResource();

        BoxFile.Info copiedFileInfo = uploadedFile.copy(rootFolder, newFileName);
        BoxFile copiedFile = copiedFileInfo.getResource();

        ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
        copiedFile.download(downloadStream);
        String downloadedContent = downloadStream.toString(StandardCharsets.UTF_8.name());
        assertThat(downloadedContent, equalTo(fileContent));

        uploadedFile.delete();
        copiedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void moveFileSucceeds() throws UnsupportedEncodingException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[moveFileSucceeds] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);
        String folderName = "[moveFileSucceeds] Destination Folder";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        BoxFolder destinationFolder = rootFolder.createFolder(folderName).getResource();
        uploadedFile.move(destinationFolder);

        assertThat(destinationFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));

        uploadedFile.delete();
        destinationFolder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void createAndUpdateSharedLinkSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[createAndUpdateSharedLinkSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
        permissions.setCanDownload(true);
        permissions.setCanPreview(true);
        BoxSharedLink sharedLink = uploadedFile.createSharedLink(BoxSharedLink.Access.OPEN, null, permissions);

        assertThat(sharedLink.getURL(), not(isEmptyOrNullString()));

        sharedLink.getPermissions().setCanDownload(false);
        BoxFile.Info info = uploadedFile.new Info();
        info.setSharedLink(sharedLink);
        uploadedFile.updateInfo(info);

        assertThat(info.getSharedLink().getPermissions().getCanDownload(), is(false));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCommentSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[addCommentSucceeds] Test File " + Calendar.getInstance().getTimeInMillis() + ".txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String commentMessage = "Non-empty message";

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxComment.Info addedCommentInfo = uploadedFile.addComment(commentMessage);

        assertThat(addedCommentInfo.getMessage(), is(equalTo(commentMessage)));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCommentWithMentionSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[addCommentSucceeds] Test File " + Calendar.getInstance().getTimeInMillis() + ".txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String commentMessage = String.format("Message mentioning @[%s:%s]", TestConfig.getCollaboratorID(),
                TestConfig.getCollaborator());
        String expectedCommentMessage = "Message mentioning " + TestConfig.getCollaborator();

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxComment.Info addedCommentInfo = uploadedFile.addComment(commentMessage);

        assertThat(addedCommentInfo.getMessage(), is(equalTo(expectedCommentMessage)));
        assertThat(uploadedFile.getComments(), hasItem(Matchers.<BoxComment.Info>hasProperty("ID",
                equalTo(addedCommentInfo.getID()))));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void createMetadataAndGetMetadataOnInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[createMetadataSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        try {
            uploadedFile.createMetadata(new Metadata().add("/foo", "bar"));

            Metadata check1 = uploadedFile.getMetadata();
            Assert.assertNotNull(check1);
            Assert.assertEquals("bar", check1.getString("/foo"));

            Metadata actualMD = uploadedFile.getInfo("metadata.global.properties").getMetadata("properties", "global");
            assertNotNull("Metadata should not be null for this file", actualMD);
        } catch (BoxAPIException e) {
            fail("Metadata should have been present on this folder");
        } finally {
            uploadedFile.delete();
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateMetadataSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[updateMetadataSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        uploadedFile.createMetadata(new Metadata().add("/foo", "bar"));

        Metadata check1 = uploadedFile.getMetadata();
        Assert.assertNotNull(check1);
        Assert.assertEquals("bar", check1.getString("/foo"));

        uploadedFile.updateMetadata(check1.replace("/foo", "baz"));

        Metadata check2 = uploadedFile.getMetadata();
        Assert.assertNotNull(check2);
        Assert.assertEquals("baz", check2.getString("/foo"));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void addTaskSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[addTaskSucceeds] Test File " + Calendar.getInstance().getTimeInMillis() + ".txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String taskMessage = "Non-empty message";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date dueAt = new Date(new Date().getTime() + (1000 * 24 * 60 * 60));

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        BoxTask.Info addedTaskInfo = uploadedFile.addTask(BoxTask.Action.REVIEW, taskMessage, dueAt);

        assertThat(addedTaskInfo.getMessage(), is(equalTo(taskMessage)));
        assertThat(dateFormat.format(addedTaskInfo.getDueAt()), is(equalTo(dateFormat.format(dueAt))));
        assertThat(uploadedFile.getTasks(), hasItem(Matchers.<BoxTask.Info>hasProperty("ID",
                equalTo(addedTaskInfo.getID()))));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getPreviewLink() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getPreviewLink] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);
        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        URL uploadedFilePreviewLink = uploadedFile.getPreviewLink();

        assertThat(uploadedFilePreviewLink, is(notNullValue()));
        assertThat(uploadedFilePreviewLink.toString(), not(isEmptyOrNullString()));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getDownloadURL() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getPreviewLink] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);
        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        URL uploadedFileDownloadURL = uploadedFile.getDownloadURL();

        assertThat(uploadedFileDownloadURL, is(notNullValue()));
        assertThat(uploadedFileDownloadURL.toString(), not(isEmptyOrNullString()));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getThumbnail() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getPreviewLink] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);
        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        byte[] thumbnail = uploadedFile.getThumbnail(BoxFile.ThumbnailFileType.PNG, 256, 256, 256, 256);

        assertThat(thumbnail, is(notNullValue()));
        assertNotEquals(thumbnail.length, 0);

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void setCollectionsSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[setCollectionsSucceeds] Test File " + Calendar.getInstance().getTimeInMillis() + ".txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(api);
        BoxCollection.Info favoritesInfo = collections.iterator().next();
        BoxFile.Info updatedInfo = uploadedFile.setCollections(favoritesInfo.getResource());

        assertThat(updatedInfo.getCollections(), hasItem(Matchers.<BoxCollection.Info>hasProperty("ID",
                equalTo(favoritesInfo.getID()))));
        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void setCollectionsWithInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[setCollectionsWithInfoSucceeds] Test File.txt";
        String fileContent = "Test file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(api);
        BoxCollection.Info favoritesInfo = collections.iterator().next();
        ArrayList<BoxCollection> newCollections = new ArrayList<BoxCollection>();
        newCollections.add(favoritesInfo.getResource());
        BoxFile.Info fileInfo = uploadedFile.new Info();
        fileInfo.setCollections(newCollections);
        uploadedFile.updateInfo(fileInfo);
        BoxFile.Info updatedInfo = uploadedFile.getInfo("collections");

        assertThat(fileInfo.getCollections(), hasItem(Matchers.<BoxCollection.Info>hasProperty("ID",
                equalTo(favoritesInfo.getID()))));
        assertThat(updatedInfo.getCollections(), hasItem(Matchers.<BoxCollection.Info>hasProperty("ID",
                equalTo(favoritesInfo.getID()))));
        uploadedFile.delete();
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
                Assert.assertEquals(
                        "https://api.box.com/2.0/collaborations?notify=true",
                        request.getUrl().toString());
                Assert.assertEquals("POST", request.getMethod());

                Assert.assertEquals(fileID, body.get("item").asObject().get("id").asString());
                Assert.assertEquals("file", body.get("item").asObject().get("type").asString());
                Assert.assertEquals(collaboratorLogin, body.get("accessible_by").asObject().get("login").asString());
                Assert.assertEquals("user", body.get("accessible_by").asObject().get("type").asString());
                Assert.assertEquals(collaboratorRole.toJSONString(), body.get("role").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"collaboration\",\"id\":\"98763245\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, fileID);
        BoxCollaboration.Info collabInfo = file.collaborate(collaboratorLogin, collaboratorRole, true, true);
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadSessionCommitFlowSuccess() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxFile uploadedFile = null;
        try {
            URL fileURL = this.getClass().getResource("/sample-files/" + BoxFileTest.LARGE_FILE_NAME);
            String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
            File file = new File(filePath);
            long fileSize = file.length();

            //Create the session
            BoxFileUploadSession.Info session =
                    this.createFileUploadSession(rootFolder, BoxFileTest.generateString(), fileSize);

            //Create the parts
            MessageDigest fileDigest = this.uploadParts(uploadedFile, session, fileSize);

            //List the session parts
            List<BoxFileUploadSessionPart> parts = this.listUploadSessionParts(session.getResource());

            byte[] digestBytes = fileDigest.digest();
            String digest = Base64.encode(digestBytes);

            //Verify the delete session
            uploadedFile = this.commitSession(session.getResource(), digest, parts);
        } finally {
            if (uploadedFile != null) {
                uploadedFile.delete();
            }
        }
    }

    private BoxFileUploadSession.Info createFileUploadSession(BoxFolder folder, String fileName, long fileSize) {
        BoxFileUploadSession.Info session = folder.createUploadSession(fileName, fileSize);
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

        return session;
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadSessionVersionCommitFlowSuccess() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxFile.Info fileInfo = this.createFile(rootFolder);

        BoxFile uploadedFile = fileInfo.getResource();
        try {
            //Create the session
            BoxFileUploadSession.Info session = this.createFileUploadSession(uploadedFile, fileInfo.getSize());

            //Create the parts
            MessageDigest fileDigest = this.uploadParts(uploadedFile, session, fileInfo.getSize());

            //List the session parts
            List<BoxFileUploadSessionPart> parts = this.listUploadSessionParts(session.getResource());

            byte[] digestBytes = fileDigest.digest();
            String digest = Base64.encode(digestBytes);

            //Verify the commit session
            uploadedFile = this.commitSession(session.getResource(), digest, parts);
        } finally {
            uploadedFile.delete();
        }
    }

    private BoxFileUploadSession.Info createFileUploadSession(BoxFile uploadedFile, long fileSize) {
        BoxFileUploadSession.Info session = uploadedFile.createUploadSession(fileSize);
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

        return session;
    }

    private MessageDigest uploadParts(BoxFile uploadedFile, BoxFileUploadSession.Info session,
                                      long fileSize) throws Exception {
        return this.uploadParts(uploadedFile, session, fileSize, BoxFileTest.LARGE_FILE_NAME);
    }

    private MessageDigest uploadParts(BoxFile uploadedFile, BoxFileUploadSession.Info session,
                                      long fileSize, String fileName) throws Exception {

        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);

        FileInputStream stream = new FileInputStream(file);
        MessageDigest fileDigest = MessageDigest.getInstance("SHA1");
        DigestInputStream dis = new DigestInputStream(stream, fileDigest);

        long offset = 0;
        byte[] bytes = null;
        long processed = 0;
        boolean canBreak = false;
        while (true) {
            long min = session.getPartSize();
            long diff = fileSize - processed;
            if (diff < min) {
                min = diff;
                canBreak = true;
            }

            BoxFileUploadSessionPart part = session.getResource().uploadPart(dis, offset, (int) min, fileSize);
            Assert.assertNotNull(part.getSha1());
            Assert.assertNotNull(part.getPartId());
            Assert.assertEquals(part.getOffset(), offset);
            Assert.assertTrue(part.getSize() <= session.getPartSize());
            offset = offset + session.getPartSize();
            processed += min;
            if (canBreak) {
                break;
            }
        }

        return fileDigest;
    }

    private String generateHex() {
        String hex = "";
        while (hex.length() != 8) {
            Random random = new Random();
            int val = random.nextInt();
            hex = Integer.toHexString(val);
        }

        return hex;
    }

    private BoxFile.Info createFile(BoxFolder folder) throws IOException {
        return this.createFile(folder, BoxFileTest.generateString());
    }

    private BoxFile.Info createFile(BoxFolder folder, String fileName) throws IOException {
        URL fileURL = this.getClass().getResource("/sample-files/" + BoxFileTest.LARGE_FILE_NAME);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        long fileSize = file.length();

        FileInputStream stream = new FileInputStream(file);

        byte[] fileBytes = new byte[(int) fileSize];

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        return folder.uploadFile(uploadStream, fileName);
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadSessionAbortFlowSuccess() throws Exception {
        URL fileURL = this.getClass().getResource("/sample-files/" + BoxFileTest.LARGE_FILE_NAME);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        long fileSize = file.length();

        FileInputStream stream = new FileInputStream(file);

        byte[] fileBytes = new byte[(int) file.length()];
        stream.read(fileBytes);
        InputStream uploadStream = new ByteArrayInputStream(fileBytes);

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, BoxFileTest.generateString()).getResource();
        try {
            BoxFileUploadSession.Info session = uploadedFile.createUploadSession(fileBytes.length);
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
        } finally {
            uploadedFile.delete();
        }
    }

    private List<BoxFileUploadSessionPart> listUploadSessionParts(BoxFileUploadSession session) {
        BoxFileUploadSessionPartList list = session.listParts(0, 100);

        List<BoxFileUploadSessionPart> parts = list.getEntries();

        return parts;
    }

    private BoxFile commitSession(BoxFileUploadSession session, String digest, List<BoxFileUploadSessionPart> parts) {
        BoxFile.Info file = session.commit(digest, parts, null, null, null);
        Assert.assertNotNull(file);

        return file.getResource();
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

    protected static byte[] readAllBytes(String fileName) throws IOException {
        RandomAccessFile f = new RandomAccessFile(fileName, "r");
        byte[] b = new byte[(int) f.length()];
        f.read(b);
        return b;
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadLargeFileVersion() throws Exception {
        URL fileURL = this.getClass().getResource("/sample-files/" + BoxFileTest.LARGE_FILE_NAME);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        FileInputStream stream = new FileInputStream(file);

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFile.Info uploadedFile = rootFolder.uploadFile(stream, BoxFileTest.generateString());

        stream = new FileInputStream(file);

        BoxFile.Info fileVerion = uploadedFile.getResource().uploadLargeFile(stream, file.length());
        Assert.assertNotNull(fileVerion);

        fileVerion.getResource().delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetFileInfoSucceeds() throws IOException {
        String result = "";
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        final String fileName = "Example.pdf";
        final String pathCollectionName = "All Files";
        final String createdByLogin = "test@user.com";
        final String modifiedByName = "Test User";
        final String ownedByID = "1111";

        result = TestConfig.getFixture("BoxFile/GetFileInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info info = file.getInfo();

        Assert.assertEquals("file", info.getType());
        Assert.assertEquals(fileID, info.getID());
        Assert.assertEquals(fileName, info.getName());
        Assert.assertEquals(pathCollectionName, info.getPathCollection().get(0).getName());
        Assert.assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(modifiedByName, info.getModifiedBy().getName());
        Assert.assertEquals(ownedByID, info.getOwnedBy().getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetTasksWithFields() throws IOException {
        String result = "";
        final String fileID = "12345";
        final String tasksURL = "/files/" + fileID + "/tasks";

        result = TestConfig.getFixture("BoxFile/GetFileTasksInfoWithFields200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(tasksURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        List<BoxTask.Info> tasks = file.getTasks("is_completed");
        for (BoxTask.Info task : tasks) {
            Assert.assertNotNull(task.isCompleted());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateFileInformationSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        final String newFileName = "New File Name";
        JsonObject updateObject = new JsonObject()
                .add("name", newFileName);

        result = TestConfig.getFixture("BoxFile/UpdateFileInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
                .withRequestBody(WireMock.equalToJson(updateObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info info = file.new Info();
        info.setName(newFileName);
        file.updateInfo(info);

        Assert.assertEquals(newFileName, info.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testCopyFileSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID + "/copy";
        final String parentID = "0";
        final String parentName = "All Files";
        JsonObject innerObject = new JsonObject()
                .add("id", "0");
        JsonObject parentObject = new JsonObject()
                .add("parent", innerObject);

        result = TestConfig.getFixture("BoxFile/CopyFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileURL))
                .withRequestBody(WireMock.equalToJson(parentObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder rootFolder = BoxFolder.getRootFolder(this.api);
        BoxFile file = new BoxFile(this.api, fileID);
        BoxFile.Info copiedFileInfo = file.copy(rootFolder);

        Assert.assertEquals(parentID, copiedFileInfo.getParent().getID());
        Assert.assertEquals(parentName, copiedFileInfo.getParent().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testMoveFileSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        final String newParentID = "1111";
        final String newParentName = "Another Move Folder";
        JsonObject moveObject = new JsonObject()
                .add("id", newParentID);
        JsonObject parentObject = new JsonObject()
                .add("parent", moveObject);

        result = TestConfig.getFixture("BoxFile/MoveFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
                .withRequestBody(WireMock.equalToJson(parentObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxFolder destinationFolder = new BoxFolder(this.api, newParentID);
        BoxItem.Info fileInfo = file.move(destinationFolder);

        Assert.assertEquals(newParentID, fileInfo.getParent().getID());
        Assert.assertEquals(fileID, fileInfo.getID());
        Assert.assertEquals(newParentName, fileInfo.getParent().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteFileSucceeds() throws IOException {
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
    @Category(UnitTest.class)
    public void testLockFileSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        final boolean isDownloadPrevented = true;
        final String lockID = "1111";
        final String createdByLogin = "test@user.com";
        final String createdByName = "Test User";

        JsonObject innerObject = new JsonObject()
                .add("type", "lock")
                .add("is_download_prevented", "true");

        JsonObject lockObject = new JsonObject()
                .add("lock", innerObject);

        result = TestConfig.getFixture("BoxFile/LockFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(fileURL))
                .withQueryParam("fields", WireMock.containing("lock"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        BoxLock fileLock = file.lock(true);

        Assert.assertEquals(isDownloadPrevented, fileLock.getIsDownloadPrevented());
        Assert.assertEquals(createdByLogin, fileLock.getCreatedBy().getLogin());
        Assert.assertEquals(createdByName, fileLock.getCreatedBy().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testUnlockFileSucceedsAndSendSendsCorrectJson() throws IOException {
        String result = "";
        String fileResult = "";
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID;
        JsonObject unlockObject = new JsonObject()
                .add("lock", JsonObject.NULL);

        fileResult = TestConfig.getFixture("BoxFile/GetFileInfo200");

        result = TestConfig.getFixture("BoxFile/UnlockFile200");

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

        Assert.assertEquals(fileID, file.getID());
        Assert.assertEquals(null, file.getInfo().getLock());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteMetadataOnFileSucceeds() throws IOException {
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
    public void testGetThumbnailSucceeds() throws IOException {
        final String fileID = "12345";
        final String fileURL = "/files/" + fileID + "/thumbnail.jpg";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(fileURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)));

        BoxFile file = new BoxFile(this.api, fileID);
        byte[] thumbnail = file.getThumbnail(BoxFile.ThumbnailFileType.JPG, 256, 256, 256, 256);
    }

    @Test
    @Category(UnitTest.class)
    public void testDeletePreviousFileVersionSucceeds() throws IOException {
        String result = "";
        String versionResult = "";
        final String versionID = "12345";
        final String fileID = "1111";
        final String fileURL = "/files/" + fileID + "/versions";
        final String versionURL = "/files/" + fileID + "/versions/" + versionID;

        result = TestConfig.getFixture("BoxFile/GetFileInfo200");

        versionResult = TestConfig.getFixture("BoxFile/GetAllFileVersions200");

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
        String result = "";
        final String metadataID = "12345";
        final String fileID = "12345";
        final String template = "properties";
        final String scope = "global";
        final String metadataURL = "/files/" + fileID + "/metadata/global/properties";
        JsonObject metadataObject = new JsonObject()
                .add("foo", "bar");

        result = TestConfig.getFixture("BoxFile/CreateMetadataOnFile201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
                .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        Metadata info = file.createMetadata(new Metadata().add("/foo", "bar"));

        Assert.assertEquals(metadataID, info.getID());
        Assert.assertEquals(scope, info.getScope());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetMetadataOnFileSucceeds() throws IOException {
        String result = "";
        final String fileID = "12345";
        final String metadataID = "12345";
        final String parent = "file_1111";
        final String template = "properties";
        final String scope = "global";
        final String metadataURL = "/files/" + fileID + "/metadata/global/properties";

        result = TestConfig.getFixture("BoxFile/GetMetadataOnFile200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);
        Metadata metadata = file.getMetadata();

        Assert.assertEquals(metadataID, metadata.getID());
        Assert.assertEquals(parent, metadata.getParentID());
        Assert.assertEquals(template, metadata.getTemplateName());
        Assert.assertEquals(scope, metadata.getScope());
    }

    @Test
    @Category(UnitTest.class)
    public void testUploadNewVersionReturnsCorrectInfo() throws IOException {

        String result = "";
        String fileID = "11111";
        String fileName = "test.txt";
        byte[] bytes = new byte[] {1, 2, 3};
        InputStream fileContents = new ByteArrayInputStream(bytes);

        result = TestConfig.getFixture("BoxFile/UploadNewVersion201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo("/files/" + fileID + "/content"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFile file = new BoxFile(this.api, fileID);

        BoxFile.Info info = file.uploadNewVersion(fileContents);

        Assert.assertEquals(fileID, info.getID());
        Assert.assertEquals(fileName, info.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void createSharedLinkSucceeds() throws IOException {
        final String fileID = "1111";
        final String password = "test1";
        String result = "";

        JsonObject permissionsObject = new JsonObject()
                .add("can_download", true)
                .add("can_preview", true);

        JsonObject innerObject = new JsonObject()
                .add("password", password)
                .add("access", "open")
                .add("permissions", permissionsObject);

        JsonObject sharedLinkObject = new JsonObject()
                .add("shared_link", innerObject);

        result = TestConfig.getFixture("BoxSharedLink/CreateSharedLink201");

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
        Assert.assertEquals(true, sharedLink.getIsPasswordEnabled());
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
}


