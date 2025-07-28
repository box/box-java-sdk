package com.box.sdkgen.test.webhooks;

import static com.box.sdkgen.internal.utils.UtilsManager.compareSignatures;
import static com.box.sdkgen.internal.utils.UtilsManager.computeWebhookSignature;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.epochSecondsToDateTime;
import static com.box.sdkgen.internal.utils.UtilsManager.getEpochTimeInSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.webhooks.CreateWebhookRequestBody;
import com.box.sdkgen.managers.webhooks.CreateWebhookRequestBodyTargetField;
import com.box.sdkgen.managers.webhooks.CreateWebhookRequestBodyTargetTypeField;
import com.box.sdkgen.managers.webhooks.CreateWebhookRequestBodyTriggersField;
import com.box.sdkgen.managers.webhooks.UpdateWebhookByIdRequestBody;
import com.box.sdkgen.managers.webhooks.WebhooksManager;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.webhook.Webhook;
import com.box.sdkgen.schemas.webhooks.Webhooks;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class WebhooksITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testWebhooksCrud() {
    FolderFull folder =
        client
            .getFolders()
            .createFolder(
                new CreateFolderRequestBody(
                    getUuid(), new CreateFolderRequestBodyParentField("0")));
    Webhook webhook =
        client
            .getWebhooks()
            .createWebhook(
                new CreateWebhookRequestBody(
                    new CreateWebhookRequestBodyTargetField.Builder()
                        .id(folder.getId())
                        .type(CreateWebhookRequestBodyTargetTypeField.FOLDER)
                        .build(),
                    "https://example.com/new-webhook",
                    Arrays.asList(CreateWebhookRequestBodyTriggersField.FILE_UPLOADED)));
    assert webhook.getTarget().getId().equals(folder.getId());
    assert convertToString(webhook.getTarget().getType()).equals("folder");
    assert webhook.getTriggers().size() == Arrays.asList("FILE.UPLOADED").size();
    assert webhook.getAddress().equals("https://example.com/new-webhook");
    Webhooks webhooks = client.getWebhooks().getWebhooks();
    assert webhooks.getEntries().size() > 0;
    Webhook webhookFromApi = client.getWebhooks().getWebhookById(webhook.getId());
    assert webhook.getId().equals(webhookFromApi.getId());
    assert webhook.getTarget().getId().equals(webhookFromApi.getTarget().getId());
    assert webhook.getAddress().equals(webhookFromApi.getAddress());
    Webhook updatedWebhook =
        client
            .getWebhooks()
            .updateWebhookById(
                webhook.getId(),
                new UpdateWebhookByIdRequestBody.Builder()
                    .address("https://example.com/updated-webhook")
                    .build());
    assert updatedWebhook.getId().equals(webhook.getId());
    assert updatedWebhook.getAddress().equals("https://example.com/updated-webhook");
    client.getWebhooks().deleteWebhookById(webhook.getId());
    assertThrows(
        RuntimeException.class, () -> client.getWebhooks().deleteWebhookById(webhook.getId()));
    client.getFolders().deleteFolderById(folder.getId());
  }

  @Test
  public void testWebhookValidation() {
    String primaryKey = "SamplePrimaryKey";
    String secondaryKey = "SampleSecondaryKey";
    String incorrectKey = "IncorrectKey";
    String body =
        "{\"type\":\"webhook_event\",\"webhook\":{\"id\":\"1234567890\"},\"trigger\":\"FILE.UPLOADED\",\"source\":{\"id\":\"1234567890\",\"type\":\"file\",\"name\":\"Test.txt\"}}";
    String bodyWithJapanese =
        "{\"webhook\":{\"id\":\"1234567890\"},\"trigger\":\"FILE.UPLOADED\",\"source\":{\"id\":\"1234567890\",\"type\":\"file\",\"name\":\"スクリーンショット 2020-08-05.txt\"}}";
    String bodyWithEmoji =
        "{\"webhook\":{\"id\":\"1234567890\"},\"trigger\":\"FILE.UPLOADED\",\"source\":{\"id\":\"1234567890\",\"type\":\"file\",\"name\":\"😀 2020-08-05.txt\"}}";
    String bodyWithCarriageReturn =
        "{\"webhook\":{\"id\":\"1234567890\"},\"trigger\":\"FILE.UPLOADED\",\"source\":{\"id\":\"1234567890\",\"type\":\"file\",\"name\":\"test \r\"}}";
    String bodyWithForwardSlash =
        "{\"webhook\":{\"id\":\"1234567890\"},\"trigger\":\"FILE.UPLOADED\",\"source\":{\"id\":\"1234567890\",\"type\":\"file\",\"name\":\"/\"}}";
    String bodyWithBackSlash =
        "{\"webhook\":{\"id\":\"1234567890\"},\"trigger\":\"FILE.UPLOADED\",\"source\":{\"id\":\"1234567890\",\"type\":\"file\",\"name\":\"\\\"}}";
    Map<String, String> headers =
        mapOf(
            entryOf("box-delivery-id", "f96bb54b-ee16-4fc5-aa65-8c2d9e5b546f"),
            entryOf("box-delivery-timestamp", "2020-01-01T00:00:00-07:00"),
            entryOf("box-signature-algorithm", "HmacSHA256"),
            entryOf("box-signature-primary", "6TfeAW3A1PASkgboxxA5yqHNKOwFyMWuEXny/FPD5hI="),
            entryOf("box-signature-secondary", "v+1CD1Jdo3muIcbpv5lxxgPglOqMfsNHPV899xWYydo="),
            entryOf("box-signature-version", "1"));
    Map<String, String> headersWithJapanese =
        mergeMaps(
            headers,
            mapOf(
                entryOf("box-signature-primary", "LV2uCu+5NJtIHrCXDYgZ0v/PP5THGRuegw3RtdnEyuE=")));
    Map<String, String> headersWithEmoji =
        mergeMaps(
            headers,
            mapOf(
                entryOf("box-signature-primary", "xF/SDZosX4le+v4A0Qn59sZhuD1RqY5KRUKzVMSbh0E=")));
    Map<String, String> headersWithCarriageReturn =
        mergeMaps(
            headers,
            mapOf(
                entryOf("box-signature-primary", "SVkbKgy3dEEf2PbbzpNu2lDZS7zZ/aboU7HOZgBGrJk=")));
    Map<String, String> headersWithForwardSlash =
        mergeMaps(
            headers,
            mapOf(
                entryOf("box-signature-primary", "t41PWT5ZB6OcysnD6SDy9Ud+p9hdXxIdXqcdweyZv/Q=")));
    Map<String, String> headersWithBackSlash =
        mergeMaps(
            headers,
            mapOf(
                entryOf("box-signature-primary", "ERpMZwUQsGDTfj82ehdX6VvDZfvOhK5ULNfVmwVAGe0=")));
    String currentDatetime = dateTimeToString(epochSecondsToDateTime(getEpochTimeInSeconds()));
    String futureDatetime =
        dateTimeToString(epochSecondsToDateTime(getEpochTimeInSeconds() + 1200));
    String pastDatetime = dateTimeToString(epochSecondsToDateTime(getEpochTimeInSeconds() - 1200));
    Map<String, String> headersWithCorrectDatetime =
        mergeMaps(
            headers,
            mapOf(
                entryOf("box-delivery-timestamp", currentDatetime),
                entryOf(
                    "box-signature-primary",
                    computeWebhookSignature(
                        body,
                        mergeMaps(
                            headers, mapOf(entryOf("box-delivery-timestamp", currentDatetime))),
                        primaryKey,
                        true)),
                entryOf(
                    "box-signature-secondary",
                    computeWebhookSignature(
                        body,
                        mergeMaps(
                            headers, mapOf(entryOf("box-delivery-timestamp", currentDatetime))),
                        secondaryKey,
                        true))));
    Map<String, String> headersWithJapaneseWithCorrectDatetime =
        mergeMaps(
            headersWithJapanese,
            mapOf(
                entryOf("box-delivery-timestamp", currentDatetime),
                entryOf(
                    "box-signature-primary",
                    computeWebhookSignature(
                        bodyWithJapanese,
                        mergeMaps(
                            headersWithJapanese,
                            mapOf(entryOf("box-delivery-timestamp", currentDatetime))),
                        primaryKey,
                        true)),
                entryOf(
                    "box-signature-secondary",
                    computeWebhookSignature(
                        bodyWithJapanese,
                        mergeMaps(
                            headersWithJapanese,
                            mapOf(entryOf("box-delivery-timestamp", currentDatetime))),
                        secondaryKey,
                        true))));
    Map<String, String> headersWithFutureDatetime =
        mergeMaps(
            headers,
            mapOf(
                entryOf("box-delivery-timestamp", futureDatetime),
                entryOf(
                    "box-signature-primary",
                    computeWebhookSignature(
                        body,
                        mergeMaps(
                            headers, mapOf(entryOf("box-delivery-timestamp", futureDatetime))),
                        primaryKey,
                        true)),
                entryOf(
                    "box-signature-secondary",
                    computeWebhookSignature(
                        body,
                        mergeMaps(
                            headers, mapOf(entryOf("box-delivery-timestamp", futureDatetime))),
                        secondaryKey,
                        true))));
    Map<String, String> headersWithPastDatetime =
        mergeMaps(
            headers,
            mapOf(
                entryOf("box-delivery-timestamp", pastDatetime),
                entryOf(
                    "box-signature-primary",
                    computeWebhookSignature(
                        body,
                        mergeMaps(headers, mapOf(entryOf("box-delivery-timestamp", pastDatetime))),
                        primaryKey,
                        true)),
                entryOf(
                    "box-signature-secondary",
                    computeWebhookSignature(
                        body,
                        mergeMaps(headers, mapOf(entryOf("box-delivery-timestamp", pastDatetime))),
                        secondaryKey,
                        true))));
    Map<String, String> headersWithWrongSignatureVersion =
        mergeMaps(headers, mapOf(entryOf("box-signature-version", "2")));
    Map<String, String> headersWithWrongSignatureAlgorithm =
        mergeMaps(headers, mapOf(entryOf("box-signature-algorithm", "HmacSHA1")));
    assert compareSignatures(
        computeWebhookSignature(body, headers, primaryKey, true),
        headers.get("box-signature-primary"));
    assert compareSignatures(
        computeWebhookSignature(body, headers, secondaryKey, true),
        headers.get("box-signature-secondary"));
    assert !(compareSignatures(
        computeWebhookSignature(body, headers, incorrectKey, true),
        headers.get("box-signature-primary")));
    assert compareSignatures(
        computeWebhookSignature(bodyWithJapanese, headersWithJapanese, primaryKey, true),
        headersWithJapanese.get("box-signature-primary"));
    assert compareSignatures(
        computeWebhookSignature(bodyWithEmoji, headersWithEmoji, primaryKey, true),
        headersWithEmoji.get("box-signature-primary"));
    assert compareSignatures(
        computeWebhookSignature(
            bodyWithCarriageReturn, headersWithCarriageReturn, primaryKey, true),
        headersWithCarriageReturn.get("box-signature-primary"));
    assert compareSignatures(
        computeWebhookSignature(bodyWithForwardSlash, headersWithForwardSlash, primaryKey, true),
        headersWithForwardSlash.get("box-signature-primary"));
    assert compareSignatures(
        computeWebhookSignature(bodyWithBackSlash, headersWithBackSlash, primaryKey, true),
        headersWithBackSlash.get("box-signature-primary"));
    assert WebhooksManager.validateMessage(
        body, headersWithCorrectDatetime, primaryKey, secondaryKey);
    assert WebhooksManager.validateMessage(
        body, headersWithCorrectDatetime, primaryKey, incorrectKey);
    assert WebhooksManager.validateMessage(
        body, headersWithCorrectDatetime, incorrectKey, secondaryKey);
    assert !(WebhooksManager.validateMessage(
        body, headersWithCorrectDatetime, incorrectKey, incorrectKey));
    assert !(WebhooksManager.validateMessage(
        body, headersWithFutureDatetime, primaryKey, secondaryKey));
    assert !(WebhooksManager.validateMessage(
        body, headersWithPastDatetime, primaryKey, secondaryKey));
    assert !(WebhooksManager.validateMessage(
        body, headersWithWrongSignatureVersion, primaryKey, secondaryKey));
    assert !(WebhooksManager.validateMessage(
        body, headersWithWrongSignatureAlgorithm, primaryKey, secondaryKey));
    assert WebhooksManager.validateMessage(
        bodyWithJapanese, headersWithJapaneseWithCorrectDatetime, primaryKey, secondaryKey);
    assert WebhooksManager.validateMessage(
        bodyWithJapanese, headersWithJapaneseWithCorrectDatetime, primaryKey, incorrectKey);
    assert WebhooksManager.validateMessage(
        bodyWithJapanese, headersWithJapaneseWithCorrectDatetime, incorrectKey, secondaryKey);
    assert !(WebhooksManager.validateMessage(
        bodyWithJapanese, headersWithJapanese, primaryKey, secondaryKey));
  }
}
