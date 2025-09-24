package com.box.sdkgen.schemas.v2025r0.hubcollaborationv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class HubCollaborationV2025R0AcceptanceRequirementsStatusField extends SerializableObject {

  @JsonProperty("terms_of_service_requirement")
  protected HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField
      termsOfServiceRequirement;

  @JsonProperty("strong_password_requirement")
  protected HubCollaborationV2025R0AcceptanceRequirementsStatusStrongPasswordRequirementField
      strongPasswordRequirement;

  @JsonProperty("two_factor_authentication_requirement")
  protected
  HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
      twoFactorAuthenticationRequirement;

  public HubCollaborationV2025R0AcceptanceRequirementsStatusField() {
    super();
  }

  protected HubCollaborationV2025R0AcceptanceRequirementsStatusField(Builder builder) {
    super();
    this.termsOfServiceRequirement = builder.termsOfServiceRequirement;
    this.strongPasswordRequirement = builder.strongPasswordRequirement;
    this.twoFactorAuthenticationRequirement = builder.twoFactorAuthenticationRequirement;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField
      getTermsOfServiceRequirement() {
    return termsOfServiceRequirement;
  }

  public HubCollaborationV2025R0AcceptanceRequirementsStatusStrongPasswordRequirementField
      getStrongPasswordRequirement() {
    return strongPasswordRequirement;
  }

  public HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
      getTwoFactorAuthenticationRequirement() {
    return twoFactorAuthenticationRequirement;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubCollaborationV2025R0AcceptanceRequirementsStatusField casted =
        (HubCollaborationV2025R0AcceptanceRequirementsStatusField) o;
    return Objects.equals(termsOfServiceRequirement, casted.termsOfServiceRequirement)
        && Objects.equals(strongPasswordRequirement, casted.strongPasswordRequirement)
        && Objects.equals(
            twoFactorAuthenticationRequirement, casted.twoFactorAuthenticationRequirement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        termsOfServiceRequirement, strongPasswordRequirement, twoFactorAuthenticationRequirement);
  }

  @Override
  public String toString() {
    return "HubCollaborationV2025R0AcceptanceRequirementsStatusField{"
        + "termsOfServiceRequirement='"
        + termsOfServiceRequirement
        + '\''
        + ", "
        + "strongPasswordRequirement='"
        + strongPasswordRequirement
        + '\''
        + ", "
        + "twoFactorAuthenticationRequirement='"
        + twoFactorAuthenticationRequirement
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField
        termsOfServiceRequirement;

    protected HubCollaborationV2025R0AcceptanceRequirementsStatusStrongPasswordRequirementField
        strongPasswordRequirement;

    protected
    HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
        twoFactorAuthenticationRequirement;

    public Builder termsOfServiceRequirement(
        HubCollaborationV2025R0AcceptanceRequirementsStatusTermsOfServiceRequirementField
            termsOfServiceRequirement) {
      this.termsOfServiceRequirement = termsOfServiceRequirement;
      return this;
    }

    public Builder strongPasswordRequirement(
        HubCollaborationV2025R0AcceptanceRequirementsStatusStrongPasswordRequirementField
            strongPasswordRequirement) {
      this.strongPasswordRequirement = strongPasswordRequirement;
      return this;
    }

    public Builder twoFactorAuthenticationRequirement(
        HubCollaborationV2025R0AcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
            twoFactorAuthenticationRequirement) {
      this.twoFactorAuthenticationRequirement = twoFactorAuthenticationRequirement;
      return this;
    }

    public HubCollaborationV2025R0AcceptanceRequirementsStatusField build() {
      return new HubCollaborationV2025R0AcceptanceRequirementsStatusField(this);
    }
  }
}
