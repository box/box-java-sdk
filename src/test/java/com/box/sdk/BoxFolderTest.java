package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.skyscreamer.jsonassert.JSONCompareMode.*;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoxFolderTest {
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    @Category(UnitTest.class)
    public void foldersWithSameIDAreEqual() {
        BoxAPIConnection api = new BoxAPIConnection("");
        BoxFolder folder1 = new BoxFolder(api, "1");
        BoxFolder folder2 = new BoxFolder(api, "1");

        assertThat(folder1, equalTo(folder2));
    }

    @Test
    @Category(UnitTest.class)
    public void createFolderSendsRequestWithRequiredFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String parentFolderID = rootFolder.getID();
        String createdFolderName = "[createFolderSendsRequestWithRequiredFields] Child Folder";

        stubFor(post(urlMatching("/folders"))
            .withRequestBody(equalToJson("{ \"name\": \"" + createdFolderName + "\", \"parent\": {\"id\": \""
                + parentFolderID + "\"} }", LENIENT))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\": \"0\"}")));

        BoxFolder childFolder = rootFolder.createFolder(createdFolderName);
    }

    @Test
    @Category(IntegrationTest.class)
    public void creatingAndDeletingFolderSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder childFolder = rootFolder.createFolder("[creatingAndDeletingFolderSucceeds] Child Folder");

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

        BoxFolder childFolder = rootFolder.createFolder(expectedName);
        BoxFolder.Info info = childFolder.getInfo();

        String actualName = info.getName();
        String actualCreatedByID = info.getCreatedBy().getID();
        String actualParentFolderID = info.getParent().getID();
        String actualParentFolderName = info.getParent().getName();
        List<BoxFolder> actualPathCollection = info.getPathCollection();

        assertThat(expectedName, equalTo(actualName));
        assertThat(expectedCreatedByID, equalTo(actualCreatedByID));
        assertThat(expectedParentFolderID, equalTo(actualParentFolderID));
        assertThat(expectedParentFolderName, equalTo(actualParentFolderName));
        assertThat(actualPathCollection, hasItem(rootFolder));

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
    public void uploadFileSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        final String fileContent = "Test file";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        BoxFile uploadedFile = rootFolder.uploadFile(stream, "Test File.txt");

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

        Date created = new Date(1415318114);
        Date modified = new Date(1315318114);
        final String fileContent = "Test file";
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        FileUploadParams params = new FileUploadParams().setName("Test File.txt").setContent(stream)
            .setModified(modified).setCreated(created);
        BoxFile uploadedFile = rootFolder.uploadFile(params);
        BoxFile.Info info = uploadedFile.getInfo();

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
        BoxFolder childFolder = rootFolder.createFolder(originalName);
        BoxFolder.Info info = childFolder.getInfo();
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
        BoxFolder originalFolder = rootFolder.createFolder(originalName);
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
        BoxFolder childFolder1 = rootFolder.createFolder(child1Name);
        BoxFolder childFolder2 = rootFolder.createFolder(child2Name);

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
        BoxFolder childFolder = rootFolder.createFolder(originalName);
        childFolder.rename(newName);

        BoxFolder.Info childFolderInfo = childFolder.getInfo();
        assertThat(childFolderInfo.getName(), is(equalTo(newName)));

        childFolder.delete(false);
        assertThat(rootFolder, not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
    }

    @Test
    @Category(IntegrationTest.class)
    public void addCollaboratorSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[addCollaborationToFolderSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName);

        BoxCollaboration.Info collabInfo = folder.collaborate(collaboratorLogin, collaboratorRole);
        BoxUser.Info accessibleBy = (BoxUser.Info) collabInfo.getAccessibleBy();

        assertThat(accessibleBy.getLogin(), is(equalTo(collaboratorLogin)));
        assertThat(collabInfo.getRole(), is(equalTo(collaboratorRole)));

        folder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCollaborationsHasCorrectCollaborations() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String folderName = "[getCollaborationsSucceeds] Test Folder";
        String collaboratorLogin = TestConfig.getCollaborator();
        BoxCollaboration.Role collaboratorRole = BoxCollaboration.Role.CO_OWNER;

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        BoxFolder folder = rootFolder.createFolder(folderName);
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
        BoxFolder folder = rootFolder.createFolder(folderName);
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
}
