package com.box.sdkgen.managers.memberships;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetGroupMembershipsHeaders {

  public Map<String, String> extraHeaders;

  public GetGroupMembershipsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetGroupMembershipsHeaders(GetGroupMembershipsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetGroupMembershipsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetGroupMembershipsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetGroupMembershipsHeaders build() {
      return new GetGroupMembershipsHeaders(this);
    }
  }
}
