package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini;

import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionbase.ShieldInformationBarrierSegmentRestrictionBase;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionbase.ShieldInformationBarrierSegmentRestrictionBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class ShieldInformationBarrierSegmentRestrictionMini
    extends ShieldInformationBarrierSegmentRestrictionBase {

  @JsonProperty("shield_information_barrier_segment")
  protected final ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
      shieldInformationBarrierSegment;

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

  protected ShieldInformationBarrierSegmentRestrictionMini(
      ShieldInformationBarrierSegmentRestrictionMiniBuilder builder) {
    super(builder);
    this.shieldInformationBarrierSegment = builder.shieldInformationBarrierSegment;
    this.restrictedSegment = builder.restrictedSegment;
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

  public static class ShieldInformationBarrierSegmentRestrictionMiniBuilder
      extends ShieldInformationBarrierSegmentRestrictionBaseBuilder {

    protected final
    ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
        shieldInformationBarrierSegment;

    protected final ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField
        restrictedSegment;

    public ShieldInformationBarrierSegmentRestrictionMiniBuilder(
        ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
            shieldInformationBarrierSegment,
        ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField restrictedSegment) {
      super();
      this.shieldInformationBarrierSegment = shieldInformationBarrierSegment;
      this.restrictedSegment = restrictedSegment;
    }

    @Override
    public ShieldInformationBarrierSegmentRestrictionMiniBuilder type(
        ShieldInformationBarrierSegmentRestrictionBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField>(type);
      return this;
    }

    @Override
    public ShieldInformationBarrierSegmentRestrictionMiniBuilder type(
        EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public ShieldInformationBarrierSegmentRestrictionMiniBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionMini build() {
      return new ShieldInformationBarrierSegmentRestrictionMini(this);
    }
  }
}
