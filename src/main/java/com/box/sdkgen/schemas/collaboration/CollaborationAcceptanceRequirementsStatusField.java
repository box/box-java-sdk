package com.box.sdkgen.schemas.collaboration;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CollaborationAcceptanceRequirementsStatusField extends SerializableObject {

  @JsonProperty("terms_of_service_requirement")
  protected CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField
      termsOfServiceRequirement;

  @JsonProperty("strong_password_requirement")
  protected CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField
      strongPasswordRequirement;

  @JsonProperty("two_factor_authentication_requirement")
  protected CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
      twoFactorAuthenticationRequirement;

  public CollaborationAcceptanceRequirementsStatusField() {
    super();
  }

  protected CollaborationAcceptanceRequirementsStatusField(Builder builder) {
    super();
    this.termsOfServiceRequirement = builder.termsOfServiceRequirement;
    this.strongPasswordRequirement = builder.strongPasswordRequirement;
    this.twoFactorAuthenticationRequirement = builder.twoFactorAuthenticationRequirement;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField
      getTermsOfServiceRequirement() {
    return termsOfServiceRequirement;
  }

  public CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField
      getStrongPasswordRequirement() {
    return strongPasswordRequirement;
  }

  public CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
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
    CollaborationAcceptanceRequirementsStatusField casted =
        (CollaborationAcceptanceRequirementsStatusField) o;
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
    return "CollaborationAcceptanceRequirementsStatusField{"
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

    protected CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField
        termsOfServiceRequirement;

    protected CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField
        strongPasswordRequirement;

    protected CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
        twoFactorAuthenticationRequirement;

    public Builder termsOfServiceRequirement(
        CollaborationAcceptanceRequirementsStatusTermsOfServiceRequirementField
            termsOfServiceRequirement) {
      this.termsOfServiceRequirement = termsOfServiceRequirement;
      return this;
    }

    public Builder strongPasswordRequirement(
        CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField
            strongPasswordRequirement) {
      this.strongPasswordRequirement = strongPasswordRequirement;
      return this;
    }

    public Builder twoFactorAuthenticationRequirement(
        CollaborationAcceptanceRequirementsStatusTwoFactorAuthenticationRequirementField
            twoFactorAuthenticationRequirement) {
      this.twoFactorAuthenticationRequirement = twoFactorAuthenticationRequirement;
      return this;
    }

    public CollaborationAcceptanceRequirementsStatusField build() {
      return new CollaborationAcceptanceRequirementsStatusField(this);
    }
  }
}
