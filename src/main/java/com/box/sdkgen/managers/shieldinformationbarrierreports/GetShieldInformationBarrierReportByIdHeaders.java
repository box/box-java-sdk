package com.box.sdkgen.managers.shieldinformationbarrierreports;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierReportByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierReportByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierReportByIdHeaders(Builder builder) {
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

    public GetShieldInformationBarrierReportByIdHeaders build() {
      return new GetShieldInformationBarrierReportByIdHeaders(this);
    }
  }
}
