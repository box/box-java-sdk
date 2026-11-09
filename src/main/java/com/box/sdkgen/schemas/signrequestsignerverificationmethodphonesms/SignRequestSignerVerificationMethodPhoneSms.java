package com.box.sdkgen.schemas.signrequestsignerverificationmethodphonesms;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * When used as a verification method, prior to signing the document, the signer will have to verify
 * themselves using two-factor authentication by receiving an SMS message on their phone.
 */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerVerificationMethodPhoneSms extends SerializableObject {

  /** The type of verification method. */
  @JsonDeserialize(
      using =
          SignRequestSignerVerificationMethodPhoneSmsTypeField
              .SignRequestSignerVerificationMethodPhoneSmsTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerVerificationMethodPhoneSmsTypeField
              .SignRequestSignerVerificationMethodPhoneSmsTypeFieldSerializer.class)
  protected EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField> type;

  /** The phone number to where the message will be sent. */
  @JsonProperty("phone_number")
  protected final String phoneNumber;

  public SignRequestSignerVerificationMethodPhoneSms(
      @JsonProperty("phone_number") String phoneNumber) {
    super();
    this.phoneNumber = phoneNumber;
    this.type =
        new EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField>(
            SignRequestSignerVerificationMethodPhoneSmsTypeField.PHONE);
  }

  protected SignRequestSignerVerificationMethodPhoneSms(Builder builder) {
    super();
    this.type = builder.type;
    this.phoneNumber = builder.phoneNumber;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField> getType() {
    return type;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestSignerVerificationMethodPhoneSms casted =
        (SignRequestSignerVerificationMethodPhoneSms) o;
    return Objects.equals(type, casted.type) && Objects.equals(phoneNumber, casted.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, phoneNumber);
  }

  @Override
  public String toString() {
    return "SignRequestSignerVerificationMethodPhoneSms{"
        + "type='"
        + type
        + '\''
        + ", "
        + "phoneNumber='"
        + phoneNumber
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField> type;

    protected final String phoneNumber;

    public Builder(String phoneNumber) {
      super();
      this.phoneNumber = phoneNumber;
    }

    public Builder type(SignRequestSignerVerificationMethodPhoneSmsTypeField type) {
      this.type = new EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField> type) {
      this.type = type;
      return this;
    }

    public SignRequestSignerVerificationMethodPhoneSms build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField>(
                SignRequestSignerVerificationMethodPhoneSmsTypeField.PHONE);
      }
      return new SignRequestSignerVerificationMethodPhoneSms(this);
    }
  }
}
