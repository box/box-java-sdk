package com.box.sdkgen.managers.retentionpolicyassignments;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField
    extends SerializableObject {

  protected String field;

  protected String value;

  public CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField() {
    super();
  }

  protected CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField(
      CreateRetentionPolicyAssignmentRequestBodyFilterFieldsFieldBuilder builder) {
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

  public static class CreateRetentionPolicyAssignmentRequestBodyFilterFieldsFieldBuilder {

    protected String field;

    protected String value;

    public CreateRetentionPolicyAssignmentRequestBodyFilterFieldsFieldBuilder field(String field) {
      this.field = field;
      return this;
    }

    public CreateRetentionPolicyAssignmentRequestBodyFilterFieldsFieldBuilder value(String value) {
      this.value = value;
      return this;
    }

    public CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField build() {
      return new CreateRetentionPolicyAssignmentRequestBodyFilterFieldsField(this);
    }
  }
}
