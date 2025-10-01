package com.box.sdkgen.managers.retentionpolicyassignments;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateRetentionPolicyAssignmentRequestBody extends SerializableObject {

  /** The ID of the retention policy to assign. */
  @JsonProperty("policy_id")
  protected final String policyId;

  /** The item to assign the policy to. */
  @JsonProperty("assign_to")
  protected final CreateRetentionPolicyAssignmentRequestBodyAssignToField assignTo;

  /**
   * If the `assign_to` type is `metadata_template`, then optionally add the `filter_fields`
   * parameter which will require an array of objects with a field entry and a value entry.
   * Currently only one object of `field` and `value` is supported.
   */
  @JsonProperty("filter_fields")
  protected List<CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField> filterFields;

  /**
   * The date the retention policy assignment begins.
   *
   * <p>If the `assigned_to` type is `metadata_template`, this field can be a date field's metadata
   * attribute key id.
   */
  @JsonProperty("start_date_field")
  protected String startDateField;

  public CreateRetentionPolicyAssignmentRequestBody(
      @JsonProperty("policy_id") String policyId,
      @JsonProperty("assign_to") CreateRetentionPolicyAssignmentRequestBodyAssignToField assignTo) {
    super();
    this.policyId = policyId;
    this.assignTo = assignTo;
  }

  protected CreateRetentionPolicyAssignmentRequestBody(Builder builder) {
    super();
    this.policyId = builder.policyId;
    this.assignTo = builder.assignTo;
    this.filterFields = builder.filterFields;
    this.startDateField = builder.startDateField;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getPolicyId() {
    return policyId;
  }

  public CreateRetentionPolicyAssignmentRequestBodyAssignToField getAssignTo() {
    return assignTo;
  }

  public List<CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField> getFilterFields() {
    return filterFields;
  }

  public String getStartDateField() {
    return startDateField;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateRetentionPolicyAssignmentRequestBody casted =
        (CreateRetentionPolicyAssignmentRequestBody) o;
    return Objects.equals(policyId, casted.policyId)
        && Objects.equals(assignTo, casted.assignTo)
        && Objects.equals(filterFields, casted.filterFields)
        && Objects.equals(startDateField, casted.startDateField);
  }

  @Override
  public int hashCode() {
    return Objects.hash(policyId, assignTo, filterFields, startDateField);
  }

  @Override
  public String toString() {
    return "CreateRetentionPolicyAssignmentRequestBody{"
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
        + ", "
        + "startDateField='"
        + startDateField
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String policyId;

    protected final CreateRetentionPolicyAssignmentRequestBodyAssignToField assignTo;

    protected List<CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField> filterFields;

    protected String startDateField;

    public Builder(
        String policyId, CreateRetentionPolicyAssignmentRequestBodyAssignToField assignTo) {
      super();
      this.policyId = policyId;
      this.assignTo = assignTo;
    }

    public Builder filterFields(
        List<CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField> filterFields) {
      this.filterFields = filterFields;
      return this;
    }

    public Builder startDateField(String startDateField) {
      this.startDateField = startDateField;
      return this;
    }

    public CreateRetentionPolicyAssignmentRequestBody build() {
      return new CreateRetentionPolicyAssignmentRequestBody(this);
    }
  }
}
