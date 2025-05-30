package com.box.sdkgen.managers.fileversions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileVersionsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileVersionsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileVersionsHeaders(GetFileVersionsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileVersionsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileVersionsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileVersionsHeaders build() {
      return new GetFileVersionsHeaders(this);
    }
  }
}
