package com.box.sdk;

import static com.box.sdk.BoxSharedLink.Access.OPEN;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static java.util.Calendar.OCTOBER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import com.box.sdk.sharedlink.BoxSharedLinkWithoutPermissionsRequest;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxWebLink} related unit tests.
 */
public class BoxWebLinkTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void testCreateWebLinkSucceedsAndSendsCorrectJson() throws IOException {
        final String folderID = "12345";
        final String webLinkURL = "/2.0/web_links";
        final String urlToLink = "https://example.com";
        final String webLinkID = "12345";
        final String webLinkName = "example.com";

        String result = TestUtils.getFixture("BoxWebLink/CreateWebLinkOnFolder201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(webLinkURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
        final String webLinkURL = "/2.0/web_links/" + webLinkID;

        String result = TestUtils.getFixture("BoxWebLink/GetWebLinkOnFolder200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(webLinkURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
        final String webLinkURL = "/2.0/web_links/" + webLinkID;

        JsonObject updatedObject = new JsonObject()
            .add("name", newName)
            .add("url", newURL);

        String result = TestUtils.getFixture("BoxWebLink/UpdateWebLinkOnFolder200");

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(webLinkURL))
            .withRequestBody(WireMock.containing(updatedObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
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
    public void createsASharedLink() {
        //given
        BoxWebLink webLink = new BoxWebLink(this.api, "12345");
        this.api.setRequestInterceptor(
            request -> {
                //then
                JsonObject responseJson = Json.parse(request.bodyToString()).asObject();
                JsonObject sharedLinkJson = responseJson.get("shared_link").asObject();
                assertThat(sharedLinkJson.get("vanity_name").asString(), is("myCustomName"));
                assertThat(sharedLinkJson.get("password").asString(), is("my-secret-password"));
                assertThat(sharedLinkJson.get("access").asString(), is("open"));
                assertThat(sharedLinkJson.get("unshared_at").asString(), is("2021-10-08T07:53:45Z"));
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        );

        //when
        GregorianCalendar unsharedDate = new GregorianCalendar(2021, OCTOBER, 8, 7, 53, 45);
        unsharedDate.setTimeZone(TimeZone.getTimeZone("utc"));
        BoxSharedLinkWithoutPermissionsRequest sharedLinkRequest = new BoxSharedLinkWithoutPermissionsRequest()
            .access(OPEN)
            .password("my-secret-password")
            .unsharedDate(unsharedDate.getTime())
            .vanityName("myCustomName");
        webLink.createSharedLink(sharedLinkRequest);
    }
}
