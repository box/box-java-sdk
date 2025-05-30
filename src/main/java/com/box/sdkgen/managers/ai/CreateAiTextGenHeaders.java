package com.box.sdkgen.managers.ai;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateAiTextGenHeaders {

  public Map<String, String> extraHeaders;

  public CreateAiTextGenHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateAiTextGenHeaders(CreateAiTextGenHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateAiTextGenHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateAiTextGenHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateAiTextGenHeaders build() {
      return new CreateAiTextGenHeaders(this);
    }
  }
}
