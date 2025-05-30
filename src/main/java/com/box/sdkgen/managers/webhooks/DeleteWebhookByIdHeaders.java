package com.box.sdkgen.managers.webhooks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteWebhookByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteWebhookByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteWebhookByIdHeaders(DeleteWebhookByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteWebhookByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteWebhookByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteWebhookByIdHeaders build() {
      return new DeleteWebhookByIdHeaders(this);
    }
  }
}
