package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteFileUploadSessionByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteFileUploadSessionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteFileUploadSessionByIdHeaders(DeleteFileUploadSessionByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteFileUploadSessionByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteFileUploadSessionByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteFileUploadSessionByIdHeaders build() {
      return new DeleteFileUploadSessionByIdHeaders(this);
    }
  }
}
