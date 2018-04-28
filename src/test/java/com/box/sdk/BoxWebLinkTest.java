package com.box.sdk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxWebLink} related tests.
 */
public class BoxWebLinkTest {

    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

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

    @Test
    @Category(UnitTest.class)
    public void testCreateWebLinkSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String folderID = "12345";
        final String webLinkURL = "/web_links";
        final String urlToLink = "https://example.com";
        final String webLinkID = "12345";
        final String webLinkName = "example.com";

        JsonObject innerObject = new JsonObject()
                .add("id", folderID);

        JsonObject watermarkObject = new JsonObject()
                .add("url", "https//example.com")
                .add("parent", innerObject);

        result = TestConfig.getFixture("BoxWebLink/CreateWebLinkOnFolder200");

        this.wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(webLinkURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxFolder folder = new BoxFolder(api, folderID);
        URL url = new URL(urlToLink);
        BoxWebLink.Info webLink = folder.createWebLink("Link to Example", url, "This goes to an example page");

        Assert.assertEquals(webLinkID, webLink.getID());
        Assert.assertEquals(webLinkName, webLink.getName());
        Assert.assertEquals(webLinkID, webLink.getID());
        Assert.assertEquals(urlToLink, webLink.getLinkURL().toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetWebLinkSucceeds() throws IOException {
        String result = "";
        final String webLinkID = "12345";
        final String linkURL = "https://example.com";
        final String createdByName = "Test User";
        final String parentName = "Example Folder";
        final String modifiedByName = "Test User";
        final String ownedByLogin = "test@user.com";
        final String webLinkURL = "/web_links/" + webLinkID;

        result = TestConfig.getFixture("BoxWebLink/GetWebLinkOnFolder200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(webLinkURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxWebLink webLink = new BoxWebLink(api, webLinkID);
        BoxWebLink.Info webLinkInfo = webLink.getInfo();

        Assert.assertEquals(webLinkID, webLinkInfo.getID());
        Assert.assertEquals(new URL(linkURL), webLinkInfo.getLinkURL());
        Assert.assertEquals(createdByName, webLinkInfo.getCreatedBy().getName());
        Assert.assertEquals(parentName, webLinkInfo.getParent().getName());
        Assert.assertEquals(modifiedByName, webLinkInfo.getModifiedBy().getName());
        Assert.assertEquals(ownedByLogin, webLinkInfo.getOwnedBy().getLogin());
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateWebLinkSucceedsAndSendsCorrectJson() throws IOException {
        String result = "";
        final String newName = "example.com";
        final String webLinkID = "12345";
        final String newURL = "https://example.com";
        final String webLinkURL = "/web_links/" + webLinkID;

        JsonObject updatedObject = new JsonObject()
                .add("name", newName)
                .add("url", newURL);

        result = TestConfig.getFixture("BoxWebLink/UpdateWebLinkOnFolder200");

        this.wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(webLinkURL))
           .withRequestBody(WireMock.containing(updatedObject.toString()))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxWebLink webLink = new BoxWebLink(api, webLinkID);
        BoxWebLink.Info webLinkInfo = webLink.new Info();
        webLinkInfo.addPendingChange("name", newName);
        webLinkInfo.addPendingChange("url", newURL);
        webLink.updateInfo(webLinkInfo);

        Assert.assertEquals(newName, webLinkInfo.getName());
        Assert.assertEquals(new URL(newURL), webLinkInfo.getLinkURL());
    }

    @Test
    @Category(UnitTest.class)
    public void testDeleteWebLinkSucceeds() throws IOException {
        final String webLinkID = "12345";
        final String webLinkURL = "/web_links/" + webLinkID;

        this.wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(webLinkURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withStatus(204)));

        BoxWebLink webLink = new BoxWebLink(api, webLinkID);
        webLink.delete();
    }
}
