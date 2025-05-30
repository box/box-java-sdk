package com.box.sdkgen.managers.shieldinformationbarriers;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetShieldInformationBarriersHeaders {

  public Map<String, String> extraHeaders;

  public GetShieldInformationBarriersHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetShieldInformationBarriersHeaders(
      GetShieldInformationBarriersHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetShieldInformationBarriersHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetShieldInformationBarriersHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetShieldInformationBarriersHeaders build() {
      return new GetShieldInformationBarriersHeaders(this);
    }
  }
}
