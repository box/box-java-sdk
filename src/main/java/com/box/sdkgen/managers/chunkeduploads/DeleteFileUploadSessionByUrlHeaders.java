package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteFileUploadSessionByUrlHeaders {

  public Map<String, String> extraHeaders;

  public DeleteFileUploadSessionByUrlHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteFileUploadSessionByUrlHeaders(
      DeleteFileUploadSessionByUrlHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteFileUploadSessionByUrlHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteFileUploadSessionByUrlHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteFileUploadSessionByUrlHeaders build() {
      return new DeleteFileUploadSessionByUrlHeaders(this);
    }
  }
}
