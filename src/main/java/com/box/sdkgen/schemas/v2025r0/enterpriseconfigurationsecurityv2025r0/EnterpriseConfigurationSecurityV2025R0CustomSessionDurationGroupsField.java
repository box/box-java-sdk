package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationsecurityv2025r0;

import com.box.sdkgen.schemas.v2025r0.customsessiondurationgroupitemv2025r0.CustomSessionDurationGroupItemV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationitemv2025r0.EnterpriseConfigurationItemV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField
    extends EnterpriseConfigurationItemV2025R0 {

  protected List<CustomSessionDurationGroupItemV2025R0> value;

  public EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField() {
    super();
  }

  protected EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField(
      Builder builder) {
    super(builder);
    this.value = builder.value;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<CustomSessionDurationGroupItemV2025R0> getValue() {
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
    EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField casted =
        (EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField) o;
    return Objects.equals(isUsed, casted.isUsed) && Objects.equals(value, casted.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isUsed, value);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField{"
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

    protected List<CustomSessionDurationGroupItemV2025R0> value;

    public Builder value(List<CustomSessionDurationGroupItemV2025R0> value) {
      this.value = value;
      return this;
    }

    @Override
    public Builder isUsed(Boolean isUsed) {
      this.isUsed = isUsed;
      return this;
    }

    public EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField build() {
      return new EnterpriseConfigurationSecurityV2025R0CustomSessionDurationGroupsField(this);
    }
  }
}
