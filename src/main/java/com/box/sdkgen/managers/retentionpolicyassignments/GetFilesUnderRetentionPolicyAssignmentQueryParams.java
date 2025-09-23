package com.box.sdkgen.managers.retentionpolicyassignments;

public class GetFilesUnderRetentionPolicyAssignmentQueryParams {

  public String marker;

  public Long limit;

  public GetFilesUnderRetentionPolicyAssignmentQueryParams() {}

  protected GetFilesUnderRetentionPolicyAssignmentQueryParams(Builder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected String marker;

    protected Long limit;

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFilesUnderRetentionPolicyAssignmentQueryParams build() {
      return new GetFilesUnderRetentionPolicyAssignmentQueryParams(this);
    }
  }
}
