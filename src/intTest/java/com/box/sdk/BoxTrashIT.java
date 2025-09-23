package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.CleanupTools.deleteFile;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolderWithSomeContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoxTrashIT {
  @BeforeClass
  public static void setup() {
    setupUniqeFolder();
  }

  @AfterClass
  public static void tearDown() {
    removeUniqueFolder();
  }

  @Test
  public void getAllTrashedItems() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    BoxFolder trashedFolder =
        rootFolder.createFolder("[getAllTrashedItems] Trashed Folder").getResource();
    trashedFolder.delete(false);

    BoxItem.Info someItemFromTrash = trash.iterator().next();

    assertThat(someItemFromTrash, not(nullValue()));
  }

  @Test
  public void getAllTrashedItemsWithLimit() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    BoxFolder trashedFolder =
        rootFolder.createFolder("[getAllTrashedItemsWithLimit] Trashed Folder").getResource();
    trashedFolder.delete(false);

    BoxItem.Info someItemFromTrash =
        trash.items(SortParameters.none(), PagingParameters.offset(0, 1)).iterator().next();

    assertThat(someItemFromTrash, not(nullValue()));
  }

  @Test
  public void getTrashedFolderInfo() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    String trashedFolderName = "[getTrashedFolderInfo] Trashed Folder";
    BoxFolder trashedFolder = rootFolder.createFolder(trashedFolderName).getResource();
    trashedFolder.delete(false);

    BoxFolder.Info info = trash.getFolderInfo(trashedFolder.getID());

    assertThat(info.getName(), is(equalTo(trashedFolderName)));
    assertThat(info.getItemStatus(), is(equalTo("trashed")));
  }

  @Test
  public void permanentlyDeleteTrashedFolder() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    String folderName = "[permanentlyDeleteTrashedFolder] Trashed Folder";

    BoxFolder folder = rootFolder.createFolder(folderName).getResource();
    folder.delete(false);
    trash.deleteFolder(folder.getID());

    assertFolderIsNotTrashed(trash, folder);
  }

  @Test
  public void restoreTrashedFolderSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    String folderName = "[restoreTrashedFolderWithNewNameSucceeds] Trashed Folder";

    BoxFolder folder = rootFolder.createFolder(folderName).getResource();
    folder.delete(false);
    trash.restoreFolder(folder.getID());

    assertFolderIsNotTrashed(trash, folder);
    assertThat(
        rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID()))));
    folder.delete(false);
  }

  @Test
  public void restoreTrashedFolderWithNewNameSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    String folderName = "[restoreTrashedFolderWithNewNameSucceeds] Trashed Folder";
    String restoredFolderName = "[restoreTrashedFolderWithNewNameSucceeds] Trashed Folder";
    BoxFolder folder = null;

    try {
      folder = rootFolder.createFolder(folderName).getResource();
      folder.delete(false);
      BoxFolder.Info restoredFolderInfo =
          trash.restoreFolder(folder.getID(), restoredFolderName, null);

      assertThat(restoredFolderInfo.getName(), is(equalTo(restoredFolderName)));
      assertFolderIsNotTrashed(trash, folder);
      assertThat(
          rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID()))));
    } finally {
      CleanupTools.deleteFolder(folder);
    }
  }

  @Test
  public void getTrashedFileInfo() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    String fileName = "[getTrashedFileInfo] Trashed File.txt";
    String fileContent = "Trashed file";
    byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

    InputStream uploadStream = new ByteArrayInputStream(fileBytes);
    BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
    uploadedFile.delete();

    BoxFile.Info trashedFileInfo = trash.getFileInfo(uploadedFile.getID());

    assertThat(trashedFileInfo.getName(), is(equalTo(fileName)));
    assertThat(trashedFileInfo.getItemStatus(), is(equalTo("trashed")));
  }

  @Test
  public void permanentlyDeleteTrashedFile() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    String fileName = "[permanentlyDeleteTrashedFile] Trashed File.txt";
    String fileContent = "Trashed file";
    byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

    InputStream uploadStream = new ByteArrayInputStream(fileBytes);
    BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
    uploadedFile.delete();
    trash.deleteFile(uploadedFile.getID());

    assertFileIsNotTrashed(trash, uploadedFile);
  }

  @Test
  public void restoreTrashedFileSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    String fileName = "[restoreTrashedFileSucceeds] Trashed File.txt";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
      uploadedFile.delete();
      trash.restoreFile(uploadedFile.getID());

      assertFileIsNotTrashed(trash, uploadedFile);
      assertThat(
          rootFolder,
          hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  @Test
  public void restoreTrashedFileWithNewNameSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxTrash trash = new BoxTrash(api);
    BoxFolder rootFolder = getUniqueFolder(api);
    String fileName = "[restoreTrashedFileWithNewNameSucceeds] Trashed File.txt";
    String restoredFileName = "[restoreTrashedFileWithNewNameSucceeds] Restored File.txt";
    BoxFile uploadedFile = null;
    try {
      uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
      uploadedFile.delete();
      BoxFile.Info restoredFileInfo =
          trash.restoreFile(uploadedFile.getID(), restoredFileName, null);

      assertThat(restoredFileInfo.getName(), is(equalTo(restoredFileName)));
      assertFileIsNotTrashed(trash, uploadedFile);
      assertThat(
          rootFolder,
          hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
    } finally {
      deleteFile(uploadedFile);
    }
  }

  private void assertFileIsNotTrashed(BoxTrash trash, BoxFile file) {
    try {
      trash.getFileInfo(file.getID(), "id");
    } catch (BoxAPIResponseException e) {
      assertThat(e.getResponseCode(), is(404));
    }
  }

  private void assertFolderIsNotTrashed(BoxTrash trash, BoxFolder folder) {
    try {
      trash.getFolderInfo(folder.getID(), "id");
    } catch (BoxAPIResponseException e) {
      assertThat(e.getResponseCode(), is(404));
    }
  }
}
