package com.box.sdkgen.managers.skills;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateAllSkillCardsOnFileRequestBodyUsageField extends SerializableObject {

  /** The value will always be `file`. */
  protected String unit;

  /** Number of resources affected. */
  protected Double value;

  public UpdateAllSkillCardsOnFileRequestBodyUsageField() {
    super();
  }

  protected UpdateAllSkillCardsOnFileRequestBodyUsageField(Builder builder) {
    super();
    this.unit = builder.unit;
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getUnit() {
    return unit;
  }

  public Double getValue() {
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
    UpdateAllSkillCardsOnFileRequestBodyUsageField casted =
        (UpdateAllSkillCardsOnFileRequestBodyUsageField) o;
    return Objects.equals(unit, casted.unit) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unit, value);
  }

  @Override
  public String toString() {
    return "UpdateAllSkillCardsOnFileRequestBodyUsageField{"
        + "unit='"
        + unit
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String unit;

    protected Double value;

    public Builder unit(String unit) {
      this.unit = unit;
      return this;
    }

    public Builder value(Double value) {
      this.value = value;
      return this;
    }

    public UpdateAllSkillCardsOnFileRequestBodyUsageField build() {
      return new UpdateAllSkillCardsOnFileRequestBodyUsageField(this);
    }
  }
}
