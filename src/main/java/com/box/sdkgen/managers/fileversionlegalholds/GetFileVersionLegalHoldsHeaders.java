package com.box.sdkgen.managers.fileversionlegalholds;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileVersionLegalHoldsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileVersionLegalHoldsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileVersionLegalHoldsHeaders(Builder builder) {
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

    public GetFileVersionLegalHoldsHeaders build() {
      return new GetFileVersionLegalHoldsHeaders(this);
    }
  }
}
