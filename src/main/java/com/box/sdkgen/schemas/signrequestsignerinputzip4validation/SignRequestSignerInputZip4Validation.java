package com.box.sdkgen.schemas.signrequestsignerinputzip4validation;

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
public class SignRequestSignerInputZip4Validation extends SerializableObject {

  /** Validates that the text input is a ZIP+4 code. */
  @JsonDeserialize(
      using =
          SignRequestSignerInputZip4ValidationValidationTypeField
              .SignRequestSignerInputZip4ValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputZip4ValidationValidationTypeField
              .SignRequestSignerInputZip4ValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputZip4ValidationValidationTypeField> validationType;

  public SignRequestSignerInputZip4Validation() {
    super();
    this.validationType =
        new EnumWrapper<SignRequestSignerInputZip4ValidationValidationTypeField>(
            SignRequestSignerInputZip4ValidationValidationTypeField.ZIP_4);
  }

  protected SignRequestSignerInputZip4Validation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputZip4ValidationValidationTypeField> getValidationType() {
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
    SignRequestSignerInputZip4Validation casted = (SignRequestSignerInputZip4Validation) o;
    return Objects.equals(validationType, casted.validationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputZip4Validation{"
        + "validationType='"
        + validationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputZip4ValidationValidationTypeField> validationType;

    public Builder() {
      super();
      this.validationType =
          new EnumWrapper<SignRequestSignerInputZip4ValidationValidationTypeField>(
              SignRequestSignerInputZip4ValidationValidationTypeField.ZIP_4);
    }

    public Builder validationType(
        SignRequestSignerInputZip4ValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputZip4ValidationValidationTypeField>(validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputZip4ValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public SignRequestSignerInputZip4Validation build() {
      return new SignRequestSignerInputZip4Validation(this);
    }
  }
}
