package com.box.sdkgen.managers.ai;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateAiExtractStructuredHeaders {

  public Map<String, String> extraHeaders;

  public CreateAiExtractStructuredHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateAiExtractStructuredHeaders(Builder builder) {
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

    public CreateAiExtractStructuredHeaders build() {
      return new CreateAiExtractStructuredHeaders(this);
    }
  }
}
