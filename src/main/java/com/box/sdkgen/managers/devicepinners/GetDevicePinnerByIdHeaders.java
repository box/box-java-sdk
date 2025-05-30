package com.box.sdkgen.managers.devicepinners;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetDevicePinnerByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetDevicePinnerByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetDevicePinnerByIdHeaders(GetDevicePinnerByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetDevicePinnerByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetDevicePinnerByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetDevicePinnerByIdHeaders build() {
      return new GetDevicePinnerByIdHeaders(this);
    }
  }
}
