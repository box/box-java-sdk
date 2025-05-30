package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierSegmentRestrictionsHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierSegmentRestrictionsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierSegmentRestrictionsHeaders(
      GetShieldInformationBarrierSegmentRestrictionsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetShieldInformationBarrierSegmentRestrictionsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetShieldInformationBarrierSegmentRestrictionsHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetShieldInformationBarrierSegmentRestrictionsHeaders build() {
      return new GetShieldInformationBarrierSegmentRestrictionsHeaders(this);
    }
  }
}
