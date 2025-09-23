package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierSegmentRestrictionsHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierSegmentRestrictionsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierSegmentRestrictionsHeaders(Builder builder) {
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

    public GetShieldInformationBarrierSegmentRestrictionsHeaders build() {
      return new GetShieldInformationBarrierSegmentRestrictionsHeaders(this);
    }
  }
}
