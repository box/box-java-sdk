package com.box.sdkgen.managers.retentionpolicies;

import java.util.List;

public class GetRetentionPolicyByIdQueryParams {

  public List<String> fields;

  public GetRetentionPolicyByIdQueryParams() {}

  protected GetRetentionPolicyByIdQueryParams(Builder builder) {
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

    public GetRetentionPolicyByIdQueryParams build() {
      return new GetRetentionPolicyByIdQueryParams(this);
    }
  }
}
