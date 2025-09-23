package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileUploadSessionPartsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileUploadSessionPartsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileUploadSessionPartsHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileUploadSessionPartsHeaders build() {
      return new GetFileUploadSessionPartsHeaders(this);
    }
  }
}
