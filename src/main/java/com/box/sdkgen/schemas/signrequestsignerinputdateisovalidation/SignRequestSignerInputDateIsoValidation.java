package com.box.sdkgen.schemas.signrequestsignerinputdateisovalidation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Specifies the ISO date formatting rules for a text field input by the signer. If set, this
 * validation is mandatory. The date format follows `YYYY-MM-DD` pattern.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInputDateIsoValidation extends SerializableObject {

  /** Validates that the text input uses the ISO date format `YYYY-MM-DD`. */
  @JsonDeserialize(
      using =
          SignRequestSignerInputDateIsoValidationValidationTypeField
              .SignRequestSignerInputDateIsoValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputDateIsoValidationValidationTypeField
              .SignRequestSignerInputDateIsoValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputDateIsoValidationValidationTypeField> validationType;

  public SignRequestSignerInputDateIsoValidation() {
    super();
  }

  protected SignRequestSignerInputDateIsoValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputDateIsoValidationValidationTypeField>
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
    SignRequestSignerInputDateIsoValidation casted = (SignRequestSignerInputDateIsoValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputDateIsoValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputDateIsoValidationValidationTypeField>
        validationType;

    public Builder validationType(
        SignRequestSignerInputDateIsoValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputDateIsoValidationValidationTypeField>(
              validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputDateIsoValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputDateIsoValidation build() {
      return new SignRequestSignerInputDateIsoValidation(this);
    }
  }
}
