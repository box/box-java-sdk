package com.box.sdkgen.managers.termsofservices;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTermsOfServiceHeaders {

  public Map<String, String> extraHeaders;

  public GetTermsOfServiceHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTermsOfServiceHeaders(GetTermsOfServiceHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetTermsOfServiceHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetTermsOfServiceHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetTermsOfServiceHeaders build() {
      return new GetTermsOfServiceHeaders(this);
    }
  }
}
