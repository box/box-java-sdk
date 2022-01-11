package com.box.sdk;

import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolderWithSomeContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoxTrashIT {
    private static final BoxLogger LOGGER = BoxLogger.defaultLogger();

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
        cleanTrash();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    @Test
    public void getAllTrashedItems() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = getUniqueFolder(api);
        BoxFolder trashedFolder = rootFolder.createFolder("[getAllTrashedItems] Trashed Folder").getResource();
        trashedFolder.delete(false);

        assertThat(trash, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(trashedFolder.getID()))));
    }

    @Test
    public void getTrashedFolderInfo() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
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
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[permanentlyDeleteTrashedFolder] Trashed Folder";

        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        folder.delete(false);
        trash.deleteFolder(folder.getID());

        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID())))));
    }

    @Test
    public void restoreTrashedFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[restoreTrashedFolderWithNewNameSucceeds] Trashed Folder";

        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        folder.delete(false);
        trash.restoreFolder(folder.getID());

        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID())))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID()))));

        folder.delete(false);
    }

    @Test
    public void restoreTrashedFolderWithNewNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[restoreTrashedFolderWithNewNameSucceeds] Trashed Folder";
        String restoredFolderName = "[restoreTrashedFolderWithNewNameSucceeds] Trashed Folder";
        BoxFolder folder = null;

        try {
            folder = rootFolder.createFolder(folderName).getResource();
            folder.delete(false);
            BoxFolder.Info restoredFolderInfo = trash.restoreFolder(folder.getID(), restoredFolderName, null);

            assertThat(restoredFolderInfo.getName(), is(equalTo(restoredFolderName)));
            assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID())))));
            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID()))));
        } finally {
            if (folder != null) {
                folder.delete(false);
            }
        }
    }

    @Test
    public void getTrashedFileInfo() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
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
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = "[permanentlyDeleteTrashedFile] Trashed File.txt";
        String fileContent = "Trashed file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        uploadedFile.delete();
        trash.deleteFile(uploadedFile.getID());

        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
    }

    @Test
    public void restoreTrashedFileSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = "[restoreTrashedFileSucceeds] Trashed File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
            uploadedFile.delete();
            trash.restoreFile(uploadedFile.getID());

            assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
        }

    }

    @Test
    public void restoreTrashedFileWithNewNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = "[restoreTrashedFileWithNewNameSucceeds] Trashed File.txt";
        String restoredFileName = "[restoreTrashedFileWithNewNameSucceeds] Restored File.txt";
        BoxFile uploadedFile = null;
        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
            uploadedFile.delete();
            BoxFile.Info restoredFileInfo = trash.restoreFile(uploadedFile.getID(), restoredFileName, null);

            assertThat(restoredFileInfo.getName(), is(equalTo(restoredFileName)));
            assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    private void deleteFile(BoxFile uploadedFile) {
        if (uploadedFile != null) {
            uploadedFile.delete();
        }
    }

    /**
     * We are removing items that might be deleted but are still in trash to speed up tests.
     */
    private static void cleanTrash() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        for (BoxItem.Info info : trash) {
            if (info.getType().equals("file")) {
                try {
                    trash.deleteFile(info.getID());
                } catch (BoxAPIException e) {
                    // with governance some files cannot be removed from trash
                    if (e.getResponseCode() == 403) {
                        LOGGER.debug("Cannot remove file[" + info.getID() + "] " + info.getName());
                    } else {
                        throw e;
                    }
                }
            }
            if (info.getType().equals("folder")) {
                trash.deleteFolder(info.getID());
            }
        }
    }
}
