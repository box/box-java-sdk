package com.box.sdkgen.managers.termsofservices;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTermsOfServiceHeaders {

  public Map<String, String> extraHeaders;

  public GetTermsOfServiceHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTermsOfServiceHeaders(Builder builder) {
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

    public GetTermsOfServiceHeaders build() {
      return new GetTermsOfServiceHeaders(this);
    }
  }
}
