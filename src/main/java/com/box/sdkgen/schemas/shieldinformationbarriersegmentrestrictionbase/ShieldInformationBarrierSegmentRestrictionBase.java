package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionbase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A base representation of a segment restriction object for the shield information barrier. */
@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierSegmentRestrictionBase extends SerializableObject {

  /** Shield information barrier segment restriction. */
  @JsonDeserialize(
      using =
          ShieldInformationBarrierSegmentRestrictionBaseTypeField
              .ShieldInformationBarrierSegmentRestrictionBaseTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldInformationBarrierSegmentRestrictionBaseTypeField
              .ShieldInformationBarrierSegmentRestrictionBaseTypeFieldSerializer.class)
  protected EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> type;

  /** The unique identifier for the shield information barrier segment restriction. */
  protected String id;

  public ShieldInformationBarrierSegmentRestrictionBase() {
    super();
  }

  protected ShieldInformationBarrierSegmentRestrictionBase(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldInformationBarrierSegmentRestrictionBase casted =
        (ShieldInformationBarrierSegmentRestrictionBase) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "ShieldInformationBarrierSegmentRestrictionBase{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> type;

    protected String id;

    public Builder type(ShieldInformationBarrierSegmentRestrictionBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public ShieldInformationBarrierSegmentRestrictionBase build() {
      return new ShieldInformationBarrierSegmentRestrictionBase(this);
    }
  }
}
