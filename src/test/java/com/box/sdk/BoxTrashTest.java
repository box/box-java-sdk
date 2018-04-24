package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxTrashTest {

    /**
     * Wiremock
     */
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(IntegrationTest.class)
    public void getAllTrashedItems() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder trashedFolder = rootFolder.createFolder("[getAllTrashedItems] Trashed Folder").getResource();
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
        BoxFolder trashedFolder = rootFolder.createFolder(trashedFolderName).getResource();
        trashedFolder.delete(false);

        BoxFolder.Info info = trash.getFolderInfo(trashedFolder.getID());

        assertThat(info.getName(), is(equalTo(trashedFolderName)));
        assertThat(info.getItemStatus(), is(equalTo("trashed")));
    }

    @Test
    @Category(IntegrationTest.class)
    public void permanentlyDeleteTrashedFolder() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTrash trash = new BoxTrash(api);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String folderName = "[permanentlyDeleteTrashedFolder] Trashed Folder";

        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
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

        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
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

        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
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
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        uploadedFile.delete();

        BoxFile.Info trashedFileInfo = trash.getFileInfo(uploadedFile.getID());

        assertThat(trashedFileInfo.getName(), is(equalTo(fileName)));
        assertThat(trashedFileInfo.getItemStatus(), is(equalTo("trashed")));
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
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
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
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
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
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();
        uploadedFile.delete();
        BoxFile.Info restoredFileInfo = trash.restoreFile(uploadedFile.getID(), restoredFileName, null);

        assertThat(restoredFileInfo.getName(), is(equalTo(restoredFileName)));
        assertThat(trash, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));

        uploadedFile.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllTrashedItemsSucceeds() throws IOException {
        String result = "";
        final String trashURL = "/folders/trash/items/";
        final String firstTrashID = "12345";
        final String firstTrashType = "folder";
        final String firstTrashName = "Test Folder";
        final String secondTrashID = "32343";
        final String secondTrashName = "File.pdf";

        result = TestConfig.getFixture("BoxTrash/GetAllTrashItems200");

      		this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(trashURL))
                .withQueryParam("limit", WireMock.containing("1000"))
                .withQueryParam("offset", WireMock.containing("0"))
      			.willReturn(WireMock.aResponse()
      				  .withHeader("Content-Type", "application/json")
      				  .withBody(result)));

        BoxTrash trash = new BoxTrash(api);
        Iterator<BoxItem.Info> trashEntries = trash.iterator();
        BoxItem.Info firstTrashItem = trashEntries.next();

        Assert.assertEquals(firstTrashID, firstTrashItem.getID());
        Assert.assertEquals(firstTrashName, firstTrashItem.getName());

        BoxItem.Info secondTrashItem = trashEntries.next();

        Assert.assertEquals(secondTrashID, secondTrashItem.getID());
        Assert.assertEquals(secondTrashName, secondTrashItem.getName());
    }
}
