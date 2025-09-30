package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders(Builder builder) {
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

    public DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders build() {
      return new DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders(this);
    }
  }
}
