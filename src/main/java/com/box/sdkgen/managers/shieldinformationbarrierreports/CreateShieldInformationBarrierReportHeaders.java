package com.box.sdkgen.managers.shieldinformationbarrierreports;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateShieldInformationBarrierReportHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public CreateShieldInformationBarrierReportHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateShieldInformationBarrierReportHeaders(Builder builder) {
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

    public CreateShieldInformationBarrierReportHeaders build() {
      return new CreateShieldInformationBarrierReportHeaders(this);
    }
  }
}
