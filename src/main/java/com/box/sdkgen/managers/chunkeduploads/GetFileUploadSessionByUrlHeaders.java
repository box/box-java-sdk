package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileUploadSessionByUrlHeaders {

  public Map<String, String> extraHeaders;

  public GetFileUploadSessionByUrlHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileUploadSessionByUrlHeaders(GetFileUploadSessionByUrlHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileUploadSessionByUrlHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileUploadSessionByUrlHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileUploadSessionByUrlHeaders build() {
      return new GetFileUploadSessionByUrlHeaders(this);
    }
  }
}
