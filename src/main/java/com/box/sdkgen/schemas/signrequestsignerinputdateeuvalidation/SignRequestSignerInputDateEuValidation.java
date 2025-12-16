package com.box.sdkgen.schemas.signrequestsignerinputdateeuvalidation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Specifies the date formatting rules used in Europe for a text field input by the signer. If set,
 * this validation is mandatory. The date format follows `DD/MM/YYYY` pattern.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInputDateEuValidation extends SerializableObject {

  /** Validates that the text input uses the European date format `DD/MM/YYYY`. */
  @JsonDeserialize(
      using =
          SignRequestSignerInputDateEuValidationValidationTypeField
              .SignRequestSignerInputDateEuValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputDateEuValidationValidationTypeField
              .SignRequestSignerInputDateEuValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputDateEuValidationValidationTypeField> validationType;

  public SignRequestSignerInputDateEuValidation() {
    super();
  }

  protected SignRequestSignerInputDateEuValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputDateEuValidationValidationTypeField>
      getValidationType() {
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
    SignRequestSignerInputDateEuValidation casted = (SignRequestSignerInputDateEuValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputDateEuValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputDateEuValidationValidationTypeField> validationType;

    public Builder validationType(
        SignRequestSignerInputDateEuValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputDateEuValidationValidationTypeField>(
              validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputDateEuValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputDateEuValidation build() {
      return new SignRequestSignerInputDateEuValidation(this);
    }
  }
}
