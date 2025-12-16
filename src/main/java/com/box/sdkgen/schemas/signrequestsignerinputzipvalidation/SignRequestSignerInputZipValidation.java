package com.box.sdkgen.schemas.signrequestsignerinputzipvalidation;

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
public class SignRequestSignerInputZipValidation extends SerializableObject {

  /** Validates that the text input is a ZIP code. */
  @JsonDeserialize(
      using =
          SignRequestSignerInputZipValidationValidationTypeField
              .SignRequestSignerInputZipValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputZipValidationValidationTypeField
              .SignRequestSignerInputZipValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputZipValidationValidationTypeField> validationType;

  public SignRequestSignerInputZipValidation() {
    super();
    this.validationType =
        new EnumWrapper<SignRequestSignerInputZipValidationValidationTypeField>(
            SignRequestSignerInputZipValidationValidationTypeField.ZIP);
  }

  protected SignRequestSignerInputZipValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputZipValidationValidationTypeField> getValidationType() {
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
    SignRequestSignerInputZipValidation casted = (SignRequestSignerInputZipValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputZipValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputZipValidationValidationTypeField> validationType;

    public Builder() {
      super();
      this.validationType =
          new EnumWrapper<SignRequestSignerInputZipValidationValidationTypeField>(
              SignRequestSignerInputZipValidationValidationTypeField.ZIP);
    }

    public Builder validationType(
        SignRequestSignerInputZipValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputZipValidationValidationTypeField>(validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputZipValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputZipValidation build() {
      return new SignRequestSignerInputZipValidation(this);
    }
  }
}
