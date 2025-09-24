package com.box.sdkgen.managers.devicepinners;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetDevicePinnerByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetDevicePinnerByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetDevicePinnerByIdHeaders(Builder builder) {
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

    public GetDevicePinnerByIdHeaders build() {
      return new GetDevicePinnerByIdHeaders(this);
    }
  }
}
