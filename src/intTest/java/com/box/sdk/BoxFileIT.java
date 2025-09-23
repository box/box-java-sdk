package com.box.sdk;

import static com.box.sdk.BinaryBodyUtils.writeStream;
import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.BoxFile.ALL_VERSION_FIELDS;
import static com.box.sdk.BoxFile.CONTENT_URL_TEMPLATE;
import static com.box.sdk.BoxRetentionPolicyAssignment.createAssignmentToFolder;
import static com.box.sdk.BoxSharedLink.Access.OPEN;
import static com.box.sdk.CleanupTools.deleteFile;
import static com.box.sdk.CleanupTools.deleteFolder;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.randomizeName;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolderWithSomeContent;
import static com.box.sdk.UniqueTestFolder.uploadFileWithContentToSpecifiedFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileWithSomeContent;
import static com.box.sdk.UniqueTestFolder.uploadSampleFileToUniqueFolder;
import static com.box.sdk.UniqueTestFolder.uploadTwoFileVersionsToUniqueFolder;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.ZoneOffset.UTC;
import static java.util.UUID.randomUUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.hamcrest.MockitoHamcrest.longThat;

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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

/** {@link BoxFile} related integration tests. */
public class BoxFileIT {

  @BeforeClass
  public static void setup() {
    setupUniqeFolder();
  }

  @AfterClass
  public static void tearDown() {
    removeUniqueFolder();
  }

  protected static byte[] readAllBytes(String fileName) {
    try (RandomAccessFile f = new RandomAccessFile(fileName, "r")) {
      byte[] b = new byte[(int) f.length()];
      f.read(b);
      return b;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void getInfoWithRepresentationsIntegrationTestWithSimpleHint() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadSampleFileToUniqueFolder(api, "red_100x100.png");
      List<Representation> representations =
          uploadedFile.getInfoWithRepresentations("[png]").getRepresentations();
      assertTrue("There should be at least one representation", representations.size() > 0);
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void getInfoWithRepresentationsIntegrationTestWithComplexHint() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadSampleFileToUniqueFolder(api, "red_100x100.png");
      List<Representation> representations =
          uploadedFile
              .getInfoWithRepresentations("[jpg,png?dimensions=1024x1024][pdf]")
              .getRepresentations();
      assertTrue("There should be at least one representation", representations.size() > 0);
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void getRepresentationContentSucceeds() throws InterruptedException {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "smalltest.pdf";
    BoxFile file = null;
    try {
      file = uploadSampleFileToUniqueFolder(api, fileName);
      final String fileId = file.getID();
      String representationHint = "[jpg?dimensions=32x32]";
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      Retry.retry(
          () -> {
            new BoxFile(api, fileId).getRepresentationContent(representationHint, outputStream);
            byte[] downloadedRepresentationContent = outputStream.toByteArray();

            assertNotNull(downloadedRepresentationContent);
          },
          5,
          100);
    } finally {
      deleteFile(file);
    }
  }

  @Test
  public void getRepresentationContentWithExtractedTextSucceeds() throws InterruptedException {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "text.pdf";
    BoxFile file = null;
    try {
      file = uploadSampleFileToUniqueFolder(api, fileName);
      final String fileId = file.getID();
      String representationHint = "[extracted_text]";
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      Retry.retry(
          () -> {
            new BoxFile(api, fileId).getRepresentationContent(representationHint, outputStream);
            byte[] downloadedRepresentationContent = outputStream.toByteArray();
            String text = new String(downloadedRepresentationContent, StandardCharsets.UTF_8);
            assertTrue(text.contains("Lorem ipsum"));
          },
          5,
          100);
    } finally {
      deleteFile(file);
    }
  }

  @Test
  public void uploadFileStreamSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder folder = getUniqueFolder(api);

    byte[] fileContent = new byte[10000];
    new Random().nextBytes(fileContent);

    BoxFile uploadedFile = null;
    try {
      InputStream uploadStream = new ByteArrayInputStream(fileContent);
      BoxFile.Info uploadedFileInfo = folder.uploadFile(uploadStream, BoxFileIT.generateString());
      uploadedFile = uploadedFileInfo.getResource();

      ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
      uploadedFile.download(downloadStream);
      byte[] downloadedFileContent = downloadStream.toByteArray();

      assertThat(downloadedFileContent, is(equalTo(fileContent)));
      assertThat(
          folder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void uploadAndDownloadFileSucceeds() throws IOException {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder folder = getUniqueFolder(api);
    String fileName = "red_100x100.png";
    URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
    String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
    long fileSize = new File(filePath).length();
    byte[] fileContent = readAllBytes(filePath);
    BoxFile uploadedFile = null;
    try {
      InputStream uploadStream = Files.newInputStream(Paths.get(filePath));
      ProgressListener mockUploadListener = mock(ProgressListener.class);
      BoxFile.Info uploadedFileInfo =
          folder.uploadFile(uploadStream, BoxFileIT.generateString(), fileSize, mockUploadListener);
      uploadedFile = uploadedFileInfo.getResource();

      ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
      ProgressListener mockDownloadListener = mock(ProgressListener.class);
      uploadedFile.download(downloadStream, mockDownloadListener);
      byte[] downloadedFileContent = downloadStream.toByteArray();

      assertThat(downloadedFileContent, is(equalTo(fileContent)));
      assertThat(
          folder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
      verify(mockUploadListener, atLeastOnce())
          .onProgressChanged(anyLong(), longThat(is(equalTo(fileSize))));
      verify(mockDownloadListener, atLeastOnce())
          .onProgressChanged(anyLong(), longThat(is(equalTo(fileSize))));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void downloadFileUseZstdSucceeds() throws IOException {
    BoxAPIConnection api = jwtApiForServiceAccount();
    api.setUseZstdCompression(true);

    String fileName = "smalltest.pdf";
    URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
    String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
    byte[] fileContent = readAllBytes(filePath);
    BoxFile file = null;
    try {
      file = uploadSampleFileToUniqueFolder(api, fileName);

      ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
      ProgressListener mockDownloadListener = mock(ProgressListener.class);

      URL url = CONTENT_URL_TEMPLATE.build(api.getBaseURL(), file.getID());
      BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
      BoxAPIResponse response = request.send();
      writeStream(response, downloadStream, mockDownloadListener);

      byte[] downloadedFileContent = downloadStream.toByteArray();
      assertThat(response.getHeaders().get("X-Content-Encoding").get(0), is(equalTo("zstd")));
      assertThat(downloadedFileContent, is(equalTo(fileContent)));
    } finally {
      deleteFile(file);
    }
  }

  @Test
  public void uploadAndDownloadFileDisabledZstdSucceeds() throws IOException {
    BoxAPIConnection api = jwtApiForServiceAccount();
    api.setUseZstdCompression(false);

    String fileName = "smalltest.pdf";
    URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
    String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
    byte[] fileContent = readAllBytes(filePath);
    BoxFile file = null;
    try {
      file = uploadSampleFileToUniqueFolder(api, fileName);

      ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
      ProgressListener mockDownloadListener = mock(ProgressListener.class);

      URL url = CONTENT_URL_TEMPLATE.build(api.getBaseURL(), file.getID());
      BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
      BoxAPIResponse response = request.send();
      writeStream(response, downloadStream, mockDownloadListener);

      byte[] downloadedFileContent = downloadStream.toByteArray();
      assertThat(response.getHeaders().get("X-Content-Encoding"), is(nullValue()));
      assertThat(downloadedFileContent, is(equalTo(fileContent)));
    } finally {
      deleteFile(file);
    }
  }

  @Test
  public void downloadFileRangeSucceeds() throws IOException {
    BoxAPIConnection api = jwtApiForServiceAccount();
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
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void getInfoWithOnlyTheNameField() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[getInfoWithOnlyTheNameField] Test File.txt";
    BoxFile uploadedFile = null;

    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      BoxFile.Info uploadedFileInfo = uploadedFile.getInfo("name");

      assertThat(uploadedFileInfo.getName(), is(equalTo(fileName)));
      assertThat(uploadedFileInfo.getDescription(), is(nullValue()));
      assertThat(uploadedFileInfo.getSize(), is(equalTo(0L)));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void fileLockAndUnlockSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName =
        "[getInfoWithOnlyTheLockField] Test File"
            + Calendar.getInstance().getTimeInMillis()
            + ".txt";
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
      assertThat(
          fileLock.getCreatedBy().getID(), is(equalTo(uploadedFileInfo.getCreatedBy().getID())));

      uploadedFile.unlock();

      BoxFile.Info updatedFileInfo = uploadedFile.getInfo("lock");
      assertThat(updatedFileInfo.getLock(), is(nullValue()));

      uploadedFile.lock(true);

      uploadedFileInfo = uploadedFile.getInfo("lock", "created_by");
      fileLock = uploadedFileInfo.getLock();

      assertThat(fileLock, is(instanceOf(BoxLock.class)));
      assertNull(fileLock.getExpiresAt());
      assertThat(fileLock.getIsDownloadPrevented(), is(equalTo(true)));
      assertThat(
          fileLock.getCreatedBy().getID(), is(equalTo(uploadedFileInfo.getCreatedBy().getID())));

      uploadedFile.unlock();

      uploadedFile.lock();

      uploadedFileInfo = uploadedFile.getInfo("lock", "created_by");
      fileLock = uploadedFileInfo.getLock();

      assertThat(fileLock, is(instanceOf(BoxLock.class)));
      assertNull(fileLock.getExpiresAt());
      assertThat(fileLock.getIsDownloadPrevented(), is(equalTo(false)));
      assertThat(
          fileLock.getCreatedBy().getID(), is(equalTo(uploadedFileInfo.getCreatedBy().getID())));

      uploadedFile.unlock();
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void getInfoWithAllFields() {
    BoxAPIConnection api = jwtApiForServiceAccount();
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
      assertThat(
          uploadedFileInfo.getPermissions(), hasItem(BoxFile.Permission.CAN_INVITE_COLLABORATOR));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void updateFileWithSpecialCharsInNameSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String originalFileName = "[updateFileWithSpecialCharsInNameSucceeds] abc\";def.txt";

    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, originalFileName, "Test file");
      assertThat(uploadedFile.getInfo().getName(), is(equalTo(originalFileName)));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void updateFileInfoSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
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
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void renameFileSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String originalFileName = "[renameFileSucceeds] Original Name.txt";
    String newFileName = "[renameFileSucceeds] New Name.txt";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, originalFileName, "Test file");

      uploadedFile.rename(newFileName);
      BoxFile.Info newInfo = uploadedFile.getInfo();

      assertThat(newInfo.getName(), is(equalTo(newFileName)));
    } finally {
      deleteFile(uploadedFile);
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
      uploadedFile =
          uploadTwoFileVersionsToUniqueFolder(
              fileName, version1Content, version2Content, mockUploadListener);
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
      verify(mockUploadListener, atLeastOnce())
          .onProgressChanged(anyLong(), longThat(is(equalTo(version1Size))));

    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void uploadNewVersionSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "Multi-version File.txt";
    String updatedFileName = "[uploadNewVersionSucceeds] Multi-version File.txt";
    Date contentModifiedAt = new Date(10000);
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Version 1");
      InputStream uploadStream = this.getFileContent("Version 1");
      BoxFile.Info newVersion =
          uploadedFile.uploadNewVersion(
              uploadStream, null, contentModifiedAt, updatedFileName, 0, null);

      assertEquals(updatedFileName, newVersion.getName());
      assertEquals(contentModifiedAt, newVersion.getContentModifiedAt());
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void getVersionSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[getVersionByIdSucceeds] Multi-version File.txt";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      uploadedFile.uploadNewVersion(this.getFileContent("Version 2"));

      Collection<BoxFileVersion> versions = uploadedFile.getVersions();
      BoxFileVersion versionFromGetVersions = versions.iterator().next();

      BoxFileVersion versionFromGetById =
          uploadedFile.getVersionByID(versionFromGetVersions.getID());

      assertThat(versionFromGetVersions.getVersionID(), equalTo(versionFromGetById.getVersionID()));
      assertThat(versionFromGetVersions.getFileID(), equalTo(versionFromGetById.getFileID()));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void deleteVersionSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[deleteVersionSucceeds] Multi-version File.txt";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      uploadedFile.uploadNewVersion(this.getFileContent("Version 2"));

      Collection<BoxFileVersion> versions = uploadedFile.getVersions();
      BoxFileVersion previousVersion = versions.iterator().next();
      previousVersion.delete();

      Collection<BoxFileVersion> versionsAfterRemove = uploadedFile.getVersions();
      assertThat(versionsAfterRemove, Matchers.hasSize(1));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void shouldReturnTrashedAtForADeleteVersion() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[deleteVersionSucceeds] Multi-version File.txt";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      uploadedFile.uploadNewVersion(this.getFileContent("Version 2"));
      Collection<BoxFileVersion> versions = uploadedFile.getVersions();
      assertThat(versions, Matchers.hasSize(1));

      BoxFileVersion version = versions.iterator().next();
      assertThat(version.getTrashedAt(), is(nullValue()));

      version.delete();

      BoxFileVersion trashedVersion = uploadedFile.getVersions().iterator().next();
      assertThat(trashedVersion.getTrashedAt(), is(notNullValue()));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void promoteVersionsSucceeds() throws UnsupportedEncodingException {
    BoxAPIConnection api = jwtApiForServiceAccount();
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
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void canListVersions() {
    BoxFile uploadedFile = null;
    String fileName = "[canListVersions] Multi-version File.txt";
    try {

      uploadedFile =
          uploadTwoFileVersionsToUniqueFolder(
              fileName, "Version 1", "Version 2", mock(ProgressListener.class));

      Collection<BoxFileVersion> versions = uploadedFile.getVersions();
      assertThat(versions.size(), is(1));
      BoxFileVersion version = versions.iterator().next();
      assertThat(version.getName(), is(notNullValue()));
      assertThat(version.getFileID(), is(notNullValue()));
      assertThat(version.getCreatedAt(), is(notNullValue()));
      assertThat(version.getVersionNumber(), is(nullValue()));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void canPaginateOverListOfVersions() {
    BoxFile uploadedFile = null;
    String fileName = "[canPaginateOverListOfVersions] Multi-version File.txt";
    try {
      BoxAPIConnection api = jwtApiForServiceAccount();
      BoxFolder uniqueFolder = getUniqueFolder(api);
      uploadedFile = uploadFileWithContentToSpecifiedFolder(fileName, "Version 1", uniqueFolder);

      byte[] fileBytes = "Version 2".getBytes(StandardCharsets.UTF_8);
      uploadedFile.uploadNewVersion(
          new ByteArrayInputStream(fileBytes),
          null,
          fileBytes.length,
          mock(ProgressListener.class));

      fileBytes = "Version 3".getBytes(StandardCharsets.UTF_8);
      uploadedFile.uploadNewVersion(
          new ByteArrayInputStream(fileBytes),
          null,
          fileBytes.length,
          mock(ProgressListener.class));

      Collection<BoxFileVersion> versionsPart1 = uploadedFile.getVersionsRange(0, 1);
      assertThat(versionsPart1.size(), is(1));
      BoxFileVersion boxFileVersion1 = versionsPart1.iterator().next();

      Collection<BoxFileVersion> versionsPart2 = uploadedFile.getVersionsRange(1, 2);
      assertThat(versionsPart2.size(), is(1));
      BoxFileVersion boxFileVersion2 = versionsPart2.iterator().next();

      Collection<BoxFileVersion> allVersions = uploadedFile.getVersionsRange(0, 10);
      assertThat(allVersions.size(), is(2));
      Iterator<BoxFileVersion> iterator = allVersions.iterator();
      assertThat(iterator.next(), is(boxFileVersion1));
      assertThat(iterator.next(), is(boxFileVersion2));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void canListVersionsWithSpecificFields() {
    BoxFile uploadedFile = null;
    String fileName = "[canListVersionsWithSpecificFields] Multi-version File.txt";
    try {

      uploadedFile =
          uploadTwoFileVersionsToUniqueFolder(
              fileName, "Version 1", "Version 2", mock(ProgressListener.class));

      Collection<BoxFileVersion> versions = uploadedFile.getVersions("name", "version_number");
      assertThat(versions.size(), is(1));
      BoxFileVersion version = versions.iterator().next();
      assertThat(version.getName(), is(notNullValue()));
      assertThat(version.getFileID(), is(notNullValue()));
      assertThat(version.getCreatedAt(), is(nullValue()));
      assertThat(version.getVersionNumber(), is(1L));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void canListVersionsWithAllFields() {
    BoxFile uploadedFile = null;
    String fileName = "[canListVersionsWithAllFields] Multi-version File.txt";
    try {
      // given
      uploadedFile =
          uploadTwoFileVersionsToUniqueFolder(
              fileName, "Version 1", "Version 2", mock(ProgressListener.class));
      BoxFileVersion version1 = uploadedFile.getVersions().iterator().next();
      version1.promote();
      version1.delete();

      // when
      Collection<BoxFileVersion> versions = uploadedFile.getVersions(ALL_VERSION_FIELDS);

      // then
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
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void copyFileSucceeds() throws UnsupportedEncodingException {
    BoxAPIConnection api = jwtApiForServiceAccount();
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
      deleteFile(uploadedFile);
      deleteFile(copiedFile);
    }
  }

  @Test
  public void moveFileSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[moveFileSucceeds] Test File.txt";
    String folderName = "[moveFileSucceeds] Destination Folder";
    BoxFile uploadedFile = null;
    BoxFolder destinationFolder = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");

      destinationFolder = getUniqueFolder(api).createFolder(folderName).getResource();
      uploadedFile.move(destinationFolder);

      assertThat(
          destinationFolder,
          hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
    } finally {
      deleteFile(uploadedFile);
      deleteFolder(destinationFolder);
    }
  }

  @Test
  public void createAndUpdateSharedLinkSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[createAndUpdateSharedLinkSucceeds] Test File.txt";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      assertThat(
          uploadedFile.getInfo("is_accessible_via_shared_link").getIsAccessibleViaSharedLink(),
          is(false));
      BoxSharedLink sharedLink =
          uploadedFile.createSharedLink(
              new BoxSharedLinkRequest().access(OPEN).permissions(true, true, true));

      assertThat(sharedLink.getURL(), not(is(emptyOrNullString())));

      sharedLink.getPermissions().setCanDownload(false);
      BoxFile.Info info = uploadedFile.new Info();
      info.setSharedLink(sharedLink);
      uploadedFile.updateInfo(info);

      BoxFile.Info updatedInfo =
          uploadedFile.getInfo("shared_link", "is_accessible_via_shared_link");
      assertThat(updatedInfo.getSharedLink().getPermissions().getCanDownload(), is(false));
      assertThat(updatedInfo.getIsAccessibleViaSharedLink(), is(true));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void downloadpdateSharedLinkSucceeds() throws IOException {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[downloadpdateSharedLinkSucceeds] Test File.txt";
    String fileContent = "Test file";
    String password = "Secret123@";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, fileContent);
      assertThat(
          uploadedFile.getInfo("is_accessible_via_shared_link").getIsAccessibleViaSharedLink(),
          is(false));
      BoxSharedLink sharedLink =
          uploadedFile.createSharedLink(
              new BoxSharedLinkRequest()
                  .access(OPEN)
                  .password(password)
                  .permissions(true, true, true));

      ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
      BoxFile.downloadFromSharedLink(api, downloadStream, sharedLink.getURL(), password);
      downloadStream.close();
      byte[] downloadedFileContent = downloadStream.toByteArray();
      assertThat(downloadedFileContent, is(equalTo(fileContent.getBytes())));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void createEditableSharedLinkSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[createEditableSharedLinkSucceeds] Test File.txt";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      uploadedFile.createSharedLink(new BoxSharedLinkRequest().permissions(true, true, true));

      BoxSharedLink.Permissions createdPermissions =
          uploadedFile.getInfo().getSharedLink().getPermissions();
      assertThat(createdPermissions.getCanPreview(), is(true));
      assertThat(createdPermissions.getCanDownload(), is(true));
      assertThat(createdPermissions.getCanEdit(), is(true));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void addCommentSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[addCommentSucceeds] Test File.txt";
    String commentMessage = "Non-empty message";
    BoxFile uploadedFile = null;

    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      BoxComment.Info addedCommentInfo = uploadedFile.addComment(commentMessage);

      assertThat(addedCommentInfo.getMessage(), is(equalTo(commentMessage)));
      assertThat(
          uploadedFile.getComments(),
          hasItem(Matchers.<BoxComment.Info>hasProperty("ID", equalTo(addedCommentInfo.getID()))));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void addCommentWithMentionSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[addCommentWithMentionSucceeds] Test File.txt";
    String commentMessage =
        String.format(
            "Message mentioning @[%s:%s]",
            TestConfig.getCollaboratorID(), TestConfig.getCollaborator());
    BoxFile uploadedFile = null;

    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      BoxComment.Info addedCommentInfo = uploadedFile.addComment(commentMessage);

      String expectedCommentMessage = "Message mentioning " + TestConfig.getCollaborator();
      assertThat(addedCommentInfo.getMessage(), is(equalTo(expectedCommentMessage)));
      assertThat(
          uploadedFile.getComments(),
          hasItem(Matchers.<BoxComment.Info>hasProperty("ID", equalTo(addedCommentInfo.getID()))));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void createMetadataAndGetMetadataOnInfoSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[createMetadataSucceeds] Test File.txt";

    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      uploadedFile.createMetadata(new Metadata().add("/foo", "bar"));

      Metadata metadata = uploadedFile.getMetadata();
      assertNotNull(metadata);
      assertEquals("bar", metadata.getString("/foo"));

      Metadata actualMD =
          uploadedFile.getInfo("metadata.global.properties").getMetadata("properties", "global");
      assertNotNull("Metadata should not be null for this file", actualMD);
    } catch (BoxAPIException e) {
      fail("Metadata should have been present on this folder");
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void updateMetadataSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
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
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void addTaskSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName =
        "[addTaskSucceeds] Test File " + Calendar.getInstance().getTimeInMillis() + ".txt";
    String taskMessage = "Non-empty message";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    Date dueAt = new Date(new Date().getTime() + (1000 * 24 * 60 * 60));
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      BoxTask.Info addedTaskInfo = uploadedFile.addTask(BoxTask.Action.REVIEW, taskMessage, dueAt);

      assertThat(addedTaskInfo.getMessage(), is(equalTo(taskMessage)));
      assertThat(
          dateFormat.format(addedTaskInfo.getDueAt()), is(equalTo(dateFormat.format(dueAt))));
      assertThat(
          uploadedFile.getTasks(),
          hasItem(Matchers.<BoxTask.Info>hasProperty("ID", equalTo(addedTaskInfo.getID()))));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void getPreviewLink() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[getPreviewLink] Test File.txt";
    BoxFile uploadedFile = null;

    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      URL uploadedFilePreviewLink = uploadedFile.getPreviewLink();

      assertThat(uploadedFilePreviewLink, is(notNullValue()));
      assertThat(uploadedFilePreviewLink.toString(), not(is(emptyOrNullString())));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void getDownloadURL() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName = "[getPreviewLink] Test File.txt";
    BoxFile uploadedFile = null;

    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      URL uploadedFileDownloadURL = uploadedFile.getDownloadURL();

      assertThat(uploadedFileDownloadURL, is(notNullValue()));
      assertThat(uploadedFileDownloadURL.toString(), not(is(emptyOrNullString())));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void setCollectionsSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String fileName =
        "[setCollectionsSucceeds] Test File " + Calendar.getInstance().getTimeInMillis() + ".txt";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolder(api, fileName, "Test file");
      Iterable<BoxCollection.Info> collections = BoxCollection.getAllCollections(api);
      BoxCollection.Info favoritesInfo = collections.iterator().next();
      BoxFile.Info updatedInfo = uploadedFile.setCollections(favoritesInfo.getResource());

      assertThat(
          updatedInfo.getCollections(),
          hasItem(Matchers.<BoxCollection.Info>hasProperty("ID", equalTo(favoritesInfo.getID()))));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void setCollectionsWithInfoSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
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

      assertThat(
          fileInfo.getCollections(),
          hasItem(Matchers.<BoxCollection.Info>hasProperty("ID", equalTo(favoritesInfo.getID()))));
      assertThat(
          updatedInfo.getCollections(),
          hasItem(Matchers.<BoxCollection.Info>hasProperty("ID", equalTo(favoritesInfo.getID()))));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void uploadSessionCommitFlowSuccess() throws Exception {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder folder = getUniqueFolder(api);

    BoxFile uploadedFile = null;
    File file;
    try {
      file = FileUtils.generate(randomizeName("LargeFile.txt"), 21_000_000);
      long fileSize = file.length();

      // Create the session
      BoxFileUploadSession.Info session =
          this.createFileUploadSession(folder, BoxFileIT.generateString(), fileSize);

      // Create the parts
      MessageDigest fileDigest = this.uploadParts(session, fileSize, file);

      // List the session parts
      List<BoxFileUploadSessionPart> parts = this.listUploadSessionParts(session.getResource());

      byte[] digestBytes = fileDigest.digest();
      String digest = Base64.encode(digestBytes);

      // Verify the delete session
      uploadedFile = this.commitSession(session.getResource(), digest, parts);
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void uploadSessionAbortFlowSuccess() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder folder = getUniqueFolder(api);
    BoxFileUploadSession.Info sessionInfo =
        this.createFileUploadSession(folder, BoxFileIT.generateString(), 25_000_000);
    assertNotNull(sessionInfo.getUploadSessionId());
    assertNotNull(sessionInfo.getSessionExpiresAt());

    BoxFileUploadSession.Endpoints endpoints = sessionInfo.getSessionEndpoints();
    assertNotNull(endpoints);
    assertNotNull(endpoints.getUploadPartEndpoint());
    assertNotNull(endpoints.getStatusEndpoint());
    assertNotNull(endpoints.getListPartsEndpoint());
    assertNotNull(endpoints.getCommitEndpoint());
    assertNotNull(endpoints.getAbortEndpoint());

    // Verify the status of the session
    BoxFileUploadSession session = sessionInfo.getResource();
    this.verifySessionExists(session);

    // Verify the delete session
    session.abort();
    this.verifySessionWasAborted(session);
  }

  @Test
  public void uploadLargeFileVersionWithAttributes() throws Exception {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFile uploadedFile = null;
    try {
      uploadedFile =
          uploadFileWithSomeContent(randomizeName("LargeFile.txt"), getUniqueFolder(api));

      Map<String, String> fileAttributes = new HashMap<>();
      fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

      File file = FileUtils.generate(randomizeName("LargeFile.txt"), 21_000_000);
      FileInputStream stream = new FileInputStream(file);
      BoxFile.Info fileVersion =
          uploadedFile.uploadLargeFile(stream, file.length(), fileAttributes);
      assertNotNull(fileVersion);

      assertEquals(1491613088000L, fileVersion.getContentModifiedAt().getTime());
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void setsVanityNameOnASharedLink() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, "file_to_share.txt");

      String vanityName = "myCustomName-" + System.currentTimeMillis();
      BoxSharedLinkRequest request =
          new BoxSharedLinkRequest()
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
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void createDefaultSharedLink() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, "file_to_share.txt");
      BoxSharedLinkRequest request = new BoxSharedLinkRequest();
      uploadedFile.createSharedLink(request);
      BoxSharedLink sharedLink = uploadedFile.getInfo().getSharedLink();
      assertThat(sharedLink, is(notNullValue()));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void setsAndRetrievesDispositionAt() throws ParseException {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder testFolder = null;
    try {
      String policyId = RetentionPolicyUtils.getOneDayRetentionPolicy(api).getID();
      BoxFolder rootFolder = getUniqueFolder(api);
      testFolder =
          rootFolder.createFolder(randomizeName("[setsAndRetrievesDispositionAt]")).getResource();
      createAssignmentToFolder(api, policyId, testFolder.getID());
      BoxFile uploadedFile = uploadFileWithSomeContent("file_with_disposition.txt", testFolder);
      BoxFile.Info info = uploadedFile.getInfo();

      String dispositionAt = BoxDateFormat.format(LocalDateTime.now().plusDays(7).toInstant(UTC));
      info.setDispositionAt(BoxDateFormat.parse(dispositionAt));
      uploadedFile.updateInfo(info);

      BoxFile.Info updatedInfo = uploadedFile.getInfo("disposition_at");
      assertThat(BoxDateFormat.format(updatedInfo.getDispositionAt()), is(dispositionAt));
    } finally {
      deleteFolder(testFolder);
    }
  }

  @Test
  public void uploadAndDownloadEmptyFileSucceeds() throws IOException {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder folder = getUniqueFolder(api);
    String fileName = "empty_file";
    URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
    String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
    long fileSize = new File(filePath).length();
    byte[] fileContent = readAllBytes(filePath);
    BoxFile uploadedFile = null;
    try {
      InputStream uploadStream = Files.newInputStream(Paths.get(filePath));
      ProgressListener mockUploadListener = mock(ProgressListener.class);
      BoxFile.Info uploadedFileInfo =
          folder.uploadFile(uploadStream, BoxFileIT.generateString(), fileSize, mockUploadListener);
      uploadedFile = uploadedFileInfo.getResource();

      ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
      uploadedFile.download(downloadStream);
      byte[] downloadedFileContent = downloadStream.toByteArray();

      assertThat(downloadedFileContent, is(equalTo(fileContent)));
      assertThat(
          folder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
    } finally {
      deleteFile(uploadedFile);
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

  private BoxFileUploadSession.Info createFileUploadSession(
      BoxFolder folder, String fileName, long fileSize) {
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

  private MessageDigest uploadParts(BoxFileUploadSession.Info session, long fileSize, File file)
      throws Exception {

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

      BoxFileUploadSessionPart part =
          session.getResource().uploadPart(dis, offset, (int) min, fileSize);
      assertNotNull(part.getSha1());
      assertNotNull(part.getPartId());
      assertEquals(part.getOffset(), offset);
      assertTrue(part.getSize() <= session.getPartSize());
      offset = offset + session.getPartSize();
      processed += min;
    } while (!canBreak);

    return fileDigest;
  }

  private List<BoxFileUploadSessionPart> listUploadSessionParts(BoxFileUploadSession session) {
    BoxFileUploadSessionPartList list = session.listParts(0, 100);
    return list.getEntries();
  }

  private BoxFile commitSession(
      BoxFileUploadSession session, String digest, List<BoxFileUploadSessionPart> parts) {
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
      // If the session is aborted, this line should not be executed.
      fail("Upload session is not deleted");
    } catch (BoxAPIException apiEx) {
      assertEquals(apiEx.getResponseCode(), 404);
    }
  }

  private static String generateString() {
    return randomUUID().toString();
  }
}
