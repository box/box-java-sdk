package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.net.URL;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxWebLink} related unit tests.
 */
public class BoxWebLinkTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private final BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testCreateWebLinkSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String webLinkURL = "/web_links";
        final String urlToLink = "https://example.com";
        final String webLinkID = "12345";
        final String webLinkName = "example.com";

        String result = TestConfig.getFixture("BoxWebLink/CreateWebLinkOnFolder201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(webLinkURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        URL url = new URL(urlToLink);
        BoxWebLink.Info webLink = folder.createWebLink("Link to Example", url, "This goes to an example page");

        assertEquals(webLinkID, webLink.getID());
        assertEquals(webLinkName, webLink.getName());
        assertEquals(webLinkID, webLink.getID());
        assertEquals(urlToLink, webLink.getLinkURL().toString());
    }

    @Test
    public void testGetWebLinkSucceeds() throws IOException {
        final String webLinkID = "12345";
        final String linkURL = "https://example.com";
        final String createdByName = "Test User";
        final String parentName = "Example Folder";
        final String modifiedByName = "Test User";
        final String ownedByLogin = "test@user.com";
        final String webLinkURL = "/web_links/" + webLinkID;

        String result = TestConfig.getFixture("BoxWebLink/GetWebLinkOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(webLinkURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxWebLink webLink = new BoxWebLink(this.api, webLinkID);
        BoxWebLink.Info webLinkInfo = webLink.getInfo();

        assertEquals(webLinkID, webLinkInfo.getID());
        assertEquals(new URL(linkURL), webLinkInfo.getLinkURL());
        assertEquals(createdByName, webLinkInfo.getCreatedBy().getName());
        assertEquals(parentName, webLinkInfo.getParent().getName());
        assertEquals(modifiedByName, webLinkInfo.getModifiedBy().getName());
        assertEquals(ownedByLogin, webLinkInfo.getOwnedBy().getLogin());
    }

    @Test
    public void testUpdateWebLinkSucceedsAndSendsCorrectJson() throws IOException {
        final String newName = "example.com";
        final String webLinkID = "12345";
        final String newURL = "https://example.com";
        final String webLinkURL = "/web_links/" + webLinkID;

        JsonObject updatedObject = new JsonObject()
            .add("name", newName)
            .add("url", newURL);

        String result = TestConfig.getFixture("BoxWebLink/UpdateWebLinkOnFolder200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(webLinkURL))
            .withRequestBody(WireMock.containing(updatedObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxWebLink webLink = new BoxWebLink(this.api, webLinkID);
        BoxWebLink.Info webLinkInfo = webLink.new Info();
        webLinkInfo.setName(newName);
        webLinkInfo.addPendingChange("url", newURL);
        webLink.updateInfo(webLinkInfo);

        assertEquals(newName, webLinkInfo.getName());
        assertEquals(new URL(newURL), webLinkInfo.getLinkURL());
    }

    @Test
    public void testDeleteWebLinkSucceeds() {
        final String webLinkID = "12345";
        final String webLinkURL = "/web_links/" + webLinkID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(webLinkURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(204)));

        BoxWebLink webLink = new BoxWebLink(this.api, webLinkID);
        webLink.delete();
    }

    @Test
    public void createSharedLinkSucceeds() throws IOException {
        final String webLinkID = "1111";
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

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo("/web_links/" + webLinkID))
            .withRequestBody(WireMock.equalToJson(sharedLinkObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxWebLink webLink = new BoxWebLink(this.api, webLinkID);
        BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();

        permissions.setCanDownload(true);
        permissions.setCanPreview(true);
        BoxSharedLink sharedLink = webLink.createSharedLink(BoxSharedLink.Access.OPEN, null, permissions,
            password);

        assertTrue(sharedLink.getIsPasswordEnabled());
    }

    @Test
    public void setsVanityUrlOnASharedLink() {
        //given
        this.api.setRequestInterceptor(
            new RequestInterceptor() {
                @Override
                public BoxAPIResponse onRequest(BoxAPIRequest request) {
                    //then
                    JsonObject responseJson = Json.parse(request.bodyToString()).asObject();
                    JsonObject sharedLinkJson = responseJson.get("shared_link").asObject();
                    assertThat(sharedLinkJson.get("vanity_name").asString(), is("myCustomName"));
                    return new BoxJSONResponse() {
                        @Override
                        public String getJSON() {
                            return "{}";
                        }
                    };
                }
            }
        );
        BoxSharedLink sharedLink = new BoxSharedLink();
        sharedLink.setVanityName("myCustomName");

        //when
        BoxWebLink webLink = new BoxWebLink(this.api, "12345");
        webLink.createSharedLink(sharedLink);
    }
}
