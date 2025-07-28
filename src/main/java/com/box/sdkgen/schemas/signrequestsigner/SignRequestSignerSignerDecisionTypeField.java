package com.box.sdkgen.schemas.signrequestsigner;

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

public enum SignRequestSignerSignerDecisionTypeField implements Valuable {
  SIGNED("signed"),
  DECLINED("declined");

  private final String value;

  SignRequestSignerSignerDecisionTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignRequestSignerSignerDecisionTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignRequestSignerSignerDecisionTypeField>> {

    public SignRequestSignerSignerDecisionTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerSignerDecisionTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestSignerSignerDecisionTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignRequestSignerSignerDecisionTypeField>(value));
    }
  }

  public static class SignRequestSignerSignerDecisionTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SignRequestSignerSignerDecisionTypeField>> {

    public SignRequestSignerSignerDecisionTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerSignerDecisionTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
