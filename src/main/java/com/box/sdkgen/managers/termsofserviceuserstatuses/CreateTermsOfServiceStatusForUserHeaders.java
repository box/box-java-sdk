package com.box.sdkgen.managers.termsofserviceuserstatuses;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateTermsOfServiceStatusForUserHeaders {

  public Map<String, String> extraHeaders;

  public CreateTermsOfServiceStatusForUserHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateTermsOfServiceStatusForUserHeaders(
      CreateTermsOfServiceStatusForUserHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateTermsOfServiceStatusForUserHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateTermsOfServiceStatusForUserHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateTermsOfServiceStatusForUserHeaders build() {
      return new CreateTermsOfServiceStatusForUserHeaders(this);
    }
  }
}
