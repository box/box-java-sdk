package com.box.sdkgen.schemas.shieldinformationbarriersegmentmemberbase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A base representation of a shield information barrier segment member object. */
@JsonFilter("nullablePropertyFilter")
public class ShieldInformationBarrierSegmentMemberBase extends SerializableObject {

  /** The unique identifier for the shield information barrier segment member. */
  protected String id;

  /** The type of the shield information barrier segment member. */
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

  protected ShieldInformationBarrierSegmentMemberBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(ShieldInformationBarrierSegmentMemberBaseTypeField type) {
      this.type = new EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldInformationBarrierSegmentMemberBase build() {
      return new ShieldInformationBarrierSegmentMemberBase(this);
    }
  }
}
