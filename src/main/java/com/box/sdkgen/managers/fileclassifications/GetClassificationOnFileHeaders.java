package com.box.sdkgen.managers.fileclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetClassificationOnFileHeaders {

  public Map<String, String> extraHeaders;

  public GetClassificationOnFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetClassificationOnFileHeaders(GetClassificationOnFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetClassificationOnFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetClassificationOnFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetClassificationOnFileHeaders build() {
      return new GetClassificationOnFileHeaders(this);
    }
  }
}
