package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierSegmentRestrictionByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierSegmentRestrictionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierSegmentRestrictionByIdHeaders(
      GetShieldInformationBarrierSegmentRestrictionByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetShieldInformationBarrierSegmentRestrictionByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetShieldInformationBarrierSegmentRestrictionByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetShieldInformationBarrierSegmentRestrictionByIdHeaders build() {
      return new GetShieldInformationBarrierSegmentRestrictionByIdHeaders(this);
    }
  }
}
