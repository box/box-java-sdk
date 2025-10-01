package com.box.sdkgen.managers.termsofserviceuserstatuses;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateTermsOfServiceStatusForUserByIdHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public UpdateTermsOfServiceStatusForUserByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateTermsOfServiceStatusForUserByIdHeaders(Builder builder) {
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

    public UpdateTermsOfServiceStatusForUserByIdHeaders build() {
      return new UpdateTermsOfServiceStatusForUserByIdHeaders(this);
    }
  }
}
