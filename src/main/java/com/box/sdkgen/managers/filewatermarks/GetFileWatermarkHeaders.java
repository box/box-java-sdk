package com.box.sdkgen.managers.filewatermarks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileWatermarkHeaders {

  public Map<String, String> extraHeaders;

  public GetFileWatermarkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileWatermarkHeaders(Builder builder) {
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

    public GetFileWatermarkHeaders build() {
      return new GetFileWatermarkHeaders(this);
    }
  }
}
