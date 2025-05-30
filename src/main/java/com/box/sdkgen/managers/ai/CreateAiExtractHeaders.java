package com.box.sdkgen.managers.ai;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateAiExtractHeaders {

  public Map<String, String> extraHeaders;

  public CreateAiExtractHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateAiExtractHeaders(CreateAiExtractHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateAiExtractHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateAiExtractHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateAiExtractHeaders build() {
      return new CreateAiExtractHeaders(this);
    }
  }
}
