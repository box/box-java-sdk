package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.experimental.categories.Category;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static com.box.sdk.BoxCollaborationAllowlist.AllowlistDirection.INBOUND;
import static com.box.sdk.UniqueTestFolder.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * {@link BoxFolder} related tests.
 */
public class BoxFolderTest {

    @SuppressWarnings("checkstyle:wrongOrder")
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    @Test
    @Category(IntegrationTest.class)
    public void creatingAndDeletingFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        BoxFolder childFolder =
                rootFolder.createFolder("[creatingAndDeletingFolderSucceeds] Ĥȅľľő Ƒŕőďő").getResource();

        assertThat(rootFolder,
                hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID()))));

        childFolder.delete(false);
        assertThat(rootFolder,
                not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getFolderInfoReturnsCorrectInfo() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser currentUser = BoxUser.getCurrentUser(api);
        final String expectedName = "[getFolderInfoReturnsCorrectInfo] Child Folder";
        final String expectedCreatedByID = currentUser.getID();
        BoxFolder childFolder = null;

        BoxFolder rootFolder = getUniqueFolder(api);
        final String expectedParentFolderID = rootFolder.getID();
        final String expectedParentFolderName = rootFolder.getInfo().getName();

        try {
            childFolder = rootFolder.createFolder(expectedName).getResource();
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
        } finally {
            this.deleteFolder(childFolder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheNameField() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        final String expectedName = UniqueTestFolder.getUniqueFolderName();

        BoxFolder.Info rootFolderInfo = rootFolder.getInfo("name");

        assertThat(expectedName, equalTo(rootFolderInfo.getName()));
        assertThat(rootFolderInfo.getDescription(), is(nullValue()));
        assertThat(rootFolderInfo.getSize(), is(0L));
    }

    @Test
    @Category(IntegrationTest.class)
    public void iterateWithOnlyTheNameField() {
        final String expectedName = "[iterateWithOnlyTheNameField] Child Folder";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        BoxFolder childFolder = null;

        try {
            childFolder = rootFolder.createFolder(expectedName).getResource();

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
        } finally {
            this.deleteFolder(childFolder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadFileSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, "Test File.txt");

            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
            assertThat(rootFolder,
                    not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID())))));
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadFileUploadFileCallbackSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        BoxFile uploadedFile = null;
        final AtomicReference<Boolean> callbackWasCalled = new AtomicReference<>(false);

        try {

            final String fileContent = "Test file";
            uploadedFile = rootFolder.uploadFile(new UploadFileCallback() {
                @Override
                public void writeToStream(OutputStream outputStream) throws IOException {
                    outputStream.write(fileContent.getBytes());
                    callbackWasCalled.set(true);
                }
            }, "Test File.txt").getResource();

            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
            assertTrue("Callback was not called", callbackWasCalled.get());
        } finally {
            this.deleteFile(uploadedFile);
        }
    }


    @Test
    @Category(IntegrationTest.class)
    public void uploadFileWithCreatedAndModifiedDatesSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        BoxFile uploadedFile = null;
        try {
            Date created = new Date(1415318114);
            Date modified = new Date(1315318114);
            final String fileContent = "Test file";
            InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
            FileUploadParams params = new FileUploadParams()
                    .setName("[uploadFileWithCreatedAndModifiedDatesSucceeds] Test File.txt").setContent(stream)
                    .setModified(modified).setCreated(created);
            BoxFile.Info info = rootFolder.uploadFile(params);
            uploadedFile = info.getResource();

            assertThat(dateFormat.format(info.getContentCreatedAt()), is(equalTo(dateFormat.format(created))));
            assertThat(dateFormat.format(info.getContentModifiedAt()), is(equalTo(dateFormat.format(modified))));
            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedFile.getID()))));
        } finally {
            this.deleteFile(uploadedFile);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateFolderInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        final String originalName = "[updateFolderInfoSucceeds] Child Folder";
        final String updatedName = "[updateFolderInfoSucceeds] Updated Child Folder";
        BoxFolder childFolder = null;

        try {
            BoxFolder.Info info = rootFolder.createFolder(originalName);
            childFolder = info.getResource();
            info.setName(updatedName);
            childFolder.updateInfo(info);
            assertThat(info.getName(), equalTo(updatedName));
        } finally {
            this.deleteFolder(childFolder);
            assertThat(rootFolder,
                    not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void copyFolderToSameDestinationWithNewNameSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        final String originalName = "[copyFolderToSameDestinationWithNewNameSucceeds] Child Folder";
        final String newName = "[copyFolderToSameDestinationWithNewNameSucceeds] New Child Folder";
        BoxFolder originalFolder = null;
        BoxFolder copiedFolder = null;
        try {
            originalFolder = rootFolder.createFolder(originalName).getResource();
            BoxFolder.Info copiedFolderInfo = originalFolder.copy(rootFolder, newName);
            copiedFolder = copiedFolderInfo.getResource();

            assertThat(copiedFolderInfo.getName(), is(equalTo(newName)));
            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(originalFolder.getID()))));
            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(copiedFolder.getID()))));
        } finally {
            this.deleteFolder(originalFolder);
            this.deleteFolder(copiedFolder);
            assertThat(rootFolder,
                    not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(originalFolder.getID())))));
            assertThat(rootFolder,
                    not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(copiedFolder.getID())))));
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void moveFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        final String child1Name = "[moveFolderSucceeds] Child Folder";
        final String child2Name = "[moveFolderSucceeds] Child Folder 2";
        BoxFolder childFolder1 = null;

        try {
            childFolder1 = rootFolder.createFolder(child1Name).getResource();
            BoxFolder childFolder2 = rootFolder.createFolder(child2Name).getResource();

            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder1.getID()))));
            assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder2.getID()))));

            childFolder2.move(childFolder1);

            assertThat(childFolder1,
                    hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder2.getID()))));
            assertThat(rootFolder,
                    not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder2.getID())))));
        } finally {
            this.deleteFolder(childFolder1);
            assertThat(rootFolder,
                    not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder1.getID())))));
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void renameFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        final String originalName = "[renameFolderSucceeds] Original Name";
        final String newName = "[renameFolderSucceeds] New Name";
        BoxFolder childFolder = null;

        try {
            childFolder = rootFolder.createFolder(originalName).getResource();

            childFolder.rename(newName);

            BoxFolder.Info childFolderInfo = childFolder.getInfo();
            assertThat(childFolderInfo.getName(), is(equalTo(newName)));
        } finally {
            this.deleteFolder(childFolder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCollaboratorSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[addCollaborationToFolderSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;
        BoxFolder folder = null;

        try {
            folder = rootFolder.createFolder(folderName).getResource();

            BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);
            BoxUser.Info accessibleBy = (BoxUser.Info) collabInfo.getAccessibleBy();

            assertThat(accessibleBy.getLogin(), is(equalTo(collaboratorLogin)));
            assertThat(collabInfo.getRole(), is(equalTo(collaboratorRole)));
        } finally {
            this.deleteFolder(folder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCollaborationsWithAttributesSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[getCollaborationsSucceeds] Test Folder";
        String collaboratorLogin = "karthik2001123@yahoo.com";
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;
        BoxFolder folder = null;
        BoxCollaborationAllowlist allowGmail = null;
        BoxCollaborationAllowlist allowYahoo = null;

        try {
            folder = rootFolder.createFolder(folderName).getResource();
            allowGmail = BoxCollaborationAllowlist.create(api, "gmail.com", INBOUND).getResource();
            allowYahoo = BoxCollaborationAllowlist.create(api, "yahoo.com", INBOUND).getResource();
            BoxCollaboration.Info collabInfo =
                    folder.collaborate(collaboratorLogin, collaboratorRole, false, true);
            String collabID = collabInfo.getID();

            collaboratorRole = BoxCollaboration.Role.VIEWER;
            collaboratorLogin = "davidsmaynard@gmail.com";
            BoxCollaboration.Info collabInfo2 =
                    folder.collaborate(collaboratorLogin, collaboratorRole, false, true);

            Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();

            assertThat(collaborations, hasSize(2));
            assertThat(collaborations,
                    hasItem(Matchers.<BoxCollaboration.Info>hasProperty("ID", equalTo(collabID))));
            assertThat(collaborations,
                    hasItem(Matchers.<BoxCollaboration.Info>hasProperty("ID", equalTo(collabInfo2.getID()))));
        } finally {
            this.deleteFolder(folder);
            this.deleteCollaborationAllowList(allowGmail);
            this.deleteCollaborationAllowList(allowYahoo);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCollaborationsHasCorrectCollaborations() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[getCollaborationsHasCorrectCollaborations] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;
        BoxFolder folder = null;
        BoxCollaborationAllowlist allowGmail = null;

        try {
            folder = rootFolder.createFolder(folderName).getResource();
            allowGmail = BoxCollaborationAllowlist.create(api, "gmail.com", INBOUND).getResource();
            BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);
            String collabID = collabInfo.getID();

            Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();

            assertThat(collaborations, hasSize(1));
            assertThat(collaborations,
                    hasItem(Matchers.<BoxCollaboration.Info>hasProperty("ID", equalTo(collabID))));
        } finally {
            this.deleteFolder(folder);
            this.deleteCollaborationAllowList(allowGmail);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void setFolderUploadEmailSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[setFolderUploadEmailSucceeds] Test Folder";
        BoxFolder folder = null;

        try {
            BoxUploadEmail uploadEmail = new BoxUploadEmail();
            uploadEmail.setAccess(BoxUploadEmail.Access.OPEN);

            folder = rootFolder.createFolder(folderName).getResource();
            BoxFolder.Info info = folder.new Info();
            info.setUploadEmail(uploadEmail);
            folder.updateInfo(info);

            BoxUploadEmail updatedEmailInfo = info.getUploadEmail();
            assertThat(updatedEmailInfo.getEmail(), not(isEmptyOrNullString()));
            assertThat(updatedEmailInfo.getAccess(), is(equalTo(BoxUploadEmail.Access.OPEN)));

            info.setUploadEmail(null);
            uploadEmail = info.getUploadEmail();
            assertThat(uploadEmail, is(nullValue()));
        } finally {
            this.deleteFolder(folder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getSharedItemAndItsChildrenSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[getSharedItemAndItsChildrenSucceeds] Test Folder";
        String childFolderName = "[getSharedItemAndItsChildrenSucceeds] Child Folder";
        BoxFolder folder = null;

        try {
            folder = rootFolder.createFolder(folderName).getResource();
            BoxFolder childFolder = folder.createFolder(childFolderName).getResource();
            BoxSharedLink sharedLink = folder.createSharedLink(BoxSharedLink.Access.OPEN, null, null);

            BoxFolder.Info sharedItem = (BoxFolder.Info) BoxItem.getSharedItem(api, sharedLink.getURL());

            assertThat(sharedItem.getID(), is(equalTo(folder.getID())));
            assertThat(sharedItem.getResource(),
                    hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID()))));
        } finally {
            this.deleteFolder(folder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink("[createWebLinkSucceeds] Test Web Link",
                new URL("https://api.box.com"), "[createWebLinkSucceeds] Test Web Link").getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkWithNoNameSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);

        BoxWebLink createdWebLink = rootFolder
                .createWebLink(new URL("https://api.box.com"), "[createWebLinkSucceeds] Test Web Link")
                .getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkWithNoDescriptionSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);

        BoxWebLink createdWebLink = rootFolder
                .createWebLink("[createWebLinkSucceeds] Test Web Link", new URL("https://api.box.com"))
                .getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebLinkWithNoNameOrDescriptionSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink(new URL("https://api.box.com")).getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }


    @Test
    @Category(IntegrationTest.class)
    public void createPropertiesMetadataSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        final String key = "/testKey";
        final String value = "testValue";
        final String folderName = "[createPropertiesMetadataSucceeds] Metadata Folder "
                + Calendar.getInstance().getTimeInMillis();
        BoxFolder folder = null;

        try {
            Metadata md = new Metadata();
            md.add(key, value);
            folder = rootFolder.createFolder(folderName).getResource();
            Metadata createdMD = folder.createMetadata(md);

            assertThat(createdMD.getString(key), is(equalTo(value)));
        } finally {
            this.deleteFolder(folder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getMetadataOnInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        final String key = "/testKey";
        final String value = "testValue";
        final String folderName = "[createPropertiesMetadataSucceeds] Metadata Folder "
                + Calendar.getInstance().getTimeInMillis();
        BoxFolder folder = null;

        try {
            Metadata md = new Metadata();
            md.add(key, value);
            folder = rootFolder.createFolder(folderName).getResource();
            folder.createMetadata(md);
            Metadata actualMD = folder.getInfo("metadata.global.properties")
                    .getMetadata("properties", "global");
            assertNotNull("Metadata should not be null for this folder", actualMD);
        } catch (BoxAPIException e) {
            fail("Metadata should have been present on this folder");
        } finally {
            this.deleteFolder(folder);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void deletePropertiesMetadataSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        final String key = "/testKey";
        final String value = "testValue";
        final String folderName = "[createPropertiesMetadataSucceeds] Metadata Folder "
                + Calendar.getInstance().getTimeInMillis();

        Metadata md = new Metadata();
        md.add(key, value);
        BoxFolder folder = rootFolder.createFolder(folderName).getResource();
        folder.createMetadata(md);
        folder.deleteMetadata();

        try {
            folder.getMetadata();
            fail("Metadata was not removed");
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
        BoxFolder rootFolder = getUniqueFolder(api);
        BoxFolder folder = null;
        try {
            folder = rootFolder.createFolder("[sharedLinkInfoHasEffectiveAccess] Test Folder").getResource();
            BoxSharedLink sharedLink = folder.createSharedLink(BoxSharedLink.Access.OPEN, null, null);

            assertThat(sharedLink, Matchers.<BoxSharedLink>hasProperty("effectiveAccess"));
            assertThat(sharedLink.getEffectiveAccess(), equalTo(BoxSharedLink.Access.OPEN));
        } finally {
            this.deleteFolder(folder);
        }

    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadSessionAbortFlowSuccess() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);

        String fileName = "Tamme-Lauri_tamm_suvepäeval.jpg";
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        FileInputStream stream = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        stream.read(fileBytes);

        BoxFileUploadSession.Info session = rootFolder.createUploadSession(fileName, fileBytes.length);
        assertNotNull(session.getUploadSessionId());
        assertNotNull(session.getSessionExpiresAt());

        BoxFileUploadSession.Endpoints endpoints = session.getSessionEndpoints();
        assertNotNull(endpoints);
        assertNotNull(endpoints.getUploadPartEndpoint());
        assertNotNull(endpoints.getStatusEndpoint());
        assertNotNull(endpoints.getListPartsEndpoint());
        assertNotNull(endpoints.getCommitEndpoint());
        assertNotNull(endpoints.getAbortEndpoint());

        //Verify the status of the session
        this.getUploadSessionStatus(session.getResource());

        //Verify the delete session
        this.abortUploadSession(session.getResource());
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadLargeFile() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = "Tamme-Lauri_tamm_suvepäeval.jpg";
        BoxFile fileUploaded = null;
        try {
            URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
            String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
            File file = new File(filePath);
            FileInputStream stream = new FileInputStream(file);

            BoxFile.Info info = rootFolder.uploadLargeFile(stream, "large", file.length());
            assertNotNull(info);
            fileUploaded = info.getResource();
        } finally {
            this.deleteFile(fileUploaded);
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void uploadLargeFileWithAttributes() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = "Tamme-Lauri_tamm_suvepäeval.jpg";
        URL fileURL = this.getClass().getResource("/sample-files/" + fileName);
        String filePath = URLDecoder.decode(fileURL.getFile(), "utf-8");
        File file = new File(filePath);
        FileInputStream stream = new FileInputStream(file);
        BoxFile fileUploaded = null;

        try {
            Map<String, String> fileAttributes = new HashMap<>();
            fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

            BoxFile.Info info = rootFolder.uploadLargeFile(stream, "large", file.length(), fileAttributes);
            fileUploaded = info.getResource();
            assertNotNull(fileUploaded);

            assertEquals(1491613088000L, info.getContentModifiedAt().getTime());
        } finally {
            this.deleteFile(fileUploaded);
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testChunkedUploadThrows409() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        final String preflightURL = "/files/content";
        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String getResult = TestConfig.getFixture("BoxException/BoxResponseException409");

        JsonObject idObject = new JsonObject()
                .add("id", "12345");

        JsonObject preflightObject = new JsonObject()
                .add("name", "testfile.txt")
                .add("size", 5)
                .add("parent", idObject);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
                .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getResult)
                        .withStatus(409)));

        try {
            BoxFolder folder = new BoxFolder(this.api, "12345");
            folder.uploadLargeFile(stream, "testfile.txt", 5);
            fail("Preflight check for chunked upload did not fail with 409.");
        } catch (BoxAPIException apiEx) {
            assertEquals(apiEx.getResponseCode(), 409);
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testChunkedUploadWithCorrectPartSizeAndAttributes() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        final String preflightURL = "/files/content";
        final String sessionURL = "/files/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String uploadResult = TestConfig.getFixture("BoxFile/UploadPartOne200");
        String commitResult = TestConfig.getFixture("BoxFile/CommitUploadWithAttributes201");

        JsonObject idObject = new JsonObject()
                .add("id", "12345");

        JsonObject preflightObject = new JsonObject()
                .add("name", "testfile.txt")
                .add("size", 5)
                .add("parent", idObject);

        JsonObject sessionObject = new JsonObject()
                .add("folder_id", "12345")
                .add("file_size", 5)
                .add("file_name", "testfile.txt");

        JsonObject partOne = new JsonObject()
                .add("part_id", "CFEB5BA9")
                .add("offset", 0)
                .add("size", 5);

        JsonArray parts = new JsonArray()
                .add(partOne);

        Map<String, String> fileAttributes = new HashMap<>();
        fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

        JsonObject fileAttributesJson = new JsonObject();
        for (String attrKey : fileAttributes.keySet()) {
            fileAttributesJson.set(attrKey, fileAttributes.get(attrKey));
        }

        JsonObject commitObject = new JsonObject()
                .add("parts", parts)
                .add("attributes", fileAttributesJson);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
                .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
                .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(sessionResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
                .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
                .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
                .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
                .withRequestBody(WireMock.equalTo("aaaaa"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(uploadResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.containing(commitObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(commitResult)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        BoxFile.Info uploadedFile = folder.uploadLargeFile(stream, "testfile.txt", 5, fileAttributes);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
        assertEquals(1491613088000L, uploadedFile.getContentModifiedAt().getTime());
    }

    @Test
    @Category(UnitTest.class)
    public void testChunkedParallelUploadWithCorrectPartSizeAndAttributes() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        final String preflightURL = "/files/content";
        final String sessionURL = "/files/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";
        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String uploadResult = TestConfig.getFixture("BoxFile/UploadPartOne200");
        String commitResult = TestConfig.getFixture("BoxFile/CommitUploadWithAttributes201");

        JsonObject idObject = new JsonObject()
                .add("id", "12345");

        JsonObject preflightObject = new JsonObject()
                .add("name", "testfile.txt")
                .add("size", 5)
                .add("parent", idObject);

        JsonObject sessionObject = new JsonObject()
                .add("folder_id", "12345")
                .add("file_size", 5)
                .add("file_name", "testfile.txt");

        JsonObject partOne = new JsonObject()
                .add("part_id", "CFEB5BA9")
                .add("offset", 0)
                .add("size", 5);

        JsonArray parts = new JsonArray()
                .add(partOne);

        Map<String, String> fileAttributes = new HashMap<>();
        fileAttributes.put("content_modified_at", "2017-04-08T00:58:08Z");

        JsonObject fileAttributesJson = new JsonObject();
        for (String attrKey : fileAttributes.keySet()) {
            fileAttributesJson.set(attrKey, fileAttributes.get(attrKey));
        }

        JsonObject commitObject = new JsonObject()
                .add("parts", parts)
                .add("attributes", fileAttributesJson);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
                .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
                .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(sessionResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
                .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
                .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
                .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
                .withRequestBody(WireMock.equalTo("aaaaa"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(uploadResult)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.containing(commitObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(commitResult)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        BoxFile.Info uploadedFile = folder.uploadLargeFile(stream, "testfile.txt",
                5, 2, 60, TimeUnit.SECONDS, fileAttributes);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
        assertEquals(1491613088000L, uploadedFile.getContentModifiedAt().getTime());
    }

    @Test
    @Category(UnitTest.class)
    public void testChunkedUploadWith500Error() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        String responseBody500 = TestConfig.getFixture("BoxException/BoxResponseException500");
        final String preflightURL = "/files/content";
        final String sessionURL = "/files/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String listPartsURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/parts";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";

        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String partsResult = TestConfig.getFixture("BoxFile/ListUploadedPart200");
        String commitResult = TestConfig.getFixture("BoxFile/CommitUpload201");

        JsonObject idObject = new JsonObject()
                .add("id", "12345");

        JsonObject preflightObject = new JsonObject()
                .add("name", "testfile.txt")
                .add("size", 5)
                .add("parent", idObject);

        JsonObject sessionObject = new JsonObject()
                .add("folder_id", "12345")
                .add("file_size", 5)
                .add("file_name", "testfile.txt");

        JsonObject partOne = new JsonObject()
                .add("part_id", "CFEB5BA9")
                .add("offset", 0)
                .add("size", 5);

        JsonArray parts = new JsonArray()
                .add(partOne);

        JsonObject commitObject = new JsonObject()
                .add("parts", parts);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
                .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
                .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(sessionResult)
                        .withStatus(201)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
                .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
                .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
                .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
                .withRequestBody(WireMock.equalTo("aaaaa"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody500)
                        .withStatus(500)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(listPartsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(partsResult)
                        .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.containing(commitObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(commitResult)
                        .withStatus(201)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        BoxFile.Info uploadedFile = folder.uploadLargeFile(stream, "testfile.txt", 5);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testRetryingChunkedUploadWith500Error() throws IOException, InterruptedException {
        String javaVersion = System.getProperty("java.version");
        Assume.assumeFalse("Test is not run for JDK 7", javaVersion.contains("1.7"));
        String responseBody500 = TestConfig.getFixture("BoxException/BoxResponseException500");
        final String preflightURL = "/files/content";
        final String sessionURL = "/files/upload_sessions";
        final String uploadURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658";
        final String listPartsURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/parts";
        final String commitURL = "/files/upload_sessions/D5E3F8ADA11A38F0A66AD0B64AACA658/commit";

        BoxFileTest.FakeStream stream = new BoxFileTest.FakeStream("aaaaa");

        String sessionResult = TestConfig.getFixture("BoxFile/CreateUploadSession201");
        String uploadResult = TestConfig.getFixture("BoxFile/UploadPartOne200");
        String wrongPartsResult = TestConfig.getFixture("BoxFile/ListUploadedParts200");
        String partsResult = TestConfig.getFixture("BoxFile/ListUploadedPart200");
        String commitResult = TestConfig.getFixture("BoxFile/CommitUpload201");

        JsonObject idObject = new JsonObject()
                .add("id", "12345");

        JsonObject preflightObject = new JsonObject()
                .add("name", "testfile.txt")
                .add("size", 5)
                .add("parent", idObject);

        JsonObject sessionObject = new JsonObject()
                .add("folder_id", "12345")
                .add("file_size", 5)
                .add("file_name", "testfile.txt");

        JsonObject partOne = new JsonObject()
                .add("part_id", "CFEB5BA9")
                .add("offset", 0)
                .add("size", 5);

        JsonArray parts = new JsonArray()
                .add(partOne);

        JsonObject commitObject = new JsonObject()
                .add("parts", parts);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.options(WireMock.urlPathEqualTo(preflightURL))
                .withRequestBody(WireMock.equalToJson(preflightObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(sessionURL))
                .withRequestBody(WireMock.equalToJson(sessionObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(sessionResult)
                        .withStatus(201)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
                .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
                .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
                .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
                .withRequestBody(WireMock.equalTo("aaaaa"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody500)
                        .withStatus(500)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(listPartsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(wrongPartsResult)
                        .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(uploadURL))
                .withHeader("Digest", WireMock.containing("sha=31HjfCaaqU04+T5Te/biAgshQGw="))
                .withHeader("Content-Type", WireMock.containing("application/octet-stream"))
                .withHeader("Content-Range", WireMock.containing("bytes 0-4/5"))
                .withRequestBody(WireMock.equalTo("aaaaa"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(uploadResult)
                        .withStatus(200)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(commitURL))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.containing(commitObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(commitResult)
                        .withStatus(201)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        BoxFile.Info uploadedFile = folder.uploadLargeFile(stream, "testfile.txt", 5);

        assertEquals("1111111", uploadedFile.getID());
        assertEquals("testuser@box.com", uploadedFile.getModifiedBy().getLogin());
        assertEquals("Test User", uploadedFile.getOwnedBy().getName());
        assertEquals("folder", uploadedFile.getParent().getType());
        assertEquals("testfile.txt", uploadedFile.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllRootFolderItemsSucceeds() throws IOException {
        final String rootFolderItemsURL = "/folders/0/items/";
        final String folderURL = "/folders/0";
        final String ownedByUserLogin = "test@user.com";
        final String modifiedByLogin = "test@user.com";
        final String modifiedByName = "Test User";

        String result = TestConfig.getFixture("BoxFolder/GetAllRootFolderItems200");

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

        assertEquals(ownedByUserLogin, rootFolderInfo.getOwnedBy().getLogin());
        assertEquals(modifiedByLogin, rootFolderInfo.getModifiedBy().getLogin());
        assertEquals(modifiedByName, rootFolderInfo.getModifiedBy().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllFolderItemsSucceeds() throws IOException {
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID + "/items/";
        final String firstFolderName = "Example.pdf";

        String result = TestConfig.getFixture("BoxFolder/GetAllFolderItems200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderURL))
                .withQueryParam("limit", WireMock.containing("1000"))
                .withQueryParam("offset", WireMock.containing("0"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxItem.Info firstFolderInfo = folder.iterator().next();

        assertEquals(folderID, firstFolderInfo.getID());
        assertEquals(firstFolderName, firstFolderInfo.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetFolderInfoSucceeds() throws IOException {
        final String folderID = "12345";
        final String folderInfoURL = "/folders/" + folderID;
        final String folderName = "Example Folder";
        final String pathCollectionItemName = "All Files";
        final String createdByLogin = "test@user.com";
        final String modifiedByName = "Test User";
        final String classificationColor = "#00FFFF";
        final String classificationDefinition = "Content that should not be shared outside the company.";
        final String classificationName = "Top Secret";
        List<String> roles = new ArrayList<>();
        roles.add("open");

        String result = TestConfig.getFixture("BoxFolder/GetFolderInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.getInfo();

        assertEquals("folder", info.getType());
        assertEquals(folderID, info.getID());
        assertEquals(folderName, info.getName());
        assertEquals(pathCollectionItemName, info.getPathCollection().get(0).getName());
        assertEquals(createdByLogin, info.getCreatedBy().getLogin());
        assertEquals(modifiedByName, info.getModifiedBy().getName());
        assertEquals(roles, info.getAllowedInviteeRoles());
        assertEquals(roles, info.getAllowedSharedLinkAccessLevels());
        assertTrue(info.getIsExternallyOwned());
        assertEquals(classificationColor, info.getClassification().getColor());
        assertEquals(classificationDefinition, info.getClassification().getDefinition());
        assertEquals(classificationName, info.getClassification().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetRestrictedCollaborationFieldSucceeds() throws IOException {
        final String folderID = "12345";
        final String folderInfoURL = "/folders/" + folderID;

        String result = TestConfig.getFixture("BoxFolder/GetFolderInfoForCollaborationRestriction200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderInfoURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder.Info info = folder.getInfo();

        assertTrue(info.getIsCollaborationRestrictedToEnterprise());
    }

    @Test
    @Category(UnitTest.class)
    public void testSetRestrictedCollaborationFieldSucceeds() throws IOException {
        final String folderID = "12345";
        final String folderInfoURL = "/folders/" + folderID;
        JsonObject jsonObject = new JsonObject()
                .add("is_collaboration_restricted_to_enterprise", true);

        String result = TestConfig.getFixture("BoxFolder/GetFolderInfoForCollaborationRestriction200");

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

        assertTrue(folder.getInfo().getIsCollaborationRestrictedToEnterprise());
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateFolderInfoSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID;
        final String folderName = "New Folder Name";
        final String folderDescription = "Folder Description";

        JsonObject updateFolderObject = new JsonObject()
                .add("name", folderName)
                .add("description", "Folder Description")
                .add("is_password_enabled", true)
                .add("can_download", false)
                .add("can_preview", false);

        String result = TestConfig.getFixture("BoxFolder/UpdateFolderInfo200");

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

        assertEquals(folderID, info.getID());
        assertEquals(folderName, info.getName());
        assertEquals(folderDescription, info.getDescription());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateNewFolderSucceedsAndSendsCorrectJson() throws IOException {
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

        String result = TestConfig.getFixture("BoxFolder/CreateNewFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderURL))
                .withRequestBody(WireMock.equalToJson(createFolderObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder parentFolder = new BoxFolder(this.api, folderID);
        BoxFolder.Info childFolderInfo = parentFolder.createFolder(folderName);

        assertEquals(folderID, childFolderInfo.getID());
        assertEquals(folderName, childFolderInfo.getName());
        assertEquals(parentFolderName, childFolderInfo.getParent().getName());
        assertEquals(ownedByUserName, childFolderInfo.getOwnedBy().getName());
        assertEquals(ownedByUserLogin, childFolderInfo.getOwnedBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testCopyFolderSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID + "/copy";
        final String newParentID = "12345";

        JsonObject parentObject = new JsonObject()
                .add("id", newParentID);

        JsonObject copyObject = new JsonObject()
                .add("parent", parentObject);

        String result = TestConfig.getFixture("BoxFolder/CopyFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderURL))
                .withRequestBody(WireMock.equalToJson(copyObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder destination = new BoxFolder(this.api, newParentID);
        BoxFolder.Info copiedFolderInfo = folder.copy(destination);

        assertEquals(folderID, copiedFolderInfo.getID());
        assertEquals(newParentID, copiedFolderInfo.getParent().getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testMoveFolderSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String moveFolderURL = "/folders/" + folderID;
        final String parentID = "2222";

        JsonObject innerObject = new JsonObject()
                .add("id", parentID);

        JsonObject parentObject = new JsonObject()
                .add("parent", innerObject);

        String result = TestConfig.getFixture("BoxFolder/MoveFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(moveFolderURL))
                .withRequestBody(WireMock.equalToJson(parentObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolder destination = new BoxFolder(this.api, parentID);
        BoxItem.Info folderInfo = folder.move(destination);

        assertEquals(parentID, folderInfo.getParent().getID());
        assertEquals(folderID, folderInfo.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteFolderSendsCorrectJson() {
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
        final String folderID = "12345";
        final String folderURL = "/folders/" + folderID;
        final BoxSharedLink.Access effectiveAccess = BoxSharedLink.Access.OPEN;
        final Boolean isPasswordEnabled = false;
        final BoxSharedLink.Access access = BoxSharedLink.Access.OPEN;

        JsonObject accessObject = new JsonObject()
                .add("access", "open");

        JsonObject sharedLinkObject = new JsonObject()
                .add("shared_link", accessObject);

        String result = TestConfig.getFixture("BoxFolder/CreateSharedLinkForFolder200");

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

        assertEquals(effectiveAccess, info.getSharedLink().getEffectiveAccess());
        assertEquals(isPasswordEnabled, info.getSharedLink().getIsPasswordEnabled());
        assertEquals(access, info.getSharedLink().getAccess());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllFolderCollaborationsSucceeds() throws IOException {
        final String folderID = "3333";
        final String folderCollaborationURL = "/folders/" + folderID + "/collaborations";
        final String collaborationID = "12345";
        final String accessiblyByLogin = "Test User";
        final BoxCollaboration.Role collaborationRole = BoxCollaboration.Role.VIEWER;

        String result = TestConfig.getFixture("BoxFolder/GetAllFolderCollaborations200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderCollaborationURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Collection<BoxCollaboration.Info> collaborations = folder.getCollaborations();
        Iterator<BoxCollaboration.Info> iterator = collaborations.iterator();
        BoxCollaboration.Info collaborationInfo = iterator.next();
        BoxCollaboration.Info collaborationInfo2 = iterator.next();

        assertEquals(collaborationID, collaborationInfo.getID());
        assertEquals(folderID, collaborationInfo.getItem().getID());
        assertEquals(accessiblyByLogin, collaborationInfo.getAccessibleBy().getName());
        assertEquals(collaborationRole, collaborationInfo.getRole());
        assertEquals(BoxCollaborator.CollaboratorType.GROUP, collaborationInfo2.getAccessibleBy().getType());
        assertEquals(BoxCollaborator.GroupType.MANAGED_GROUP,
                collaborationInfo2.getAccessibleBy().getGroupType());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateMetadataOnFolderSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata/global/properties";
        final String metadataID = "12345";
        final String parentID = "folder_12345";

        JsonObject metadataObject = new JsonObject()
                .add("foo", "bar");

        String result = TestConfig.getFixture("BoxFolder/CreateMetadataOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
                .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Metadata metadataInfo = folder.createMetadata(new Metadata().add("/foo", "bar"));
        assertEquals(metadataID, metadataInfo.getID());
        assertEquals(parentID, metadataInfo.getParentID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetMetadataOnFolderSucceds() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata/global/properties";
        final String metadataID = "12345";
        final String parentID = "folder_12345";

        String result = TestConfig.getFixture("BoxFolder/CreateMetadataOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Metadata metadata = folder.getMetadata();

        assertEquals(metadataID, metadata.getID());
        assertEquals(parentID, metadata.getParentID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataSucceeds() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata";
        final String metadataID = "12345";
        final String parentID = "folder_12345";
        final String template = "properties";
        final String scope = "global";

        String result = TestConfig.getFixture("BoxFolder/GetAllMetadataOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
                .withQueryParam("limit", WireMock.containing("100"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<Metadata> metadataList = folder.getAllMetadata().iterator();
        Metadata metadata = metadataList.next();

        assertEquals(metadataID, metadata.getID());
        assertEquals(parentID, metadata.getParentID());
        assertEquals(template, metadata.getTemplateName());
        assertEquals(scope, metadata.getScope());
    }

    @Test
    @Category(UnitTest.class)
    public void testAddMetadataCascadePolicySucceedsSendsCorrectJson() throws IOException {
        final String cascadePolicyURL = "/metadata_cascade_policies";
        final String folderID = "22222";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        JsonObject cascadeObject = new JsonObject()
                .add("folder_id", folderID)
                .add("scope", scope)
                .add("templateKey", templateKey);

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/CreateMetadataCascadePolicies201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(cascadePolicyURL))
                .withRequestBody(WireMock.equalToJson(cascadeObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxMetadataCascadePolicy.Info metadataCascadePolicyInfo = folder.addMetadataCascadePolicy(scope, templateKey);

        assertEquals(folderID, metadataCascadePolicyInfo.getParent().getID());
        assertEquals(scope, metadataCascadePolicyInfo.getScope());
        assertEquals(templateKey, metadataCascadePolicyInfo.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataCascadePoliciesOnFolderSucceeds() throws IOException {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(cascadePoliciesURL))
                .withQueryParam("folder_id", WireMock.equalTo(folderID))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<BoxMetadataCascadePolicy.Info> metadataCascadePolicies = folder
                .getMetadataCascadePolicies().iterator();

        BoxMetadataCascadePolicy.Info firstCascadePolicy = metadataCascadePolicies.next();

        assertEquals(folderID, firstCascadePolicy.getParent().getID());
        assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        assertEquals(scope, firstCascadePolicy.getScope());
        assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataCascadePoliciesOnFolderWithFieldsSucceeds() throws IOException {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

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

        assertEquals(folderID, firstCascadePolicy.getParent().getID());
        assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        assertEquals(scope, firstCascadePolicy.getScope());
        assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllMetadataCascadePoliciesWithEnterpriseIDSucceeds() throws IOException {
        final String folderID = "22222";
        final String cascadePolicyID = "84113349-794d-445c-b93c-d8481b223434";
        final String enterpriseID = "11111";
        final String scope = "enterprise_11111";
        final String templateKey = "testTemplate";
        final int limit = 100;
        final String cascadePoliciesURL = "/metadata_cascade_policies";

        String result = TestConfig.getFixture("BoxMetadataCascadePolicy/GetAllMetadataCascadePolicies200");

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

        assertEquals(folderID, firstCascadePolicy.getParent().getID());
        assertEquals(cascadePolicyID, firstCascadePolicy.getID());
        assertEquals(enterpriseID, firstCascadePolicy.getOwnerEnterprise().getID());
        assertEquals(scope, firstCascadePolicy.getScope());
        assertEquals(templateKey, firstCascadePolicy.getTemplateKey());
    }

    @Test
    @Category(UnitTest.class)
    public void createSharedLinkSucceeds() throws IOException {
        final String folderID = "1111";
        final String password = "test1";

        JsonObject permissionsObject = new JsonObject()
                .add("can_download", true)
                .add("can_preview", true);

        JsonObject innerObject = new JsonObject()
                .add("password", password)
                .add("access", "open")
                .add("permissions", permissionsObject);

        JsonObject sharedLinkObject = new JsonObject()
                .add("shared_link", innerObject);

        String result = TestConfig.getFixture("BoxSharedLink/CreateSharedLink201");

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

        assertTrue(sharedLink.getIsPasswordEnabled());
    }

    @Test
    @Category(UnitTest.class)
    public void testAddClassification() throws IOException {
        final String folderID = "12345";
        final String classificationType = "Public";
        final String metadataURL = "/folders/" + folderID
                + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
                .add("Box__Security__Classification__Key", classificationType);

        String result = TestConfig.getFixture("BoxFolder/CreateClassificationOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
                .withRequestBody(WireMock.equalToJson(metadataObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.addClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateClassification() throws IOException {
        final String folderID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/folders/" + folderID
                + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
                .add("op", "replace")
                .add("path", "/Box__Security__Classification__Key")
                .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
                .add(metadataObject);

        String result = TestConfig.getFixture("BoxFolder/UpdateClassificationOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
                .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json-patch+json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.updateClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test
    @Category(UnitTest.class)
    public void testSetClassification() throws IOException {
        final String folderID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/folders/" + folderID
                + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
                .add("op", "replace")
                .add("path", "/Box__Security__Classification__Key")
                .add("value", "Internal");

        JsonArray metadataArray = new JsonArray()
                .add(metadataObject);

        String result = TestConfig.getFixture("BoxFolder/UpdateClassificationOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
                .willReturn(WireMock.aResponse()
                        .withStatus(409)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
                .withRequestBody(WireMock.equalToJson(metadataArray.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json-patch+json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.setClassification(classificationType);

        assertEquals(classificationType, classification);
    }

    @Test(expected = BoxAPIResponseException.class)
    @Category(UnitTest.class)
    public void testSetClassificationThrowsException() {
        final String folderID = "12345";
        final String classificationType = "Internal";
        final String metadataURL = "/folders/" + folderID
                + "/metadata/enterprise/securityClassification-6VMVochwUWo";
        JsonObject metadataObject = new JsonObject()
                .add("op", "replace")
                .add("path", "/Box__Security__Classification__Key")
                .add("value", "Internal");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
                .willReturn(WireMock.aResponse()
                        .withStatus(403)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.setClassification(classificationType);

        WireMock.verify(1, WireMock.postRequestedFor(WireMock.urlPathEqualTo(metadataURL)));
    }

    @Test
    @Category(UnitTest.class)
    public void testGetClassification() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID
                + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        String result = TestConfig.getFixture("BoxFolder/CreateClassificationOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(metadataURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        String classification = folder.getClassification();

        assertEquals("Public", classification);
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteClassification() {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID
                + "/metadata/enterprise/securityClassification-6VMVochwUWo";

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(metadataURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json-patch+json")
                        .withStatus(204)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        folder.deleteClassification();

        WireMock.verify(1, WireMock.deleteRequestedFor(WireMock.urlPathEqualTo(metadataURL)));
    }

    @Test
    @Category(UnitTest.class)
    public void testUploadFileWithDescriptionSucceeds() throws IOException {
        final String folderID = "12345";
        final String fileURL = "/files/content";
        final String fileContent = "Test file";
        final String fileName = "Test File.txt";
        final String fileDescription = "Test Description";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));

        String result = TestConfig.getFixture("BoxFile/CreateFileWithDescription201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(fileURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json-patch+json")
                        .withBody(result)
                        .withStatus(201)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFile.Info file = folder.uploadFile(stream, fileName, fileDescription);

        assertEquals(fileDescription, file.getDescription());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetFolderItemsWithSort() throws IOException {
        final String folderID = "12345";
        final String sortField = "name";
        final String folderItemsURL = "/folders/" + folderID + "/items/";

        String result = TestConfig.getFixture("BoxFolder/GetFolderItemsWithSort200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderItemsURL))
                .withQueryParam("sort", WireMock.equalTo("name"))
                .withQueryParam("direction", WireMock.equalTo("ASC"))
                .withQueryParam("fields", WireMock.equalTo("name"))
                .withQueryParam("limit", WireMock.equalTo("1000"))
                .withQueryParam("offset", WireMock.equalTo("0"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)
                        .withStatus(200)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        Iterator<BoxItem.Info> itemIterator = folder.getChildren("name",
                BoxFolder.SortDirection.ASC, "name").iterator();
        BoxItem.Info boxItem1 = itemIterator.next();
        assertEquals("Test", boxItem1.getName());
        BoxItem.Info boxItem2 = itemIterator.next();
        assertEquals("Test 2", boxItem2.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetFolderItemsWithOffsetAndLimit() throws IOException {
        final String folderID = "12345";
        final String folderItemsURL = "/folders/" + folderID + "/items/";

        String result = TestConfig.getFixture("BoxFolder/GetFolderItemsWithSort200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderItemsURL))
                .withQueryParam("sort", WireMock.equalTo("name"))
                .withQueryParam("direction", WireMock.equalTo("ASC"))
                .withQueryParam("fields", WireMock.equalTo("name"))
                .withQueryParam("limit", WireMock.equalTo("500"))
                .withQueryParam("offset", WireMock.equalTo("10"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)
                        .withStatus(200)));

        BoxFolder folder = new BoxFolder(this.api, "12345");
        Iterator<BoxItem.Info> itemIterator = folder.getChildren("name",
                BoxFolder.SortDirection.ASC, 10, 500, "name").iterator();
        BoxItem.Info boxItem1 = itemIterator.next();
        assertEquals("Test", boxItem1.getName());
        BoxItem.Info boxItem2 = itemIterator.next();
        assertEquals("Test 2", boxItem2.getName());
    }

    @Test
    @Category(UnitTest.class)
    public void testSetMetadataReturnsCorrectly() throws IOException {
        final String folderID = "12345";
        final String metadataURL = "/folders/" + folderID + "/metadata/enterprise/testtemplate";
        ArrayList<String> secondValueArray = new ArrayList<>();
        secondValueArray.add("first");
        secondValueArray.add("second");
        secondValueArray.add("third");

        String postResult = TestConfig.getFixture("/BoxException/BoxResponseException409");
        String putResult = TestConfig.getFixture("/BoxFolder/UpdateMetadataOnFolder200");

        final String firstValue = "text";
        JsonArray secondValueJson = new JsonArray()
                .add("first")
                .add("second")
                .add("third");
        final int thirdValue = 2;
        final float fourthValue = 1234567890f;
        final double fifthValue = 233333333333333340.0;

        JsonObject firstAttribute = new JsonObject()
                .add("op", "add")
                .add("path", "/test1")
                .add("value", "text");

        JsonObject secondAttribute = new JsonObject()
                .add("op", "add")
                .add("path", "/test2")
                .add("value", secondValueJson);

        JsonObject thirdAttribute = new JsonObject()
                .add("op", "add")
                .add("path", "/test3")
                .add("value", thirdValue);

        JsonObject fourthAttribute = new JsonObject()
                .add("op", "add")
                .add("path", "/test4")
                .add("value", fourthValue);

        JsonObject fifthAttribute = new JsonObject()
                .add("op", "add")
                .add("path", "/test5")
                .add("value", fifthValue);

        JsonArray jsonArray = new JsonArray()
                .add(firstAttribute)
                .add(secondAttribute)
                .add(thirdAttribute)
                .add(fourthAttribute)
                .add(fifthAttribute);

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(metadataURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(postResult)
                        .withStatus(409)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(metadataURL))
                .withRequestBody(WireMock.equalToJson(jsonArray.toString()))
                .withHeader("Content-Type", WireMock.equalTo("application/json-patch+json"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json-patch+json")
                        .withBody(putResult)
                        .withStatus(200)));

        BoxFolder folder = new BoxFolder(this.api, "12345");

        Metadata metadata = new Metadata()
                .add("/test1", firstValue)
                .add("/test2", secondValueArray)
                .add("/test3", thirdValue)
                .add("/test4", fourthValue)
                .add("/test5", fifthValue);

        Metadata metadataValues = folder.setMetadata("testtemplate", "enterprise", metadata);

        assertEquals("folder_12345", metadataValues.getParentID());
        assertEquals("testtemplate", metadataValues.getTemplateName());
        assertEquals("enterprise_11111", metadataValues.getScope());
        assertEquals(firstValue, metadataValues.getString("/test1"));
        assertEquals(secondValueJson, metadataValues.getValue("/test2"));
        assertEquals(thirdValue, metadataValues.getDouble("/test3"), 0);
        assertEquals(fourthValue, metadataValues.getDouble("/test4"), 4);
        assertEquals(fifthValue, metadataValues.getDouble("/test5"), 0);
    }

    @Test(expected = BoxDeserializationException.class)
    public void testDeserializationException() throws IOException {
        final String folderID = "12345";
        final String foldersURL = "/folders/" + folderID;

        String result = TestConfig.getFixture("BoxFolder/GetFolderInfoCausesDeserializationException");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(foldersURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder.Info folderInfo = new BoxFolder(this.api, folderID).getInfo();
        assertEquals("12345", folderInfo.getID());
    }

    private void getUploadSessionStatus(BoxFileUploadSession session) {
        assertNotNull(session);
        BoxFileUploadSession.Info sessionInfo = session.getStatus();
        assertNotNull(sessionInfo.getSessionExpiresAt());
    }

    @Test
    @Category(UnitTest.class)
    public void createFolderLockSucceeds() throws IOException {
        final String folderID = "12345678";
        final String folderLockURL = "/folder_locks";

        JsonObject folderObject = new JsonObject();
        folderObject.add("type", "folder");
        folderObject.add("id", folderID);

        JsonObject lockedOperations = new JsonObject();
        lockedOperations.add("move", true);
        lockedOperations.add("delete", true);

        JsonObject body = new JsonObject();
        body.add("folder", folderObject);
        body.add("locked_operations", lockedOperations);

        String result = TestConfig.getFixture("BoxFolder/CreateFolderLock200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(folderLockURL))
                .withRequestBody(WireMock.equalToJson(body.toString()))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxFolderLock.Info folderLock = folder.lock();

        assertEquals("12345678", folderLock.getID());
        assertEquals("11446498", folderLock.getCreatedBy().getID());
        assertEquals("Contracts", folderLock.getFolder().getName());
    }

    @Test
    @Category(UnitTest.class)
    public void getFolderLocks() throws IOException {
        final String folderID = "12345";
        final String folderLocksURL = "/folder_locks";

        String result = TestConfig.getFixture("BoxFolder/GetFolderLocks200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(folderLocksURL))
                .withQueryParam("folder_id", WireMock.equalTo(folderID))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)
                        .withStatus(200)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        Iterator<BoxFolderLock.Info> lockIterator = folder.getLocks().iterator();
        BoxFolderLock.Info lock = lockIterator.next();

        assertEquals("12345678", lock.getID());
        assertEquals("Contracts", lock.getFolder().getName());
        assertEquals("freeze", lock.getLockType());
        assertTrue(lock.getLockedOperations().get("move"));
    }

    @Test
    @Category(UnitTest.class)
    public void deleteFolderLockSucceeds() {
        final String folderLockID = "12345678";
        final String deleteFolderLockURL = "/folder_locks/" + folderLockID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteFolderLockURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxFolderLock folderLock = new BoxFolderLock(this.api, folderLockID);
        folderLock.delete();
    }

    private void abortUploadSession(BoxFileUploadSession session) {
        session.abort();

        try {
            session.getStatus();
            fail("Upload session is not deleted");
        } catch (BoxAPIException apiEx) {
            assertEquals(apiEx.getResponseCode(), 404);
        }
    }

    private void deleteFolder(BoxFolder folder) {
        if (folder != null) {
            folder.delete(true);
        }
    }


    private void deleteFile(BoxFile file) {
        if (file != null) {
            file.delete();
        }
    }

    private void deleteCollaborationAllowList(BoxCollaborationAllowlist collaborationAllowList) {
        if (collaborationAllowList != null) {
            collaborationAllowList.delete();
        }
    }
}
