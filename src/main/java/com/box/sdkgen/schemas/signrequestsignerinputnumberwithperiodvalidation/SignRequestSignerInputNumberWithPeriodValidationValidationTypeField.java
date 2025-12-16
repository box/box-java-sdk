package com.box.sdkgen.schemas.signrequestsignerinputnumberwithperiodvalidation;

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

public enum SignRequestSignerInputNumberWithPeriodValidationValidationTypeField
    implements Valuable {
  NUMBER_WITH_PERIOD("number_with_period");

  private final String value;

  SignRequestSignerInputNumberWithPeriodValidationValidationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static
  class SignRequestSignerInputNumberWithPeriodValidationValidationTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField>> {

    public SignRequestSignerInputNumberWithPeriodValidationValidationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField>
        deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(
              SignRequestSignerInputNumberWithPeriodValidationValidationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField>(
                  value));
    }
  }

  public static class SignRequestSignerInputNumberWithPeriodValidationValidationTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField>> {

    public SignRequestSignerInputNumberWithPeriodValidationValidationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerInputNumberWithPeriodValidationValidationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
