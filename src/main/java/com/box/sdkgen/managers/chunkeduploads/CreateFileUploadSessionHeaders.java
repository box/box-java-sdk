package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateFileUploadSessionHeaders {

  public Map<String, String> extraHeaders;

  public CreateFileUploadSessionHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateFileUploadSessionHeaders(CreateFileUploadSessionHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateFileUploadSessionHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateFileUploadSessionHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateFileUploadSessionHeaders build() {
      return new CreateFileUploadSessionHeaders(this);
    }
  }
}
