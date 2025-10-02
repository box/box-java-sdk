package com.box.sdkgen.schemas.collaboration;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField
    extends SerializableObject {

  /**
   * Whether or not the enterprise that owns the content requires a strong password to collaborate
   * on the content, or enforces an exposed password detection for the external collaborators.
   */
  @JsonProperty("enterprise_has_strong_password_required_for_external_users")
  protected Boolean enterpriseHasStrongPasswordRequiredForExternalUsers;

  /**
   * Whether or not the user has a strong and not exposed password set for their account. The field
   * is `null` when a strong password is not required.
   */
  @JsonProperty("user_has_strong_password")
  @Nullable
  protected Boolean userHasStrongPassword;

  public CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField() {
    super();
  }

  protected CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField(
      Builder builder) {
    super();
    this.enterpriseHasStrongPasswordRequiredForExternalUsers =
        builder.enterpriseHasStrongPasswordRequiredForExternalUsers;
    this.userHasStrongPassword = builder.userHasStrongPassword;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected Boolean enterpriseHasStrongPasswordRequiredForExternalUsers;

    protected Boolean userHasStrongPassword;

    public Builder enterpriseHasStrongPasswordRequiredForExternalUsers(
        Boolean enterpriseHasStrongPasswordRequiredForExternalUsers) {
      this.enterpriseHasStrongPasswordRequiredForExternalUsers =
          enterpriseHasStrongPasswordRequiredForExternalUsers;
      return this;
    }

    public Builder userHasStrongPassword(Boolean userHasStrongPassword) {
      this.userHasStrongPassword = userHasStrongPassword;
      this.markNullableFieldAsSet("user_has_strong_password");
      return this;
    }

    public CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField build() {
      return new CollaborationAcceptanceRequirementsStatusStrongPasswordRequirementField(this);
    }
  }
}
