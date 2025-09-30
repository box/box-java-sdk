package com.box.sdkgen.managers.zipdownloads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateZipDownloadHeaders {

  public Map<String, String> extraHeaders;

  public CreateZipDownloadHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateZipDownloadHeaders(Builder builder) {
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

    public CreateZipDownloadHeaders build() {
      return new CreateZipDownloadHeaders(this);
    }
  }
}
