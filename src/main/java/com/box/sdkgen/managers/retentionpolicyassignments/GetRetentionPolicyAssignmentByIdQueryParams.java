package com.box.sdkgen.managers.retentionpolicyassignments;

import java.util.List;

public class GetRetentionPolicyAssignmentByIdQueryParams {

  public List<String> fields;

  public GetRetentionPolicyAssignmentByIdQueryParams() {}

  protected GetRetentionPolicyAssignmentByIdQueryParams(
      GetRetentionPolicyAssignmentByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetRetentionPolicyAssignmentByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetRetentionPolicyAssignmentByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetRetentionPolicyAssignmentByIdQueryParams build() {
      return new GetRetentionPolicyAssignmentByIdQueryParams(this);
    }
  }
}
