package com.box.sdkgen.managers.shieldinformationbarrierreports;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierReportByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierReportByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierReportByIdHeaders(
      GetShieldInformationBarrierReportByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetShieldInformationBarrierReportByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetShieldInformationBarrierReportByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetShieldInformationBarrierReportByIdHeaders build() {
      return new GetShieldInformationBarrierReportByIdHeaders(this);
    }
  }
}
