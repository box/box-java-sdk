package com.box.sdkgen.managers.shieldinformationbarriersegments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateShieldInformationBarrierSegmentHeaders {

  public Map<String, String> extraHeaders;

  public CreateShieldInformationBarrierSegmentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateShieldInformationBarrierSegmentHeaders(
      CreateShieldInformationBarrierSegmentHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateShieldInformationBarrierSegmentHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateShieldInformationBarrierSegmentHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateShieldInformationBarrierSegmentHeaders build() {
      return new CreateShieldInformationBarrierSegmentHeaders(this);
    }
  }
}
