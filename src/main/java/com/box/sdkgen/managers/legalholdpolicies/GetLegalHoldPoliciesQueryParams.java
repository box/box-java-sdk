package com.box.sdkgen.managers.legalholdpolicies;

import java.util.List;

public class GetLegalHoldPoliciesQueryParams {

  /**
   * Limits results to policies for which the names start with this search term. This is a
   * case-insensitive prefix.
   */
  public String policyName;

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   */
  public List<String> fields;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetLegalHoldPoliciesQueryParams() {}

  protected GetLegalHoldPoliciesQueryParams(Builder builder) {
    this.policyName = builder.policyName;
    this.fields = builder.fields;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getPolicyName() {
    return policyName;
  }

  public List<String> getFields() {
    return fields;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected String policyName;

    protected List<String> fields;

    protected String marker;

    protected Long limit;

    public Builder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetLegalHoldPoliciesQueryParams build() {
      return new GetLegalHoldPoliciesQueryParams(this);
    }
  }
}
