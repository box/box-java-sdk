package com.box.sdkgen.managers.zipdownloads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetZipDownloadStatusHeaders {

  public Map<String, String> extraHeaders;

  public GetZipDownloadStatusHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetZipDownloadStatusHeaders(GetZipDownloadStatusHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetZipDownloadStatusHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetZipDownloadStatusHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetZipDownloadStatusHeaders build() {
      return new GetZipDownloadStatusHeaders(this);
    }
  }
}
