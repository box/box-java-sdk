package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestriction;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionbase.ShieldInformationBarrierSegmentRestrictionBaseTypeField;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini.ShieldInformationBarrierSegmentRestrictionMini;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini.ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini.ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierSegmentRestriction
    extends ShieldInformationBarrierSegmentRestrictionMini {

  @JsonProperty("shield_information_barrier")
  protected ShieldInformationBarrierBase shieldInformationBarrier;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("updated_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime updatedAt;

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

  protected ShieldInformationBarrierSegmentRestriction(Builder builder) {
    super(builder);
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    this.updatedAt = builder.updatedAt;
    this.updatedBy = builder.updatedBy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public ShieldInformationBarrierBase getShieldInformationBarrier() {
    return shieldInformationBarrier;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  public OffsetDateTime getUpdatedAt() {
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

  public static class Builder extends ShieldInformationBarrierSegmentRestrictionMini.Builder {

    protected ShieldInformationBarrierBase shieldInformationBarrier;

    protected OffsetDateTime createdAt;

    protected UserBase createdBy;

    protected OffsetDateTime updatedAt;

    protected UserBase updatedBy;

    public Builder(
        ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentField
            shieldInformationBarrierSegment,
        ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentField restrictedSegment) {
      super(shieldInformationBarrierSegment, restrictedSegment);
    }

    public Builder shieldInformationBarrier(ShieldInformationBarrierBase shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder updatedAt(OffsetDateTime updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public Builder updatedBy(UserBase updatedBy) {
      this.updatedBy = updatedBy;
      return this;
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

    public ShieldInformationBarrierSegmentRestriction build() {
      return new ShieldInformationBarrierSegmentRestriction(this);
    }
  }
}
