package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileUploadSessionPartsByUrlHeaders {

  public Map<String, String> extraHeaders;

  public GetFileUploadSessionPartsByUrlHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileUploadSessionPartsByUrlHeaders(Builder builder) {
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

    public GetFileUploadSessionPartsByUrlHeaders build() {
      return new GetFileUploadSessionPartsByUrlHeaders(this);
    }
  }
}
