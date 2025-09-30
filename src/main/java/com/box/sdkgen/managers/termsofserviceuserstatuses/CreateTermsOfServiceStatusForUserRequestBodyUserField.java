package com.box.sdkgen.managers.termsofserviceuserstatuses;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateTermsOfServiceStatusForUserRequestBodyUserField extends SerializableObject {

  @JsonDeserialize(
      using =
          CreateTermsOfServiceStatusForUserRequestBodyUserTypeField
              .CreateTermsOfServiceStatusForUserRequestBodyUserTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateTermsOfServiceStatusForUserRequestBodyUserTypeField
              .CreateTermsOfServiceStatusForUserRequestBodyUserTypeFieldSerializer.class)
  protected EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField> type;

  protected final String id;

  public CreateTermsOfServiceStatusForUserRequestBodyUserField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField>(
            CreateTermsOfServiceStatusForUserRequestBodyUserTypeField.USER);
  }

  protected CreateTermsOfServiceStatusForUserRequestBodyUserField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField> getType() {
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
    CreateTermsOfServiceStatusForUserRequestBodyUserField casted =
        (CreateTermsOfServiceStatusForUserRequestBodyUserField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "CreateTermsOfServiceStatusForUserRequestBodyUserField{"
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

    protected EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField>(
              CreateTermsOfServiceStatusForUserRequestBodyUserTypeField.USER);
    }

    public Builder type(CreateTermsOfServiceStatusForUserRequestBodyUserTypeField type) {
      this.type = new EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField>(type);
      return this;
    }

    public Builder type(
        EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField> type) {
      this.type = type;
      return this;
    }

    public CreateTermsOfServiceStatusForUserRequestBodyUserField build() {
      return new CreateTermsOfServiceStatusForUserRequestBodyUserField(this);
    }
  }
}
