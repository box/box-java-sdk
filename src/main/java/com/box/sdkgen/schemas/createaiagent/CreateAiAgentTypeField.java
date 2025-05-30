package com.box.sdkgen.schemas.createaiagent;

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

public enum CreateAiAgentTypeField implements Valuable {
  AI_AGENT("ai_agent");

  private final String value;

  CreateAiAgentTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateAiAgentTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateAiAgentTypeField>> {

    public CreateAiAgentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateAiAgentTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateAiAgentTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateAiAgentTypeField>(value));
    }
  }

  public static class CreateAiAgentTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateAiAgentTypeField>> {

    public CreateAiAgentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateAiAgentTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
