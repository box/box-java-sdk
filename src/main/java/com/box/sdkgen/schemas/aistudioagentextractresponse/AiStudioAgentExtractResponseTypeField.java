package com.box.sdkgen.schemas.aistudioagentextractresponse;

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

public enum AiStudioAgentExtractResponseTypeField implements Valuable {
  AI_AGENT_EXTRACT("ai_agent_extract");

  private final String value;

  AiStudioAgentExtractResponseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiStudioAgentExtractResponseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiStudioAgentExtractResponseTypeField>> {

    public AiStudioAgentExtractResponseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiStudioAgentExtractResponseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiStudioAgentExtractResponseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiStudioAgentExtractResponseTypeField>(value));
    }
  }

  public static class AiStudioAgentExtractResponseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiStudioAgentExtractResponseTypeField>> {

    public AiStudioAgentExtractResponseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiStudioAgentExtractResponseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
