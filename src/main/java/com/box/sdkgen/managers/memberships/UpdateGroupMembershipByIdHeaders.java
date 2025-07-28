package com.box.sdkgen.managers.memberships;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateGroupMembershipByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateGroupMembershipByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateGroupMembershipByIdHeaders(Builder builder) {
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

    public UpdateGroupMembershipByIdHeaders build() {
      return new UpdateGroupMembershipByIdHeaders(this);
    }
  }
}
