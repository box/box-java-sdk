package com.box.sdkgen.managers.shieldinformationbarriers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarrierByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarrierByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarrierByIdHeaders(
      GetShieldInformationBarrierByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetShieldInformationBarrierByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetShieldInformationBarrierByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetShieldInformationBarrierByIdHeaders build() {
      return new GetShieldInformationBarrierByIdHeaders(this);
    }
  }
}
