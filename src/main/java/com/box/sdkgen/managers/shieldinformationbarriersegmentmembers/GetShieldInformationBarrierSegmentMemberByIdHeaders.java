package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierSegmentMemberByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierSegmentMemberByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierSegmentMemberByIdHeaders(
      GetShieldInformationBarrierSegmentMemberByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetShieldInformationBarrierSegmentMemberByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetShieldInformationBarrierSegmentMemberByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetShieldInformationBarrierSegmentMemberByIdHeaders build() {
      return new GetShieldInformationBarrierSegmentMemberByIdHeaders(this);
    }
  }
}
