package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateFileUploadSessionForExistingFileHeaders {

  public Map<String, String> extraHeaders;

  public CreateFileUploadSessionForExistingFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateFileUploadSessionForExistingFileHeaders(
      CreateFileUploadSessionForExistingFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateFileUploadSessionForExistingFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateFileUploadSessionForExistingFileHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateFileUploadSessionForExistingFileHeaders build() {
      return new CreateFileUploadSessionForExistingFileHeaders(this);
    }
  }
}
