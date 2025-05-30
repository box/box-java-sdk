package com.box.sdkgen.schemas.collaboration;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
    extends SerializableObject {

  @JsonProperty("enterprise_has_two_factor_auth_enabled")
  protected Boolean enterpriseHasTwoFactorAuthEnabled;

  @JsonProperty("user_has_two_factor_authentication_enabled")
  protected Boolean userHasTwoFactorAuthenticationEnabled;

  public CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField() {
    super();
  }

  protected CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField(
      CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementFieldBuilder
          builder) {
    super();
    this.enterpriseHasTwoFactorAuthEnabled = builder.enterpriseHasTwoFactorAuthEnabled;
    this.userHasTwoFactorAuthenticationEnabled = builder.userHasTwoFactorAuthenticationEnabled;
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
    CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField casted =
        (CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField) o;
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
    return "CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField{"
        + "enterpriseHasTwoFactorAuthEnabled='"
        + enterpriseHasTwoFactorAuthEnabled
        + '\''
        + ", "
        + "userHasTwoFactorAuthenticationEnabled='"
        + userHasTwoFactorAuthenticationEnabled
        + '\''
        + "}";
  }

  public static
  class CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementFieldBuilder {

    protected Boolean enterpriseHasTwoFactorAuthEnabled;

    protected Boolean userHasTwoFactorAuthenticationEnabled;

    public CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementFieldBuilder
        enterpriseHasTwoFactorAuthEnabled(Boolean enterpriseHasTwoFactorAuthEnabled) {
      this.enterpriseHasTwoFactorAuthEnabled = enterpriseHasTwoFactorAuthEnabled;
      return this;
    }

    public CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementFieldBuilder
        userHasTwoFactorAuthenticationEnabled(Boolean userHasTwoFactorAuthenticationEnabled) {
      this.userHasTwoFactorAuthenticationEnabled = userHasTwoFactorAuthenticationEnabled;
      return this;
    }

    public CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
        build() {
      return new CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField(
          this);
    }
  }
}
