package com.box.sdkgen.managers.retentionpolicies;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetRetentionPoliciesQueryParams {

  public String policyName;

  public EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField> policyType;

  public String createdByUserId;

  public List<String> fields;

  public Long limit;

  public String marker;

  public GetRetentionPoliciesQueryParams() {}

  protected GetRetentionPoliciesQueryParams(Builder builder) {
    this.policyName = builder.policyName;
    this.policyType = builder.policyType;
    this.createdByUserId = builder.createdByUserId;
    this.fields = builder.fields;
    this.limit = builder.limit;
    this.marker = builder.marker;
  }

  public String getPolicyName() {
    return policyName;
  }

  public EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField> getPolicyType() {
    return policyType;
  }

  public String getCreatedByUserId() {
    return createdByUserId;
  }

  public List<String> getFields() {
    return fields;
  }

  public Long getLimit() {
    return limit;
  }

  public String getMarker() {
    return marker;
  }

  public static class Builder {

    protected String policyName;

    protected EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField> policyType;

    protected String createdByUserId;

    protected List<String> fields;

    protected Long limit;

    protected String marker;

    public Builder policyName(String policyName) {
      this.policyName = policyName;
      return this;
    }

    public Builder policyType(GetRetentionPoliciesQueryParamsPolicyTypeField policyType) {
      this.policyType = new EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField>(policyType);
      return this;
    }

    public Builder policyType(
        EnumWrapper<GetRetentionPoliciesQueryParamsPolicyTypeField> policyType) {
      this.policyType = policyType;
      return this;
    }

    public Builder createdByUserId(String createdByUserId) {
      this.createdByUserId = createdByUserId;
      return this;
    }

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetRetentionPoliciesQueryParams build() {
      return new GetRetentionPoliciesQueryParams(this);
    }
  }
}
