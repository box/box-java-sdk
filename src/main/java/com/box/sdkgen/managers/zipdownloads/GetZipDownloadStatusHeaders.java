package com.box.sdkgen.managers.zipdownloads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetZipDownloadStatusHeaders {

  public Map<String, String> extraHeaders;

  public GetZipDownloadStatusHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetZipDownloadStatusHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetZipDownloadStatusHeaders build() {
      return new GetZipDownloadStatusHeaders(this);
    }
  }
}
