package com.box.sdkgen.managers.memberships;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetGroupMembershipByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetGroupMembershipByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetGroupMembershipByIdHeaders(GetGroupMembershipByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetGroupMembershipByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetGroupMembershipByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetGroupMembershipByIdHeaders build() {
      return new GetGroupMembershipByIdHeaders(this);
    }
  }
}
