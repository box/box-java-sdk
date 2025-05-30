package com.box.sdkgen.schemas.shieldinformationbarrierbase;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

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

  protected ShieldInformationBarrierBase(ShieldInformationBarrierBaseBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
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

  public static class ShieldInformationBarrierBaseBuilder {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierBaseTypeField> type;

    public ShieldInformationBarrierBaseBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierBaseBuilder type(ShieldInformationBarrierBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierBaseTypeField>(type);
      return this;
    }

    public ShieldInformationBarrierBaseBuilder type(
        EnumWrapper<ShieldInformationBarrierBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierBase build() {
      return new ShieldInformationBarrierBase(this);
    }
  }
}
