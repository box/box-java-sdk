package com.box.sdkgen.schemas.groupfull;

import com.box.sdkgen.schemas.group.Group;
import com.box.sdkgen.schemas.groupbase.GroupBaseTypeField;
import com.box.sdkgen.schemas.groupmini.GroupMiniGroupTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class GroupFull extends Group {

  protected String provenance;

  @JsonProperty("external_sync_identifier")
  protected String externalSyncIdentifier;

  protected String description;

  @JsonDeserialize(
      using = GroupFullInvitabilityLevelField.GroupFullInvitabilityLevelFieldDeserializer.class)
  @JsonSerialize(
      using = GroupFullInvitabilityLevelField.GroupFullInvitabilityLevelFieldSerializer.class)
  @JsonProperty("invitability_level")
  protected EnumWrapper<GroupFullInvitabilityLevelField> invitabilityLevel;

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

  protected GroupFull(GroupFullBuilder builder) {
    super(builder);
    this.provenance = builder.provenance;
    this.externalSyncIdentifier = builder.externalSyncIdentifier;
    this.description = builder.description;
    this.invitabilityLevel = builder.invitabilityLevel;
    this.memberViewabilityLevel = builder.memberViewabilityLevel;
    this.permissions = builder.permissions;
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

  public static class GroupFullBuilder extends GroupBuilder {

    protected String provenance;

    protected String externalSyncIdentifier;

    protected String description;

    protected EnumWrapper<GroupFullInvitabilityLevelField> invitabilityLevel;

    protected EnumWrapper<GroupFullMemberViewabilityLevelField> memberViewabilityLevel;

    protected GroupFullPermissionsField permissions;

    public GroupFullBuilder(String id) {
      super(id);
    }

    public GroupFullBuilder provenance(String provenance) {
      this.provenance = provenance;
      return this;
    }

    public GroupFullBuilder externalSyncIdentifier(String externalSyncIdentifier) {
      this.externalSyncIdentifier = externalSyncIdentifier;
      return this;
    }

    public GroupFullBuilder description(String description) {
      this.description = description;
      return this;
    }

    public GroupFullBuilder invitabilityLevel(GroupFullInvitabilityLevelField invitabilityLevel) {
      this.invitabilityLevel = new EnumWrapper<GroupFullInvitabilityLevelField>(invitabilityLevel);
      return this;
    }

    public GroupFullBuilder invitabilityLevel(
        EnumWrapper<GroupFullInvitabilityLevelField> invitabilityLevel) {
      this.invitabilityLevel = invitabilityLevel;
      return this;
    }

    public GroupFullBuilder memberViewabilityLevel(
        GroupFullMemberViewabilityLevelField memberViewabilityLevel) {
      this.memberViewabilityLevel =
          new EnumWrapper<GroupFullMemberViewabilityLevelField>(memberViewabilityLevel);
      return this;
    }

    public GroupFullBuilder memberViewabilityLevel(
        EnumWrapper<GroupFullMemberViewabilityLevelField> memberViewabilityLevel) {
      this.memberViewabilityLevel = memberViewabilityLevel;
      return this;
    }

    public GroupFullBuilder permissions(GroupFullPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    @Override
    public GroupFullBuilder type(GroupBaseTypeField type) {
      this.type = new EnumWrapper<GroupBaseTypeField>(type);
      return this;
    }

    @Override
    public GroupFullBuilder type(EnumWrapper<GroupBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public GroupFullBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public GroupFullBuilder groupType(GroupMiniGroupTypeField groupType) {
      this.groupType = new EnumWrapper<GroupMiniGroupTypeField>(groupType);
      return this;
    }

    @Override
    public GroupFullBuilder groupType(EnumWrapper<GroupMiniGroupTypeField> groupType) {
      this.groupType = groupType;
      return this;
    }

    @Override
    public GroupFullBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    @Override
    public GroupFullBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public GroupFull build() {
      return new GroupFull(this);
    }
  }
}
