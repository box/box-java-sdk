package com.box.sdkgen.managers.shieldinformationbarriersegments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateShieldInformationBarrierSegmentHeaders {

  public Map<String, String> extraHeaders;

  public CreateShieldInformationBarrierSegmentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateShieldInformationBarrierSegmentHeaders(Builder builder) {
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

    public CreateShieldInformationBarrierSegmentHeaders build() {
      return new CreateShieldInformationBarrierSegmentHeaders(this);
    }
  }
}
