package com.box.sdkgen.managers.shieldinformationbarriersegments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierSegmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierSegmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierSegmentByIdHeaders(
      GetShieldInformationBarrierSegmentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetShieldInformationBarrierSegmentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetShieldInformationBarrierSegmentByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetShieldInformationBarrierSegmentByIdHeaders build() {
      return new GetShieldInformationBarrierSegmentByIdHeaders(this);
    }
  }
}
