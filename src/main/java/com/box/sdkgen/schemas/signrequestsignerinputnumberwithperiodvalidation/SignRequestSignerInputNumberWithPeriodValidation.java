package com.box.sdkgen.schemas.signrequestsignerinputnumberwithperiodvalidation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Specifies the number with period formatting rules for a text field input by the signer. If set,
 * this validation is mandatory.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInputNumberWithPeriodValidation extends SerializableObject {

  /**
   * Validates that the text input uses a number format with a period as the decimal separator (for
   * example, 1.23).
   */
  @JsonDeserialize(
      using =
          SignRequestSignerInputNumberWithPeriodValidationValidationTypeField
              .SignRequestSignerInputNumberWithPeriodValidationValidationTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          SignRequestSignerInputNumberWithPeriodValidationValidationTypeField
              .SignRequestSignerInputNumberWithPeriodValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField>
      validationType;

  public SignRequestSignerInputNumberWithPeriodValidation() {
    super();
  }

  protected SignRequestSignerInputNumberWithPeriodValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField>
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
    SignRequestSignerInputNumberWithPeriodValidation casted =
        (SignRequestSignerInputNumberWithPeriodValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputNumberWithPeriodValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField>
        validationType;

    public Builder validationType(
        SignRequestSignerInputNumberWithPeriodValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField>(
              validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField>
            validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputNumberWithPeriodValidation build() {
      return new SignRequestSignerInputNumberWithPeriodValidation(this);
    }
  }
}
