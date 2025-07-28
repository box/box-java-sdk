package com.box.sdkgen.managers.memberships;

import java.util.List;

public class CreateGroupMembershipQueryParams {

  public List<String> fields;

  public CreateGroupMembershipQueryParams() {}

  protected CreateGroupMembershipQueryParams(Builder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class Builder {

    protected List<String> fields;

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public CreateGroupMembershipQueryParams build() {
      return new CreateGroupMembershipQueryParams(this);
    }
  }
}
