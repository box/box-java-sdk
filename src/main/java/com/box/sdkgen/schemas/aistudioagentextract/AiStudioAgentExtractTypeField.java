package com.box.sdkgen.schemas.aistudioagentextract;

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

public enum AiStudioAgentExtractTypeField implements Valuable {
  AI_AGENT_EXTRACT("ai_agent_extract");

  private final String value;

  AiStudioAgentExtractTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiStudioAgentExtractTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiStudioAgentExtractTypeField>> {

    public AiStudioAgentExtractTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiStudioAgentExtractTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiStudioAgentExtractTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiStudioAgentExtractTypeField>(value));
    }
  }

  public static class AiStudioAgentExtractTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiStudioAgentExtractTypeField>> {

    public AiStudioAgentExtractTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiStudioAgentExtractTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
