package com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationshieldv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.shieldruleitemv2025r0.ShieldRuleItemV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** The enterprise configuration for the shield category. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseConfigurationShieldV2025R0 extends SerializableObject {

  /** The shield rules configuration for the enterprise. */
  @JsonProperty("shield_rules")
  protected List<ShieldRuleItemV2025R0> shieldRules;

  public EnterpriseConfigurationShieldV2025R0() {
    super();
  }

  protected EnterpriseConfigurationShieldV2025R0(Builder builder) {
    super();
    this.shieldRules = builder.shieldRules;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<ShieldRuleItemV2025R0> getShieldRules() {
    return shieldRules;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseConfigurationShieldV2025R0 casted = (EnterpriseConfigurationShieldV2025R0) o;
    return Objects.equals(shieldRules, casted.shieldRules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shieldRules);
  }

  @Override
  public String toString() {
    return "EnterpriseConfigurationShieldV2025R0{" + "shieldRules='" + shieldRules + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<ShieldRuleItemV2025R0> shieldRules;

    public Builder shieldRules(List<ShieldRuleItemV2025R0> shieldRules) {
      this.shieldRules = shieldRules;
      return this;
    }

    public EnterpriseConfigurationShieldV2025R0 build() {
      return new EnterpriseConfigurationShieldV2025R0(this);
    }
  }
}
