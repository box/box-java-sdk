package com.box.sdkgen.managers.devicepinners;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteDevicePinnerByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteDevicePinnerByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteDevicePinnerByIdHeaders(DeleteDevicePinnerByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteDevicePinnerByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteDevicePinnerByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteDevicePinnerByIdHeaders build() {
      return new DeleteDevicePinnerByIdHeaders(this);
    }
  }
}
