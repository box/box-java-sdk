package com.box.sdkgen.schemas.shieldinformationbarriersegmentmemberbase;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ShieldInformationBarrierSegmentMemberBase extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          ShieldInformationBarrierSegmentMemberBaseTypeField
              .ShieldInformationBarrierSegmentMemberBaseTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierSegmentMemberBaseTypeField
              .ShieldInformationBarrierSegmentMemberBaseTypeFieldSerializer.class)
  protected EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> type;

  public ShieldInformationBarrierSegmentMemberBase() {
    super();
  }

  protected ShieldInformationBarrierSegmentMemberBase(
      ShieldInformationBarrierSegmentMemberBaseBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> getType() {
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
    ShieldInformationBarrierSegmentMemberBase casted =
        (ShieldInformationBarrierSegmentMemberBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegmentMemberBase{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class ShieldInformationBarrierSegmentMemberBaseBuilder {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> type;

    public ShieldInformationBarrierSegmentMemberBaseBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberBaseBuilder type(
        ShieldInformationBarrierSegmentMemberBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField>(type);
      return this;
    }

    public ShieldInformationBarrierSegmentMemberBaseBuilder type(
        EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberBase build() {
      return new ShieldInformationBarrierSegmentMemberBase(this);
    }
  }
}
