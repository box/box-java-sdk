package com.box.sdkgen.schemas.signrequestsignerinputnumberwithcommavalidation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Specifies the number with comma formatting rules for a text field input by the signer. If set,
 * this validation is mandatory.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInputNumberWithCommaValidation extends SerializableObject {

  /**
   * Validates that the text input uses a number format with a comma as the decimal separator (for
   * example, 1,23).
   */
  @JsonDeserialize(
      using =
          SignRequestSignerInputNumberWithCommaValidationValidationTypeField
              .SignRequestSignerInputNumberWithCommaValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputNumberWithCommaValidationValidationTypeField
              .SignRequestSignerInputNumberWithCommaValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputNumberWithCommaValidationValidationTypeField>
      validationType;

  public SignRequestSignerInputNumberWithCommaValidation() {
    super();
  }

  protected SignRequestSignerInputNumberWithCommaValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputNumberWithCommaValidationValidationTypeField>
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
    SignRequestSignerInputNumberWithCommaValidation casted =
        (SignRequestSignerInputNumberWithCommaValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputNumberWithCommaValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputNumberWithCommaValidationValidationTypeField>
        validationType;

    public Builder validationType(
        SignRequestSignerInputNumberWithCommaValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputNumberWithCommaValidationValidationTypeField>(
              validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputNumberWithCommaValidationValidationTypeField>
            validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputNumberWithCommaValidation build() {
      return new SignRequestSignerInputNumberWithCommaValidation(this);
    }
  }
}
