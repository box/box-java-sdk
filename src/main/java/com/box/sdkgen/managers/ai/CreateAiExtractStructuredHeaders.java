package com.box.sdkgen.managers.ai;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateAiExtractStructuredHeaders {

  public Map<String, String> extraHeaders;

  public CreateAiExtractStructuredHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateAiExtractStructuredHeaders(CreateAiExtractStructuredHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateAiExtractStructuredHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateAiExtractStructuredHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateAiExtractStructuredHeaders build() {
      return new CreateAiExtractStructuredHeaders(this);
    }
  }
}
