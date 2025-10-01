package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
    extends SerializableObject {

  /** The ID reference of the requesting shield information barrier segment. */
  protected String id;

  /** The type of the shield information barrier segment. */
  @JsonDeserialize(
      using =
          ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField
              .ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField
              .ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeFieldSerializer
              .class)
  protected EnumWrapper<
          ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>
      type;

  public ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField() {
    super();
  }

  protected ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField(
      Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<
          ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>
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
    ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField casted =
        (ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField{"
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

    protected EnumWrapper<
            ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>
        type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(
        ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField
            type) {
      this.type =
          new EnumWrapper<
              ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>(
              type);
      return this;
    }

    public Builder type(
        EnumWrapper<
                ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>
            type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
        build() {
      return new ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField(
          this);
    }
  }
}
