package com.box.sdkgen.schemas.signrequestsignerinputdateasiavalidation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Specifies the date formatting rules used in Asia for a text field input by the signer. If set,
 * this validation is mandatory. The date format follows `YYYY/MM/DD` pattern.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInputDateAsiaValidation extends SerializableObject {

  /** Validates that the text input uses the Asian date format `YYYY/MM/DD`. */
  @JsonDeserialize(
      using =
          SignRequestSignerInputDateAsiaValidationValidationTypeField
              .SignRequestSignerInputDateAsiaValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputDateAsiaValidationValidationTypeField
              .SignRequestSignerInputDateAsiaValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputDateAsiaValidationValidationTypeField> validationType;

  public SignRequestSignerInputDateAsiaValidation() {
    super();
  }

  protected SignRequestSignerInputDateAsiaValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputDateAsiaValidationValidationTypeField>
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
    SignRequestSignerInputDateAsiaValidation casted = (SignRequestSignerInputDateAsiaValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputDateAsiaValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputDateAsiaValidationValidationTypeField>
        validationType;

    public Builder validationType(
        SignRequestSignerInputDateAsiaValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputDateAsiaValidationValidationTypeField>(
              validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputDateAsiaValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputDateAsiaValidation build() {
      return new SignRequestSignerInputDateAsiaValidation(this);
    }
  }
}
