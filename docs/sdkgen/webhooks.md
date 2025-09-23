# WebhooksManager


- [List all webhooks](#list-all-webhooks)
- [Create webhook](#create-webhook)
- [Get webhook](#get-webhook)
- [Update webhook](#update-webhook)
- [Remove webhook](#remove-webhook)
- [Validate a webhook message](#validate-a-webhook-message)

## List all webhooks

Returns all defined webhooks for the requesting application.

This API only returns webhooks that are applied to files or folders that are
owned by the authenticated user. This means that an admin can not see webhooks
created by a service account unless the admin has access to those folders, and
vice versa.

This operation is performed by calling function `getWebhooks`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-webhooks/).

<!-- sample get_webhooks -->
```
client.getWebhooks().getWebhooks()
```

### Arguments

- queryParams `GetWebhooksQueryParams`
  - Query parameters of getWebhooks method
- headers `GetWebhooksHeaders`
  - Headers of getWebhooks method


### Returns

This function returns a value of type `Webhooks`.

Returns a list of webhooks.


## Create webhook

Creates a webhook.

This operation is performed by calling function `createWebhook`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-webhooks/).

<!-- sample post_webhooks -->
```
client.getWebhooks().createWebhook(new CreateWebhookRequestBody(new CreateWebhookRequestBodyTargetField.Builder().id(folder.getId()).type(CreateWebhookRequestBodyTargetTypeField.FOLDER).build(), "https://example.com/new-webhook", Arrays.asList(CreateWebhookRequestBodyTriggersField.FILE_UPLOADED)))
```

### Arguments

- requestBody `CreateWebhookRequestBody`
  - Request body of createWebhook method
- headers `CreateWebhookHeaders`
  - Headers of createWebhook method


### Returns

This function returns a value of type `Webhook`.

Returns the new webhook object.


## Get webhook

Retrieves a specific webhook.

This operation is performed by calling function `getWebhookById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-webhooks-id/).

<!-- sample get_webhooks_id -->
```
client.getWebhooks().getWebhookById(webhook.getId())
```

### Arguments

- webhookId `String`
  - The ID of the webhook. Example: "3321123"
- headers `GetWebhookByIdHeaders`
  - Headers of getWebhookById method


### Returns

This function returns a value of type `Webhook`.

Returns a webhook object.


## Update webhook

Updates a webhook.

This operation is performed by calling function `updateWebhookById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-webhooks-id/).

<!-- sample put_webhooks_id -->
```
client.getWebhooks().updateWebhookById(webhook.getId(), new UpdateWebhookByIdRequestBody.Builder().address("https://example.com/updated-webhook").build())
```

### Arguments

- webhookId `String`
  - The ID of the webhook. Example: "3321123"
- requestBody `UpdateWebhookByIdRequestBody`
  - Request body of updateWebhookById method
- headers `UpdateWebhookByIdHeaders`
  - Headers of updateWebhookById method


### Returns

This function returns a value of type `Webhook`.

Returns the new webhook object.


## Remove webhook

Deletes a webhook.

This operation is performed by calling function `deleteWebhookById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-webhooks-id/).

<!-- sample delete_webhooks_id -->
```
client.getWebhooks().deleteWebhookById(webhook.getId())
```

### Arguments

- webhookId `String`
  - The ID of the webhook. Example: "3321123"
- headers `DeleteWebhookByIdHeaders`
  - Headers of deleteWebhookById method


### Returns

This function returns a value of type `void`.

An empty response will be returned when the webhook
was successfully deleted.


## Validate a webhook message

Validate a webhook message by verifying the signature and the delivery timestamp

This operation is performed by calling function `validateMessage`.



```
WebhooksManager.validateMessage(body, headersWithCorrectDatetime, primaryKey, secondaryKey)
```

### Arguments

- body `String`
  - The request body of the webhook message
- headers `Map<String, String>`
  - The headers of the webhook message
- primaryKey `String`
  - The primary signature to verify the message with
- secondaryKey `String`
  - The secondary signature to verify the message with
- maxAge `Integer`
  - The maximum age of the message in seconds, defaults to 10 minutes


### Returns

This function returns a value of type `boolean`.




