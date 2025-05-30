package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileUploadSessionPartsByUrlHeaders {

  public Map<String, String> extraHeaders;

  public GetFileUploadSessionPartsByUrlHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileUploadSessionPartsByUrlHeaders(
      GetFileUploadSessionPartsByUrlHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileUploadSessionPartsByUrlHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileUploadSessionPartsByUrlHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileUploadSessionPartsByUrlHeaders build() {
      return new GetFileUploadSessionPartsByUrlHeaders(this);
    }
  }
}
