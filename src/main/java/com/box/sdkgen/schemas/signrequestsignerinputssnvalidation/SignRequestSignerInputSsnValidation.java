package com.box.sdkgen.schemas.signrequestsignerinputssnvalidation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Specifies the validation rules for a text field input by the signer. If set, this validation is
 * mandatory.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInputSsnValidation extends SerializableObject {

  /** Validates that the text input is a Social Security Number (SSN). */
  @JsonDeserialize(
      using =
          SignRequestSignerInputSsnValidationValidationTypeField
              .SignRequestSignerInputSsnValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputSsnValidationValidationTypeField
              .SignRequestSignerInputSsnValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputSsnValidationValidationTypeField> validationType;

  public SignRequestSignerInputSsnValidation() {
    super();
    this.validationType =
        new EnumWrapper<SignRequestSignerInputSsnValidationValidationTypeField>(
            SignRequestSignerInputSsnValidationValidationTypeField.SSN);
  }

  protected SignRequestSignerInputSsnValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputSsnValidationValidationTypeField> getValidationType() {
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
    SignRequestSignerInputSsnValidation casted = (SignRequestSignerInputSsnValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputSsnValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputSsnValidationValidationTypeField> validationType;

    public Builder() {
      super();
      this.validationType =
          new EnumWrapper<SignRequestSignerInputSsnValidationValidationTypeField>(
              SignRequestSignerInputSsnValidationValidationTypeField.SSN);
    }

    public Builder validationType(
        SignRequestSignerInputSsnValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputSsnValidationValidationTypeField>(validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputSsnValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputSsnValidation build() {
      return new SignRequestSignerInputSsnValidation(this);
    }
  }
}
