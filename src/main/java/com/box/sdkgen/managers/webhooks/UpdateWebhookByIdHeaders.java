package com.box.sdkgen.managers.webhooks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateWebhookByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateWebhookByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateWebhookByIdHeaders(UpdateWebhookByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateWebhookByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateWebhookByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateWebhookByIdHeaders build() {
      return new UpdateWebhookByIdHeaders(this);
    }
  }
}
