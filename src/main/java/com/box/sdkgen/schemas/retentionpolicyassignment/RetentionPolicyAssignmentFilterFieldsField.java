package com.box.sdkgen.schemas.retentionpolicyassignment;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RetentionPolicyAssignmentFilterFieldsField extends SerializableObject {

  @Nullable protected String field;

  @Nullable protected String value;

  public RetentionPolicyAssignmentFilterFieldsField() {
    super();
  }

  protected RetentionPolicyAssignmentFilterFieldsField(Builder builder) {
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
    RetentionPolicyAssignmentFilterFieldsField casted =
        (RetentionPolicyAssignmentFilterFieldsField) o;
    return Objects.equals(field, casted.field) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, value);
  }

  @Override
  public String toString() {
    return "RetentionPolicyAssignmentFilterFieldsField{"
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
      this.markNullableFieldAsSet("field");
      return this;
    }

    public Builder value(String value) {
      this.value = value;
      this.markNullableFieldAsSet("value");
      return this;
    }

    public RetentionPolicyAssignmentFilterFieldsField build() {
      return new RetentionPolicyAssignmentFilterFieldsField(this);
    }
  }
}
