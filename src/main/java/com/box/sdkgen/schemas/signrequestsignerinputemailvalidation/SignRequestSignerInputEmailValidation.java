package com.box.sdkgen.schemas.signrequestsignerinputemailvalidation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Specifies the formatting rules that signers must follow for text field inputs. If set, this
 * validation is mandatory.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInputEmailValidation extends SerializableObject {

  /** Validates that the text input is an email address. */
  @JsonDeserialize(
      using =
          SignRequestSignerInputEmailValidationValidationTypeField
              .SignRequestSignerInputEmailValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputEmailValidationValidationTypeField
              .SignRequestSignerInputEmailValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField> validationType;

  public SignRequestSignerInputEmailValidation() {
    super();
    this.validationType =
        new EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField>(
            SignRequestSignerInputEmailValidationValidationTypeField.EMAIL);
  }

  protected SignRequestSignerInputEmailValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField> getValidationType() {
    return validationType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestSignerInputEmailValidation casted = (SignRequestSignerInputEmailValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputEmailValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField> validationType;

    public Builder() {
      super();
      this.validationType =
          new EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField>(
              SignRequestSignerInputEmailValidationValidationTypeField.EMAIL);
    }

    public Builder validationType(
        SignRequestSignerInputEmailValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField>(validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputEmailValidation build() {
      return new SignRequestSignerInputEmailValidation(this);
    }
  }
}
