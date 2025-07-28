package com.box.sdkgen.schemas.aistudioagentask;

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

public enum AiStudioAgentAskTypeField implements Valuable {
  AI_AGENT_ASK("ai_agent_ask");

  private final String value;

  AiStudioAgentAskTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiStudioAgentAskTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiStudioAgentAskTypeField>> {

    public AiStudioAgentAskTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiStudioAgentAskTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiStudioAgentAskTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiStudioAgentAskTypeField>(value));
    }
  }

  public static class AiStudioAgentAskTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiStudioAgentAskTypeField>> {

    public AiStudioAgentAskTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiStudioAgentAskTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
