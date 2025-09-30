package com.box.sdkgen.managers.retentionpolicyassignments;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetRetentionPolicyAssignmentsQueryParams {

  public EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField> type;

  public List<String> fields;

  public String marker;

  public Long limit;

  public GetRetentionPolicyAssignmentsQueryParams() {}

  protected GetRetentionPolicyAssignmentsQueryParams(Builder builder) {
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

  public static class Builder {

    protected EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField> type;

    protected List<String> fields;

    protected String marker;

    protected Long limit;

    public Builder type(GetRetentionPolicyAssignmentsQueryParamsTypeField type) {
      this.type = new EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<GetRetentionPolicyAssignmentsQueryParamsTypeField> type) {
      this.type = type;
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

    public GetRetentionPolicyAssignmentsQueryParams build() {
      return new GetRetentionPolicyAssignmentsQueryParams(this);
    }
  }
}
