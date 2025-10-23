package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationusersettingsv2025r0;

import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.box.sdkgen.schemas.v2025r0.usertrackingcodev2025r0.UserTrackingCodeV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField
    extends EnterpriseConfigurationItemV2025R0 {

  protected List<UserTrackingCodeV2025R0> value;

  public EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField() {
    super();
  }

  protected EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField(Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<UserTrackingCodeV2025R0> getValue() {
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
    EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField casted =
        (EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField) o;
    return Objects.equals(isUsed, casted.isUsed) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isUsed, value);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField{"
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

    protected List<UserTrackingCodeV2025R0> value;

    public Builder value(List<UserTrackingCodeV2025R0> value) {
      this.value = value;
      return this;
    }

    @Override
    public Builder isUsed(Boolean isUsed) {
      this.isUsed = isUsed;
      return this;
    }

    public EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField build() {
      return new EnterpriseConfigurationUserSettingsV2025R0UserTrackingCodesField(this);
    }
  }
}
