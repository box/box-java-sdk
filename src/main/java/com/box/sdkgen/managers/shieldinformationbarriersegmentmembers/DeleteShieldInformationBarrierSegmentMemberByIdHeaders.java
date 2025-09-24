package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteShieldInformationBarrierSegmentMemberByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteShieldInformationBarrierSegmentMemberByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteShieldInformationBarrierSegmentMemberByIdHeaders(Builder builder) {
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

    public DeleteShieldInformationBarrierSegmentMemberByIdHeaders build() {
      return new DeleteShieldInformationBarrierSegmentMemberByIdHeaders(this);
    }
  }
}
