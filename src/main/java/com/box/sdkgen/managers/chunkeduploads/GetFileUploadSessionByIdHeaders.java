package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileUploadSessionByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetFileUploadSessionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileUploadSessionByIdHeaders(GetFileUploadSessionByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileUploadSessionByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileUploadSessionByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileUploadSessionByIdHeaders build() {
      return new GetFileUploadSessionByIdHeaders(this);
    }
  }
}
