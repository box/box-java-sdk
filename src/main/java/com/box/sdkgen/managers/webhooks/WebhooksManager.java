package com.box.sdkgen.managers.webhooks;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
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
import java.util.Map;

public class WebhooksManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public WebhooksManager() {
    this.networkSession = new NetworkSession();
  }

  protected WebhooksManager(WebhooksManagerBuilder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public Webhooks getWebhooks() {
    return getWebhooks(new GetWebhooksQueryParams(), new GetWebhooksHeaders());
  }

  public Webhooks getWebhooks(GetWebhooksQueryParams queryParams) {
    return getWebhooks(queryParams, new GetWebhooksHeaders());
  }

  public Webhooks getWebhooks(GetWebhooksHeaders headers) {
    return getWebhooks(new GetWebhooksQueryParams(), headers);
  }

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
                new FetchOptions.FetchOptionsBuilder(
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

  public Webhook createWebhook(CreateWebhookRequestBody requestBody) {
    return createWebhook(requestBody, new CreateWebhookHeaders());
  }

  public Webhook createWebhook(CreateWebhookRequestBody requestBody, CreateWebhookHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.FetchOptionsBuilder(
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

  public Webhook getWebhookById(String webhookId) {
    return getWebhookById(webhookId, new GetWebhookByIdHeaders());
  }

  public Webhook getWebhookById(String webhookId, GetWebhookByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.FetchOptionsBuilder(
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

  public Webhook updateWebhookById(String webhookId) {
    return updateWebhookById(
        webhookId, new UpdateWebhookByIdRequestBody(), new UpdateWebhookByIdHeaders());
  }

  public Webhook updateWebhookById(String webhookId, UpdateWebhookByIdRequestBody requestBody) {
    return updateWebhookById(webhookId, requestBody, new UpdateWebhookByIdHeaders());
  }

  public Webhook updateWebhookById(String webhookId, UpdateWebhookByIdHeaders headers) {
    return updateWebhookById(webhookId, new UpdateWebhookByIdRequestBody(), headers);
  }

  public Webhook updateWebhookById(
      String webhookId,
      UpdateWebhookByIdRequestBody requestBody,
      UpdateWebhookByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.FetchOptionsBuilder(
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

  public void deleteWebhookById(String webhookId) {
    deleteWebhookById(webhookId, new DeleteWebhookByIdHeaders());
  }

  public void deleteWebhookById(String webhookId, DeleteWebhookByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.FetchOptionsBuilder(
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

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public static class WebhooksManagerBuilder {

    protected Authentication auth;

    protected NetworkSession networkSession;

    public WebhooksManagerBuilder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public WebhooksManagerBuilder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public WebhooksManager build() {
      return new WebhooksManager(this);
    }
  }
}
