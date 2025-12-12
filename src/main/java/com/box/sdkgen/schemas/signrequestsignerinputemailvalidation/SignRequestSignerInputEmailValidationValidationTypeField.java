package com.box.sdkgen.schemas.signrequestsignerinputemailvalidation;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum SignRequestSignerInputEmailValidationValidationTypeField implements Valuable {
  EMAIL("email");

  private final String value;

  SignRequestSignerInputEmailValidationValidationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignRequestSignerInputEmailValidationValidationTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField>> {

    public SignRequestSignerInputEmailValidationValidationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestSignerInputEmailValidationValidationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField>(value));
    }
  }

  public static class SignRequestSignerInputEmailValidationValidationTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField>> {

    public SignRequestSignerInputEmailValidationValidationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerInputEmailValidationValidationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
