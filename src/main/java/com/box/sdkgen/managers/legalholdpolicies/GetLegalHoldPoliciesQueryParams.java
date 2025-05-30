package com.box.sdkgen.managers.legalholdpolicies;

import java.util.List;

public class GetLegalHoldPoliciesQueryParams {

  public String policyName;

  public List<String> fields;

  public String marker;

  public Long limit;

  public GetLegalHoldPoliciesQueryParams() {}

  protected GetLegalHoldPoliciesQueryParams(GetLegalHoldPoliciesQueryParamsBuilder builder) {
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

  public static class GetLegalHoldPoliciesQueryParamsBuilder {

    protected String policyName;

    protected List<String> fields;

    protected String marker;

    protected Long limit;

    public GetLegalHoldPoliciesQueryParamsBuilder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    public GetLegalHoldPoliciesQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetLegalHoldPoliciesQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetLegalHoldPoliciesQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetLegalHoldPoliciesQueryParams build() {
      return new GetLegalHoldPoliciesQueryParams(this);
    }
  }
}
