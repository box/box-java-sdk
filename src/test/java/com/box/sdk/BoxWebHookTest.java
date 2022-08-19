package com.box.sdk;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxWebHook} related tests.
 */
public class BoxWebHookTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testCreateWebhookSucceedsAndSendsCorrectJson() throws IOException {
        final String webhookID = "12345";
        final String folderID = "1111";
        final String createdByID = "2222";
        final String createdByLogin = "test@user.com";
        final String address = "https:/example.com";
        final String webhookURL = "/2.0/webhooks";

        JsonObject innerObject = new JsonObject()
            .add("type", "folder")
            .add("id", "1111");

        JsonObject webhookObject = new JsonObject()
            .add("target", innerObject)
            .add("triggers", new JsonArray().add("FILE.LOCKED"))
            .add("address", address);

        String result = TestUtils.getFixture("BoxWebhook/CreateWebhook201");

        wireMockRule.stubFor(WireMock.post(WireMock.urlPathEqualTo(webhookURL))
            .withRequestBody(WireMock.equalToJson(webhookObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxFolder folder = new BoxFolder(this.api, folderID);
        BoxWebHook.Info webhookInfo = BoxWebHook.create(folder, new URL(address), BoxWebHook.Trigger.FILE_LOCKED);

        assertEquals(webhookID, webhookInfo.getID());
        assertEquals(folderID, webhookInfo.getTarget().getId());
        assertEquals(createdByID, webhookInfo.getCreatedBy().getID());
        assertEquals(createdByLogin, webhookInfo.getCreatedBy().getLogin());
        assertEquals(BoxWebHook.Trigger.FILE_LOCKED, webhookInfo.getTriggers().iterator().next());
    }

    @Test
    public void testGetWebhookSuceeds() throws IOException {
        final String webhookID = "12345";
        final String folderID = "1111";
        final String createdByID = "2222";
        final String webhookURL = "/2.0/webhooks/" + webhookID;

        String result = TestUtils.getFixture("BoxWebhook/GetWebhook200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(webhookURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxWebHook webhook = new BoxWebHook(this.api, webhookID);
        BoxWebHook.Info info = webhook.getInfo();

        assertEquals(webhookID, info.getID());
        assertEquals(folderID, info.getTarget().getId());
        assertEquals(createdByID, info.getCreatedBy().getID());
    }

    @Test
    public void testGetAllWebhooksSucceeds() throws IOException {
        final String webhookID = "12345";
        final String folderID = "1111";
        final String webhookURL = "/2.0/webhooks";

        String result = TestUtils.getFixture("BoxWebhook/GetAllWebhooks200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(webhookURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxWebHook.Info> webhooks = BoxWebHook.all(this.api).iterator();
        BoxWebHook.Info webhookInfo = webhooks.next();

        assertEquals(webhookID, webhookInfo.getID());
        assertEquals(folderID, webhookInfo.getTarget().getId());
    }

    @Test
    public void testUpdateWebhookInfoSucceeds() throws IOException {
        final String newAddress = "https://newexample.com";
        final String webhookID = "12345";
        final String createdByLogin = "test@user.com";
        final String webhookURL = "/2.0/webhooks/" + webhookID;

        JsonArray triggers = new JsonArray()
            .add("FILE.UPLOADED");

        JsonObject updateObject = new JsonObject()
            .add("triggers", triggers)
            .add("address", newAddress);

        String result = TestUtils.getFixture("BoxWebhook/UpdateWebhookInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(webhookURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        wireMockRule.stubFor(WireMock.put(WireMock.urlPathEqualTo(webhookURL))
            .withRequestBody(WireMock.equalToJson(updateObject.toString()))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxWebHook webhook = new BoxWebHook(this.api, webhookID);
        BoxWebHook.Info info = webhook.new Info();
        info.setAddress(new URL(newAddress));
        info.setTriggers(BoxWebHook.Trigger.FILE_UPLOADED);
        webhook.updateInfo(info);

        assertEquals(new URL(newAddress), info.getAddress());
        assertEquals(webhookID, info.getID());
        assertEquals(createdByLogin, info.getCreatedBy().getLogin());
    }

    @Test
    public void testDeleteWebhookSucceeds() {
        final String webhookID = "12345";
        final String webhookURL = "/2.0/webhooks/" + webhookID;

        wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(webhookURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("")));

        BoxWebHook webhook = new BoxWebHook(this.api, webhookID);
        webhook.delete();
    }
}
