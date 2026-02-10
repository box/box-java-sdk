package com.box.sdkgen.schemas.groupmini;

import com.box.sdkgen.schemas.groupbase.GroupBase;
import com.box.sdkgen.schemas.groupbase.GroupBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** Mini representation of a group, including id and name of group. */
@JsonFilter("nullablePropertyFilter")
public class GroupMini extends GroupBase {

  /** The name of the group. */
  protected String name;

  /** The type of the group. */
  @JsonDeserialize(using = GroupMiniGroupTypeField.GroupMiniGroupTypeFieldDeserializer.class)
  @JsonSerialize(using = GroupMiniGroupTypeField.GroupMiniGroupTypeFieldSerializer.class)
  @JsonProperty("group_type")
  protected EnumWrapper<GroupMiniGroupTypeField> groupType;

  public GroupMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected GroupMini(Builder builder) {
    super(builder);
    this.name = builder.name;
    this.groupType = builder.groupType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public EnumWrapper<GroupMiniGroupTypeField> getGroupType() {
    return groupType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupMini casted = (GroupMini) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(groupType, casted.groupType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, groupType);
  }

  @Override
  public String toString() {
    return "GroupMini{"
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
        + "}";
  }

  public static class Builder extends GroupBase.Builder {

    protected String name;

    protected EnumWrapper<GroupMiniGroupTypeField> groupType;

    public Builder(String id) {
      super(id);
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder groupType(GroupMiniGroupTypeField groupType) {
      this.groupType = new EnumWrapper<GroupMiniGroupTypeField>(groupType);
      return this;
    }

    public Builder groupType(EnumWrapper<GroupMiniGroupTypeField> groupType) {
      this.groupType = groupType;
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

    public GroupMini build() {
      if (this.type == null) {
        this.type = new EnumWrapper<GroupBaseTypeField>(GroupBaseTypeField.GROUP);
      }
      return new GroupMini(this);
    }
  }
}
