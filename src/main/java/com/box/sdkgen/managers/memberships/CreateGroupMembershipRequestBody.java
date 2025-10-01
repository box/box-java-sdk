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
public class CreateGroupMembershipRequestBody extends SerializableObject {

  /** The user to add to the group. */
  protected final CreateGroupMembershipRequestBodyUserField user;

  /** The group to add the user to. */
  protected final CreateGroupMembershipRequestBodyGroupField group;

  /** The role of the user in the group. */
  @JsonDeserialize(
      using =
          CreateGroupMembershipRequestBodyRoleField
              .CreateGroupMembershipRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateGroupMembershipRequestBodyRoleField
              .CreateGroupMembershipRequestBodyRoleFieldSerializer.class)
  protected EnumWrapper<CreateGroupMembershipRequestBodyRoleField> role;

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

  public CreateGroupMembershipRequestBody(
      @JsonProperty("user") CreateGroupMembershipRequestBodyUserField user,
      @JsonProperty("group") CreateGroupMembershipRequestBodyGroupField group) {
    super();
    this.user = user;
    this.group = group;
  }

  protected CreateGroupMembershipRequestBody(Builder builder) {
    super();
    this.user = builder.user;
    this.group = builder.group;
    this.role = builder.role;
    this.configurablePermissions = builder.configurablePermissions;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public CreateGroupMembershipRequestBodyUserField getUser() {
    return user;
  }

  public CreateGroupMembershipRequestBodyGroupField getGroup() {
    return group;
  }

  public EnumWrapper<CreateGroupMembershipRequestBodyRoleField> getRole() {
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
    CreateGroupMembershipRequestBody casted = (CreateGroupMembershipRequestBody) o;
    return Objects.equals(user, casted.user)
        && Objects.equals(group, casted.group)
        && Objects.equals(role, casted.role)
        && Objects.equals(configurablePermissions, casted.configurablePermissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, group, role, configurablePermissions);
  }

  @Override
  public String toString() {
    return "CreateGroupMembershipRequestBody{"
        + "user='"
        + user
        + '\''
        + ", "
        + "group='"
        + group
        + '\''
        + ", "
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

    protected final CreateGroupMembershipRequestBodyUserField user;

    protected final CreateGroupMembershipRequestBodyGroupField group;

    protected EnumWrapper<CreateGroupMembershipRequestBodyRoleField> role;

    protected Map<String, Boolean> configurablePermissions;

    public Builder(
        CreateGroupMembershipRequestBodyUserField user,
        CreateGroupMembershipRequestBodyGroupField group) {
      super();
      this.user = user;
      this.group = group;
    }

    public Builder role(CreateGroupMembershipRequestBodyRoleField role) {
      this.role = new EnumWrapper<CreateGroupMembershipRequestBodyRoleField>(role);
      return this;
    }

    public Builder role(EnumWrapper<CreateGroupMembershipRequestBodyRoleField> role) {
      this.role = role;
      return this;
    }

    public Builder configurablePermissions(Map<String, Boolean> configurablePermissions) {
      this.configurablePermissions = configurablePermissions;
      this.markNullableFieldAsSet("configurable_permissions");
      return this;
    }

    public CreateGroupMembershipRequestBody build() {
      return new CreateGroupMembershipRequestBody(this);
    }
  }
}
