package com.box.sdkgen.managers.zipdownloads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DownloadZipHeaders {

  public Map<String, String> extraHeaders;

  public DownloadZipHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DownloadZipHeaders(DownloadZipHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DownloadZipHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DownloadZipHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DownloadZipHeaders build() {
      return new DownloadZipHeaders(this);
    }
  }
}
