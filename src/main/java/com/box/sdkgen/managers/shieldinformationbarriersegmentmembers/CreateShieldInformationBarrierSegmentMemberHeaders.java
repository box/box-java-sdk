package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateShieldInformationBarrierSegmentMemberHeaders {

  public Map<String, String> extraHeaders;

  public CreateShieldInformationBarrierSegmentMemberHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateShieldInformationBarrierSegmentMemberHeaders(
      CreateShieldInformationBarrierSegmentMemberHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateShieldInformationBarrierSegmentMemberHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateShieldInformationBarrierSegmentMemberHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateShieldInformationBarrierSegmentMemberHeaders build() {
      return new CreateShieldInformationBarrierSegmentMemberHeaders(this);
    }
  }
}
