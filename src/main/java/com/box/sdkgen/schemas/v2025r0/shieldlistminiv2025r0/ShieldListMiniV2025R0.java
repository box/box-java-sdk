package com.box.sdkgen.schemas.v2025r0.shieldlistminiv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A mini representation of a Shield List. */
@JsonFilter("nullablePropertyFilter")
public class ShieldListMiniV2025R0 extends SerializableObject {

  /** Unique global identifier for this list. */
  protected final String id;

  /** The type of object. */
  @JsonDeserialize(
      using = ShieldListMiniV2025R0TypeField.ShieldListMiniV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShieldListMiniV2025R0TypeField.ShieldListMiniV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<ShieldListMiniV2025R0TypeField> type;

  /** Name of Shield List. */
  protected final String name;

  protected final ShieldListMiniV2025R0ContentField content;

  public ShieldListMiniV2025R0(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("content") ShieldListMiniV2025R0ContentField content) {
    super();
    this.id = id;
    this.name = name;
    this.content = content;
    this.type =
        new EnumWrapper<ShieldListMiniV2025R0TypeField>(ShieldListMiniV2025R0TypeField.SHIELD_LIST);
  }

  protected ShieldListMiniV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.content = builder.content;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShieldListMiniV2025R0TypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public ShieldListMiniV2025R0ContentField getContent() {
    return content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldListMiniV2025R0 casted = (ShieldListMiniV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(content, casted.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, content);
  }

  @Override
  public String toString() {
    return "ShieldListMiniV2025R0{"
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
        + "content='"
        + content
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ShieldListMiniV2025R0TypeField> type;

    protected final String name;

    protected final ShieldListMiniV2025R0ContentField content;

    public Builder(String id, String name, ShieldListMiniV2025R0ContentField content) {
      super();
      this.id = id;
      this.name = name;
      this.content = content;
      this.type =
          new EnumWrapper<ShieldListMiniV2025R0TypeField>(
              ShieldListMiniV2025R0TypeField.SHIELD_LIST);
    }

    public Builder type(ShieldListMiniV2025R0TypeField type) {
      this.type = new EnumWrapper<ShieldListMiniV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldListMiniV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldListMiniV2025R0 build() {
      return new ShieldListMiniV2025R0(this);
    }
  }
}
