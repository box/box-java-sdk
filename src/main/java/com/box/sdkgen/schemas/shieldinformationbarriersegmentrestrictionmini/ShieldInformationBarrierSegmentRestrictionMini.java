package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini;

import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionbase.ShieldInformationBarrierSegmentRestrictionBase;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionbase.ShieldInformationBarrierSegmentRestrictionBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A mini representation of a segment restriction object for the shield information barrier. */
@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierSegmentRestrictionMini
    extends ShieldInformationBarrierSegmentRestrictionBase {

  /** The `type` and `id` of the requested shield information barrier segment. */
  @JsonProperty("shield_information_barrier_segment")
  protected final ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
      shieldInformationBarrierSegment;

  /** The `type` and `id` of the restricted shield information barrier segment. */
  @JsonProperty("restricted_segment")
  protected final ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField
      restrictedSegment;

  public ShieldInformationBarrierSegmentRestrictionMini(
      @JsonProperty("shield_information_barrier_segment")
          ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
              shieldInformationBarrierSegment,
      @JsonProperty("restricted_segment")
          ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField restrictedSegment) {
    super();
    this.shieldInformationBarrierSegment = shieldInformationBarrierSegment;
    this.restrictedSegment = restrictedSegment;
  }

  protected ShieldInformationBarrierSegmentRestrictionMini(Builder builder) {
    super(builder);
    this.shieldInformationBarrierSegment = builder.shieldInformationBarrierSegment;
    this.restrictedSegment = builder.restrictedSegment;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
      getShieldInformationBarrierSegment() {
    return shieldInformationBarrierSegment;
  }

  public ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField
      getRestrictedSegment() {
    return restrictedSegment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierSegmentRestrictionMini casted =
        (ShieldInformationBarrierSegmentRestrictionMini) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(shieldInformationBarrierSegment, casted.shieldInformationBarrierSegment)
        && Objects.equals(restrictedSegment, casted.restrictedSegment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, shieldInformationBarrierSegment, restrictedSegment);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegmentRestrictionMini{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "shieldInformationBarrierSegment='"
        + shieldInformationBarrierSegment
        + '\''
        + ", "
        + "restrictedSegment='"
        + restrictedSegment
        + '\''
        + "}";
  }

  public static class Builder extends ShieldInformationBarrierSegmentRestrictionBase.Builder {

    protected final
    ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
        shieldInformationBarrierSegment;

    protected final ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField
        restrictedSegment;

    public Builder(
        ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
            shieldInformationBarrierSegment,
        ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField restrictedSegment) {
      super();
      this.shieldInformationBarrierSegment = shieldInformationBarrierSegment;
      this.restrictedSegment = restrictedSegment;
    }

    @Override
    public Builder type(ShieldInformationBarrierSegmentRestrictionBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionMini build() {
      return new ShieldInformationBarrierSegmentRestrictionMini(this);
    }
  }
}
