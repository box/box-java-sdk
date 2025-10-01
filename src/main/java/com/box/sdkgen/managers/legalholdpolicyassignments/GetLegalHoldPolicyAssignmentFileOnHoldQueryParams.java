package com.box.sdkgen.managers.legalholdpolicyassignments;

import java.util.List;

public class GetLegalHoldPolicyAssignmentFileOnHoldQueryParams {

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   */
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
