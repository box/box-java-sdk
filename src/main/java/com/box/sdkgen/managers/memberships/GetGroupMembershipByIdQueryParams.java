package com.box.sdkgen.managers.memberships;

import java.util.List;

public class GetGroupMembershipByIdQueryParams {

  public List<String> fields;

  public GetGroupMembershipByIdQueryParams() {}

  protected GetGroupMembershipByIdQueryParams(GetGroupMembershipByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetGroupMembershipByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetGroupMembershipByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetGroupMembershipByIdQueryParams build() {
      return new GetGroupMembershipByIdQueryParams(this);
    }
  }
}
