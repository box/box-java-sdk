package com.box.sdkgen.managers.memberships;

import java.util.List;

public class UpdateGroupMembershipByIdQueryParams {

  public List<String> fields;

  public UpdateGroupMembershipByIdQueryParams() {}

  protected UpdateGroupMembershipByIdQueryParams(Builder builder) {
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

    public UpdateGroupMembershipByIdQueryParams build() {
      return new UpdateGroupMembershipByIdQueryParams(this);
    }
  }
}
