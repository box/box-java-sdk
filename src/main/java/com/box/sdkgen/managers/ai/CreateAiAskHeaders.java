package com.box.sdkgen.managers.ai;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateAiAskHeaders {

  public Map<String, String> extraHeaders;

  public CreateAiAskHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateAiAskHeaders(CreateAiAskHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateAiAskHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateAiAskHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateAiAskHeaders build() {
      return new CreateAiAskHeaders(this);
    }
  }
}
