package com.box.sdkgen.managers.devicepinners;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetEnterpriseDevicePinnersHeaders {

  public Map<String, String> extraHeaders;

  public GetEnterpriseDevicePinnersHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetEnterpriseDevicePinnersHeaders(Builder builder) {
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

    public GetEnterpriseDevicePinnersHeaders build() {
      return new GetEnterpriseDevicePinnersHeaders(this);
    }
  }
}
