package com.box.sdkgen.schemas.signrequestsignerinputdateusvalidation;

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

public enum SignRequestSignerInputDateUsValidationValidationTypeField implements Valuable {
  DATE_US("date_us");

  private final String value;

  SignRequestSignerInputDateUsValidationValidationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignRequestSignerInputDateUsValidationValidationTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField>> {

    public SignRequestSignerInputDateUsValidationValidationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestSignerInputDateUsValidationValidationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField>(value));
    }
  }

  public static class SignRequestSignerInputDateUsValidationValidationTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField>> {

    public SignRequestSignerInputDateUsValidationValidationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerInputDateUsValidationValidationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
