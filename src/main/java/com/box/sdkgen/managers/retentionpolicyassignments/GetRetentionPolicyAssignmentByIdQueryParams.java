package com.box.sdkgen.managers.retentionpolicyassignments;

import java.util.List;

public class GetRetentionPolicyAssignmentByIdQueryParams {

  public List<String> fields;

  public GetRetentionPolicyAssignmentByIdQueryParams() {}

  protected GetRetentionPolicyAssignmentByIdQueryParams(Builder builder) {
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

    public GetRetentionPolicyAssignmentByIdQueryParams build() {
      return new GetRetentionPolicyAssignmentByIdQueryParams(this);
    }
  }
}
