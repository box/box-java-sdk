package com.box.sdkgen.managers.fileversionlegalholds;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileVersionLegalHoldsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileVersionLegalHoldsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileVersionLegalHoldsHeaders(GetFileVersionLegalHoldsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileVersionLegalHoldsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileVersionLegalHoldsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileVersionLegalHoldsHeaders build() {
      return new GetFileVersionLegalHoldsHeaders(this);
    }
  }
}
