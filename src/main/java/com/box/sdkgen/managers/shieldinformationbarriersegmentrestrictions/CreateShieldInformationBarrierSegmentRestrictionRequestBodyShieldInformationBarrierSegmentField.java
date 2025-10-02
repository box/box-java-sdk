package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public
class CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField
    extends SerializableObject {

  /** The ID reference of the requesting shield information barrier segment. */
  protected String id;

  /** The type of the shield barrier segment for this member. */
  @JsonDeserialize(
      using =
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField
              .CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField
              .CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeFieldSerializer
              .class)
  protected EnumWrapper<
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>
      type;

  public
  CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField() {
    super();
  }

  protected
  CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField(
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
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>
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
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField
        casted =
            (CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField)
                o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField{"
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
            CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>
        type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(
        CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField
            type) {
      this.type =
          new EnumWrapper<
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>(
              type);
      return this;
    }

    public Builder type(
        EnumWrapper<
                CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>
            type) {
      this.type = type;
      return this;
    }

    public
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField
        build() {
      return new CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField(
          this);
    }
  }
}
