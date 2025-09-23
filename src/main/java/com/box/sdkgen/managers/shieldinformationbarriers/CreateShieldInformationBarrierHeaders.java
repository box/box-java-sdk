package com.box.sdkgen.managers.shieldinformationbarriers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateShieldInformationBarrierHeaders {

  public Map<String, String> extraHeaders;

  public CreateShieldInformationBarrierHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateShieldInformationBarrierHeaders(Builder builder) {
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

    public CreateShieldInformationBarrierHeaders build() {
      return new CreateShieldInformationBarrierHeaders(this);
    }
  }
}
