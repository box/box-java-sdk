package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxWebLinkTest {

    @Test
    @Category(UnitTest.class)
    public void setCollectionSendsCorrectRequest() {
        final String collectionID = "123";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("https://api.box.com/2.0/");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                assertEquals("https://api.box.com/2.0/web_links/0", request.getUrl().toString());
                Scanner body = new Scanner(request.getBody());
                assertEquals("{\"collections\":[{\"id\":\"123\"}]}", body.next());
                body.close();
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        BoxWebLink weblink = new BoxWebLink(api, "0");
        weblink.setCollections(collectionID);
    }

    @Test
    @Category(IntegrationTest.class)
    public void copyWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
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
    @Category(IntegrationTest.class)
    public void moveWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
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
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheURLField() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
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
    @Category(IntegrationTest.class)
    public void getInfoWithAllFields() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
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
    @Category(IntegrationTest.class)
    public void updateLinkWithSpecialCharsInNameSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String originalFileName = "[updateLinkWithSpecialCharsInNameSucceeds] abc\";def";
        URL url = new URL("https://api.box.com");
        String description = "[updateLinkWithSpecialCharsInNameSucceeds] Test WebLink";

        BoxWebLink.Info uploadedWebLinkInfo = rootFolder.createWebLink(originalFileName, url, description);

        assertThat(uploadedWebLinkInfo.getName(), is(equalTo(originalFileName)));

        uploadedWebLinkInfo.getResource().delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateLinkInfoSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
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
}
