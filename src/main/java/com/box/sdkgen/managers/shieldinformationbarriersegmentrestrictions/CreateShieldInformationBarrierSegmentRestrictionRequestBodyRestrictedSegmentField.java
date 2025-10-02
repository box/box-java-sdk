package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField
    extends SerializableObject {

  /** The ID reference of the restricted shield information barrier segment. */
  protected String id;

  /** The type of the restricted shield information barrier segment. */
  @JsonDeserialize(
      using =
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField
              .CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField
              .CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeFieldSerializer
              .class)
  protected EnumWrapper<
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>
      type;

  public CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField() {
    super();
  }

  protected CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField(
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
          CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>
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
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField casted =
        (CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField{"
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
            CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>
        type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(
        CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField
            type) {
      this.type =
          new EnumWrapper<
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>(
              type);
      return this;
    }

    public Builder type(
        EnumWrapper<
                CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>
            type) {
      this.type = type;
      return this;
    }

    public CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField
        build() {
      return new CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField(
          this);
    }
  }
}
