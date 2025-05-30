package com.box.sdkgen.managers.skills;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class UpdateAllSkillCardsOnFileRequestBodyUsageField extends SerializableObject {

  protected String unit;

  protected Double value;

  public UpdateAllSkillCardsOnFileRequestBodyUsageField() {
    super();
  }

  protected UpdateAllSkillCardsOnFileRequestBodyUsageField(
      UpdateAllSkillCardsOnFileRequestBodyUsageFieldBuilder builder) {
    super();
    this.unit = builder.unit;
    this.value = builder.value;
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

  public static class UpdateAllSkillCardsOnFileRequestBodyUsageFieldBuilder {

    protected String unit;

    protected Double value;

    public UpdateAllSkillCardsOnFileRequestBodyUsageFieldBuilder unit(String unit) {
      this.unit = unit;
      return this;
    }

    public UpdateAllSkillCardsOnFileRequestBodyUsageFieldBuilder value(Double value) {
      this.value = value;
      return this;
    }

    public UpdateAllSkillCardsOnFileRequestBodyUsageField build() {
      return new UpdateAllSkillCardsOnFileRequestBodyUsageField(this);
    }
  }
}
