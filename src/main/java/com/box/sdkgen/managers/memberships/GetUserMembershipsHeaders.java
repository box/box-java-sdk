package com.box.sdkgen.managers.memberships;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetUserMembershipsHeaders {

  public Map<String, String> extraHeaders;

  public GetUserMembershipsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetUserMembershipsHeaders(Builder builder) {
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

    public GetUserMembershipsHeaders build() {
      return new GetUserMembershipsHeaders(this);
    }
  }
}
