package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.longThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxFile} related unit tests.
 */
public class BoxFileTest {

    /**
     * Unit test for {@link BoxFile#getWatermark(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetWatermarkSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/files/0/watermark",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        new BoxFile(api, "0").getWatermark();
    }

    /**
     * Unit test for {@link BoxFile#getWatermark(String...)}
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

        BoxWatermark watermark = new BoxFile(api, "0").getWatermark();
        Assert.assertEquals(createdAt, watermark.getCreatedAt());
        Assert.assertEquals(modifiedAt, watermark.getModifiedAt());
    }

    /**
     * Unit test for {@link BoxFile#applyWatermark()}
     */
    @Test
    @Category(UnitTest.class)
    public void testApplyWatermarkSendsCorrectJson() {
        final String imprint = "default";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/files/0/watermark",
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

        new BoxFile(api, "0").applyWatermark();
    }

    /**
     * Unit test for {@link BoxFile#applyWatermark()}
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

        BoxWatermark watermark = new BoxFile(api, "0").applyWatermark();
        Assert.assertEquals(createdAt, watermark.getCreatedAt());
        Assert.assertEquals(modifiedAt, watermark.getModifiedAt());
    }

    /**
     * Unit test for {@link BoxFile#removeWatermark()}
     */
    @Test
    @Category(UnitTest.class)
    public void testRemoveWatermarkSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/files/0/watermark",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        new BoxFile(api, "0").removeWatermark();
    }

    /**
     * Unit test for {@link BoxFile#getAllMetadata(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataSendsCorrectRequest() {
        final BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/files/5010739061/metadata?fields=name%2Cdescription&limit=100",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, "5010739061");
        Iterator<Metadata> iterator = file.getAllMetadata("name", "description").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxFile#getAllMetadata(String...)}.
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

        BoxFile file = new BoxFile(api, "0");
        Iterator<Metadata> iterator = file.getAllMetadata().iterator();
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
        BoxFile.Info uploadedFileInfo = rootFolder.uploadFile(uploadStream, fileName, fileSize, mockUploadListener);
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
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[uploadAndDownloadMultipleVersionsSucceeds] Multi-version File.txt";
        String version1Content = "Version 1";
        String version1Sha = "db3cbc01da600701b9fe4a497fe328e71fa7022f";
        byte[] version1Bytes = version1Content.getBytes(StandardCharsets.UTF_8);
        long version1Size = version1Bytes.length;
        String version2Content = "Version 2";
        byte[] version2Bytes = version2Content.getBytes(StandardCharsets.UTF_8);
        long version2Size = version1Bytes.length;

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        uploadStream = new ByteArrayInputStream(version2Bytes);
        ProgressListener mockUploadListener = mock(ProgressListener.class);
        uploadedFile.uploadVersion(uploadStream, null, version2Size, mockUploadListener);

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
        verify(mockUploadListener, atLeastOnce()).onProgressChanged(anyLong(), longThat(is(equalTo(version1Size))));

        uploadedFile.delete();
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
        String fileName = "[getInfoWithOnlyTheLockField] Test File.txt";
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
        uploadedFile.uploadVersion(uploadStream);

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
        uploadedFile.uploadVersion(uploadStream);

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
        String fileName = "[promoteVersionsSucceeds] Multi-version File.txt";
        String version1Content = "Version 1";
        byte[] version1Bytes = version1Content.getBytes(StandardCharsets.UTF_8);
        byte[] version2Bytes = "Version 2".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(version1Bytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        uploadStream = new ByteArrayInputStream(version2Bytes);
        uploadedFile.uploadVersion(uploadStream);

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
        String fileName = "[addCommentSucceeds] Test File.txt";
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
        String fileName = "[addCommentSucceeds] Test File.txt";
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
    public void createMetadataSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[createMetadataSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        uploadedFile.createMetadata(new Metadata().add("/foo", "bar"));

        Metadata check1 = uploadedFile.getMetadata();
        Assert.assertNotNull(check1);
        Assert.assertEquals("bar", check1.get("/foo"));

        uploadedFile.delete();
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
        Assert.assertEquals("bar", check1.get("/foo"));

        uploadedFile.updateMetadata(check1.replace("/foo", "baz"));

        Metadata check2 = uploadedFile.getMetadata();
        Assert.assertNotNull(check2);
        Assert.assertEquals("baz", check2.get("/foo"));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void addTaskSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[addTaskSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);
        String taskMessage = "Non-empty message";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date dueAt = new Date();

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
        String fileName = "[setCollectionsSucceeds] Test File.txt";
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

    private static byte[] readAllBytes(String fileName) throws IOException {
        RandomAccessFile f = new RandomAccessFile(fileName, "r");
        byte[] b = new byte[(int) f.length()];
        f.read(b);
        return b;
    }
}
