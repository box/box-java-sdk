package com.box.sdkgen.schemas.signrequestsignerinputcustomvalidation;

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

public enum SignRequestSignerInputCustomValidationValidationTypeField implements Valuable {
  CUSTOM("custom");

  private final String value;

  SignRequestSignerInputCustomValidationValidationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignRequestSignerInputCustomValidationValidationTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField>> {

    public SignRequestSignerInputCustomValidationValidationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestSignerInputCustomValidationValidationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField>(value));
    }
  }

  public static class SignRequestSignerInputCustomValidationValidationTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField>> {

    public SignRequestSignerInputCustomValidationValidationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerInputCustomValidationValidationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
