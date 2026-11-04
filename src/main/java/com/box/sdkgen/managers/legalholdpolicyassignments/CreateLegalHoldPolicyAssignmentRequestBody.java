package com.box.sdkgen.managers.legalholdpolicyassignments;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.legalholdpolicyassignmentfilterfield.LegalHoldPolicyAssignmentFilterField;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateLegalHoldPolicyAssignmentRequestBody extends SerializableObject {

  /** The ID of the policy to assign. */
  @JsonProperty("policy_id")
  protected final String policyId;

  /** The item to assign the policy to. */
  @JsonProperty("assign_to")
  protected final CreateLegalHoldPolicyAssignmentRequestBodyAssignToField assignTo;

  /**
   * List of metadata template field filters. Only required when `assign_to.type` is
   * `metadata_template`.
   */
  @JsonProperty("filter_fields")
  protected List<LegalHoldPolicyAssignmentFilterField> filterFields;

  public CreateLegalHoldPolicyAssignmentRequestBody(
      @JsonProperty("policy_id") String policyId,
      @JsonProperty("assign_to") CreateLegalHoldPolicyAssignmentRequestBodyAssignToField assignTo) {
    super();
    this.policyId = policyId;
    this.assignTo = assignTo;
  }

  protected CreateLegalHoldPolicyAssignmentRequestBody(Builder builder) {
    super();
    this.policyId = builder.policyId;
    this.assignTo = builder.assignTo;
    this.filterFields = builder.filterFields;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getPolicyId() {
    return policyId;
  }

  public CreateLegalHoldPolicyAssignmentRequestBodyAssignToField getAssignTo() {
    return assignTo;
  }

  public List<LegalHoldPolicyAssignmentFilterField> getFilterFields() {
    return filterFields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateLegalHoldPolicyAssignmentRequestBody casted =
        (CreateLegalHoldPolicyAssignmentRequestBody) o;
    return Objects.equals(policyId, casted.policyId)
        && Objects.equals(assignTo, casted.assignTo)
        && Objects.equals(filterFields, casted.filterFields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(policyId, assignTo, filterFields);
  }

  @Override
  public String toString() {
    return "CreateLegalHoldPolicyAssignmentRequestBody{"
        + "policyId='"
        + policyId
        + '\''
        + ", "
        + "assignTo='"
        + assignTo
        + '\''
        + ", "
        + "filterFields='"
        + filterFields
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String policyId;

    protected final CreateLegalHoldPolicyAssignmentRequestBodyAssignToField assignTo;

    protected List<LegalHoldPolicyAssignmentFilterField> filterFields;

    public Builder(
        String policyId, CreateLegalHoldPolicyAssignmentRequestBodyAssignToField assignTo) {
      super();
      this.policyId = policyId;
      this.assignTo = assignTo;
    }

    public Builder filterFields(List<LegalHoldPolicyAssignmentFilterField> filterFields) {
      this.filterFields = filterFields;
      return this;
    }

    public CreateLegalHoldPolicyAssignmentRequestBody build() {
      return new CreateLegalHoldPolicyAssignmentRequestBody(this);
    }
  }
}
