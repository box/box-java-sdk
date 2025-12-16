package com.box.sdkgen.schemas.signrequestsignerinputcustomvalidation;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Specifies the custom validation rules for a text field input by the signer. If set, this
 * validation is mandatory.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerInputCustomValidation extends SerializableObject {

  /**
   * Defines the validation format for the text input as custom. A custom regular expression is used
   * for validation.
   */
  @JsonDeserialize(
      using =
          SignRequestSignerInputCustomValidationValidationTypeField
              .SignRequestSignerInputCustomValidationValidationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerInputCustomValidationValidationTypeField
              .SignRequestSignerInputCustomValidationValidationTypeFieldSerializer.class)
  @JsonProperty("validation_type")
  protected EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField> validationType;

  /** Regular expression used for validation. */
  @JsonProperty("custom_regex")
  @Nullable
  protected String customRegex;

  /** Error message shown if input fails custom regular expression validation. */
  @JsonProperty("custom_error_message")
  @Nullable
  protected String customErrorMessage;

  public SignRequestSignerInputCustomValidation() {
    super();
    this.validationType =
        new EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField>(
            SignRequestSignerInputCustomValidationValidationTypeField.CUSTOM);
  }

  protected SignRequestSignerInputCustomValidation(Builder builder) {
    super();
    this.validationType = builder.validationType;
    this.customRegex = builder.customRegex;
    this.customErrorMessage = builder.customErrorMessage;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField>
      getValidationType() {
    return validationType;
  }

  public String getCustomRegex() {
    return customRegex;
  }

  public String getCustomErrorMessage() {
    return customErrorMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestSignerInputCustomValidation casted = (SignRequestSignerInputCustomValidation) o;
    return Objects.equals(validationType, casted.validationType)
        && Objects.equals(customRegex, casted.customRegex)
        && Objects.equals(customErrorMessage, casted.customErrorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationType, customRegex, customErrorMessage);
  }

  @Override
  public String toString() {
    return "SignRequestSignerInputCustomValidation{"
        + "validationType='"
        + validationType
        + '\''
        + ", "
        + "customRegex='"
        + customRegex
        + '\''
        + ", "
        + "customErrorMessage='"
        + customErrorMessage
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField> validationType;

    protected String customRegex;

    protected String customErrorMessage;

    public Builder() {
      super();
      this.validationType =
          new EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField>(
              SignRequestSignerInputCustomValidationValidationTypeField.CUSTOM);
    }

    public Builder validationType(
        SignRequestSignerInputCustomValidationValidationTypeField validationType) {
      this.validationType =
          new EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField>(
              validationType);
      return this;
    }

    public Builder validationType(
        EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField> validationType) {
      this.validationType = validationType;
      return this;
    }

    public Builder customRegex(String customRegex) {
      this.customRegex = customRegex;
      this.markNullableFieldAsSet("custom_regex");
      return this;
    }

    public Builder customErrorMessage(String customErrorMessage) {
      this.customErrorMessage = customErrorMessage;
      this.markNullableFieldAsSet("custom_error_message");
      return this;
    }

    public SignRequestSignerInputCustomValidation build() {
      return new SignRequestSignerInputCustomValidation(this);
    }
  }
}
