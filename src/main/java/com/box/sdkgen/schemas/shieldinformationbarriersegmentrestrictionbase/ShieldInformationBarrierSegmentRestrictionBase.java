package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionbase;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ShieldInformationBarrierSegmentRestrictionBase extends SerializableObject {

  @JsonDeserialize(
      using =
          ShieldInformationBarrierSegmentRestrictionBaseTypeField
              .ShieldInformationBarrierSegmentRestrictionBaseTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierSegmentRestrictionBaseTypeField
              .ShieldInformationBarrierSegmentRestrictionBaseTypeFieldSerializer.class)
  protected EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> type;

  protected String id;

  public ShieldInformationBarrierSegmentRestrictionBase() {
    super();
  }

  protected ShieldInformationBarrierSegmentRestrictionBase(
      ShieldInformationBarrierSegmentRestrictionBaseBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
  }

  public EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> getType() {
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
    ShieldInformationBarrierSegmentRestrictionBase casted =
        (ShieldInformationBarrierSegmentRestrictionBase) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegmentRestrictionBase{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class ShieldInformationBarrierSegmentRestrictionBaseBuilder {

    protected EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> type;

    protected String id;

    public ShieldInformationBarrierSegmentRestrictionBaseBuilder type(
        ShieldInformationBarrierSegmentRestrictionBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField>(type);
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionBaseBuilder type(
        EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionBaseBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionBase build() {
      return new ShieldInformationBarrierSegmentRestrictionBase(this);
    }
  }
}
