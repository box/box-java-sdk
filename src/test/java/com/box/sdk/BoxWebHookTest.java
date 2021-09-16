package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.box.sdk.BoxWebHook.Trigger.FOLDER_COPIED;
import static com.box.sdk.BoxWebHook.Trigger.FOLDER_DOWNLOADED;
import static com.box.sdk.UniqueTestFolder.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import javax.swing.*;

/**
 * {@link BoxWebHook} related tests.
 */
public class BoxWebHookTest {

    @BeforeClass
    public static void setup() {
        setupUniqeFolder();
    }

    @AfterClass
    public static void afterClass() {
        removeUniqueFolder();
    }

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(IntegrationTest.class)
    public void createWebHookFileSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[createWebhook] Test File.txt";

        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
            URL address = new URL("https://www.google.com");
            BoxWebHook.Info info = BoxWebHook.create(uploadedFile, address,
                    BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED);

            assertThat(info.getID(), is(notNullValue()));
            assertThat(info.getAddress(), is(equalTo(address)));
            assertThat(info.getTarget().getType(), is(equalTo(BoxResource.getResourceType(BoxFile.class))));
            assertThat(info.getTarget().getId(), is(equalTo(uploadedFile.getID())));
            assertThat(info.getTriggers(), is(equalTo(this.toSet(
                    new BoxWebHook.Trigger[]{BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED}))));

            info.getResource().delete();
        } finally {
            if (uploadedFile != null) {
                uploadedFile.delete();
            }
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void createWebHookFolderSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = getUniqueFolder(api);
        String folderName = "[createWebhook] Folder";
        BoxFolder folder = null;

        try {
            folder = rootFolder.createFolder(folderName).getResource();
            URL address = new URL("https://www.google.com");
            BoxWebHook.Info info = BoxWebHook.create(folder, address, FOLDER_DOWNLOADED, FOLDER_COPIED);

            assertThat(info.getID(), is(notNullValue()));
            assertThat(info.getAddress(), is(equalTo(address)));
            assertThat(info.getTarget().getType(), is(equalTo(BoxResource.getResourceType(BoxFolder.class))));
            assertThat(info.getTarget().getId(), is(equalTo(folder.getID())));
            assertThat(info.getTriggers(),
                    is(equalTo(this.toSet(new BoxWebHook.Trigger[]{FOLDER_COPIED, FOLDER_DOWNLOADED}))));

            info.getResource().delete();
        } finally {
            if (folder != null) {
                folder.delete(true);
            }
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void listWebHooksSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[listWebhooks] Test File.txt";
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
            URL address = new URL("https://www.google.com");
            BoxWebHook.Info info = BoxWebHook.create(uploadedFile, address, BoxWebHook.Trigger.FILE_PREVIEWED);
            Iterable<BoxWebHook.Info> webhooks = BoxWebHook.all(api);

            assertThat(webhooks, hasItem(Matchers.<BoxWebHook.Info>hasProperty("ID", equalTo(info.getID()))));

            info.getResource().delete();

            webhooks = BoxWebHook.all(api);
            assertThat(webhooks, not(hasItem(Matchers.<BoxWebHook.Info>hasProperty("ID", equalTo(info.getID())))));
        } finally {
            if (uploadedFile != null) {
                uploadedFile.delete();
            }
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateWebHookInfoSucceeds() throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[updateWebHookInfoSucceeds] Test File.txt";
        byte[] fileBytes = "Non-empty string".getBytes(StandardCharsets.UTF_8);

        InputStream uploadStream = new ByteArrayInputStream(fileBytes);
        BoxFile uploadedFile = rootFolder.uploadFile(uploadStream, fileName).getResource();

        try {
            URL address = new URL("https://www.google.com");
            BoxWebHook webHook = BoxWebHook.create(uploadedFile, address,
                    BoxWebHook.Trigger.FILE_PREVIEWED, BoxWebHook.Trigger.FILE_LOCKED).getResource();

            URL newAddress = new URL("https://www.yahoo.com");

            BoxWebHook.Info newInfo = webHook.new Info();
            newInfo.setTriggers(BoxWebHook.Trigger.FILE_UNLOCKED);
            newInfo.setAddress(newAddress);

            webHook.updateInfo(newInfo);

            assertThat(newInfo.getAddress(), is(equalTo(newAddress)));
            assertThat(newInfo.getTriggers(), is(equalTo(
                    this.toSet(new BoxWebHook.Trigger[]{BoxWebHook.Trigger.FILE_UNLOCKED})
            )));

            webHook.delete();
        } finally {
            uploadedFile.delete();
        }
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateWebhookSucceedsAndSendsCorrectJson() throws IOException {
        final String webhookID = "12345";
        final String folderID = "1111";
        final String createdByID = "2222";
        final String createdByLogin = "test@user.com";
        final String address = "https:/example.com";
        final String webhookURL = "/webhooks";

        JsonObject innerObject = new JsonObject()
                .add("type", "folder")
                .add("id", "1111");

        JsonObject webhookObject = new JsonObject()
                .add("target", innerObject)
                .add("triggers", new JsonArray().add("FILE.LOCKED"))
                .add("address", address);

        String result = TestConfig.getFixture("BoxWebhook/CreateWebhook201");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.post(WireMock.urlPathEqualTo(webhookURL))
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
    @Category(UnitTest.class)
    public void testGetWebhookSuceeds() throws IOException {
        final String webhookID = "12345";
        final String folderID = "1111";
        final String createdByID = "2222";
        final String webhookURL = "/webhooks/" + webhookID;

        String result = TestConfig.getFixture("BoxWebhook/GetWebhook200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(webhookURL))
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
    @Category(UnitTest.class)
    public void testGetAllWebhooksSucceeds() throws IOException {
        final String webhookID = "12345";
        final String folderID = "1111";
        final String webhookURL = "/webhooks";

        String result = TestConfig.getFixture("BoxWebhook/GetAllWebhooks200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(webhookURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxWebHook.Info> webhooks = BoxWebHook.all(this.api).iterator();
        BoxWebHook.Info webhookInfo = webhooks.next();

        assertEquals(webhookID, webhookInfo.getID());
        assertEquals(folderID, webhookInfo.getTarget().getId());
    }

    @Test
    @Category(UnitTest.class)
    public void testUpdateWebhookInfoSucceeds() throws IOException {
        final String newAddress = "https://newexample.com";
        final String webhookID = "12345";
        final String createdByLogin = "test@user.com";
        final String webhookURL = "/webhooks/" + webhookID;

        JsonArray triggers = new JsonArray()
                .add("FILE.UPLOADED");

        JsonObject updateObject = new JsonObject()
                .add("triggers", triggers)
                .add("address", newAddress);

        String result = TestConfig.getFixture("BoxWebhook/UpdateWebhookInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(webhookURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.put(WireMock.urlPathEqualTo(webhookURL))
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
    @Category(UnitTest.class)
    public void testDeleteWebhookSucceeds() {
        final String webhookID = "12345";
        final String webhookURL = "/webhooks/" + webhookID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(webhookURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("")));

        BoxWebHook webhook = new BoxWebHook(this.api, webhookID);
        webhook.delete();
    }

    private <T> Set<T> toSet(T[] values) {
        return new HashSet<>(Arrays.asList(values));
    }
}
