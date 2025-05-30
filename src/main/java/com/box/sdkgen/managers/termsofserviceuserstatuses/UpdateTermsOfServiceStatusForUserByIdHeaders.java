package com.box.sdkgen.managers.termsofserviceuserstatuses;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateTermsOfServiceStatusForUserByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateTermsOfServiceStatusForUserByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateTermsOfServiceStatusForUserByIdHeaders(
      UpdateTermsOfServiceStatusForUserByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateTermsOfServiceStatusForUserByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateTermsOfServiceStatusForUserByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateTermsOfServiceStatusForUserByIdHeaders build() {
      return new UpdateTermsOfServiceStatusForUserByIdHeaders(this);
    }
  }
}
