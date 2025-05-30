package com.box.sdkgen.managers.memberships;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import java.util.Objects;

public class UpdateGroupMembershipByIdRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          UpdateGroupMembershipByIdRequestBodyRoleField
              .UpdateGroupMembershipByIdRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateGroupMembershipByIdRequestBodyRoleField
              .UpdateGroupMembershipByIdRequestBodyRoleFieldSerializer.class)
  protected EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField> role;

  @JsonProperty("configurable_permissions")
  protected Map<String, Boolean> configurablePermissions;

  public UpdateGroupMembershipByIdRequestBody() {
    super();
  }

  protected UpdateGroupMembershipByIdRequestBody(
      UpdateGroupMembershipByIdRequestBodyBuilder builder) {
    super();
    this.role = builder.role;
    this.configurablePermissions = builder.configurablePermissions;
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

  public static class UpdateGroupMembershipByIdRequestBodyBuilder {

    protected EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField> role;

    protected Map<String, Boolean> configurablePermissions;

    public UpdateGroupMembershipByIdRequestBodyBuilder role(
        UpdateGroupMembershipByIdRequestBodyRoleField role) {
      this.role = new EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField>(role);
      return this;
    }

    public UpdateGroupMembershipByIdRequestBodyBuilder role(
        EnumWrapper<UpdateGroupMembershipByIdRequestBodyRoleField> role) {
      this.role = role;
      return this;
    }

    public UpdateGroupMembershipByIdRequestBodyBuilder configurablePermissions(
        Map<String, Boolean> configurablePermissions) {
      this.configurablePermissions = configurablePermissions;
      return this;
    }

    public UpdateGroupMembershipByIdRequestBody build() {
      return new UpdateGroupMembershipByIdRequestBody(this);
    }
  }
}
