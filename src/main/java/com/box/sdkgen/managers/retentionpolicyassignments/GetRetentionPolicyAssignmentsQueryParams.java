package com.box.sdkgen.managers.retentionpolicyassignments;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetRetentionPolicyAssignmentsQueryParams {

  public EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField> type;

  public List<String> fields;

  public String marker;

  public Long limit;

  public GetRetentionPolicyAssignmentsQueryParams() {}

  protected GetRetentionPolicyAssignmentsQueryParams(
      GetRetentionPolicyAssignmentsQueryParamsBuilder builder) {
    this.type = builder.type;
    this.fields = builder.fields;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField> getType() {
    return type;
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

  public static class GetRetentionPolicyAssignmentsQueryParamsBuilder {

    protected EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField> type;

    protected List<String> fields;

    protected String marker;

    protected Long limit;

    public GetRetentionPolicyAssignmentsQueryParamsBuilder type(
        GetRetentionPolicyAssignmentsQueryParamsTypeField type) {
      this.type = new EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField>(type);
      return this;
    }

    public GetRetentionPolicyAssignmentsQueryParamsBuilder type(
        EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField> type) {
      this.type = type;
      return this;
    }

    public GetRetentionPolicyAssignmentsQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetRetentionPolicyAssignmentsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetRetentionPolicyAssignmentsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetRetentionPolicyAssignmentsQueryParams build() {
      return new GetRetentionPolicyAssignmentsQueryParams(this);
    }
  }
}
