package com.box.sdkgen.schemas.v2025r0.enterprisefeaturesettingsitemv2025r0;

import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterprisefeaturesettingv2025r0.EnterpriseFeatureSettingV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** An enterprise feature settings item. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseFeatureSettingsItemV2025R0 extends EnterpriseConfigurationItemV2025R0 {

  protected EnterpriseFeatureSettingV2025R0 value;

  public EnterpriseFeatureSettingsItemV2025R0() {
    super();
  }

  protected EnterpriseFeatureSettingsItemV2025R0(Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnterpriseFeatureSettingV2025R0 getValue() {
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
    EnterpriseFeatureSettingsItemV2025R0 casted = (EnterpriseFeatureSettingsItemV2025R0) o;
    return Objects.equals(isUsed, casted.isUsed) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isUsed, value);
  }

  @Override
  public String toString() {
    return "EnterpriseFeatureSettingsItemV2025R0{"
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

    protected EnterpriseFeatureSettingV2025R0 value;

    public Builder value(EnterpriseFeatureSettingV2025R0 value) {
      this.value = value;
      return this;
    }

    @Override
    public Builder isUsed(Boolean isUsed) {
      this.isUsed = isUsed;
      return this;
    }

    public EnterpriseFeatureSettingsItemV2025R0 build() {
      return new EnterpriseFeatureSettingsItemV2025R0(this);
    }
  }
}
