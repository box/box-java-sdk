package com.box.sdkgen.managers.retentionpolicyassignments;

public class GetFilesUnderRetentionPolicyAssignmentQueryParams {

  public String marker;

  public Long limit;

  public GetFilesUnderRetentionPolicyAssignmentQueryParams() {}

  protected GetFilesUnderRetentionPolicyAssignmentQueryParams(
      GetFilesUnderRetentionPolicyAssignmentQueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetFilesUnderRetentionPolicyAssignmentQueryParamsBuilder {

    protected String marker;

    protected Long limit;

    public GetFilesUnderRetentionPolicyAssignmentQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetFilesUnderRetentionPolicyAssignmentQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFilesUnderRetentionPolicyAssignmentQueryParams build() {
      return new GetFilesUnderRetentionPolicyAssignmentQueryParams(this);
    }
  }
}
