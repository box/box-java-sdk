package com.box.sdkgen.managers.fileclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateClassificationOnFileHeaders {

  public Map<String, String> extraHeaders;

  public UpdateClassificationOnFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateClassificationOnFileHeaders(UpdateClassificationOnFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateClassificationOnFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateClassificationOnFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateClassificationOnFileHeaders build() {
      return new UpdateClassificationOnFileHeaders(this);
    }
  }
}
