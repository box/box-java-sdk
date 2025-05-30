package com.box.sdkgen.managers.shieldinformationbarrierreports;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateShieldInformationBarrierReportHeaders {

  public Map<String, String> extraHeaders;

  public CreateShieldInformationBarrierReportHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateShieldInformationBarrierReportHeaders(
      CreateShieldInformationBarrierReportHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateShieldInformationBarrierReportHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateShieldInformationBarrierReportHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateShieldInformationBarrierReportHeaders build() {
      return new CreateShieldInformationBarrierReportHeaders(this);
    }
  }
}
