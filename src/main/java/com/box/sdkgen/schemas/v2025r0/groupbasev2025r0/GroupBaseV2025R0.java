package com.box.sdkgen.schemas.v2025r0.groupbasev2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class GroupBaseV2025R0 extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = GroupBaseV2025R0TypeField.GroupBaseV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(using = GroupBaseV2025R0TypeField.GroupBaseV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<GroupBaseV2025R0TypeField> type;

  public GroupBaseV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<GroupBaseV2025R0TypeField>(GroupBaseV2025R0TypeField.GROUP);
  }

  protected GroupBaseV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<GroupBaseV2025R0TypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupBaseV2025R0 casted = (GroupBaseV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "GroupBaseV2025R0{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<GroupBaseV2025R0TypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
      this.type = new EnumWrapper<GroupBaseV2025R0TypeField>(GroupBaseV2025R0TypeField.GROUP);
    }

    public Builder type(GroupBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<GroupBaseV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<GroupBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public GroupBaseV2025R0 build() {
      return new GroupBaseV2025R0(this);
    }
  }
}
