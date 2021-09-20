package com.box.sdk;

import static org.junit.Assert.assertEquals;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.ClassRule;
import org.junit.Test;

public class BoxTrashTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testGetAllTrashedItemsSucceeds() throws IOException {
        final String trashURL = "/folders/trash/items/";
        final String firstTrashID = "12345";
        final String firstTrashName = "Test Folder";
        final String secondTrashID = "32343";
        final String secondTrashName = "File.pdf";

        String result = TestConfig.getFixture("BoxTrash/GetAllTrashItems200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(trashURL))
            .withQueryParam("limit", WireMock.containing("1000"))
            .withQueryParam("offset", WireMock.containing("0"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTrash trash = new BoxTrash(this.api);
        Iterator<BoxItem.Info> trashEntries = trash.iterator();
        BoxItem.Info firstTrashItem = trashEntries.next();

        assertEquals(firstTrashID, firstTrashItem.getID());
        assertEquals(firstTrashName, firstTrashItem.getName());

        BoxItem.Info secondTrashItem = trashEntries.next();

        assertEquals(secondTrashID, secondTrashItem.getID());
        assertEquals(secondTrashName, secondTrashItem.getName());
    }

    @Test
    public void testRestoreFolderFromTrashSucceeds() throws IOException {
        final String folderID = "12345";
        final String restoreFolderURL = "/folders/" + folderID;
        final String folderName = "Test Folder";
        final String createdByName = "Test User";
        final String parentFolderName = "All Files";

        String result = TestConfig.getFixture("BoxTrash/RestoreFolderItemFromTrash201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(restoreFolderURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTrash trash = new BoxTrash(this.api);
        BoxFolder.Info restoredFolder = trash.restoreFolder(folderID);

        assertEquals(folderID, restoredFolder.getID());
        assertEquals(folderName, restoredFolder.getName());
        assertEquals(createdByName, restoredFolder.getCreatedBy().getName());
        assertEquals(parentFolderName, restoredFolder.getParent().getName());
    }

    @Test
    public void testRestoreFileFromTrashSucceeds() throws IOException {
        final String fileID = "12345";
        final String restoreFileURL = "/files/" + fileID;
        final String fileName = "File.pdf";
        final String pathCollectionName = "All Files";
        final String createdByName = "Test User";
        final String parentFolderName = "Test Folder";

        String result = TestConfig.getFixture("BoxTrash/RestoreFileItemFromTrash201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(restoreFileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTrash trash = new BoxTrash(this.api);
        BoxFile.Info restoredFile = trash.restoreFile(fileID);

        assertEquals(fileID, restoredFile.getID());
        assertEquals(fileName, restoredFile.getName());
        assertEquals(pathCollectionName, restoredFile.getPathCollection().get(0).getName());
        assertEquals(createdByName, restoredFile.getCreatedBy().getName());
        assertEquals(parentFolderName, restoredFile.getParent().getName());
    }

    @Test
    public void testGetTrashedFolderItemInfoSucceeds() throws IOException {
        final String folderID = "12345";
        final String trashURL = "/folders/" + folderID + "/trash";
        final String folderName = "Another retention test";
        final String createdByLogin = "test@user.com";
        final String modifiedByName = "Test User";
        final String ownedByID = "1111";

        String result = TestConfig.getFixture("BoxTrash/GetTrashedFolderItemInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(trashURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTrash trash = new BoxTrash(this.api);
        BoxFolder.Info folderInfo = trash.getFolderInfo(folderID);

        assertEquals(folderName, folderInfo.getName());
        assertEquals(createdByLogin, folderInfo.getCreatedBy().getLogin());
        assertEquals(modifiedByName, folderInfo.getModifiedBy().getName());
        assertEquals(ownedByID, folderInfo.getOwnedBy().getID());
    }

    @Test
    public void testGetTrashedFileItemInfoSucceeds() throws IOException {
        final String fileID = "12345";
        final String trashURL = "/files/" + fileID + "/trash";
        final String folderName = "File.pdf";
        final String createdByLogin = "test@user.com";
        final String modifiedByName = "Test User";
        final String ownedByID = "1111";

        String result = TestConfig.getFixture("BoxTrash/GetTrashedFileItemInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(trashURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTrash trash = new BoxTrash(this.api);
        BoxFile.Info fileInfo = trash.getFileInfo(fileID);

        assertEquals(folderName, fileInfo.getName());
        assertEquals(createdByLogin, fileInfo.getCreatedBy().getLogin());
        assertEquals(modifiedByName, fileInfo.getModifiedBy().getName());
        assertEquals(ownedByID, fileInfo.getOwnedBy().getID());
    }

    @Test
    public void testPermanentlyDeleteFolderFromTrash() {
        final String folderID = "12345";
        final String deleteFolderURL = "/folders/" + folderID + "/trash";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFolderURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxTrash trash = new BoxTrash(this.api);
        trash.deleteFolder(folderID);
    }

    @Test
    public void testPermanentlyDeleteFileFromTrash() {
        final String fileID = "12345";
        final String deleteFileURL = "/files/" + fileID + "/trash";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxTrash trash = new BoxTrash(this.api);
        trash.deleteFile(fileID);
    }
}
