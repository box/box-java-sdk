package com.box.sdk;

import static com.box.sdk.BoxCollaborationAllowlist.AllowlistDirection.INBOUND;
import static com.box.sdk.BoxSharedLink.Access.OPEN;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolderWithSomeContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link BoxFolder} related integration tests.
 */
public class BoxFolderIT {

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    @Test
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
    public void createWebLinkWithNoNameOrDescriptionSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);

        BoxWebLink createdWebLink = rootFolder.createWebLink(new URL("https://api.box.com")).getResource();

        assertThat(rootFolder, hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID()))));

        createdWebLink.delete();
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(createdWebLink.getID())))));
    }


    @Test
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
     * Verifies the fix for issue #325.
     */
    @Test
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
    public void setsVanityNameOnASharedLink() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        BoxFolder sharedFolder = null;
        try {
            sharedFolder = rootFolder.createFolder(UUID.randomUUID().toString()).getResource();

            BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
            permissions.setCanDownload(true);
            permissions.setCanPreview(true);
            BoxSharedLink link = new BoxSharedLink();
            link.setAccess(OPEN);
            link.setPermissions(permissions);
            link.setVanityName("myCustomName");
            BoxSharedLink linkWithVanityName = sharedFolder.createSharedLink(link);

            assertThat(linkWithVanityName.getVanityName(), is("myCustomName"));
            assertThat(sharedFolder.getInfo().getSharedLink().getVanityName(), is("myCustomName"));
        } finally {
            this.deleteFolder(sharedFolder);
        }
    }

    private void getUploadSessionStatus(BoxFileUploadSession session) {
        assertNotNull(session);
        BoxFileUploadSession.Info sessionInfo = session.getStatus();
        assertNotNull(sessionInfo.getSessionExpiresAt());
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
