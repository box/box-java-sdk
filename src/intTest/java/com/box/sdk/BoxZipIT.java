package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.BoxZipDownloadStatus.State.IN_PROGRESS;
import static com.box.sdk.BoxZipDownloadStatus.State.SUCCEEDED;
import static com.box.sdk.CleanupTools.deleteFile;
import static com.box.sdk.CleanupTools.deleteFolder;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadSampleFileToUniqueFolder;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/** {@link BoxZip} related integration tests. */
public class BoxZipIT {
  @BeforeClass
  public static void setup() {
    setupUniqeFolder();
  }

  @AfterClass
  public static void tearDown() {
    removeUniqueFolder();
  }

  @Test
  public void createAndDownloadZipSucceeds() throws IOException {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder folder = getUniqueFolder(api);
    String fileName = "small_file.rtf";
    BoxFile uploadedFile = null;
    BoxFolder createdFolder = null;
    try {
      createdFolder = folder.createFolder("Zip Test Folder").getResource();
      uploadedFile = uploadSampleFileToUniqueFolder(api, fileName);
      uploadedFile.copy(createdFolder);

      ArrayList<BoxZipItem> items = new ArrayList<>();
      BoxZipItem file = new BoxZipItem("file", uploadedFile.getID());
      BoxZipItem subFolder = new BoxZipItem("folder", createdFolder.getID());
      items.add(file);
      items.add(subFolder);

      ByteArrayOutputStream downloadStream = new ByteArrayOutputStream();
      BoxZipDownloadStatus zipDownloadStatus =
          new BoxZip(api).download("zip_test", items, downloadStream);
      byte[] downloadedFileContent = downloadStream.toByteArray();

      Assert.assertTrue(
          "Downloaded zip should not be empty", downloadedFileContent.length > 0);
      assertThat(zipDownloadStatus.getState(), anyOf(is(SUCCEEDED), is(IN_PROGRESS)));
    } finally {
      deleteFile(uploadedFile);
      deleteFolder(createdFolder);
    }
  }
}
