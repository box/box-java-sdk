package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemintegerv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** An enterprise configuration item with a integer type value. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationItemIntegerV2025R0 extends EnterpriseConfigurationItemV2025R0 {

  /** The value of the enterprise configuration as an integer. */
  @Nullable protected Long value;

  public EnterpriseConfigurationItemIntegerV2025R0() {
    super();
  }

  protected EnterpriseConfigurationItemIntegerV2025R0(Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getValue() {
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
    EnterpriseConfigurationItemIntegerV2025R0 casted =
        (EnterpriseConfigurationItemIntegerV2025R0) o;
    return Objects.equals(isUsed, casted.isUsed) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isUsed, value);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationItemIntegerV2025R0{"
        + "isUsed='"
        + isUsed
        + '\''
        + ", "
        + "value='"
        + value
        + '\''
        + "}";
  }

  public static class Builder extends EnterpriseConfigurationItemV2025R0.Builder {

    protected Long value;

    public Builder value(Long value) {
      this.value = value;
      this.markNullableFieldAsSet("value");
      return this;
    }

    @Override
    public Builder isUsed(Boolean isUsed) {
      this.isUsed = isUsed;
      return this;
    }

    public EnterpriseConfigurationItemIntegerV2025R0 build() {
      return new EnterpriseConfigurationItemIntegerV2025R0(this);
    }
  }
}
