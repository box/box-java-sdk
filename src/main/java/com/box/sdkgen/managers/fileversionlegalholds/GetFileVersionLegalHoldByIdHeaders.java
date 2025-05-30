package com.box.sdkgen.managers.fileversionlegalholds;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileVersionLegalHoldByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetFileVersionLegalHoldByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileVersionLegalHoldByIdHeaders(GetFileVersionLegalHoldByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileVersionLegalHoldByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileVersionLegalHoldByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileVersionLegalHoldByIdHeaders build() {
      return new GetFileVersionLegalHoldByIdHeaders(this);
    }
  }
}
