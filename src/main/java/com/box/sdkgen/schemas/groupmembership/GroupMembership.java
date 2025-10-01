package com.box.sdkgen.schemas.groupmembership;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.groupmini.GroupMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** Membership is used to signify that a user is part of a group. */
@JsonFilter("nullablePropertyFilter")
public class GroupMembership extends SerializableObject {

  /** The unique identifier for this group membership. */
  protected String id;

  /** The value will always be `group_membership`. */
  @JsonDeserialize(using = GroupMembershipTypeField.GroupMembershipTypeFieldDeserializer.class)
  @JsonSerialize(using = GroupMembershipTypeField.GroupMembershipTypeFieldSerializer.class)
  protected EnumWrapper<GroupMembershipTypeField> type;

  protected UserMini user;

  protected GroupMini group;

  /** The role of the user in the group. */
  @JsonDeserialize(using = GroupMembershipRoleField.GroupMembershipRoleFieldDeserializer.class)
  @JsonSerialize(using = GroupMembershipRoleField.GroupMembershipRoleFieldSerializer.class)
  protected EnumWrapper<GroupMembershipRoleField> role;

  /** The time this membership was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** The time this membership was last modified. */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  public GroupMembership() {
    super();
  }

  protected GroupMembership(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.user = builder.user;
    this.group = builder.group;
    this.role = builder.role;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<GroupMembershipTypeField> type;

    protected UserMini user;

    protected GroupMini group;

    protected EnumWrapper<GroupMembershipRoleField> role;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(GroupMembershipTypeField type) {
      this.type = new EnumWrapper<GroupMembershipTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<GroupMembershipTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder user(UserMini user) {
      this.user = user;
      return this;
    }

    public Builder group(GroupMini group) {
      this.group = group;
      return this;
    }

    public Builder role(GroupMembershipRoleField role) {
      this.role = new EnumWrapper<GroupMembershipRoleField>(role);
      return this;
    }

    public Builder role(EnumWrapper<GroupMembershipRoleField> role) {
      this.role = role;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public GroupMembership build() {
      return new GroupMembership(this);
    }
  }
}
