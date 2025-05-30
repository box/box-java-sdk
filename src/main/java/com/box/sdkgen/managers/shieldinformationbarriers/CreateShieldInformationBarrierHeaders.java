package com.box.sdkgen.managers.shieldinformationbarriers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateShieldInformationBarrierHeaders {

  public Map<String, String> extraHeaders;

  public CreateShieldInformationBarrierHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateShieldInformationBarrierHeaders(
      CreateShieldInformationBarrierHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateShieldInformationBarrierHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateShieldInformationBarrierHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateShieldInformationBarrierHeaders build() {
      return new CreateShieldInformationBarrierHeaders(this);
    }
  }
}
