package com.box.sdkgen.managers.filewatermarks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteFileWatermarkHeaders {

  public Map<String, String> extraHeaders;

  public DeleteFileWatermarkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteFileWatermarkHeaders(DeleteFileWatermarkHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteFileWatermarkHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteFileWatermarkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteFileWatermarkHeaders build() {
      return new DeleteFileWatermarkHeaders(this);
    }
  }
}
