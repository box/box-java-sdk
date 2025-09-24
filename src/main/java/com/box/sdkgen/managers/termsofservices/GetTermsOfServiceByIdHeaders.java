package com.box.sdkgen.managers.termsofservices;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTermsOfServiceByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetTermsOfServiceByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTermsOfServiceByIdHeaders(Builder builder) {
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

    public GetTermsOfServiceByIdHeaders build() {
      return new GetTermsOfServiceByIdHeaders(this);
    }
  }
}
