package com.box.sdkgen.managers.memberships;

import java.util.List;

public class CreateGroupMembershipQueryParams {

  public List<String> fields;

  public CreateGroupMembershipQueryParams() {}

  protected CreateGroupMembershipQueryParams(CreateGroupMembershipQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class CreateGroupMembershipQueryParamsBuilder {

    protected List<String> fields;

    public CreateGroupMembershipQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public CreateGroupMembershipQueryParams build() {
      return new CreateGroupMembershipQueryParams(this);
    }
  }
}
