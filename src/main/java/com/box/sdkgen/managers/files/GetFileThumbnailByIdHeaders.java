package com.box.sdkgen.managers.files;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileThumbnailByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetFileThumbnailByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileThumbnailByIdHeaders(GetFileThumbnailByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileThumbnailByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileThumbnailByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileThumbnailByIdHeaders build() {
      return new GetFileThumbnailByIdHeaders(this);
    }
  }
}
