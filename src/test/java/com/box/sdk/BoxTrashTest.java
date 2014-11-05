package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxTrashTest {
    @Test
    @Category(IntegrationTest.class)
    public void getAllTrashedItems() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder trashedFolder = rootFolder.createFolder("[getAllTrashedItems] Trashed Folder");
        trashedFolder.delete(false);

        assertThat(trash, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(trashedFolder.getID()))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getTrashedFolderInfo() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String trashedFolderName = "[getTrashedFolderInfo] Trashed Folder";
        BoxFolder trashedFolder = rootFolder.createFolder(trashedFolderName);
        trashedFolder.delete(false);

        BoxFolder.Info info = trash.getFolderInfo(trashedFolder.getID());

        assertThat(info.getName(), is(equalTo(trashedFolderName)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void permanentlyDeleteTrashedFolder() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String folderName = "[permanentlyDeleteTrashedFolder] Trashed Folder";

        BoxFolder folder = rootFolder.createFolder(folderName);
        folder.delete(false);
        trash.deleteFolder(folder.getID());

        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void restoreTrashedFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String folderName = "[restoreTrashedFolderWithNewNameSucceeds] Trashed Folder";

        BoxFolder folder = rootFolder.createFolder(folderName);
        folder.delete(false);
        BoxFolder.Info restoredFolderInfo = trash.restoreFolder(folder.getID());

        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID())))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID()))));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void restoreTrashedFolderWithNewNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String folderName = "[restoreTrashedFolderWithNewNameSucceeds] Trashed Folder";
        String restoredFolderName = "[restoreTrashedFolderWithNewNameSucceeds] Trashed Folder";

        BoxFolder folder = rootFolder.createFolder(folderName);
        folder.delete(false);
        BoxFolder.Info restoredFolderInfo = trash.restoreFolder(folder.getID(), restoredFolderName, null);

        assertThat(restoredFolderInfo.getName(), is(equalTo(restoredFolderName)));
        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID())))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(folder.getID()))));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getTrashedFileInfo() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getTrashedFileInfo] Trashed File.txt";
        String fileContent = "Trashed file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
        uploadedFile.delete();

        BoxFile.Info trashedFileInfo = trash.getFileInfo(uploadedFile.getID());

        assertThat(trashedFileInfo.getName(), is(equalTo(fileName)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void permanentlyDeleteTrashedFile() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[permanentlyDeleteTrashedFile] Trashed File.txt";
        String fileContent = "Trashed file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
        uploadedFile.delete();
        trash.deleteFile(uploadedFile.getID());

        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void restoreTrashedFileSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[restoreTrashedFileSucceeds] Trashed File.txt";
        String fileContent = "Trashed file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
        uploadedFile.delete();
        trash.restoreFile(uploadedFile.getID());

        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void restoreTrashedFileWithNewNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[restoreTrashedFileWithNewNameSucceeds] Trashed File.txt";
        String restoredFileName = "[restoreTrashedFileWithNewNameSucceeds] Restored File.txt";
        String fileContent = "Trashed file";
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName);
        uploadedFile.delete();
        BoxFile.Info restoredFileInfo = trash.restoreFile(uploadedFile.getID(), restoredFileName, null);

        assertThat(restoredFileInfo.getName(), is(equalTo(restoredFileName)));
        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));

        uploadedFile.delete();
    }
}
