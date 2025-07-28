package com.box.sdkgen.schemas.groups;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class GroupsOrderField extends SerializableObject {

  protected String by;

  @JsonDeserialize(using = GroupsOrderDirectionField.GroupsOrderDirectionFieldDeserializer.class)
  @JsonSerialize(using = GroupsOrderDirectionField.GroupsOrderDirectionFieldSerializer.class)
  protected EnumWrapper<GroupsOrderDirectionField> direction;

  public GroupsOrderField() {
    super();
  }

  protected GroupsOrderField(Builder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getBy() {
    return by;
  }

  public EnumWrapper<GroupsOrderDirectionField> getDirection() {
    return direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupsOrderField casted = (GroupsOrderField) o;
    return Objects.equals(by, casted.by) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, direction);
  }

  @Override
  public String toString() {
    return "GroupsOrderField{" + "by='" + by + '\'' + ", " + "direction='" + direction + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String by;

    protected EnumWrapper<GroupsOrderDirectionField> direction;

    public Builder by(String by) {
      this.by = by;
      return this;
    }

    public Builder direction(GroupsOrderDirectionField direction) {
      this.direction = new EnumWrapper<GroupsOrderDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<GroupsOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public GroupsOrderField build() {
      return new GroupsOrderField(this);
    }
  }
}
