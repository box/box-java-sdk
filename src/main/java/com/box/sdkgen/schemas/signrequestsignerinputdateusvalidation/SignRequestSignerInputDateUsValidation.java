package com.box.sdkgen.schemas.signrequestsignerinputdateusvalidation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Specifies the US date formatting rules for a text field input by the signer. If set, this
 * validation is mandatory. The date format follows `MM/DD/YYYY` pattern.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInputDateUsValidation extends SerializableObject {

  /** Validates that the text input uses the US date format `MM/DD/YYYY`. */
  @JsonDeserialize(
      using =
          SignRequestSignerInputDateUsValidationValidationTypeField
              .SignRequestSignerInputDateUsValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputDateUsValidationValidationTypeField
              .SignRequestSignerInputDateUsValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField> validationType;

  public SignRequestSignerInputDateUsValidation() {
    super();
  }

  protected SignRequestSignerInputDateUsValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField>
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
    SignRequestSignerInputDateUsValidation casted = (SignRequestSignerInputDateUsValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputDateUsValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField> validationType;

    public Builder validationType(
        SignRequestSignerInputDateUsValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField>(
              validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputDateUsValidation build() {
      return new SignRequestSignerInputDateUsValidation(this);
    }
  }
}
