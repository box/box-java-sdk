package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierSegmentMembersHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierSegmentMembersHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierSegmentMembersHeaders(
      GetShieldInformationBarrierSegmentMembersHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetShieldInformationBarrierSegmentMembersHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetShieldInformationBarrierSegmentMembersHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetShieldInformationBarrierSegmentMembersHeaders build() {
      return new GetShieldInformationBarrierSegmentMembersHeaders(this);
    }
  }
}
