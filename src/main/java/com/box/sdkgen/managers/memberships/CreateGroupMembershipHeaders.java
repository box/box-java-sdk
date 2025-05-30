package com.box.sdkgen.managers.memberships;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateGroupMembershipHeaders {

  public Map<String, String> extraHeaders;

  public CreateGroupMembershipHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateGroupMembershipHeaders(CreateGroupMembershipHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateGroupMembershipHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateGroupMembershipHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateGroupMembershipHeaders build() {
      return new CreateGroupMembershipHeaders(this);
    }
  }
}
