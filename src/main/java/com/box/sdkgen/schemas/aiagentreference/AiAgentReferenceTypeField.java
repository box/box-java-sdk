package com.box.sdkgen.schemas.aiagentreference;

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

public enum AiAgentReferenceTypeField implements Valuable {
  AI_AGENT_ID("ai_agent_id");

  private final String value;

  AiAgentReferenceTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiAgentReferenceTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiAgentReferenceTypeField>> {

    public AiAgentReferenceTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiAgentReferenceTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiAgentReferenceTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiAgentReferenceTypeField>(value));
    }
  }

  public static class AiAgentReferenceTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiAgentReferenceTypeField>> {

    public AiAgentReferenceTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiAgentReferenceTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
