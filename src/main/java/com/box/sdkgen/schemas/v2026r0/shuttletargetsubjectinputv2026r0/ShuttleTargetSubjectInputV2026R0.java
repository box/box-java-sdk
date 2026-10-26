package com.box.sdkgen.schemas.v2026r0.shuttletargetsubjectinputv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A Box user or group to map a source subject to. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleTargetSubjectInputV2026R0 extends SerializableObject {

  /** Whether the Box target is a user or a group. */
  @JsonDeserialize(
      using =
          ShuttleTargetSubjectInputV2026R0TypeField
              .ShuttleTargetSubjectInputV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleTargetSubjectInputV2026R0TypeField
              .ShuttleTargetSubjectInputV2026R0TypeFieldSerializer.class)
  protected final EnumWrapper<ShuttleTargetSubjectInputV2026R0TypeField> type;

  /** The identifier of the target Box subject. */
  protected final String id;

  public ShuttleTargetSubjectInputV2026R0(
      ShuttleTargetSubjectInputV2026R0TypeField type, String id) {
    super();
    this.type = new EnumWrapper<ShuttleTargetSubjectInputV2026R0TypeField>(type);
    this.id = id;
  }

  public ShuttleTargetSubjectInputV2026R0(
      @JsonProperty("type") EnumWrapper<ShuttleTargetSubjectInputV2026R0TypeField> type,
      @JsonProperty("id") String id) {
    super();
    this.type = type;
    this.id = id;
  }

  public EnumWrapper<ShuttleTargetSubjectInputV2026R0TypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleTargetSubjectInputV2026R0 casted = (ShuttleTargetSubjectInputV2026R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "ShuttleTargetSubjectInputV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }
}
