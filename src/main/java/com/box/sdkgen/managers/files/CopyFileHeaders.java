package com.box.sdkgen.managers.files;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CopyFileHeaders {

  public Map<String, String> extraHeaders;

  public CopyFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CopyFileHeaders(CopyFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CopyFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CopyFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CopyFileHeaders build() {
      return new CopyFileHeaders(this);
    }
  }
}
