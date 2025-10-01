package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField
    extends SerializableObject {

  /** The ID reference of the restricted shield information barrier segment. */
  protected String id;

  /** The type of the shield information segment. */
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

  protected ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>
        type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(
        ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField type) {
      this.type =
          new EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>(
              type);
      return this;
    }

    public Builder type(
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
