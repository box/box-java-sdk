package com.box.sdkgen.managers.folderwatermarks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateFolderWatermarkHeaders {

  public Map<String, String> extraHeaders;

  public UpdateFolderWatermarkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateFolderWatermarkHeaders(UpdateFolderWatermarkHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateFolderWatermarkHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateFolderWatermarkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateFolderWatermarkHeaders build() {
      return new UpdateFolderWatermarkHeaders(this);
    }
  }
}
