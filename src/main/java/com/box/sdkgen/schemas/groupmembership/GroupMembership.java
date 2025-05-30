package com.box.sdkgen.schemas.groupmembership;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.groupmini.GroupMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class GroupMembership extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = GroupMembershipTypeField.GroupMembershipTypeFieldDeserializer.class)
  @JsonSerialize(using = GroupMembershipTypeField.GroupMembershipTypeFieldSerializer.class)
  protected EnumWrapper<GroupMembershipTypeField> type;

  protected UserMini user;

  protected GroupMini group;

  @JsonDeserialize(using = GroupMembershipRoleField.GroupMembershipRoleFieldDeserializer.class)
  @JsonSerialize(using = GroupMembershipRoleField.GroupMembershipRoleFieldSerializer.class)
  protected EnumWrapper<GroupMembershipRoleField> role;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  public GroupMembership() {
    super();
  }

  protected GroupMembership(GroupMembershipBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.user = builder.user;
    this.group = builder.group;
    this.role = builder.role;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<GroupMembershipTypeField> getType() {
    return type;
  }

  public UserMini getUser() {
    return user;
  }

  public GroupMini getGroup() {
    return group;
  }

  public EnumWrapper<GroupMembershipRoleField> getRole() {
    return role;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupMembership casted = (GroupMembership) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(user, casted.user)
        && Objects.equals(group, casted.group)
        && Objects.equals(role, casted.role)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, user, group, role, createdAt, modifiedAt);
  }

  @Override
  public String toString() {
    return "GroupMembership{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
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
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + "}";
  }

  public static class GroupMembershipBuilder {

    protected String id;

    protected EnumWrapper<GroupMembershipTypeField> type;

    protected UserMini user;

    protected GroupMini group;

    protected EnumWrapper<GroupMembershipRoleField> role;

    protected String createdAt;

    protected String modifiedAt;

    public GroupMembershipBuilder id(String id) {
      this.id = id;
      return this;
    }

    public GroupMembershipBuilder type(GroupMembershipTypeField type) {
      this.type = new EnumWrapper<GroupMembershipTypeField>(type);
      return this;
    }

    public GroupMembershipBuilder type(EnumWrapper<GroupMembershipTypeField> type) {
      this.type = type;
      return this;
    }

    public GroupMembershipBuilder user(UserMini user) {
      this.user = user;
      return this;
    }

    public GroupMembershipBuilder group(GroupMini group) {
      this.group = group;
      return this;
    }

    public GroupMembershipBuilder role(GroupMembershipRoleField role) {
      this.role = new EnumWrapper<GroupMembershipRoleField>(role);
      return this;
    }

    public GroupMembershipBuilder role(EnumWrapper<GroupMembershipRoleField> role) {
      this.role = role;
      return this;
    }

    public GroupMembershipBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public GroupMembershipBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public GroupMembership build() {
      return new GroupMembership(this);
    }
  }
}
