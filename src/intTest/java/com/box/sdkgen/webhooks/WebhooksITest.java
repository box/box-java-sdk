package com.box.sdkgen.webhooks;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.webhooks.CreateWebhookRequestBody;
import com.box.sdkgen.managers.webhooks.CreateWebhookRequestBodyTargetField;
import com.box.sdkgen.managers.webhooks.CreateWebhookRequestBodyTargetTypeField;
import com.box.sdkgen.managers.webhooks.CreateWebhookRequestBodyTriggersField;
import com.box.sdkgen.managers.webhooks.UpdateWebhookByIdRequestBody;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.webhook.Webhook;
import com.box.sdkgen.schemas.webhooks.Webhooks;
import java.util.Arrays;
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
                    new CreateWebhookRequestBodyTargetField
                            .CreateWebhookRequestBodyTargetFieldBuilder()
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
                new UpdateWebhookByIdRequestBody.UpdateWebhookByIdRequestBodyBuilder()
                    .address("https://example.com/updated-webhook")
                    .build());
    assert updatedWebhook.getId().equals(webhook.getId());
    assert updatedWebhook.getAddress().equals("https://example.com/updated-webhook");
    client.getWebhooks().deleteWebhookById(webhook.getId());
    assertThrows(
        RuntimeException.class, () -> client.getWebhooks().deleteWebhookById(webhook.getId()));
    client.getFolders().deleteFolderById(folder.getId());
  }
}
