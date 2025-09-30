package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateShieldInformationBarrierSegmentRestrictionRequestBody
    extends SerializableObject {

  @JsonDeserialize(
      using =
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField
              .CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField
              .CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeFieldSerializer.class)
  protected EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField> type;

  @JsonProperty("shield_information_barrier")
  protected ShieldInformationBarrierBase shieldInformationBarrier;

  @JsonProperty("shield_information_barrier_segment")
  protected final
  CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField
      shieldInformationBarrierSegment;

  @JsonProperty("restricted_segment")
  protected final CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField
      restrictedSegment;

  public CreateShieldInformationBarrierSegmentRestrictionRequestBody(
      @JsonProperty("shield_information_barrier_segment")
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField
              shieldInformationBarrierSegment,
      @JsonProperty("restricted_segment")
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField
              restrictedSegment) {
    super();
    this.shieldInformationBarrierSegment = shieldInformationBarrierSegment;
    this.restrictedSegment = restrictedSegment;
    this.type =
        new EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField>(
            CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField
                .SHIELD_INFORMATION_BARRIER_SEGMENT_RESTRICTION);
  }

  protected CreateShieldInformationBarrierSegmentRestrictionRequestBody(Builder builder) {
    super();
    this.type = builder.type;
    this.shieldInformationBarrier = builder.shieldInformationBarrier;
    this.shieldInformationBarrierSegment = builder.shieldInformationBarrierSegment;
    this.restrictedSegment = builder.restrictedSegment;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField>
      getType() {
    return type;
  }

  public ShieldInformationBarrierBase getShieldInformationBarrier() {
    return shieldInformationBarrier;
  }

  public
  CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField
      getShieldInformationBarrierSegment() {
    return shieldInformationBarrierSegment;
  }

  public CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField
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
    CreateShieldInformationBarrierSegmentRestrictionRequestBody casted =
        (CreateShieldInformationBarrierSegmentRestrictionRequestBody) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(shieldInformationBarrier, casted.shieldInformationBarrier)
        && Objects.equals(shieldInformationBarrierSegment, casted.shieldInformationBarrierSegment)
        && Objects.equals(restrictedSegment, casted.restrictedSegment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type, shieldInformationBarrier, shieldInformationBarrierSegment, restrictedSegment);
  }

  @Override
  public String toString() {
    return "CreateShieldInformationBarrierSegmentRestrictionRequestBody{"
        + "type='"
        + type
        + '\''
        + ", "
        + "shieldInformationBarrier='"
        + shieldInformationBarrier
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

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField>
        type;

    protected ShieldInformationBarrierBase shieldInformationBarrier;

    protected final
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField
        shieldInformationBarrierSegment;

    protected final
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField
        restrictedSegment;

    public Builder(
        CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField
            shieldInformationBarrierSegment,
        CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField
            restrictedSegment) {
      super();
      this.shieldInformationBarrierSegment = shieldInformationBarrierSegment;
      this.restrictedSegment = restrictedSegment;
      this.type =
          new EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField>(
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField
                  .SHIELD_INFORMATION_BARRIER_SEGMENT_RESTRICTION);
    }

    public Builder type(CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField type) {
      this.type =
          new EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField>(
              type);
      return this;
    }

    public Builder type(
        EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder shieldInformationBarrier(ShieldInformationBarrierBase shieldInformationBarrier) {
      this.shieldInformationBarrier = shieldInformationBarrier;
      return this;
    }

    public CreateShieldInformationBarrierSegmentRestrictionRequestBody build() {
      return new CreateShieldInformationBarrierSegmentRestrictionRequestBody(this);
    }
  }
}
