package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public
class CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField
    extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField
              .CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField
              .CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeFieldSerializer
              .class)
  protected EnumWrapper<
          CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>
      type;

  public
  CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField() {
    super();
  }

  protected
  CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField(
      CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentFieldBuilder
          builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<
          CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>
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
    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField
        casted =
            (CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField)
                o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static
  class CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentFieldBuilder {

    protected String id;

    protected EnumWrapper<
            CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>
        type;

    public
    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentFieldBuilder
        id(String id) {
      this.id = id;
      return this;
    }

    public
    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentFieldBuilder
        type(
            CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField
                type) {
      this.type =
          new EnumWrapper<
              CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>(
              type);
      return this;
    }

    public
    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentFieldBuilder
        type(
            EnumWrapper<
                    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>
                type) {
      this.type = type;
      return this;
    }

    public
    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField
        build() {
      return new CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField(
          this);
    }
  }
}
