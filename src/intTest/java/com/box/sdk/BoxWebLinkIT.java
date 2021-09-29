package com.box.sdk;

import static com.box.sdk.BoxSharedLink.Access.OPEN;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.net.MalformedURLException;
import java.net.URL;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * {@link BoxWebLink} related integration tests.
 */
public class BoxWebLinkIT {

    @BeforeClass
    public static void beforeClass() throws Exception {
        setupUniqeFolder();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        removeUniqueFolder();
    }

    @Test
    public void copyWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String originalFileName = "[copyWebLinkSucceeds] Original WebLink";
        String newFileName = "[copyWebLinkSucceeds] New WebLink";
        URL url = new URL("https://api.box.com");
        String description = "[copyWebLinkSucceeds] Original WebLink";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(originalFileName, url, description).getResource();
        BoxWebLink.Info uploadedWebLinkInfo = uploadedWebLink.getInfo();

        BoxWebLink.Info copiedWebLinkInfo = uploadedWebLink.copy(rootFolder, newFileName);
        BoxWebLink copiedWebLink = copiedWebLinkInfo.getResource();

        assertThat(copiedWebLinkInfo.getLinkURL(), equalTo(uploadedWebLinkInfo.getLinkURL()));
        assertThat(copiedWebLinkInfo.getDescription(), equalTo(uploadedWebLinkInfo.getDescription()));

        uploadedWebLink.delete();
        copiedWebLink.delete();
    }

    @Test
    public void moveWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = "[moveWebLinkSucceeds] Test WebLink";
        URL url = new URL("https://api.box.com");
        String description = "[moveWebLinkSucceeds] Test WebLink";
        String folderName = "[moveWebLinkSucceeds] Destination Folder";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(fileName, url, description).getResource();

        BoxFolder destinationFolder = rootFolder.createFolder(folderName).getResource();
        uploadedWebLink.move(destinationFolder);

        assertThat(destinationFolder,
            hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedWebLink.getID()))));

        uploadedWebLink.delete();
        destinationFolder.delete(false);
    }

    @Test
    public void getInfoWithOnlyTheURLField() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = "[getInfoWithOnlyTheURLField] Test WebLink";
        URL url = new URL("https://api.box.com");
        String description = "[getInfoWithOnlyTheURLField] Test WebLink";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(fileName, url, description).getResource();
        BoxWebLink.Info uploadedLinkInfo = uploadedWebLink.getInfo("url");

        assertThat(uploadedLinkInfo.getLinkURL(), is(equalTo(url)));
        assertThat(uploadedLinkInfo.getDescription(), is(nullValue()));

        uploadedLinkInfo.getResource().delete();
    }

    @Test
    public void getInfoWithAllFields() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String fileName = "[getInfoWithAllFields] Test WebLink";
        URL url = new URL("https://api.box.com");
        String description = "[getInfoWithAllFields] Test WebLink";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(fileName, url, description).getResource();
        BoxWebLink.Info uploadedLinkInfo = uploadedWebLink.getInfo(BoxWebLink.ALL_FIELDS);

        assertThat(uploadedLinkInfo.getName(), is(equalTo(fileName)));
        assertThat(uploadedLinkInfo.getLinkURL(), is(equalTo(url)));
        assertThat(uploadedLinkInfo.getDescription(), is(equalTo(description)));

        uploadedLinkInfo.getResource().delete();
    }

    @Test
    public void updateLinkWithSpecialCharsInNameSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String originalFileName = "[updateLinkWithSpecialCharsInNameSucceeds] abc\";def";
        URL url = new URL("https://api.box.com");
        String description = "[updateLinkWithSpecialCharsInNameSucceeds] Test WebLink";

        BoxWebLink.Info uploadedWebLinkInfo = rootFolder.createWebLink(originalFileName, url, description);

        assertThat(uploadedWebLinkInfo.getName(), is(equalTo(originalFileName)));

        uploadedWebLinkInfo.getResource().delete();
    }

    @Test
    public void updateLinkInfoSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String originalFileName = "[updateLinkInfoSucceeds] Original Name";
        String newFileName = "[updateLinkInfoSucceeds] New Name";
        URL url = new URL("https://api.box.com");
        String description = "[updateLinkWithSpecialCharsInNameSucceeds] Test WebLink";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(originalFileName, url, description).getResource();

        BoxWebLink.Info newInfo = uploadedWebLink.new Info();
        newInfo.setName(newFileName);
        uploadedWebLink.updateInfo(newInfo);

        assertThat(newInfo.getName(), is(equalTo(newFileName)));

        uploadedWebLink.delete();
    }

    @Test
    public void renameWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String originalFileName = "[updateLinkInfoSucceeds] Original Name";
        String newFileName = "[updateLinkInfoSucceeds] New Name";
        URL url = new URL("https://api.box.com");
        String description = "[updateLinkWithSpecialCharsInNameSucceeds] Test WebLink";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(originalFileName, url, description).getResource();

        uploadedWebLink.rename(newFileName);
        BoxWebLink.Info newInfo = uploadedWebLink.getInfo();

        assertThat(newInfo.getName(), is(equalTo(newFileName)));

        uploadedWebLink.delete();
    }

    @Test
    @Ignore("Cannot create shared link on a WebLink this way. API returns 400 error.")
    public void setsVanityUrlOnASharedLink() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String webLinkName = "createSharedLink";
        URL url = new URL("https://api.box.com");
        String description = "[createSharedLink] Test WebLink";
        BoxWebLink webLink = null;

        try {
            webLink = rootFolder.createWebLink(webLinkName, url, description).getResource();
            BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
            permissions.setCanDownload(true);
            permissions.setCanPreview(true);
            BoxSharedLink sharedLink = new BoxSharedLink();
            sharedLink.setAccess(OPEN);
            sharedLink.setPermissions(permissions);
            sharedLink.setVanityName("myCustomName");
            BoxSharedLink linkWithVanityName = webLink.createSharedLink(sharedLink);

            assertThat(linkWithVanityName.getVanityName(), is("myCustomName"));
            assertThat(webLink.getInfo().getSharedLink().getVanityName(), is("myCustomName"));
            assertThat(webLink.getInfo().getSharedLink().getVanityURL(), endsWith("myCustomName"));
        } finally {
            if (webLink != null) {
                webLink.delete();
            }
        }
    }
}
