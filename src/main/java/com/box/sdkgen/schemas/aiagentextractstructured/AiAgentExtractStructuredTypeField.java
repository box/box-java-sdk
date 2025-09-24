package com.box.sdkgen.schemas.aiagentextractstructured;

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

public enum AiAgentExtractStructuredTypeField implements Valuable {
  AI_AGENT_EXTRACT_STRUCTURED("ai_agent_extract_structured");

  private final String value;

  AiAgentExtractStructuredTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiAgentExtractStructuredTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiAgentExtractStructuredTypeField>> {

    public AiAgentExtractStructuredTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiAgentExtractStructuredTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiAgentExtractStructuredTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiAgentExtractStructuredTypeField>(value));
    }
  }

  public static class AiAgentExtractStructuredTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiAgentExtractStructuredTypeField>> {

    public AiAgentExtractStructuredTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiAgentExtractStructuredTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
