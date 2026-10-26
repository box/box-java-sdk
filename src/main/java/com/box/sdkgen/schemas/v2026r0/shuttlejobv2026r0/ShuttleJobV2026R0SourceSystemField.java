package com.box.sdkgen.schemas.v2026r0.shuttlejobv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShuttleJobV2026R0SourceSystemField extends SerializableObject {

  /** The value will always be `shuttle_system`. */
  @JsonDeserialize(
      using =
          ShuttleJobV2026R0SourceSystemTypeField.ShuttleJobV2026R0SourceSystemTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShuttleJobV2026R0SourceSystemTypeField.ShuttleJobV2026R0SourceSystemTypeFieldSerializer
              .class)
  protected EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField> type;

  /** The unique identifier for the source system. */
  protected final String id;

  /** The human-readable name of the source system. */
  protected final String name;

  public ShuttleJobV2026R0SourceSystemField(
      @JsonProperty("id") String id, @JsonProperty("name") String name) {
    super();
    this.id = id;
    this.name = name;
    this.type =
        new EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField>(
            ShuttleJobV2026R0SourceSystemTypeField.SHUTTLE_SYSTEM);
  }

  protected ShuttleJobV2026R0SourceSystemField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
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
    ShuttleJobV2026R0SourceSystemField casted = (ShuttleJobV2026R0SourceSystemField) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, name);
  }

  @Override
  public String toString() {
    return "ShuttleJobV2026R0SourceSystemField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField> type;

    protected final String id;

    protected final String name;

    public Builder(String id, String name) {
      super();
      this.id = id;
      this.name = name;
    }

    public Builder type(ShuttleJobV2026R0SourceSystemTypeField type) {
      this.type = new EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField> type) {
      this.type = type;
      return this;
    }

    public ShuttleJobV2026R0SourceSystemField build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField>(
                ShuttleJobV2026R0SourceSystemTypeField.SHUTTLE_SYSTEM);
      }
      return new ShuttleJobV2026R0SourceSystemField(this);
    }
  }
}
