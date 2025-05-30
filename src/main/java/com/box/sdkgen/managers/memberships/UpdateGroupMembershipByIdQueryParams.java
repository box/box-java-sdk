package com.box.sdkgen.managers.memberships;

import java.util.List;

public class UpdateGroupMembershipByIdQueryParams {

  public List<String> fields;

  public UpdateGroupMembershipByIdQueryParams() {}

  protected UpdateGroupMembershipByIdQueryParams(
      UpdateGroupMembershipByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class UpdateGroupMembershipByIdQueryParamsBuilder {

    protected List<String> fields;

    public UpdateGroupMembershipByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public UpdateGroupMembershipByIdQueryParams build() {
      return new UpdateGroupMembershipByIdQueryParams(this);
    }
  }
}
