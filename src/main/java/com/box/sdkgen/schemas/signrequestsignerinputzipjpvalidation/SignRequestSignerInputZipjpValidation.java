package com.box.sdkgen.schemas.signrequestsignerinputzipjpvalidation;

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
public class SignRequestSignerInputZipjpValidation extends SerializableObject {

  /** Validates that the text input is a Japanese ZIP code. */
  @JsonDeserialize(
      using =
          SignRequestSignerInputZipjpValidationValidationTypeField
              .SignRequestSignerInputZipjpValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputZipjpValidationValidationTypeField
              .SignRequestSignerInputZipjpValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField> validationType;

  public SignRequestSignerInputZipjpValidation() {
    super();
    this.validationType =
        new EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField>(
            SignRequestSignerInputZipjpValidationValidationTypeField.ZIP_JP);
  }

  protected SignRequestSignerInputZipjpValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField> getValidationType() {
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
    SignRequestSignerInputZipjpValidation casted = (SignRequestSignerInputZipjpValidation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputZipjpValidation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField> validationType;

    public Builder() {
      super();
    }

    public Builder validationType(
        SignRequestSignerInputZipjpValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField>(validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputZipjpValidation build() {
      if (this.validationType == null) {
        this.validationType =
            new EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField>(
                SignRequestSignerInputZipjpValidationValidationTypeField.ZIP_JP);
      }
      return new SignRequestSignerInputZipjpValidation(this);
    }
  }
}
