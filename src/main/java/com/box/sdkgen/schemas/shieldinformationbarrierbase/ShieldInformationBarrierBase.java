package com.box.sdkgen.schemas.shieldinformationbarrierbase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierBase extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          ShieldInformationBarrierBaseTypeField.ShieldInformationBarrierBaseTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierBaseTypeField.ShieldInformationBarrierBaseTypeFieldSerializer
              .class)
  protected EnumWrapper<ShieldInformationBarrierBaseTypeField> type;

  public ShieldInformationBarrierBase() {
    super();
  }

  protected ShieldInformationBarrierBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShieldInformationBarrierBaseTypeField> getType() {
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
    ShieldInformationBarrierBase casted = (ShieldInformationBarrierBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierBase{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierBaseTypeField> type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(ShieldInformationBarrierBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldInformationBarrierBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierBase build() {
      return new ShieldInformationBarrierBase(this);
    }
  }
}
