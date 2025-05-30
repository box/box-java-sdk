package com.box.sdkgen.managers.fileclassifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteClassificationFromFileHeaders {

  public Map<String, String> extraHeaders;

  public DeleteClassificationFromFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteClassificationFromFileHeaders(
      DeleteClassificationFromFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteClassificationFromFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteClassificationFromFileHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteClassificationFromFileHeaders build() {
      return new DeleteClassificationFromFileHeaders(this);
    }
  }
}
