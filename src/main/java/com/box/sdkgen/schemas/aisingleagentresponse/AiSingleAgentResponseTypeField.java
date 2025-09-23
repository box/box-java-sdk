package com.box.sdkgen.schemas.aisingleagentresponse;

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

public enum AiSingleAgentResponseTypeField implements Valuable {
  AI_AGENT("ai_agent");

  private final String value;

  AiSingleAgentResponseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiSingleAgentResponseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiSingleAgentResponseTypeField>> {

    public AiSingleAgentResponseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiSingleAgentResponseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiSingleAgentResponseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiSingleAgentResponseTypeField>(value));
    }
  }

  public static class AiSingleAgentResponseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiSingleAgentResponseTypeField>> {

    public AiSingleAgentResponseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiSingleAgentResponseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
