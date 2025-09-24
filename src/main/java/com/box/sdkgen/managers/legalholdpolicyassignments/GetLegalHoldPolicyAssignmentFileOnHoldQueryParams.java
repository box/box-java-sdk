package com.box.sdkgen.managers.legalholdpolicyassignments;

import java.util.List;

public class GetLegalHoldPolicyAssignmentFileOnHoldQueryParams {

  public String marker;

  public Long limit;

  public List<String> fields;

  public GetLegalHoldPolicyAssignmentFileOnHoldQueryParams() {}

  protected GetLegalHoldPolicyAssignmentFileOnHoldQueryParams(Builder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
    this.fields = builder.fields;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class Builder {

    protected String marker;

    protected Long limit;

    protected List<String> fields;

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetLegalHoldPolicyAssignmentFileOnHoldQueryParams build() {
      return new GetLegalHoldPolicyAssignmentFileOnHoldQueryParams(this);
    }
  }
}
