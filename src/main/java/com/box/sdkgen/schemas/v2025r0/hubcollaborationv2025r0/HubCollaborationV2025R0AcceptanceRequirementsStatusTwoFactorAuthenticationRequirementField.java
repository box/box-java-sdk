package com.box.sdkgen.schemas.v2025r0.hubcollaborationv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public
class HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
    extends SerializableObject {

  /**
   * Whether or not the enterprise that owns the content requires two-factor authentication to be
   * enabled in order to collaborate on the content.
   */
  @JsonProperty("enterprise_has_two_factor_auth_enabled")
  protected Boolean enterpriseHasTwoFactorAuthEnabled;

  /**
   * Whether or not the user has two-factor authentication enabled. The field is `null` when
   * two-factor authentication is not required.
   */
  @JsonProperty("user_has_two_factor_authentication_enabled")
  @Nullable
  protected Boolean userHasTwoFactorAuthenticationEnabled;

  public
  HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField() {
    super();
  }

  protected
  HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField(
      Builder builder) {
    super();
    this.enterpriseHasTwoFactorAuthEnabled = builder.enterpriseHasTwoFactorAuthEnabled;
    this.userHasTwoFactorAuthenticationEnabled = builder.userHasTwoFactorAuthenticationEnabled;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getEnterpriseHasTwoFactorAuthEnabled() {
    return enterpriseHasTwoFactorAuthEnabled;
  }

  public Boolean getUserHasTwoFactorAuthenticationEnabled() {
    return userHasTwoFactorAuthenticationEnabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
        casted =
            (HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField)
                o;
    return Objects.equals(
            enterpriseHasTwoFactorAuthEnabled, casted.enterpriseHasTwoFactorAuthEnabled)
        && Objects.equals(
            userHasTwoFactorAuthenticationEnabled, casted.userHasTwoFactorAuthenticationEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enterpriseHasTwoFactorAuthEnabled, userHasTwoFactorAuthenticationEnabled);
  }

  @Override
  public String toString() {
    return "HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField{"
        + "enterpriseHasTwoFactorAuthEnabled='"
        + enterpriseHasTwoFactorAuthEnabled
        + '\''
        + ", "
        + "userHasTwoFactorAuthenticationEnabled='"
        + userHasTwoFactorAuthenticationEnabled
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean enterpriseHasTwoFactorAuthEnabled;

    protected Boolean userHasTwoFactorAuthenticationEnabled;

    public Builder enterpriseHasTwoFactorAuthEnabled(Boolean enterpriseHasTwoFactorAuthEnabled) {
      this.enterpriseHasTwoFactorAuthEnabled = enterpriseHasTwoFactorAuthEnabled;
      return this;
    }

    public Builder userHasTwoFactorAuthenticationEnabled(
        Boolean userHasTwoFactorAuthenticationEnabled) {
      this.userHasTwoFactorAuthenticationEnabled = userHasTwoFactorAuthenticationEnabled;
      this.markNullableFieldAsSet("user_has_two_factor_authentication_enabled");
      return this;
    }

    public
    HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
        build() {
      return new HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField(
          this);
    }
  }
}
