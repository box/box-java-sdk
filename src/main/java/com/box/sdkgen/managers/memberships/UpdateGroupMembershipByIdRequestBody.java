package com.box.sdkgen.managers.memberships;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateGroupMembershipByIdRequestBody extends SerializableObject {

  /** The role of the user in the group. */
  @JsonDeserialize(
      using =
          UpdateGroupMembershipByIdRequestBodyRoleField
              .UpdateGroupMembershipByIdRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateGroupMembershipByIdRequestBodyRoleField
              .UpdateGroupMembershipByIdRequestBodyRoleFieldSerializer.class)
  protected EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField> role;

  /**
   * Custom configuration for the permissions an admin if a group will receive. This option has no
   * effect on members with a role of `member`.
   *
   * <p>Setting these permissions overwrites the default access levels of an admin.
   *
   * <p>Specifying a value of `null` for this object will disable all configurable permissions.
   * Specifying permissions will set them accordingly, omitted permissions will be enabled by
   * default.
   */
  @JsonProperty("configurable_permissions")
  @Nullable
  protected Map<String, Boolean> configurablePermissions;

  public UpdateGroupMembershipByIdRequestBody() {
    super();
  }

  protected UpdateGroupMembershipByIdRequestBody(Builder builder) {
    super();
    this.role = builder.role;
    this.configurablePermissions = builder.configurablePermissions;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField> getRole() {
    return role;
  }

  public Map<String, Boolean> getConfigurablePermissions() {
    return configurablePermissions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateGroupMembershipByIdRequestBody casted = (UpdateGroupMembershipByIdRequestBody) o;
    return Objects.equals(role, casted.role)
        && Objects.equals(configurablePermissions, casted.configurablePermissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, configurablePermissions);
  }

  @Override
  public String toString() {
    return "UpdateGroupMembershipByIdRequestBody{"
        + "role='"
        + role
        + '\''
        + ", "
        + "configurablePermissions='"
        + configurablePermissions
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField> role;

    protected Map<String, Boolean> configurablePermissions;

    public Builder role(UpdateGroupMembershipByIdRequestBodyRoleField role) {
      this.role = new EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField>(role);
      return this;
    }

    public Builder role(EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField> role) {
      this.role = role;
      return this;
    }

    public Builder configurablePermissions(Map<String, Boolean> configurablePermissions) {
      this.configurablePermissions = configurablePermissions;
      this.markNullableFieldAsSet("configurable_permissions");
      return this;
    }

    public UpdateGroupMembershipByIdRequestBody build() {
      return new UpdateGroupMembershipByIdRequestBody(this);
    }
  }
}
