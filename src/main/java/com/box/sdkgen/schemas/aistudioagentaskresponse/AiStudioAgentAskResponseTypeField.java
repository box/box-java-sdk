package com.box.sdkgen.schemas.aistudioagentaskresponse;

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

public enum AiStudioAgentAskResponseTypeField implements Valuable {
  AI_AGENT_ASK("ai_agent_ask");

  private final String value;

  AiStudioAgentAskResponseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiStudioAgentAskResponseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiStudioAgentAskResponseTypeField>> {

    public AiStudioAgentAskResponseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiStudioAgentAskResponseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiStudioAgentAskResponseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiStudioAgentAskResponseTypeField>(value));
    }
  }

  public static class AiStudioAgentAskResponseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiStudioAgentAskResponseTypeField>> {

    public AiStudioAgentAskResponseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiStudioAgentAskResponseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
