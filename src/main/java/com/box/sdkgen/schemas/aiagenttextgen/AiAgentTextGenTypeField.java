package com.box.sdkgen.schemas.aiagenttextgen;

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

public enum AiAgentTextGenTypeField implements Valuable {
  AI_AGENT_TEXT_GEN("ai_agent_text_gen");

  private final String value;

  AiAgentTextGenTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiAgentTextGenTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiAgentTextGenTypeField>> {

    public AiAgentTextGenTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiAgentTextGenTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiAgentTextGenTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiAgentTextGenTypeField>(value));
    }
  }

  public static class AiAgentTextGenTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiAgentTextGenTypeField>> {

    public AiAgentTextGenTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiAgentTextGenTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
