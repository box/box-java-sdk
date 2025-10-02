package com.box.sdkgen.managers.retentionpolicyassignments;

import java.util.List;

public class GetRetentionPolicyAssignmentByIdQueryParams {

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   */
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
