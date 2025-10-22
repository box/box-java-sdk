package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationsecurityv2025r0;

import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.box.sdkgen.schemas.v2025r0.keysafesettingsv2025r0.KeysafeSettingsV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationSecurityV2025R0KeysafeField
    extends EnterpriseConfigurationItemV2025R0 {

  protected KeysafeSettingsV2025R0 value;

  public EnterpriseConfigurationSecurityV2025R0KeysafeField() {
    super();
  }

  protected EnterpriseConfigurationSecurityV2025R0KeysafeField(Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public KeysafeSettingsV2025R0 getValue() {
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
    EnterpriseConfigurationSecurityV2025R0KeysafeField casted =
        (EnterpriseConfigurationSecurityV2025R0KeysafeField) o;
    return Objects.equals(isUsed, casted.isUsed) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isUsed, value);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationSecurityV2025R0KeysafeField{"
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

    protected KeysafeSettingsV2025R0 value;

    public Builder value(KeysafeSettingsV2025R0 value) {
      this.value = value;
      return this;
    }

    @Override
    public Builder isUsed(Boolean isUsed) {
      this.isUsed = isUsed;
      return this;
    }

    public EnterpriseConfigurationSecurityV2025R0KeysafeField build() {
      return new EnterpriseConfigurationSecurityV2025R0KeysafeField(this);
    }
  }
}
