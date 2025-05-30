package com.box.sdkgen.managers.users;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetUsersHeaders {

  public Map<String, String> extraHeaders;

  public GetUsersHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetUsersHeaders(GetUsersHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetUsersHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetUsersHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetUsersHeaders build() {
      return new GetUsersHeaders(this);
    }
  }
}
