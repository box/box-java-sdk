package com.box.sdkgen.managers.filewatermarks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileWatermarkHeaders {

  public Map<String, String> extraHeaders;

  public GetFileWatermarkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileWatermarkHeaders(GetFileWatermarkHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileWatermarkHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileWatermarkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileWatermarkHeaders build() {
      return new GetFileWatermarkHeaders(this);
    }
  }
}
