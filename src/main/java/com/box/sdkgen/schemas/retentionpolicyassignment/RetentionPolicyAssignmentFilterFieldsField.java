package com.box.sdkgen.schemas.retentionpolicyassignment;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class RetentionPolicyAssignmentFilterFieldsField extends SerializableObject {

  protected String field;

  protected String value;

  public RetentionPolicyAssignmentFilterFieldsField() {
    super();
  }

  protected RetentionPolicyAssignmentFilterFieldsField(
      RetentionPolicyAssignmentFilterFieldsFieldBuilder builder) {
    super();
    this.field = builder.field;
    this.value = builder.value;
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

  public static class RetentionPolicyAssignmentFilterFieldsFieldBuilder {

    protected String field;

    protected String value;

    public RetentionPolicyAssignmentFilterFieldsFieldBuilder field(String field) {
      this.field = field;
      return this;
    }

    public RetentionPolicyAssignmentFilterFieldsFieldBuilder value(String value) {
      this.value = value;
      return this;
    }

    public RetentionPolicyAssignmentFilterFieldsField build() {
      return new RetentionPolicyAssignmentFilterFieldsField(this);
    }
  }
}
