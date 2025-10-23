package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationsecurityv2025r0;

import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.box.sdkgen.schemas.v2025r0.externalcollabsecuritysettingsv2025r0.ExternalCollabSecuritySettingsV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField
    extends EnterpriseConfigurationItemV2025R0 {

  protected ExternalCollabSecuritySettingsV2025R0 value;

  public EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField() {
    super();
  }

  protected EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField(
      Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public ExternalCollabSecuritySettingsV2025R0 getValue() {
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
    EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField casted =
        (EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField) o;
    return Objects.equals(isUsed, casted.isUsed) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isUsed, value);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField{"
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

    protected ExternalCollabSecuritySettingsV2025R0 value;

    public Builder value(ExternalCollabSecuritySettingsV2025R0 value) {
      this.value = value;
      return this;
    }

    @Override
    public Builder isUsed(Boolean isUsed) {
      this.isUsed = isUsed;
      return this;
    }

    public EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField
        build() {
      return new EnterpriseConfigurationSecurityV2025R0ExternalCollabMultiFactorAuthSettingsField(
          this);
    }
  }
}
