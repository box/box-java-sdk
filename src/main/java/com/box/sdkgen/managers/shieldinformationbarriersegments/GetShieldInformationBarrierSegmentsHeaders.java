package com.box.sdkgen.managers.shieldinformationbarriersegments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierSegmentsHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierSegmentsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierSegmentsHeaders(
      GetShieldInformationBarrierSegmentsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetShieldInformationBarrierSegmentsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetShieldInformationBarrierSegmentsHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetShieldInformationBarrierSegmentsHeaders build() {
      return new GetShieldInformationBarrierSegmentsHeaders(this);
    }
  }
}
