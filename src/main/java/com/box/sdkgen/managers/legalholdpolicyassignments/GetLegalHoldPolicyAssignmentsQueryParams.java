package com.box.sdkgen.managers.legalholdpolicyassignments;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetLegalHoldPolicyAssignmentsQueryParams {

  public final String policyId;

  public EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField> assignToType;

  public String assignToId;

  public String marker;

  public Long limit;

  public List<String> fields;

  public GetLegalHoldPolicyAssignmentsQueryParams(String policyId) {
    this.policyId = policyId;
  }

  protected GetLegalHoldPolicyAssignmentsQueryParams(
      GetLegalHoldPolicyAssignmentsQueryParamsBuilder builder) {
    this.policyId = builder.policyId;
    this.assignToType = builder.assignToType;
    this.assignToId = builder.assignToId;
    this.marker = builder.marker;
    this.limit = builder.limit;
    this.fields = builder.fields;
  }

  public String getPolicyId() {
    return policyId;
  }

  public EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField> getAssignToType() {
    return assignToType;
  }

  public String getAssignToId() {
    return assignToId;
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

  public static class GetLegalHoldPolicyAssignmentsQueryParamsBuilder {

    protected final String policyId;

    protected EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField> assignToType;

    protected String assignToId;

    protected String marker;

    protected Long limit;

    protected List<String> fields;

    public GetLegalHoldPolicyAssignmentsQueryParamsBuilder(String policyId) {
      this.policyId = policyId;
    }

    public GetLegalHoldPolicyAssignmentsQueryParamsBuilder assignToType(
        GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField assignToType) {
      this.assignToType =
          new EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField>(assignToType);
      return this;
    }

    public GetLegalHoldPolicyAssignmentsQueryParamsBuilder assignToType(
        EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField> assignToType) {
      this.assignToType = assignToType;
      return this;
    }

    public GetLegalHoldPolicyAssignmentsQueryParamsBuilder assignToId(String assignToId) {
      this.assignToId = assignToId;
      return this;
    }

    public GetLegalHoldPolicyAssignmentsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetLegalHoldPolicyAssignmentsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetLegalHoldPolicyAssignmentsQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetLegalHoldPolicyAssignmentsQueryParams build() {
      return new GetLegalHoldPolicyAssignmentsQueryParams(this);
    }
  }
}
