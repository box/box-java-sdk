package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoxTrashTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void testGetAllTrashedItemsSucceeds() {
        final String trashURL = "/2.0/folders/trash/items/";
        final String firstTrashID = "12345";
        final String firstTrashName = "Test Folder";
        final String secondTrashID = "32343";
        final String secondTrashName = "File.pdf";

        String result = TestUtils.getFixture("BoxTrash/GetAllTrashItems200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(trashURL))
            .withQueryParam("limit", WireMock.containing("1000"))
            .withQueryParam("usemarker", WireMock.containing("true"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testRestoreFolderFromTrashSucceeds() {
        final String folderID = "12345";
        final String restoreFolderURL = "/2.0/folders/" + folderID;
        final String folderName = "Test Folder";
        final String createdByName = "Test User";
        final String parentFolderName = "All Files";

        String result = TestUtils.getFixture("BoxTrash/RestoreFolderItemFromTrash201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(restoreFolderURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxTrash trash = new BoxTrash(this.api);
        BoxFolder.Info restoredFolder = trash.restoreFolder(folderID);

        assertEquals(folderID, restoredFolder.getID());
        assertEquals(folderName, restoredFolder.getName());
        assertEquals(createdByName, restoredFolder.getCreatedBy().getName());
        assertEquals(parentFolderName, restoredFolder.getParent().getName());
    }

    @Test
    public void testRestoreFileFromTrashSucceeds() {
        final String fileID = "12345";
        final String restoreFileURL = "/2.0/files/" + fileID;
        final String fileName = "File.pdf";
        final String pathCollectionName = "All Files";
        final String createdByName = "Test User";
        final String parentFolderName = "Test Folder";

        String result = TestUtils.getFixture("BoxTrash/RestoreFileItemFromTrash201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(restoreFileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void testGetTrashedFolderItemInfoSucceeds() {
        final String folderID = "12345";
        final String trashURL = "/2.0/folders/" + folderID + "/trash";
        final String folderName = "Another retention test";
        final String createdByLogin = "test@user.com";
        final String modifiedByName = "Test User";
        final String ownedByID = "1111";

        String result = TestUtils.getFixture("BoxTrash/GetTrashedFolderItemInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(trashURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxTrash trash = new BoxTrash(this.api);
        BoxFolder.Info folderInfo = trash.getFolderInfo(folderID);

        assertEquals(folderName, folderInfo.getName());
        assertEquals(createdByLogin, folderInfo.getCreatedBy().getLogin());
        assertEquals(modifiedByName, folderInfo.getModifiedBy().getName());
        assertEquals(ownedByID, folderInfo.getOwnedBy().getID());
    }

    @Test
    public void testGetTrashedFileItemInfoSucceeds() {
        final String fileID = "12345";
        final String trashURL = "/2.0/files/" + fileID + "/trash";
        final String folderName = "File.pdf";
        final String createdByLogin = "test@user.com";
        final String modifiedByName = "Test User";
        final String ownedByID = "1111";

        String result = TestUtils.getFixture("BoxTrash/GetTrashedFileItemInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(trashURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
        final String deleteFolderURL = "/2.0/folders/" + folderID + "/trash";

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFolderURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxTrash trash = new BoxTrash(this.api);
        trash.deleteFolder(folderID);
    }

    @Test
    public void testPermanentlyDeleteFileFromTrash() {
        final String fileID = "12345";
        final String deleteFileURL = "/2.0/files/" + fileID + "/trash";

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFileURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxTrash trash = new BoxTrash(this.api);
        trash.deleteFile(fileID);
    }

    @Test
    public void testGetAllTrashedItemsWithOrderAndOffsetAndLimit() {
        final String trashURL = "/2.0/folders/trash/items/";
        final String firstTrashID = "12345";
        final String firstTrashName = "Test Folder";
        final String secondTrashID = "32343";
        final String secondTrashName = "File.pdf";

        String result = TestUtils.getFixture("BoxTrash/GetAllTrashItems200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(trashURL))
            .withQueryParam("limit", WireMock.containing("500"))
            .withQueryParam("offset", WireMock.containing("100"))
            .withQueryParam("sort", WireMock.containing("name"))
            .withQueryParam("direction", WireMock.containing("DESC"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxTrash trash = new BoxTrash(this.api);
        Iterable<BoxItem.Info> trashEntries = trash.items(
            SortParameters.descending("name"),
            PagingParameters.offset(100, 500)
        );
        Iterator<BoxItem.Info> iterator = trashEntries.iterator();
        BoxItem.Info firstTrashItem = iterator.next();

        assertEquals(firstTrashID, firstTrashItem.getID());
        assertEquals(firstTrashName, firstTrashItem.getName());

        BoxItem.Info secondTrashItem = iterator.next();

        assertEquals(secondTrashID, secondTrashItem.getID());
        assertEquals(secondTrashName, secondTrashItem.getName());
    }

    @Test
    public void testGetAllTrashedItemsWithStreamPositionAndLimit() {
        final String trashURL = "/2.0/folders/trash/items/";
        final String firstTrashID = "12345";
        final String firstTrashName = "Test Folder";
        final String secondTrashID = "32343";
        final String secondTrashName = "File.pdf";

        String result = TestUtils.getFixture("BoxTrash/GetAllTrashItemsUsingmarker200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(trashURL))
            .withQueryParam("limit", WireMock.equalTo("500"))
            .withQueryParam("usemarker", WireMock.equalTo("true"))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxTrash trash = new BoxTrash(this.api);
        Iterable<BoxItem.Info> trashEntries = trash.items(
            SortParameters.none(),
            PagingParameters.marker(500)
        );

        Iterator<BoxItem.Info> iterator = trashEntries.iterator();
        BoxItem.Info firstTrashItem = iterator.next();

        assertEquals(firstTrashID, firstTrashItem.getID());
        assertEquals(firstTrashName, firstTrashItem.getName());

        BoxItem.Info secondTrashItem = iterator.next();

        assertEquals(secondTrashID, secondTrashItem.getID());
        assertEquals(secondTrashName, secondTrashItem.getName());
    }

    @Test
    public void testGetAllTrashedItemsFailsWithOrderAndStreamPosition() {

        BoxTrash trash = new BoxTrash(this.api);
        assertThrows(
            "Sorting is not supported when using marker based pagination.",
            IllegalArgumentException.class,
            () -> trash.items(
                SortParameters.ascending("name"),
                PagingParameters.marker(500)
            )
        );
    }
}
