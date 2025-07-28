package com.box.sdkgen.schemas.retentionpolicyassignment;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.retentionpolicymini.RetentionPolicyMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RetentionPolicyAssignment extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using =
          RetentionPolicyAssignmentTypeField.RetentionPolicyAssignmentTypeFieldDeserializer.class)
  @JsonSerialize(
      using = RetentionPolicyAssignmentTypeField.RetentionPolicyAssignmentTypeFieldSerializer.class)
  protected EnumWrapper<RetentionPolicyAssignmentTypeField> type;

  @JsonProperty("retention_policy")
  protected RetentionPolicyMini retentionPolicy;

  @JsonProperty("assigned_to")
  protected RetentionPolicyAssignmentAssignedToField assignedTo;

  @JsonProperty("filter_fields")
  @Nullable
  protected List<RetentionPolicyAssignmentFilterFieldsField> filterFields;

  @JsonProperty("assigned_by")
  protected UserMini assignedBy;

  @JsonProperty("assigned_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date assignedAt;

  @JsonProperty("start_date_field")
  protected String startDateField;

  public RetentionPolicyAssignment(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<RetentionPolicyAssignmentTypeField>(
            RetentionPolicyAssignmentTypeField.RETENTION_POLICY_ASSIGNMENT);
  }

  protected RetentionPolicyAssignment(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.retentionPolicy = builder.retentionPolicy;
    this.assignedTo = builder.assignedTo;
    this.filterFields = builder.filterFields;
    this.assignedBy = builder.assignedBy;
    this.assignedAt = builder.assignedAt;
    this.startDateField = builder.startDateField;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<RetentionPolicyAssignmentTypeField> getType() {
    return type;
  }

  public RetentionPolicyMini getRetentionPolicy() {
    return retentionPolicy;
  }

  public RetentionPolicyAssignmentAssignedToField getAssignedTo() {
    return assignedTo;
  }

  public List<RetentionPolicyAssignmentFilterFieldsField> getFilterFields() {
    return filterFields;
  }

  public UserMini getAssignedBy() {
    return assignedBy;
  }

  public Date getAssignedAt() {
    return assignedAt;
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
    RetentionPolicyAssignment casted = (RetentionPolicyAssignment) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(retentionPolicy, casted.retentionPolicy)
        && Objects.equals(assignedTo, casted.assignedTo)
        && Objects.equals(filterFields, casted.filterFields)
        && Objects.equals(assignedBy, casted.assignedBy)
        && Objects.equals(assignedAt, casted.assignedAt)
        && Objects.equals(startDateField, casted.startDateField);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        retentionPolicy,
        assignedTo,
        filterFields,
        assignedBy,
        assignedAt,
        startDateField);
  }

  @Override
  public String toString() {
    return "RetentionPolicyAssignment{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "retentionPolicy='"
        + retentionPolicy
        + '\''
        + ", "
        + "assignedTo='"
        + assignedTo
        + '\''
        + ", "
        + "filterFields='"
        + filterFields
        + '\''
        + ", "
        + "assignedBy='"
        + assignedBy
        + '\''
        + ", "
        + "assignedAt='"
        + assignedAt
        + '\''
        + ", "
        + "startDateField='"
        + startDateField
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<RetentionPolicyAssignmentTypeField> type;

    protected RetentionPolicyMini retentionPolicy;

    protected RetentionPolicyAssignmentAssignedToField assignedTo;

    protected List<RetentionPolicyAssignmentFilterFieldsField> filterFields;

    protected UserMini assignedBy;

    protected Date assignedAt;

    protected String startDateField;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<RetentionPolicyAssignmentTypeField>(
              RetentionPolicyAssignmentTypeField.RETENTION_POLICY_ASSIGNMENT);
    }

    public Builder type(RetentionPolicyAssignmentTypeField type) {
      this.type = new EnumWrapper<RetentionPolicyAssignmentTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<RetentionPolicyAssignmentTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder retentionPolicy(RetentionPolicyMini retentionPolicy) {
      this.retentionPolicy = retentionPolicy;
      return this;
    }

    public Builder assignedTo(RetentionPolicyAssignmentAssignedToField assignedTo) {
      this.assignedTo = assignedTo;
      return this;
    }

    public Builder filterFields(List<RetentionPolicyAssignmentFilterFieldsField> filterFields) {
      this.filterFields = filterFields;
      this.markNullableFieldAsSet("filter_fields");
      return this;
    }

    public Builder assignedBy(UserMini assignedBy) {
      this.assignedBy = assignedBy;
      return this;
    }

    public Builder assignedAt(Date assignedAt) {
      this.assignedAt = assignedAt;
      return this;
    }

    public Builder startDateField(String startDateField) {
      this.startDateField = startDateField;
      return this;
    }

    public RetentionPolicyAssignment build() {
      return new RetentionPolicyAssignment(this);
    }
  }
}
