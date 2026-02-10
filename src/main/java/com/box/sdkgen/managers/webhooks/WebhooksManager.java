package com.box.sdkgen.managers.webhooks;

import static com.box.sdkgen.internal.utils.UtilsManager.compareSignatures;
import static com.box.sdkgen.internal.utils.UtilsManager.computeWebhookSignature;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeFromString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeToEpochSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.getEpochTimeInSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.webhook.Webhook;
import com.box.sdkgen.schemas.webhooks.Webhooks;
import com.box.sdkgen.serialization.json.JsonManager;
import java.time.OffsetDateTime;
import java.util.Map;

public class WebhooksManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public WebhooksManager() {
    this.networkSession = new NetworkSession();
  }

  protected WebhooksManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Returns all defined webhooks for the requesting application.
   *
   * <p>This API only returns webhooks that are applied to files or folders that are owned by the
   * authenticated user. This means that an admin can not see webhooks created by a service account
   * unless the admin has access to those folders, and vice versa.
   */
  public Webhooks getWebhooks() {
    return getWebhooks(new GetWebhooksQueryParams(), new GetWebhooksHeaders());
  }

  /**
   * Returns all defined webhooks for the requesting application.
   *
   * <p>This API only returns webhooks that are applied to files or folders that are owned by the
   * authenticated user. This means that an admin can not see webhooks created by a service account
   * unless the admin has access to those folders, and vice versa.
   *
   * @param queryParams Query parameters of getWebhooks method
   */
  public Webhooks getWebhooks(GetWebhooksQueryParams queryParams) {
    return getWebhooks(queryParams, new GetWebhooksHeaders());
  }

  /**
   * Returns all defined webhooks for the requesting application.
   *
   * <p>This API only returns webhooks that are applied to files or folders that are owned by the
   * authenticated user. This means that an admin can not see webhooks created by a service account
   * unless the admin has access to those folders, and vice versa.
   *
   * @param headers Headers of getWebhooks method
   */
  public Webhooks getWebhooks(GetWebhooksHeaders headers) {
    return getWebhooks(new GetWebhooksQueryParams(), headers);
  }

  /**
   * Returns all defined webhooks for the requesting application.
   *
   * <p>This API only returns webhooks that are applied to files or folders that are owned by the
   * authenticated user. This means that an admin can not see webhooks created by a service account
   * unless the admin has access to those folders, and vice versa.
   *
   * @param queryParams Query parameters of getWebhooks method
   * @param headers Headers of getWebhooks method
   */
  public Webhooks getWebhooks(GetWebhooksQueryParams queryParams, GetWebhooksHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/webhooks"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Webhooks.class);
  }

  /**
   * Creates a webhook.
   *
   * @param requestBody Request body of createWebhook method
   */
  public Webhook createWebhook(CreateWebhookRequestBody requestBody) {
    return createWebhook(requestBody, new CreateWebhookHeaders());
  }

  /**
   * Creates a webhook.
   *
   * @param requestBody Request body of createWebhook method
   * @param headers Headers of createWebhook method
   */
  public Webhook createWebhook(CreateWebhookRequestBody requestBody, CreateWebhookHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/webhooks"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Webhook.class);
  }

  /**
   * Retrieves a specific webhook.
   *
   * @param webhookId The ID of the webhook. Example: "3321123"
   */
  public Webhook getWebhookById(String webhookId) {
    return getWebhookById(webhookId, new GetWebhookByIdHeaders());
  }

  /**
   * Retrieves a specific webhook.
   *
   * @param webhookId The ID of the webhook. Example: "3321123"
   * @param headers Headers of getWebhookById method
   */
  public Webhook getWebhookById(String webhookId, GetWebhookByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/webhooks/",
                            convertToString(webhookId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Webhook.class);
  }

  /**
   * Updates a webhook.
   *
   * @param webhookId The ID of the webhook. Example: "3321123"
   */
  public Webhook updateWebhookById(String webhookId) {
    return updateWebhookById(
        webhookId, new UpdateWebhookByIdRequestBody(), new UpdateWebhookByIdHeaders());
  }

  /**
   * Updates a webhook.
   *
   * @param webhookId The ID of the webhook. Example: "3321123"
   * @param requestBody Request body of updateWebhookById method
   */
  public Webhook updateWebhookById(String webhookId, UpdateWebhookByIdRequestBody requestBody) {
    return updateWebhookById(webhookId, requestBody, new UpdateWebhookByIdHeaders());
  }

  /**
   * Updates a webhook.
   *
   * @param webhookId The ID of the webhook. Example: "3321123"
   * @param headers Headers of updateWebhookById method
   */
  public Webhook updateWebhookById(String webhookId, UpdateWebhookByIdHeaders headers) {
    return updateWebhookById(webhookId, new UpdateWebhookByIdRequestBody(), headers);
  }

  /**
   * Updates a webhook.
   *
   * @param webhookId The ID of the webhook. Example: "3321123"
   * @param requestBody Request body of updateWebhookById method
   * @param headers Headers of updateWebhookById method
   */
  public Webhook updateWebhookById(
      String webhookId,
      UpdateWebhookByIdRequestBody requestBody,
      UpdateWebhookByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/webhooks/",
                            convertToString(webhookId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Webhook.class);
  }

  /**
   * Deletes a webhook.
   *
   * @param webhookId The ID of the webhook. Example: "3321123"
   */
  public void deleteWebhookById(String webhookId) {
    deleteWebhookById(webhookId, new DeleteWebhookByIdHeaders());
  }

  /**
   * Deletes a webhook.
   *
   * @param webhookId The ID of the webhook. Example: "3321123"
   * @param headers Headers of deleteWebhookById method
   */
  public void deleteWebhookById(String webhookId, DeleteWebhookByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/webhooks/",
                            convertToString(webhookId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Validate a webhook message by verifying the signature and the delivery timestamp
   *
   * @param body The request body of the webhook message
   * @param headers The headers of the webhook message
   * @param primaryKey The primary signature to verify the message with
   */
  public static boolean validateMessage(
      String body, Map<String, String> headers, String primaryKey) {
    return validateMessage(body, headers, primaryKey, null, 600);
  }

  /**
   * Validate a webhook message by verifying the signature and the delivery timestamp
   *
   * @param body The request body of the webhook message
   * @param headers The headers of the webhook message
   * @param primaryKey The primary signature to verify the message with
   * @param secondaryKey The secondary signature to verify the message with
   */
  public static boolean validateMessage(
      String body, Map<String, String> headers, String primaryKey, String secondaryKey) {
    return validateMessage(body, headers, primaryKey, secondaryKey, 600);
  }

  /**
   * Validate a webhook message by verifying the signature and the delivery timestamp
   *
   * @param body The request body of the webhook message
   * @param headers The headers of the webhook message
   * @param primaryKey The primary signature to verify the message with
   * @param maxAge The maximum age of the message in seconds, defaults to 10 minutes
   */
  public static boolean validateMessage(
      String body, Map<String, String> headers, String primaryKey, Integer maxAge) {
    return validateMessage(body, headers, primaryKey, null, maxAge);
  }

  /**
   * Validate a webhook message by verifying the signature and the delivery timestamp
   *
   * @param body The request body of the webhook message
   * @param headers The headers of the webhook message
   * @param primaryKey The primary signature to verify the message with
   * @param secondaryKey The secondary signature to verify the message with
   * @param maxAge The maximum age of the message in seconds, defaults to 10 minutes
   */
  public static boolean validateMessage(
      String body,
      Map<String, String> headers,
      String primaryKey,
      String secondaryKey,
      Integer maxAge) {
    OffsetDateTime deliveryTimestamp = dateTimeFromString(headers.get("box-delivery-timestamp"));
    long currentEpoch = getEpochTimeInSeconds();
    if (currentEpoch - maxAge > dateTimeToEpochSeconds(deliveryTimestamp)
        || dateTimeToEpochSeconds(deliveryTimestamp) > currentEpoch) {
      return false;
    }
    if (!(primaryKey == null)
        && !(headers.get("box-signature-primary") == null)
        && compareSignatures(
            computeWebhookSignature(body, headers, primaryKey, false),
            headers.get("box-signature-primary"))) {
      return true;
    }
    if (!(primaryKey == null)
        && !(headers.get("box-signature-primary") == null)
        && compareSignatures(
            computeWebhookSignature(body, headers, primaryKey, true),
            headers.get("box-signature-primary"))) {
      return true;
    }
    if (!(secondaryKey == null)
        && !(headers.get("box-signature-secondary") == null)
        && compareSignatures(
            computeWebhookSignature(body, headers, secondaryKey, false),
            headers.get("box-signature-secondary"))) {
      return true;
    }
    if (!(secondaryKey == null)
        && !(headers.get("box-signature-secondary") == null)
        && compareSignatures(
            computeWebhookSignature(body, headers, secondaryKey, true),
            headers.get("box-signature-secondary"))) {
      return true;
    }
    return false;
  }

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public static class Builder {

    protected Authentication auth;

    protected NetworkSession networkSession;

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public WebhooksManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new WebhooksManager(this);
    }
  }
}
