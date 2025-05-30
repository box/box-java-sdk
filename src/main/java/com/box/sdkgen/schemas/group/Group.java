package com.box.sdkgen.schemas.group;

import com.box.sdkgen.schemas.groupbase.GroupBaseTypeField;
import com.box.sdkgen.schemas.groupmini.GroupMini;
import com.box.sdkgen.schemas.groupmini.GroupMiniGroupTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Group extends GroupMini {

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  public Group(@JsonProperty("id") String id) {
    super(id);
  }

  protected Group(GroupBuilder builder) {
    super(builder);
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
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
    Group casted = (Group) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(groupType, casted.groupType)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, groupType, createdAt, modifiedAt);
  }

  @Override
  public String toString() {
    return "Group{"
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
        + "}";
  }

  public static class GroupBuilder extends GroupMiniBuilder {

    protected String createdAt;

    protected String modifiedAt;

    public GroupBuilder(String id) {
      super(id);
    }

    public GroupBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public GroupBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    @Override
    public GroupBuilder type(GroupBaseTypeField type) {
      this.type = new EnumWrapper<GroupBaseTypeField>(type);
      return this;
    }

    @Override
    public GroupBuilder type(EnumWrapper<GroupBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public GroupBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public GroupBuilder groupType(GroupMiniGroupTypeField groupType) {
      this.groupType = new EnumWrapper<GroupMiniGroupTypeField>(groupType);
      return this;
    }

    @Override
    public GroupBuilder groupType(EnumWrapper<GroupMiniGroupTypeField> groupType) {
      this.groupType = groupType;
      return this;
    }

    public Group build() {
      return new Group(this);
    }
  }
}
