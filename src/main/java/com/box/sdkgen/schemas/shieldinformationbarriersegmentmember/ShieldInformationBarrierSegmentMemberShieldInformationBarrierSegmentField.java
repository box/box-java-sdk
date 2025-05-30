package com.box.sdkgen.schemas.shieldinformationbarriersegmentmember;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField
    extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeField
              .ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeField
              .ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeFieldSerializer
              .class)
  protected EnumWrapper<
          ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeField>
      type;

  public ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField() {
    super();
  }

  protected ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField(
      ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeField>
      getType() {
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
    ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField casted =
        (ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static
  class ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentFieldBuilder {

    protected String id;

    protected EnumWrapper<
            ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeField>
        type;

    public ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentFieldBuilder id(
        String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentFieldBuilder type(
        ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeField type) {
      this.type =
          new EnumWrapper<
              ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeField>(type);
      return this;
    }

    public ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentFieldBuilder type(
        EnumWrapper<ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentTypeField>
            type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField build() {
      return new ShieldInformationBarrierSegmentMemberShieldInformationBarrierSegmentField(this);
    }
  }
}
