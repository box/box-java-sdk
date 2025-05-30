package com.box.sdkgen.managers.authorization;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RequestAccessTokenHeaders {

  public Map<String, String> extraHeaders;

  public RequestAccessTokenHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RequestAccessTokenHeaders(RequestAccessTokenHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class RequestAccessTokenHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public RequestAccessTokenHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public RequestAccessTokenHeaders build() {
      return new RequestAccessTokenHeaders(this);
    }
  }
}
