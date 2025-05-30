package com.box.sdkgen.schemas.signrequestsignerinput;

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

public enum SignRequestSignerInputTypeField implements Valuable {
  SIGNATURE("signature"),
  DATE("date"),
  TEXT("text"),
  CHECKBOX("checkbox"),
  RADIO("radio"),
  DROPDOWN("dropdown");

  private final String value;

  SignRequestSignerInputTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignRequestSignerInputTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignRequestSignerInputTypeField>> {

    public SignRequestSignerInputTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerInputTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestSignerInputTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignRequestSignerInputTypeField>(value));
    }
  }

  public static class SignRequestSignerInputTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SignRequestSignerInputTypeField>> {

    public SignRequestSignerInputTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerInputTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
