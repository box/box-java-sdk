package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileUploadSessionPartsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileUploadSessionPartsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileUploadSessionPartsHeaders(GetFileUploadSessionPartsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileUploadSessionPartsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileUploadSessionPartsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileUploadSessionPartsHeaders build() {
      return new GetFileUploadSessionPartsHeaders(this);
    }
  }
}
