package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collections;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class BoxItemTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
        api.setBaseUploadURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void shouldUseRequestInterceptor() {
        String itemType = "file";
        String itemId = "some_id";
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(request -> {
            if ("https://api.box.com/2.0/shared_items".equals(request.getUrl().toString())) {
                JsonObject body = new JsonObject();
                body.add("type", itemType);
                body.add("id", itemId);
                return new BoxJSONResponse(
                        200,
                        request.getMethod(),
                        request.getUrl().toString(),
                        Collections.emptyMap(),
                        body
                );
            }
            return null;
        });

        BoxItem.Info item = BoxItem.getSharedItem(api, "some_link");
        MatcherAssert.assertThat(item.getType(), CoreMatchers.is(itemType));
        MatcherAssert.assertThat(item.getID(), CoreMatchers.is(itemId));
    }

    @Test
    public void shouldgetSharedItemWithoutPasswordAndUseBaseClient() {
        final String sharedLink = "https://app.box.com/s/kwio6b4ovt1264rnfbyqo1";
        final String expectedSharedLinkHeaderValue = "shared_link=" + sharedLink;
        final String sharedItemsURL = "/2.0/shared_items";

        String result = TestUtils.getFixture("BoxItem/GetSharedItemFile200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(sharedItemsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody(result)));

        BoxItem.Info itemInfo = BoxItem.getSharedItem(api, sharedLink);

        assertEquals("file", itemInfo.getType());

        verify(1, getRequestedFor(
                urlEqualTo("/2.0/shared_items")).withHeader("BoxApi", equalTo(expectedSharedLinkHeaderValue)));
    }

    @Test
    public void shouldgetSharedItemWitPasswordAndUseBaseClient() {
        final String sharedLink = "https://app.box.com/s/kwio6b4ovt1264rnfbyqo1";
        final String password = "letmein";
        final String expectedSharedLinkHeaderValue = "shared_link=" + sharedLink + "&shared_link_password=" + password;
        final String sharedItemsURL = "/2.0/shared_items";

        String result = TestUtils.getFixture("BoxItem/GetSharedItemFile200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(sharedItemsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody(result)));

        BoxItem.Info itemInfo = BoxItem.getSharedItem(api, sharedLink, password);

        assertEquals("file", itemInfo.getType());

        verify(1, getRequestedFor(
                urlEqualTo("/2.0/shared_items")).withHeader("BoxApi", equalTo(expectedSharedLinkHeaderValue)));
    }
}
