package com.box.sdkgen.managers.retentionpolicyassignments;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField
    extends SerializableObject {

  /** The metadata attribute key id. */
  protected String field;

  /** The metadata attribute field id. For value, only enum and multiselect types are supported. */
  protected String value;

  public CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField() {
    super();
  }

  protected CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField(Builder builder) {
    super();
    this.field = builder.field;
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getField() {
    return field;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField casted =
        (CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField) o;
    return Objects.equals(field, casted.field) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, value);
  }

  @Override
  public String toString() {
    return "CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField{"
        + "field='"
        + field
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String field;

    protected String value;

    public Builder field(String field) {
      this.field = field;
      return this;
    }

    public Builder value(String value) {
      this.value = value;
      return this;
    }

    public CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField build() {
      return new CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField(this);
    }
  }
}
