package com.box.sdkgen.schemas.groupfull;

import com.box.sdkgen.schemas.group.Group;
import com.box.sdkgen.schemas.groupbase.GroupBaseTypeField;
import com.box.sdkgen.schemas.groupmini.GroupMiniGroupTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Groups contain a set of users, and can be used in place of users in some operations, such as
 * collaborations.
 */
@JsonFilter("nullablePropertyFilter")
public class GroupFull extends Group {

  /**
   * Keeps track of which external source this group is coming from (e.g. "Active Directory",
   * "Google Groups", "Facebook Groups"). Setting this will also prevent Box users from editing the
   * group name and its members directly via the Box web application. This is desirable for one-way
   * syncing of groups.
   */
  protected String provenance;

  /**
   * An arbitrary identifier that can be used by external group sync tools to link this Box Group to
   * an external group. Example values of this field could be an Active Directory Object ID or a
   * Google Group ID. We recommend you use of this field in order to avoid issues when group names
   * are updated in either Box or external systems.
   */
  @JsonProperty("external_sync_identifier")
  protected String externalSyncIdentifier;

  /** Human readable description of the group. */
  protected String description;

  /**
   * Specifies who can invite the group to collaborate on items.
   *
   * <p>When set to `admins_only` the enterprise admin, co-admins, and the group's admin can invite
   * the group.
   *
   * <p>When set to `admins_and_members` all the admins listed above and group members can invite
   * the group.
   *
   * <p>When set to `all_managed_users` all managed users in the enterprise can invite the group.
   */
  @JsonDeserialize(
      using = GroupFullInvitabilityLevelField.GroupFullInvitabilityLevelFieldDeserializer.class)
  @JsonSerialize(
      using = GroupFullInvitabilityLevelField.GroupFullInvitabilityLevelFieldSerializer.class)
  @JsonProperty("invitability_level")
  protected EnumWrapper<GroupFullInvitabilityLevelField> invitabilityLevel;

  /**
   * Specifies who can view the members of the group (Get Memberships for Group).
   *
   * <p>* `admins_only` - the enterprise admin, co-admins, group's group admin. *
   * `admins_and_members` - all admins and group members. * `all_managed_users` - all managed users
   * in the enterprise.
   */
  @JsonDeserialize(
      using =
          GroupFullMemberViewabilityLevelField.GroupFullMemberViewabilityLevelFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          GroupFullMemberViewabilityLevelField.GroupFullMemberViewabilityLevelFieldSerializer.class)
  @JsonProperty("member_viewability_level")
  protected EnumWrapper<GroupFullMemberViewabilityLevelField> memberViewabilityLevel;

  protected GroupFullPermissionsField permissions;

  public GroupFull(@JsonProperty("id") String id) {
    super(id);
  }

  protected GroupFull(Builder builder) {
    super(builder);
    this.provenance = builder.provenance;
    this.externalSyncIdentifier = builder.externalSyncIdentifier;
    this.description = builder.description;
    this.invitabilityLevel = builder.invitabilityLevel;
    this.memberViewabilityLevel = builder.memberViewabilityLevel;
    this.permissions = builder.permissions;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getProvenance() {
    return provenance;
  }

  public String getExternalSyncIdentifier() {
    return externalSyncIdentifier;
  }

  public String getDescription() {
    return description;
  }

  public EnumWrapper<GroupFullInvitabilityLevelField> getInvitabilityLevel() {
    return invitabilityLevel;
  }

  public EnumWrapper<GroupFullMemberViewabilityLevelField> getMemberViewabilityLevel() {
    return memberViewabilityLevel;
  }

  public GroupFullPermissionsField getPermissions() {
    return permissions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupFull casted = (GroupFull) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(groupType, casted.groupType)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(provenance, casted.provenance)
        && Objects.equals(externalSyncIdentifier, casted.externalSyncIdentifier)
        && Objects.equals(description, casted.description)
        && Objects.equals(invitabilityLevel, casted.invitabilityLevel)
        && Objects.equals(memberViewabilityLevel, casted.memberViewabilityLevel)
        && Objects.equals(permissions, casted.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        name,
        groupType,
        createdAt,
        modifiedAt,
        provenance,
        externalSyncIdentifier,
        description,
        invitabilityLevel,
        memberViewabilityLevel,
        permissions);
  }

  @Override
  public String toString() {
    return "GroupFull{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "groupType='"
        + groupType
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + ", "
        + "provenance='"
        + provenance
        + '\''
        + ", "
        + "externalSyncIdentifier='"
        + externalSyncIdentifier
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "invitabilityLevel='"
        + invitabilityLevel
        + '\''
        + ", "
        + "memberViewabilityLevel='"
        + memberViewabilityLevel
        + '\''
        + ", "
        + "permissions='"
        + permissions
        + '\''
        + "}";
  }

  public static class Builder extends Group.Builder {

    protected String provenance;

    protected String externalSyncIdentifier;

    protected String description;

    protected EnumWrapper<GroupFullInvitabilityLevelField> invitabilityLevel;

    protected EnumWrapper<GroupFullMemberViewabilityLevelField> memberViewabilityLevel;

    protected GroupFullPermissionsField permissions;

    public Builder(String id) {
      super(id);
    }

    public Builder provenance(String provenance) {
      this.provenance = provenance;
      return this;
    }

    public Builder externalSyncIdentifier(String externalSyncIdentifier) {
      this.externalSyncIdentifier = externalSyncIdentifier;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder invitabilityLevel(GroupFullInvitabilityLevelField invitabilityLevel) {
      this.invitabilityLevel = new EnumWrapper<GroupFullInvitabilityLevelField>(invitabilityLevel);
      return this;
    }

    public Builder invitabilityLevel(
        EnumWrapper<GroupFullInvitabilityLevelField> invitabilityLevel) {
      this.invitabilityLevel = invitabilityLevel;
      return this;
    }

    public Builder memberViewabilityLevel(
        GroupFullMemberViewabilityLevelField memberViewabilityLevel) {
      this.memberViewabilityLevel =
          new EnumWrapper<GroupFullMemberViewabilityLevelField>(memberViewabilityLevel);
      return this;
    }

    public Builder memberViewabilityLevel(
        EnumWrapper<GroupFullMemberViewabilityLevelField> memberViewabilityLevel) {
      this.memberViewabilityLevel = memberViewabilityLevel;
      return this;
    }

    public Builder permissions(GroupFullPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    @Override
    public Builder type(GroupBaseTypeField type) {
      this.type = new EnumWrapper<GroupBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<GroupBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder groupType(GroupMiniGroupTypeField groupType) {
      this.groupType = new EnumWrapper<GroupMiniGroupTypeField>(groupType);
      return this;
    }

    @Override
    public Builder groupType(EnumWrapper<GroupMiniGroupTypeField> groupType) {
      this.groupType = groupType;
      return this;
    }

    @Override
    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    @Override
    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public GroupFull build() {
      return new GroupFull(this);
    }
  }
}
