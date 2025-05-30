package com.box.sdkgen.schemas.collaboration;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField
    extends SerializableObject {

  @JsonProperty("enterprise_has_strong_password_required_for_external_users")
  protected Boolean enterpriseHasStrongPasswordRequiredForExternalUsers;

  @JsonProperty("user_has_strong_password")
  protected Boolean userHasStrongPassword;

  public CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField() {
    super();
  }

  protected CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField(
      CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementFieldBuilder builder) {
    super();
    this.enterpriseHasStrongPasswordRequiredForExternalUsers =
        builder.enterpriseHasStrongPasswordRequiredForExternalUsers;
    this.userHasStrongPassword = builder.userHasStrongPassword;
  }

  public Boolean getEnterpriseHasStrongPasswordRequiredForExternalUsers() {
    return enterpriseHasStrongPasswordRequiredForExternalUsers;
  }

  public Boolean getUserHasStrongPassword() {
    return userHasStrongPassword;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField casted =
        (CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField) o;
    return Objects.equals(
            enterpriseHasStrongPasswordRequiredForExternalUsers,
            casted.enterpriseHasStrongPasswordRequiredForExternalUsers)
        && Objects.equals(userHasStrongPassword, casted.userHasStrongPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enterpriseHasStrongPasswordRequiredForExternalUsers, userHasStrongPassword);
  }

  @Override
  public String toString() {
    return "CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField{"
        + "enterpriseHasStrongPasswordRequiredForExternalUsers='"
        + enterpriseHasStrongPasswordRequiredForExternalUsers
        + '\''
        + ", "
        + "userHasStrongPassword='"
        + userHasStrongPassword
        + '\''
        + "}";
  }

  public static
  class CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementFieldBuilder {

    protected Boolean enterpriseHasStrongPasswordRequiredForExternalUsers;

    protected Boolean userHasStrongPassword;

    public CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementFieldBuilder
        enterpriseHasStrongPasswordRequiredForExternalUsers(
            Boolean enterpriseHasStrongPasswordRequiredForExternalUsers) {
      this.enterpriseHasStrongPasswordRequiredForExternalUsers =
          enterpriseHasStrongPasswordRequiredForExternalUsers;
      return this;
    }

    public CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementFieldBuilder
        userHasStrongPassword(Boolean userHasStrongPassword) {
      this.userHasStrongPassword = userHasStrongPassword;
      return this;
    }

    public CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField build() {
      return new CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField(this);
    }
  }
}
