package com.box.sdkgen.managers.fileversions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileVersionByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetFileVersionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileVersionByIdHeaders(GetFileVersionByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileVersionByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileVersionByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileVersionByIdHeaders build() {
      return new GetFileVersionByIdHeaders(this);
    }
  }
}
