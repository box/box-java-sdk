package com.box.sdkgen.managers.devicepinners;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetEnterpriseDevicePinnersHeaders {

  public Map<String, String> extraHeaders;

  public GetEnterpriseDevicePinnersHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetEnterpriseDevicePinnersHeaders(GetEnterpriseDevicePinnersHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetEnterpriseDevicePinnersHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetEnterpriseDevicePinnersHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetEnterpriseDevicePinnersHeaders build() {
      return new GetEnterpriseDevicePinnersHeaders(this);
    }
  }
}
