package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders(
      DeleteShieldInformationBarrierSegmentRestrictionByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteShieldInformationBarrierSegmentRestrictionByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteShieldInformationBarrierSegmentRestrictionByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders build() {
      return new DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders(this);
    }
  }
}
