package com.box.sdkgen.managers.termsofservices;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateTermsOfServiceHeaders {

  public Map<String, String> extraHeaders;

  public CreateTermsOfServiceHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateTermsOfServiceHeaders(CreateTermsOfServiceHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateTermsOfServiceHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateTermsOfServiceHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateTermsOfServiceHeaders build() {
      return new CreateTermsOfServiceHeaders(this);
    }
  }
}
