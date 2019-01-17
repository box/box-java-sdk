package com.box.sdk;

import com.eclipsesource.json.JsonObject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * {@link BoxFolder} related tests.
 */
public class BoxFolderTest {

    @SuppressWarnings("checkstyle:wrongOrder")
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(IntegrationTest.class)
    public void creatingAndDeletingFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder("[creatingAndDeletingFolderSucceeds] Ĥȅľľő Ƒŕőďő")
                .getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID()))));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getFolderInfoReturnsCorrectInfo() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser currentUser = BoxUser.getCurrentUser(api);
        final String expectedName = "[getFolderInfoReturnsCorrectInfo] Child Folder";
        final String expectedCreatedByID = currentUser.getID();

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        final String expectedParentFolderID = rootFolder.getID();
        final String expectedParentFolderName = rootFolder.getInfo().getName();

        BoxFolder childFolder = rootFolder.createFolder(expectedName).getResource();
        BoxFolder.Info info = childFolder.getInfo(BoxItem.ALL_FIELDS);

        String actualName = info.getName();
        String actualCreatedByID = info.getCreatedBy().getID();
        String actualParentFolderID = info.getParent().getID();
        String actualParentFolderName = info.getParent().getName();
        List<BoxFolder.Info> actualPathCollection = info.getPathCollection();

        assertThat(expectedName, equalTo(actualName));
        assertThat(expectedCreatedByID, equalTo(actualCreatedByID));
        assertThat(expectedParentFolderID, equalTo(actualParentFolderID));
        assertThat(expectedParentFolderName, equalTo(actualParentFolderName));
        assertThat(actualPathCollection, hasItem(Matchers.<BoxFolder.Info>hasProperty("ID", equalTo("0"))));
        assertThat(info.getPermissions(), is(equalTo(EnumSet.allOf(BoxFolder.Permission.class))));
        assertThat(info.getItemStatus(), is(equalTo("active")));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheNameField() {
        final String expectedName = "All Files";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder.Info rootFolderInfo = rootFolder.getInfo("name");
        final String actualName = rootFolderInfo.getName();
        final String actualDescription = rootFolderInfo.getDescription();
        final long actualSize = rootFolderInfo.getSize();

        assertThat(expectedName, equalTo(actualName));
        assertThat(actualDescription, is(nullValue()));
        assertThat(actualSize, is(0L));
    }

    @Test
    @Category(IntegrationTest.class)
    public void iterateWithOnlyTheNameField() {
        final String expectedName = "[iterateWithOnlyTheNameField] Child Folder";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder.Info rootFolderInfo = rootFolder.getInfo("name");

        BoxFolder childFolder = rootFolder.createFolder(expectedName).getResource();

        Iterable<BoxItem.Info> children = rootFolder.getChildren("name");
        boolean found = false;
        for (BoxItem.Info childInfo : children) {
            if (childInfo.getID().equals(childFolder.getID())) {
                found = true;
                assertThat(childInfo.getName(), is(equalTo(expectedName)));
                assertThat(childInfo.getSize(), is(equalTo(0L)));
                assertThat(childInfo.getDescription(), is(nullValue()));
            }
        }
        assertThat(found, is(true));

        childFolder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadFileSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        final String fileContent = "Test file";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        BoxFile uploadedFile = rootFolder.uploadFile(stream, "Test File.txt").getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));

        uploadedFile.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadFileUploadFileCallbackSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        final String fileContent = "Test file";
        BoxFile uploadedFile = rootFolder.uploadFile(new UploadFileCallback() {
            @Override
            public void writeToStream(OutputStream outputStream) throws IOException {
                    outputStream.write(fileContent.getBytes());
            }
        }, "Test File.txt").getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));

        uploadedFile.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
    }


    @Test
    @Category(IntegrationTest.class)
    public void uploadFileWithCreatedAndModifiedDatesSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date created = new Date(1415318114);
        Date modified = new Date(1315318114);
        final String fileContent = "Test file";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        FileUploadParams params = new FileUploadParams()
                .setName("[uploadFileWithCreatedAndModifiedDatesSucceeds] Test File.txt").setContent(stream)
                .setModified(modified).setCreated(created);
        BoxFile.Info info = rootFolder.uploadFile(params);
        BoxFile uploadedFile = info.getResource();

        assertThat(dateFormat.format(info.getContentCreatedAt()), is(equalTo(dateFormat.format(created))));
        assertThat(dateFormat.format(info.getContentModifiedAt()), is(equalTo(dateFormat.format(modified))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));

        uploadedFile.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFolderInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String originalName = "[updateFolderInfoSucceeds] Child Folder";
        final String updatedName = "[updateFolderInfoSucceeds] Updated Child Folder";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder.Info info = rootFolder.createFolder(originalName);
        BoxFolder childFolder = info.getResource();
        info.setName(updatedName);
        childFolder.updateInfo(info);
        assertThat(info.getName(), equalTo(updatedName));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void copyFolderToSameDestinationWithNewNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String originalName = "[copyFolderToSameDestinationWithNewNameSucceeds] Child Folder";
        final String newName = "[copyFolderToSameDestinationWithNewNameSucceeds] New Child Folder";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder originalFolder = rootFolder.createFolder(originalName).getResource();
        BoxFolder.Info copiedFolderInfo = originalFolder.copy(rootFolder, newName);
        BoxFolder copiedFolder = copiedFolderInfo.getResource();

        assertThat(copiedFolderInfo.getName(), is(equalTo(newName)));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(originalFolder.getID()))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(copiedFolder.getID()))));

        originalFolder.delete(false);
        copiedFolder.delete(false);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(originalFolder.getID())))));
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(copiedFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void moveFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String child1Name = "[moveFolderSucceeds] Child Folder";
        final String child2Name = "[moveFolderSucceeds] Child Folder 2";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder1 = rootFolder.createFolder(child1Name).getResource();
        BoxFolder childFolder2 = rootFolder.createFolder(child2Name).getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder1.getID()))));
        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder2.getID()))));

        childFolder2.move(childFolder1);

        assertThat(childFolder1, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder2.getID()))));
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder2.getID())))));

        childFolder1.delete(true);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder1.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void renameFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String originalName = "[renameFolderSucceeds] Original Name";
        final String newName = "[renameFolderSucceeds] New Name";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder(originalName).getResource();
        childFolder.rename(newName);
        BoxFolder.Info childFolderInfo = childFolder.getInfo();

        assertThat(childFolderInfo.getName(), is(equalTo(newName)));

        childFolder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCollaboratorSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[addCollaborationToFolderSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();

        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);
        BoxUser.Info accessibleBy = (BoxUser.Info) collabInfo.getAccessibleBy();

        assertThat(accessibleBy.getLogin(), is(equalTo(collaboratorLogin)));
        assertThat(collabInfo.getRole(), is(equalTo(collaboratorRole)));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCollaborationsWithAttributesSucceeds() {
        // Logger logger = TestConfig.enableLogger("FINE");
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[getCollaborationsSucceeds] Test Folder";
        String collaboratorLogin = "karthik2001123@yahoo.com";
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole, true, true);
        String collabID = collabInfo.getID();

        collaboratorRole = BoxCollaboration.Role.VIEWER;
        collaboratorLogin = "davidsmaynard@gmail.com";
        BoxCollaboration.Info collabInfo2 = folder.collaborate(collaboratorLogin, collaboratorRole, true, true);

        collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Info collabInfo3 = folder.collaborate(collaboratorLogin, collaboratorRole, true, true);


        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();

        assertThat(collaborations, hasSize(3));
        assertThat(collaborations, hasItem(Matchers.<BoxCollaboration.Info>hasProperty("ID", equalTo(collabID))));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCollaborationsHasCorrectCollaborations() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[getCollaborationsHasCorrectCollaborations] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);
        String collabID = collabInfo.getID();

        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();

        assertThat(collaborations, hasSize(1));
        assertThat(collaborations, hasItem(Matchers.<BoxCollaboration.Info>hasProperty("ID", equalTo(collabID))));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void setFolderUploadEmailSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[setFolderUploadEmailSucceeds] Test Folder";

        BoxUploadEmail uploadEmail = new BoxUploadEmail();
        uploadEmail.setAccess(BoxUploadEmail.Access.OPEN);

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        BoxFolder.Info info = folder.new Info();
        info.setUploadEmail(uploadEmail);
        folder.updateInfo(info);

        assertThat(uploadEmail.getEmail(), not(isEmptyOrNullString()));
        assertThat(uploadEmail.getAccess(), is(equalTo(BoxUploadEmail.Access.OPEN)));

        info.setUploadEmail(null);
        uploadEmail = info.getUploadEmail();

        assertThat(uploadEmail, is(nullValue()));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getSharedItemAndItsChildrenSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[getSharedItemAndItsChildrenSucceeds] Test Folder";
        String childFolderName = "[getSharedItemAndItsChildrenSucceeds] Child Folder";

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        BoxFolder childFolder = folder.createFolder(childFolderName).getResource();
        BoxSharedLink sharedLink = folder.createSharedLink(BoxSharedLink.Access.OPEN, null, null);

        BoxFolder.Info sharedItem = (BoxFolder.Info) BoxItem.getSharedItem(api, sharedLink.getURL());

        assertThat(sharedItem.getID(), is(equalTo(folder.getID())));
        assertThat(sharedItem.getResource(), hasItem(Matchers.<BoxItem.Info>hasProperty("ID",
                equalTo(childFolder.getID()))));

        folder.delete(true);
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink("[createWebLinkSucceeds] Test Web Link",
                new URL("https://api.box.com"), "[createWebLinkSucceeds] Test Web Link").getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkNoNameSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink(new URL("https://api.box.com"),
                "[createWebLinkSucceeds] Test Web Link").getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkNoDescriptionSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink("[createWebLinkSucceeds] Test Web Link",
                new URL("https://api.box.com")).getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkNoNameOrDescriptionSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink(new URL("https://api.box.com")).getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }


    @Test
    @Category(IntegrationTest.class)
    public void createPropertiesMetadataSucceeds() {
        final String key = "/testKey";
        final String value = "testValue";
        final String folderName = "[createPropertiesMetadataSucceeds] Metadata Folder "
                + Calendar.getInstance().getTimeInMillis();

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Metadata md = new Metadata();
        md.add(key, value);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        Metadata createdMD = folder.createMetadata(md);

        assertThat(createdMD.getString(key), is(equalTo(value)));
        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getMetadataOnInfoSucceeds() {
        final String key = "/testKey";
        final String value = "testValue";
        final String folderName = "[createPropertiesMetadataSucceeds] Metadata Folder "
                + Calendar.getInstance().getTimeInMillis();

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Metadata md = new Metadata();
        md.add(key, value);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        folder.createMetadata(md);

        try {
            Metadata actualMD = folder.getInfo("metadata.global.properties").getMetadata("properties", "global");
            assertNotNull("Metadata should not be null for this folder", actualMD);
        } catch (BoxAPIException e) {
            fail("Metadata should have been present on this folder");
        } finally {
            folder.delete(false);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void deletePropertiesMetadataSucceeds() {
        final String key = "/testKey";
        final String value = "testValue";
        final String folderName = "[createPropertiesMetadataSucceeds] Metadata Folder "
                + Calendar.getInstance().getTimeInMillis();

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Metadata md = new Metadata();
        md.add(key, value);
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        folder.createMetadata(md);
        folder.deleteMetadata();

        try {
            Metadata actualMD = folder.getMetadata();
            fail();
        } catch (BoxAPIException e) {
            assertThat(e.getResponseCode(), is(equalTo(404)));
        } finally {
            folder.delete(false);
        }
    }

    /**
     * Verifies the fix for issue #325
     */
    @Test
    @Category(IntegrationTest.class)
    public void sharedLinkInfoHasEffectiveAccess() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder("[sharedLinkInfoHasEffectiveAccess] Test Folder").getResource();
        BoxSharedLink sharedLink = folder.createSharedLink(BoxSharedLink.Access.OPEN, null, null);

        assertThat(sharedLink, Matchers.<BoxSharedLink>hasProperty("effectiveAccess"));
        assertThat(sharedLink.getEffectiveAccess(), equalTo(BoxSharedLink.Access.OPEN));

        folder.delete(true);
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadSessionAbortFlowSuccess() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        String fileName = "oversize_pdf_test_0.pdf";
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        long fileSize = file.length();

        FileInputStream stream = new FileInputStream(file);

        byte[] fileBytes = new byte[(int) file.length()];
        stream.read(fileBytes);
        InputStream uploadStream = new ByteArrayInputStream(fileBytes);

        BoxFileUploadSession.Info session = rootFolder.createUploadSession(
                "Tamme-Lauri_tamm_suvepäeval.jpg", fileBytes.length);
        Assert.assertNotNull(session.getUploadSessionId());
        Assert.assertNotNull(session.getSessionExpiresAt());
        Assert.assertNotNull(session.getPartSize());

        BoxFileUploadSession.Endpoints endpoints = session.getSessionEndpoints();
        Assert.assertNotNull(endpoints);
        Assert.assertNotNull(endpoints.getUploadPartEndpoint());
        Assert.assertNotNull(endpoints.getStatusEndpoint());
        Assert.assertNotNull(endpoints.getListPartsEndpoint());
        Assert.assertNotNull(endpoints.getCommitEndpoint());
        Assert.assertNotNull(endpoints.getAbortEndpoint());

        //Verify the status of the session
        this.getUploadSessionStatus(session.getResource());

        //Verify the delete session
        this.abortUploadSession(session.getResource());
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadLargeFile() throws Exception {
        String fileName = "oversize_pdf_test_0.pdf";
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        FileInputStream stream = new FileInputStream(file);

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFile.Info fileUploaded = rootFolder.uploadLargeFile(stream, "100mb", file.length());
        Assert.assertNotNull(fileUploaded);

        fileUploaded.getResource().delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllRootFolderItemsSucceeds() throws IOException {
        String result = "";
        final String rootFolderItemsURL = "/folders/0/items/";
        final String folderURL = "/folders/0";
        final String ownedByUserLogin = "test@user.com";
        final String modifiedByLogin = "test@user.com";
        final String modifiedByName = "Test User";

        result = TestConfig.getFixture("BoxFolder/GetAllRootFolderItems200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(rootFolderItemsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder rootFolder = BoxFolder.getRootFolder(this.api);
        BoxFolder.Info rootFolderInfo = rootFolder.getInfo();

        Assert.assertEquals(ownedByUserLogin, rootFolderInfo.getOwnedBy().getLogin());
        Assert.assertEquals(modifiedByLogin, rootFolderInfo.getModifiedBy().getLogin());
        Assert.assertEquals(modifiedByName, rootFolderInfo.getModifiedBy().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllFolderItemsSucceeds() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID + "/items/";
        final String firstFolderName = "Example.pdf";

        result = TestConfig.getFixture("BoxFolder/GetAllFolderItems200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderURL))
                .withQueryParam("limit", WireMock.containing("1000"))
                .withQueryParam("offset", WireMock.containing("0"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxItem.Info firstFolderInfo = folder.iterator().next();

        Assert.assertEquals(folderID, firstFolderInfo.getID());
        Assert.assertEquals(firstFolderName, firstFolderInfo.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetFolderInfoSucceeds() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String folderInfoURL = "/folders/" + folderID;
        final String folderName = "Example Folder";
        final String pathCollectionItemName = "All Files";
        final String createdByLogin = "test@user.com";
        final String modifiedByName = "Test User";

        result = TestConfig.getFixture("BoxFolder/GetFolderInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.getInfo();

        Assert.assertEquals("folder", info.getType());
        Assert.assertEquals(folderID, info.getID());
        Assert.assertEquals(folderName, info.getName());
        Assert.assertEquals(pathCollectionItemName, info.getPathCollection().get(0).getName());
        Assert.assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(modifiedByName, info.getModifiedBy().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetRestrictedCollaborationFieldSucceeds() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String folderInfoURL = "/folders/" + folderID;

        result = TestConfig.getFixture("BoxFolder/GetFolderInfoForCollaborationRestriction200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.getInfo();

        Assert.assertTrue(info.getIsCollaborationRestrictedToEnterprise());
    }

    @Test
    @Category(UnitTest.class)
    public void testSetRestrictedCollaborationFieldSucceeds() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String folderInfoURL = "/folders/" + folderID;
        JsonObject jsonObject = new JsonObject()
                .add("is_collaboration_restricted_to_enterprise", true);

        result = TestConfig.getFixture("BoxFolder/GetFolderInfoForCollaborationRestriction200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(folderInfoURL))
            .withRequestBody(WireMock.equalToJson(jsonObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info folderInfo = folder.getInfo();
        folderInfo.setIsCollaborationRestrictedToEnterprise(true);
        folder.updateInfo(folderInfo);

        Assert.assertTrue(folder.getInfo().getIsCollaborationRestrictedToEnterprise());
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateFolderInfoSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID;
        final String folderName = "New Folder Name";
        final String folderDescription = "Folder Description";
        final Boolean isPasswordEnabled = true;
        final Boolean canDownload = false;
        final Boolean canPreview = false;

        JsonObject updateFolderObject = new JsonObject()
                .add("name", folderName)
                .add("description", "Folder Description")
                .add("is_password_enabled", true)
                .add("can_download", false)
                .add("can_preview", false);

        result = TestConfig.getFixture("BoxFolder/UpdateFolderInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(folderURL))
                .withRequestBody(WireMock.equalToJson(updateFolderObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.new Info();
        info.setName(folderName);
        info.setDescription(folderDescription);
        info.addPendingChange("is_password_enabled", true);
        info.addPendingChange("can_download", false);
        info.addPendingChange("can_preview", false);
        folder.updateInfo(info);

        Assert.assertEquals(folderID, info.getID());
        Assert.assertEquals(folderName, info.getName());
        Assert.assertEquals(folderDescription, info.getDescription());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateNewFolderSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String folderID = "0";
        final String folderURL = "/folders";
        final String folderName = "Example Test Folder";
        final String parentFolderName = "All Files";
        final String ownedByUserName = "Test User";
        final String ownedByUserLogin = "test@user.com";

        JsonObject parentObject = new JsonObject()
                .add("id", "0");

        JsonObject createFolderObject = new JsonObject()
                .add("name", folderName)
                .add("parent", parentObject);

        result = TestConfig.getFixture("BoxFolder/CreateNewFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderURL))
                .withRequestBody(WireMock.equalToJson(createFolderObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder parentFolder = new BoxFolder(this.api, folderID);
        BoxFolder.Info childFolderInfo = parentFolder.createFolder(folderName);

        Assert.assertEquals(folderID, childFolderInfo.getID());
        Assert.assertEquals(folderName, childFolderInfo.getName());
        Assert.assertEquals(parentFolderName, childFolderInfo.getParent().getName());
        Assert.assertEquals(ownedByUserName, childFolderInfo.getOwnedBy().getName());
        Assert.assertEquals(ownedByUserLogin, childFolderInfo.getOwnedBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testCopyFolderSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID + "/copy";
        final String newParentID = "12345";

        JsonObject parentObject = new JsonObject()
                .add("id", newParentID);

        JsonObject copyObject = new JsonObject()
                .add("parent", parentObject);

        result = TestConfig.getFixture("BoxFolder/CopyFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderURL))
                .withRequestBody(WireMock.equalToJson(copyObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder destination = new BoxFolder(this.api, newParentID);
        BoxFolder.Info copiedFolderInfo = folder.copy(destination);

        Assert.assertEquals(folderID, copiedFolderInfo.getID());
        Assert.assertEquals(newParentID, copiedFolderInfo.getParent().getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testMoveFolderSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String moveFolderURL = "/folders/" + folderID;
        final String parentID = "2222";

        JsonObject innerObject = new JsonObject()
                .add("id", parentID);

        JsonObject parentObject = new JsonObject()
                .add("parent", innerObject);

        result = TestConfig.getFixture("BoxFolder/MoveFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(moveFolderURL))
                .withRequestBody(WireMock.equalToJson(parentObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder destination = new BoxFolder(this.api, parentID);
        BoxItem.Info folderInfo = folder.move(destination);

        Assert.assertEquals(parentID, folderInfo.getParent().getID());
        Assert.assertEquals(folderID, folderInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteFolderSendsCorrectJson() {
        String result = "";
        final String folderID = "12345";
        final String deleteFolderURL = "/folders/" + folderID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFolderURL))
                .withQueryParam("recursive", WireMock.containing("true"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.delete(true);
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateSharedLinkForFolderSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID;
        final BoxSharedLink.Access effectiveAccess = BoxSharedLink.Access.OPEN;
        final Boolean isPasswordEnabled = false;
        final BoxSharedLink.Access access = BoxSharedLink.Access.OPEN;

        JsonObject accessObject = new JsonObject()
                .add("access", "open");

        JsonObject sharedLinkObject = new JsonObject()
                .add("shared_link", accessObject);

        result = TestConfig.getFixture("BoxFolder/CreateSharedLinkForFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(folderURL))
                .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxSharedLink sharedLink = new BoxSharedLink();
        sharedLink.setAccess(BoxSharedLink.Access.OPEN);

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.new Info();
        info.setSharedLink(sharedLink);
        folder.updateInfo(info);

        Assert.assertEquals(effectiveAccess, info.getSharedLink().getEffectiveAccess());
        Assert.assertEquals(isPasswordEnabled, info.getSharedLink().getIsPasswordEnabled());
        Assert.assertEquals(access, info.getSharedLink().getAccess());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllFolderCollaborationsSucceeds() throws IOException {
        String result = "";
        final String folderID = "3333";
        final String folderCollaborationURL = "/folders/" + folderID + "/collaborations";
        final String collaborationID = "12345";
        final String accessiblyByLogin = "Test User";
        final BoxCollaboration.Role collaborationRole = BoxCollaboration.Role.VIEWER;

        result = TestConfig.getFixture("BoxFolder/GetAllFolderCollaborations200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();
        BoxCollaboration.Info collaborationInfo = collaborations.iterator().next();

        Assert.assertEquals(collaborationID, collaborationInfo.getID());
        Assert.assertEquals(folderID, collaborationInfo.getItem().getID());
        Assert.assertEquals(accessiblyByLogin, collaborationInfo.getAccessibleBy().getName());
        Assert.assertEquals(collaborationRole, collaborationInfo.getRole());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateMetadataOnFolderSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata/global/properties";
        final String metadataID = "12345";
        final String parentID = "folder_12345";

        JsonObject metadataObject = new JsonObject()
                .add("foo", "bar");

        result = TestConfig.getFixture("BoxFolder/CreateMetadataOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
                .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Metadata metadataInfo = folder.createMetadata(new Metadata().add("/foo", "bar"));
        Assert.assertEquals(metadataID, metadataInfo.getID());
        Assert.assertEquals(parentID, metadataInfo.getParentID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetMetadataOnFolderSucceds() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata/global/properties";
        final String metadataID = "12345";
        final String parentID = "folder_12345";

        result = TestConfig.getFixture("BoxFolder/CreateMetadataOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Metadata metadata = folder.getMetadata();

        Assert.assertEquals(metadataID, metadata.getID());
        Assert.assertEquals(parentID, metadata.getParentID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataSucceeds() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata";
        final String metadataID = "12345";
        final String parentID = "folder_12345";
        final String template = "properties";
        final String scope = "global";

        result = TestConfig.getFixture("BoxFolder/GetAllMetadataOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
                .withQueryParam("limit", WireMock.containing("100"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<Metadata> metadataList = folder.getAllMetadata().iterator();
        Metadata metadata = metadataList.next();

        Assert.assertEquals(metadataID, metadata.getID());
        Assert.assertEquals(parentID, metadata.getParentID());
        Assert.assertEquals(template, metadata.getTemplateName());
        Assert.assertEquals(scope, metadata.getScope());
    }

    @Test
    @Category(UnitTest.class)
    public void testAddMetadataCascadePolicySucceedsSendsCorrectJson() throws IOException {
        String result = "";
        final String cascadePolicyURL = "/metadata_cascade_policies";
        final String folderID = "22222";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        JsonObject cascadeObject = new JsonObject()
                .add("folder_id", folderID)
                .add("scope", scope)
                .add("templateKey", templateKey);

        result = TestConfig.getFixture("BoxMetadataCascadePolicy/CreateMetadataCascadePolicies201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(cascadePolicyURL))
                .withRequestBody(WireMock.equalToJson(cascadeObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxMetadataCascadePolicy.Info metadataCascadePolicyInfo = folder.addMetadataCascadePolicy(scope, templateKey);

        Assert.assertEquals(folderID, metadataCascadePolicyInfo.getParent().getID());
        Assert.assertEquals(scope, metadataCascadePolicyInfo.getScope());
        Assert.assertEquals(templateKey, metadataCascadePolicyInfo.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataCascadePoliciesOnFolderSucceeds() throws IOException {
        String result = "";
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
                .withQueryParam("folder_id", WireMock.equalTo(folderID))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<BoxMetadataCascadePolicy.Info> metadataCascadePolicies = folder
                .getMetadataCascadePolicies().iterator();

        BoxMetadataCascadePolicy.Info firstCascadePolicy = metadataCascadePolicies.next();

        Assert.assertEquals(folderID, firstCascadePolicy.getParent().getID());
        Assert.assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        Assert.assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        Assert.assertEquals(scope, firstCascadePolicy.getScope());
        Assert.assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataCascadePoliciesOnFolderWithFieldsSucceeds() throws IOException {
        String result = "";
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
                .withQueryParam("folder_id", WireMock.equalTo(folderID))
                .withQueryParam("fields", WireMock.equalTo("owner_enterprise"))
                .withQueryParam("limit", WireMock.equalTo("100"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<BoxMetadataCascadePolicy.Info> metadataCascadePolicies = folder
                .getMetadataCascadePolicies("owner_enterprise").iterator();

        BoxMetadataCascadePolicy.Info firstCascadePolicy = metadataCascadePolicies.next();

        Assert.assertEquals(folderID, firstCascadePolicy.getParent().getID());
        Assert.assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        Assert.assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        Assert.assertEquals(scope, firstCascadePolicy.getScope());
        Assert.assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataCascadePoliciesWithEnterpriseIDSucceeds() throws IOException {
        String result = "";
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final int limit = 100;
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
                .withQueryParam("folder_id", WireMock.equalTo(folderID))
                .withQueryParam("owner_enterprise_id", WireMock.equalTo(enterpriseID))
                .withQueryParam("fields", WireMock.equalTo("owner_enterprise"))
                .withQueryParam("limit", WireMock.equalTo("100"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<BoxMetadataCascadePolicy.Info> metadataCascadePolicies = folder
                .getMetadataCascadePolicies(enterpriseID, limit, "owner_enterprise").iterator();

        BoxMetadataCascadePolicy.Info firstCascadePolicy = metadataCascadePolicies.next();

        Assert.assertEquals(folderID, firstCascadePolicy.getParent().getID());
        Assert.assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        Assert.assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        Assert.assertEquals(scope, firstCascadePolicy.getScope());
        Assert.assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void createSharedLinkSucceeds() throws IOException {
        final String folderID = "1111";
        final String password = "test1";
        String result = "";

        JsonObject permissionsObject = new JsonObject()
                .add("can_download", true)
                .add("can_preview", true);

        JsonObject innerObject = new JsonObject()
                .add("password", password)
                .add("access", "open")
                .add("permissions", permissionsObject);

        JsonObject sharedLinkObject = new JsonObject()
                .add("shared_link", innerObject);

        result = TestConfig.getFixture("BoxSharedLink/CreateSharedLink201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo("/folders/" + folderID))
                .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();

        permissions.setCanDownload(true);
        permissions.setCanPreview(true);
        BoxSharedLink sharedLink = folder.createSharedLink(BoxSharedLink.Access.OPEN, null, permissions,
                password);

        Assert.assertEquals(true, sharedLink.getIsPasswordEnabled());
    }

    private void getUploadSessionStatus(BoxFileUploadSession session) {
        BoxFileUploadSession.Info sessionInfo = session.getStatus();
        Assert.assertNotNull(sessionInfo.getSessionExpiresAt());
        Assert.assertNotNull(sessionInfo.getPartSize());
        Assert.assertNotNull(sessionInfo.getTotalParts());
        Assert.assertNotNull(sessionInfo.getPartsProcessed());
    }

    private void abortUploadSession(BoxFileUploadSession session) {
        session.abort();

        try {
            BoxFileUploadSession.Info sessionInfo = session.getStatus();

            //If the session is aborted, this line should not be executed.
            Assert.assertFalse("Upload session is not deleted", true);
        } catch (BoxAPIException apiEx) {
            Assert.assertEquals(apiEx.getResponseCode(), 404);
        }
    }
}
