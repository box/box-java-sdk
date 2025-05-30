package com.box.sdkgen.managers.users;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetUserByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetUserByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetUserByIdHeaders(GetUserByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetUserByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetUserByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetUserByIdHeaders build() {
      return new GetUserByIdHeaders(this);
    }
  }
}
