package com.box.sdkgen.managers.webhooks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetWebhooksHeaders {

  public Map<String, String> extraHeaders;

  public GetWebhooksHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetWebhooksHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetWebhooksHeaders build() {
      return new GetWebhooksHeaders(this);
    }
  }
}
