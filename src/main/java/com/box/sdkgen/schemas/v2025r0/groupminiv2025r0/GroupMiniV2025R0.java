package com.box.sdkgen.schemas.v2025r0.groupminiv2025r0;

import com.box.sdkgen.schemas.v2025r0.groupbasev2025r0.GroupBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.groupbasev2025r0.GroupBaseV2025R0TypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** Mini representation of a group, including id and name of group. */
@JsonFilter("nullablePropertyFilter")
public class GroupMiniV2025R0 extends GroupBaseV2025R0 {

  /** The name of the group. */
  protected String name;

  /** The type of the group. */
  @JsonDeserialize(
      using = GroupMiniV2025R0GroupTypeField.GroupMiniV2025R0GroupTypeFieldDeserializer.class)
  @JsonSerialize(
      using = GroupMiniV2025R0GroupTypeField.GroupMiniV2025R0GroupTypeFieldSerializer.class)
  @JsonProperty("group_type")
  protected EnumWrapper<GroupMiniV2025R0GroupTypeField> groupType;

  public GroupMiniV2025R0(@JsonProperty("id") String id) {
    super(id);
  }

  protected GroupMiniV2025R0(Builder builder) {
    super(builder);
    this.name = builder.name;
    this.groupType = builder.groupType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public EnumWrapper<GroupMiniV2025R0GroupTypeField> getGroupType() {
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
    GroupMiniV2025R0 casted = (GroupMiniV2025R0) o;
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
    return "GroupMiniV2025R0{"
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

  public static class Builder extends GroupBaseV2025R0.Builder {

    protected String name;

    protected EnumWrapper<GroupMiniV2025R0GroupTypeField> groupType;

    public Builder(String id) {
      super(id);
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder groupType(GroupMiniV2025R0GroupTypeField groupType) {
      this.groupType = new EnumWrapper<GroupMiniV2025R0GroupTypeField>(groupType);
      return this;
    }

    public Builder groupType(EnumWrapper<GroupMiniV2025R0GroupTypeField> groupType) {
      this.groupType = groupType;
      return this;
    }

    @Override
    public Builder type(GroupBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<GroupBaseV2025R0TypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<GroupBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public GroupMiniV2025R0 build() {
      if (this.type == null) {
        this.type = new EnumWrapper<GroupBaseV2025R0TypeField>(GroupBaseV2025R0TypeField.GROUP);
      }
      return new GroupMiniV2025R0(this);
    }
  }
}
