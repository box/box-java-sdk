package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestriction;

import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionbase.ShieldInformationBarrierSegmentRestrictionBaseTypeField;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini.ShieldInformationBarrierSegmentRestrictionMini;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini.ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini.ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class ShieldInformationBarrierSegmentRestriction
    extends ShieldInformationBarrierSegmentRestrictionMini {

  @JsonProperty("shield_information_barrier")
  protected ShieldInformationBarrierBase shieldInformationBarrier;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("updated_at")
  protected String updatedAt;

  @JsonProperty("updated_by")
  protected UserBase updatedBy;

  public ShieldInformationBarrierSegmentRestriction(
      @JsonProperty("shield_information_barrier_segment")
          ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
              shieldInformationBarrierSegment,
      @JsonProperty("restricted_segment")
          ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField restrictedSegment) {
    super(shieldInformationBarrierSegment, restrictedSegment);
  }

  protected ShieldInformationBarrierSegmentRestriction(
      ShieldInformationBarrierSegmentRestrictionBuilder builder) {
    super(builder);
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    this.updatedAt = builder.updatedAt;
    this.updatedBy = builder.updatedBy;
  }

  public ShieldInformationBarrierBase getShieldInformationBarrier() {
    return shieldInformationBarrier;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public UserBase getUpdatedBy() {
    return updatedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierSegmentRestriction casted =
        (ShieldInformationBarrierSegmentRestriction) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(shieldInformationBarrierSegment, casted.shieldInformationBarrierSegment)
        && Objects.equals(restrictedSegment, casted.restrictedSegment)
        && Objects.equals(shieldInformationBarrier, casted.shieldInformationBarrier)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(updatedAt, casted.updatedAt)
        && Objects.equals(updatedBy, casted.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type,
        id,
        shieldInformationBarrierSegment,
        restrictedSegment,
        shieldInformationBarrier,
        createdAt,
        createdBy,
        updatedAt,
        updatedBy);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegmentRestriction{"
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
        + ", "
        + "shieldInformationBarrier='"
        + shieldInformationBarrier
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "updatedAt='"
        + updatedAt
        + '\''
        + ", "
        + "updatedBy='"
        + updatedBy
        + '\''
        + "}";
  }

  public static class ShieldInformationBarrierSegmentRestrictionBuilder
      extends ShieldInformationBarrierSegmentRestrictionMiniBuilder {

    protected ShieldInformationBarrierBase shieldInformationBarrier;

    protected String createdAt;

    protected UserBase createdBy;

    protected String updatedAt;

    protected UserBase updatedBy;

    public ShieldInformationBarrierSegmentRestrictionBuilder(
        ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
            shieldInformationBarrierSegment,
        ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField restrictedSegment) {
      super(shieldInformationBarrierSegment, restrictedSegment);
    }

    public ShieldInformationBarrierSegmentRestrictionBuilder shieldInformationBarrier(
        ShieldInformationBarrierBase shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionBuilder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionBuilder updatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionBuilder updatedBy(UserBase updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    @Override
    public ShieldInformationBarrierSegmentRestrictionBuilder type(
        ShieldInformationBarrierSegmentRestrictionBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField>(type);
      return this;
    }

    @Override
    public ShieldInformationBarrierSegmentRestrictionBuilder type(
        EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public ShieldInformationBarrierSegmentRestrictionBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierSegmentRestriction build() {
      return new ShieldInformationBarrierSegmentRestriction(this);
    }
  }
}
