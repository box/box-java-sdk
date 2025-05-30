package com.box.sdkgen.schemas.aistudioagenttextgenresponse;

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

public enum AiStudioAgentTextGenResponseTypeField implements Valuable {
  AI_AGENT_TEXT_GEN("ai_agent_text_gen");

  private final String value;

  AiStudioAgentTextGenResponseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiStudioAgentTextGenResponseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiStudioAgentTextGenResponseTypeField>> {

    public AiStudioAgentTextGenResponseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiStudioAgentTextGenResponseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiStudioAgentTextGenResponseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiStudioAgentTextGenResponseTypeField>(value));
    }
  }

  public static class AiStudioAgentTextGenResponseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiStudioAgentTextGenResponseTypeField>> {

    public AiStudioAgentTextGenResponseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiStudioAgentTextGenResponseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
