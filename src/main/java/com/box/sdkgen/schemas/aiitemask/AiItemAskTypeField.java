package com.box.sdkgen.schemas.aiitemask;

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

public enum AiItemAskTypeField implements Valuable {
  FILE("file"),
  HUBS("hubs");

  private final String value;

  AiItemAskTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiItemAskTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiItemAskTypeField>> {

    public AiItemAskTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiItemAskTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiItemAskTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiItemAskTypeField>(value));
    }
  }

  public static class AiItemAskTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiItemAskTypeField>> {

    public AiItemAskTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiItemAskTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
