package com.box.sdkgen.schemas.v2025r0.hubitemv2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A Box Hub Item is a Box Item that is referenced in a Box Hub. */
@JsonFilter("nullablePropertyFilter")
public class HubItemV2025R0 extends SerializableObject {

  /** The unique identifier for this item. */
  protected final String id;

  /** The type of the item. */
  @JsonDeserialize(using = HubItemV2025R0TypeField.HubItemV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(using = HubItemV2025R0TypeField.HubItemV2025R0TypeFieldSerializer.class)
  protected final EnumWrapper<HubItemV2025R0TypeField> type;

  /** The name of the item. */
  protected final String name;

  public HubItemV2025R0(String id, HubItemV2025R0TypeField type, String name) {
    super();
    this.id = id;
    this.type = new EnumWrapper<HubItemV2025R0TypeField>(type);
    this.name = name;
  }

  public HubItemV2025R0(
      @JsonProperty("id") String id,
      @JsonProperty("type") EnumWrapper<HubItemV2025R0TypeField> type,
      @JsonProperty("name") String name) {
    super();
    this.id = id;
    this.type = type;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<HubItemV2025R0TypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubItemV2025R0 casted = (HubItemV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name);
  }

  @Override
  public String toString() {
    return "HubItemV2025R0{"
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
        + "}";
  }
}
