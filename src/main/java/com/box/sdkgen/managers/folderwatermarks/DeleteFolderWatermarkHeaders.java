package com.box.sdkgen.managers.folderwatermarks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteFolderWatermarkHeaders {

  public Map<String, String> extraHeaders;

  public DeleteFolderWatermarkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteFolderWatermarkHeaders(DeleteFolderWatermarkHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteFolderWatermarkHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteFolderWatermarkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteFolderWatermarkHeaders build() {
      return new DeleteFolderWatermarkHeaders(this);
    }
  }
}
