package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateShieldInformationBarrierSegmentMemberHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public CreateShieldInformationBarrierSegmentMemberHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateShieldInformationBarrierSegmentMemberHeaders(Builder builder) {
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

    public CreateShieldInformationBarrierSegmentMemberHeaders build() {
      return new CreateShieldInformationBarrierSegmentMemberHeaders(this);
    }
  }
}
