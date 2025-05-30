package com.box.sdkgen.managers.shieldinformationbarriers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateShieldInformationBarrierStatusHeaders {

  public Map<String, String> extraHeaders;

  public UpdateShieldInformationBarrierStatusHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateShieldInformationBarrierStatusHeaders(
      UpdateShieldInformationBarrierStatusHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateShieldInformationBarrierStatusHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateShieldInformationBarrierStatusHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateShieldInformationBarrierStatusHeaders build() {
      return new UpdateShieldInformationBarrierStatusHeaders(this);
    }
  }
}
