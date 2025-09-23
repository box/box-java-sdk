package com.box.sdkgen.schemas.aistudioagenttextgen;

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

public enum AiStudioAgentTextGenTypeField implements Valuable {
  AI_AGENT_TEXT_GEN("ai_agent_text_gen");

  private final String value;

  AiStudioAgentTextGenTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiStudioAgentTextGenTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiStudioAgentTextGenTypeField>> {

    public AiStudioAgentTextGenTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiStudioAgentTextGenTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiStudioAgentTextGenTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiStudioAgentTextGenTypeField>(value));
    }
  }

  public static class AiStudioAgentTextGenTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiStudioAgentTextGenTypeField>> {

    public AiStudioAgentTextGenTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiStudioAgentTextGenTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
