package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField
    extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField
              .ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField
              .ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeFieldSerializer
              .class)
  protected EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>
      type;

  public ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField() {
    super();
  }

  protected ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField(
      ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>
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
    ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField casted =
        (ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentFieldBuilder {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>
        type;

    public ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentFieldBuilder id(
        String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentFieldBuilder type(
        ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField type) {
      this.type =
          new EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>(
              type);
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentFieldBuilder type(
        EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>
            type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField build() {
      return new ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField(this);
    }
  }
}
