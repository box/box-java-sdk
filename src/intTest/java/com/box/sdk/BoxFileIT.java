package com.box.sdk;

import static com.box.sdk.BoxFile.ALL_VERSION_FIELDS;
import static com.box.sdk.BoxSharedLink.Access.OPEN;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolderWithSomeContent;
import static com.box.sdk.UniqueTestFolder.uploadSampleFileToUniqueFolder;
import static com.box.sdk.UniqueTestFolder.uploadTwoFileVersionsToUniqueFolder;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.longThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.box.sdk.sharedlink.BoxSharedLinkRequest;
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
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link BoxFile} related integration tests.
 */
public class BoxFileIT {

    static final String LARGE_FILE_NAME = "Tamme-Lauri_tamm_suvepäeval.jpg";

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    protected static byte[] readAllBytes(String fileName) throws IOException {
        RandomAccessFile f = new RandomAccessFile(fileName, "r");
        byte[] b = new byte[(int) f.length()];
        f.read(b);
        return b;
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

    @Test
    public void getInfoWithRepresentationsIntegrationTestWithSimpleHint() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, "red_100x100.png");
            List<Representation> representations =
                uploadedFile.getInfoWithRepresentations("[png]").getRepresentations();
            assertTrue("There should be at least one representation", representations.size() > 0);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    public void getInfoWithRepresentationsIntegrationTestWithComplexHint() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, "red_100x100.png");
            List<Representation> representations = uploadedFile.getInfoWithRepresentations(
                "[jpg,png?dimensions=1024x1024][pdf]").getRepresentations();
            assertTrue("There should be at least one representation", representations.size() > 0);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    public void getRepresentationContentSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "smalltest.pdf";
        BoxFile file = null;
        try {
            file = uploadSampleFileToUniqueFolder(api, fileName);

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
                BoxFileIT.generateString(), fileSize, mockUploadListener);
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
    public void downloadFileRangeSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "red_100x100.png";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, fileName);
            BoxFile.Info uplodedFileInfo = uploadedFile.getInfo();
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
    public void canListVersions() {
        BoxFile uploadedFile = null;
        String fileName = "[canListVersions] Multi-version File.txt";
        try {

            uploadedFile = uploadTwoFileVersionsToUniqueFolder(
                fileName, "Version 1", "Version 2", mock(ProgressListener.class)
            );

            Collection<BoxFileVersion> versions = uploadedFile.getVersions();
            assertThat(versions.size(), is(1));
            BoxFileVersion version = versions.iterator().next();
            assertThat(version.getName(), is(notNullValue()));
            assertThat(version.getFileID(), is(notNullValue()));
            assertThat(version.getCreatedAt(), is(notNullValue()));
            assertThat(version.getVersionNumber(), is(nullValue()));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    public void canListVersionsWithSpecificFields() {
        BoxFile uploadedFile = null;
        String fileName = "[canListVersionsWithSpecificFields] Multi-version File.txt";
        try {

            uploadedFile = uploadTwoFileVersionsToUniqueFolder(
                fileName, "Version 1", "Version 2", mock(ProgressListener.class)
            );

            Collection<BoxFileVersion> versions = uploadedFile.getVersions("name", "version_number");
            assertThat(versions.size(), is(1));
            BoxFileVersion version = versions.iterator().next();
            assertThat(version.getName(), is(notNullValue()));
            assertThat(version.getFileID(), is(notNullValue()));
            assertThat(version.getCreatedAt(), is(nullValue()));
            assertThat(version.getVersionNumber(), is(1L));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    public void canListVersionsWithAllFields() {
        BoxFile uploadedFile = null;
        String fileName = "[canListVersionsWithAllFields] Multi-version File.txt";
        try {
            // given
            uploadedFile = uploadTwoFileVersionsToUniqueFolder(
                fileName, "Version 1", "Version 2", mock(ProgressListener.class)
            );
            BoxFileVersion version1 = uploadedFile.getVersions().iterator().next();
            version1.promote();
            version1.delete();

            // when
            Collection<BoxFileVersion> versions = uploadedFile.getVersions(ALL_VERSION_FIELDS);

            //then
            assertThat(versions.size(), is(2));
            Iterator<BoxFileVersion> iterator = versions.iterator();
            iterator.next();
            BoxFileVersion version = iterator.next();
            assertThat(version.getID(), is(notNullValue()));
            assertThat(version.getSha1(), is(notNullValue()));
            assertThat(version.getName(), is(notNullValue()));
            assertThat(version.getSize(), is(notNullValue()));
            assertThat(version.getUploaderDisplayName(), is(notNullValue()));
            assertThat(version.getCreatedAt(), is(notNullValue()));
            assertThat(version.getModifiedAt(), is(notNullValue()));
            assertThat(version.getModifiedBy(), is(notNullValue()));
            assertThat(version.getTrashedAt(), is(notNullValue()));
            assertThat(version.getTrashedBy(), is(notNullValue()));
            assertThat(version.getPurgedAt(), is(notNullValue()));
            assertThat(version.getFileID(), is(uploadedFile.getID()));
            assertThat(version.getVersionNumber(), is(notNullValue()));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
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
    public void createAndUpdateSharedLinkSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[createAndUpdateSharedLinkSucceeds] Test File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
            BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
            permissions.setCanDownload(true);
            permissions.setCanPreview(true);
            BoxSharedLink sharedLink = uploadedFile.createSharedLink(OPEN, null, permissions);

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
                this.createFileUploadSession(folder, BoxFileIT.generateString(), fileSize);

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
        BoxFile uploadedFile = folder.uploadFile(uploadStream, BoxFileIT.generateString()).getResource();
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
    public void canUploadLargeFileVersion() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, LARGE_FILE_NAME);
            boolean result = uploadedFile.canUploadVersion("new name");

            assertTrue(result);
        } finally {
            this.deleteFile(uploadedFile);
        }

    }

    @Test
    public void uploadLargeFileVersion() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, LARGE_FILE_NAME);

            URL fileURL = this.getClass().getResource("/sample-files/" + LARGE_FILE_NAME);
            String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
            File file = new File(filePath);
            FileInputStream stream = new FileInputStream(file);
            BoxFile.Info fileVerion = uploadedFile.uploadLargeFile(stream, file.length());
            assertNotNull(fileVerion);
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    public void uploadLargeFileVersionWithAttributes() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadSampleFileToUniqueFolder(api, LARGE_FILE_NAME);

            Map<String, String> fileAttributes = new HashMap<>();
            fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

            URL fileURL = this.getClass().getResource("/sample-files/" + LARGE_FILE_NAME);
            String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
            File file = new File(filePath);
            FileInputStream stream = new FileInputStream(file);
            BoxFile.Info fileVersion = uploadedFile.uploadLargeFile(stream, file.length(), fileAttributes);
            assertNotNull(fileVersion);

            assertEquals(1491613088000L, fileVersion.getContentModifiedAt().getTime());
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    public void setsVanityNameOnASharedLink() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, "file_to_share.txt");

            String vanityName = "myCustomName-" + System.currentTimeMillis();
            BoxSharedLinkRequest request = new BoxSharedLinkRequest()
                .permissions(true, true)
                .access(OPEN)
                .vanityName(vanityName)
                .password("my-random-password");
            BoxSharedLink linkWithVanityName = uploadedFile.createSharedLink(request);

            assertThat(linkWithVanityName.getVanityName(), is(vanityName));
            BoxSharedLink sharedLink = uploadedFile.getInfo().getSharedLink();
            assertThat(sharedLink.getVanityName(), is(vanityName));
            assertThat(sharedLink.getPermissions().getCanPreview(), is(true));
            assertThat(sharedLink.getPermissions().getCanDownload(), is(true));
            assertThat(sharedLink.getAccess(), is(OPEN));
            assertThat(sharedLink.getIsPasswordEnabled(), is(true));
        } finally {
            this.deleteFile(uploadedFile);
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
        return this.createFile(folder, BoxFileIT.generateString());
    }

    private BoxFile.Info createFile(BoxFolder folder, String fileName) throws IOException {
        URL fileURL = this.getClass().getResource("/sample-files/" + BoxFileIT.LARGE_FILE_NAME);
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
