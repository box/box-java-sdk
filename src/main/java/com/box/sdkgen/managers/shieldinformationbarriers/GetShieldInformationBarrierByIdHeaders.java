package com.box.sdkgen.managers.shieldinformationbarriers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierByIdHeaders(Builder builder) {
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

    public GetShieldInformationBarrierByIdHeaders build() {
      return new GetShieldInformationBarrierByIdHeaders(this);
    }
  }
}
