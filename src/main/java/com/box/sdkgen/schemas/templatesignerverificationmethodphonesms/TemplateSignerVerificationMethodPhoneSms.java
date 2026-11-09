package com.box.sdkgen.schemas.templatesignerverificationmethodphonesms;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * When used as a verification method, prior to signing the document, the signer will have to verify
 * themselves using two-factor authentication by receiving an SMS message on their phone.
 */
@JsonFilter("nullablePropertyFilter")
public class TemplateSignerVerificationMethodPhoneSms extends SerializableObject {

  /** The type of verification method. */
  @JsonDeserialize(
      using =
          TemplateSignerVerificationMethodPhoneSmsTypeField
              .TemplateSignerVerificationMethodPhoneSmsTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          TemplateSignerVerificationMethodPhoneSmsTypeField
              .TemplateSignerVerificationMethodPhoneSmsTypeFieldSerializer.class)
  protected EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField> type;

  public TemplateSignerVerificationMethodPhoneSms() {
    super();
    this.type =
        new EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField>(
            TemplateSignerVerificationMethodPhoneSmsTypeField.PHONE);
  }

  protected TemplateSignerVerificationMethodPhoneSms(Builder builder) {
    super();
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateSignerVerificationMethodPhoneSms casted = (TemplateSignerVerificationMethodPhoneSms) o;
    return Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    return "TemplateSignerVerificationMethodPhoneSms{" + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField> type;

    public Builder() {
      super();
    }

    public Builder type(TemplateSignerVerificationMethodPhoneSmsTypeField type) {
      this.type = new EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField> type) {
      this.type = type;
      return this;
    }

    public TemplateSignerVerificationMethodPhoneSms build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<TemplateSignerVerificationMethodPhoneSmsTypeField>(
                TemplateSignerVerificationMethodPhoneSmsTypeField.PHONE);
      }
      return new TemplateSignerVerificationMethodPhoneSms(this);
    }
  }
}
