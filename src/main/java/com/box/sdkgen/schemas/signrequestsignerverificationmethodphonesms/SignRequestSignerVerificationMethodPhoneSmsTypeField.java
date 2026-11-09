package com.box.sdkgen.schemas.signrequestsignerverificationmethodphonesms;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum SignRequestSignerVerificationMethodPhoneSmsTypeField implements Valuable {
  PHONE("phone");

  private final String value;

  SignRequestSignerVerificationMethodPhoneSmsTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class SignRequestSignerVerificationMethodPhoneSmsTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField>> {

    public SignRequestSignerVerificationMethodPhoneSmsTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestSignerVerificationMethodPhoneSmsTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField>(value));
    }
  }

  public static class SignRequestSignerVerificationMethodPhoneSmsTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField>> {

    public SignRequestSignerVerificationMethodPhoneSmsTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerVerificationMethodPhoneSmsTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
