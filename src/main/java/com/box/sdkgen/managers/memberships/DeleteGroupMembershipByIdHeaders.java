package com.box.sdkgen.managers.memberships;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteGroupMembershipByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteGroupMembershipByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteGroupMembershipByIdHeaders(DeleteGroupMembershipByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteGroupMembershipByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteGroupMembershipByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteGroupMembershipByIdHeaders build() {
      return new DeleteGroupMembershipByIdHeaders(this);
    }
  }
}
