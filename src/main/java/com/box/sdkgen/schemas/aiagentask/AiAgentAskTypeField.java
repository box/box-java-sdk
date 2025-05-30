package com.box.sdkgen.schemas.aiagentask;

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

public enum AiAgentAskTypeField implements Valuable {
  AI_AGENT_ASK("ai_agent_ask");

  private final String value;

  AiAgentAskTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiAgentAskTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiAgentAskTypeField>> {

    public AiAgentAskTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiAgentAskTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiAgentAskTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiAgentAskTypeField>(value));
    }
  }

  public static class AiAgentAskTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiAgentAskTypeField>> {

    public AiAgentAskTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiAgentAskTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
