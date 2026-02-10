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
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A retention assignment represents a rule specifying the files a retention policy retains.
 * Assignments can retain files based on their folder or metadata, or hold all files in the
 * enterprise.
 */
@JsonFilter("nullablePropertyFilter")
public class RetentionPolicyAssignment extends SerializableObject {

  /** The unique identifier for a retention policy assignment. */
  protected final String id;

  /** The value will always be `retention_policy_assignment`. */
  @JsonDeserialize(
      using =
          RetentionPolicyAssignmentTypeField.RetentionPolicyAssignmentTypeFieldDeserializer.class)
  @JsonSerialize(
      using = RetentionPolicyAssignmentTypeField.RetentionPolicyAssignmentTypeFieldSerializer.class)
  protected EnumWrapper<RetentionPolicyAssignmentTypeField> type;

  @JsonProperty("retention_policy")
  protected RetentionPolicyMini retentionPolicy;

  /**
   * The `type` and `id` of the content that is under retention. The `type` can either be `folder`
   * `enterprise`, or `metadata_template`.
   */
  @JsonProperty("assigned_to")
  protected RetentionPolicyAssignmentAssignedToField assignedTo;

  /**
   * An array of field objects. Values are only returned if the `assigned_to` type is
   * `metadata_template`. Otherwise, the array is blank.
   */
  @JsonProperty("filter_fields")
  @Nullable
  protected List<RetentionPolicyAssignmentFilterFieldsField> filterFields;

  @JsonProperty("assigned_by")
  protected UserMini assignedBy;

  /** When the retention policy assignment object was created. */
  @JsonProperty("assigned_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime assignedAt;

  /**
   * The date the retention policy assignment begins. If the `assigned_to` type is
   * `metadata_template`, this field can be a date field's metadata attribute key id.
   */
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

  public OffsetDateTime getAssignedAt() {
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

    protected OffsetDateTime assignedAt;

    protected String startDateField;

    public Builder(String id) {
      super();
      this.id = id;
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

    public Builder assignedAt(OffsetDateTime assignedAt) {
      this.assignedAt = assignedAt;
      return this;
    }

    public Builder startDateField(String startDateField) {
      this.startDateField = startDateField;
      return this;
    }

    public RetentionPolicyAssignment build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<RetentionPolicyAssignmentTypeField>(
                RetentionPolicyAssignmentTypeField.RETENTION_POLICY_ASSIGNMENT);
      }
      return new RetentionPolicyAssignment(this);
    }
  }
}
