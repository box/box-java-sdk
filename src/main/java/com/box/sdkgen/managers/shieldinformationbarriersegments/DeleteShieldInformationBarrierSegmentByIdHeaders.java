package com.box.sdkgen.managers.shieldinformationbarriersegments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteShieldInformationBarrierSegmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteShieldInformationBarrierSegmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteShieldInformationBarrierSegmentByIdHeaders(
      DeleteShieldInformationBarrierSegmentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteShieldInformationBarrierSegmentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteShieldInformationBarrierSegmentByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteShieldInformationBarrierSegmentByIdHeaders build() {
      return new DeleteShieldInformationBarrierSegmentByIdHeaders(this);
    }
  }
}
