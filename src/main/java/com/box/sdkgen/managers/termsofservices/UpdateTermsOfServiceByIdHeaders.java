package com.box.sdkgen.managers.termsofservices;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateTermsOfServiceByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateTermsOfServiceByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateTermsOfServiceByIdHeaders(UpdateTermsOfServiceByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateTermsOfServiceByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateTermsOfServiceByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateTermsOfServiceByIdHeaders build() {
      return new UpdateTermsOfServiceByIdHeaders(this);
    }
  }
}
