package com.box.sdkgen.schemas.legalholdpolicyassignmentfilterfield;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A metadata template field filter for a legal hold policy assignment. */
@JsonFilter("nullablePropertyFilter")
public class LegalHoldPolicyAssignmentFilterField extends SerializableObject {

  /** The ID of the metadata template field to filter on. */
  protected final String field;

  /** The value of the metadata template field to filter on. */
  protected final String value;

  public LegalHoldPolicyAssignmentFilterField(
      @JsonProperty("field") String field, @JsonProperty("value") String value) {
    super();
    this.field = field;
    this.value = value;
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
    LegalHoldPolicyAssignmentFilterField casted = (LegalHoldPolicyAssignmentFilterField) o;
    return Objects.equals(field, casted.field) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, value);
  }

  @Override
  public String toString() {
    return "LegalHoldPolicyAssignmentFilterField{"
        + "field='"
        + field
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + "}";
  }
}
