package com.box.sdkgen.managers.webhooks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateWebhookHeaders {

  public Map<String, String> extraHeaders;

  public CreateWebhookHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateWebhookHeaders(CreateWebhookHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateWebhookHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateWebhookHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateWebhookHeaders build() {
      return new CreateWebhookHeaders(this);
    }
  }
}
