package com.box.sdkgen.managers.retentionpolicies;

import java.util.List;

public class GetRetentionPolicyByIdQueryParams {

  public List<String> fields;

  public GetRetentionPolicyByIdQueryParams() {}

  protected GetRetentionPolicyByIdQueryParams(GetRetentionPolicyByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetRetentionPolicyByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetRetentionPolicyByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetRetentionPolicyByIdQueryParams build() {
      return new GetRetentionPolicyByIdQueryParams(this);
    }
  }
}
