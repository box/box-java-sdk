package com.box.sdkgen.managers.termsofserviceuserstatuses;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTermsOfServiceUserStatusesHeaders {

  public Map<String, String> extraHeaders;

  public GetTermsOfServiceUserStatusesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTermsOfServiceUserStatusesHeaders(
      GetTermsOfServiceUserStatusesHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetTermsOfServiceUserStatusesHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetTermsOfServiceUserStatusesHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetTermsOfServiceUserStatusesHeaders build() {
      return new GetTermsOfServiceUserStatusesHeaders(this);
    }
  }
}
