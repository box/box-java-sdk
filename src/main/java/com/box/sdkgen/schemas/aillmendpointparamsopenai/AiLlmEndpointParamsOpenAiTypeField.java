package com.box.sdkgen.schemas.aillmendpointparamsopenai;

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

public enum AiLlmEndpointParamsOpenAiTypeField implements Valuable {
  OPENAI_PARAMS("openai_params");

  private final String value;

  AiLlmEndpointParamsOpenAiTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiLlmEndpointParamsOpenAiTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiLlmEndpointParamsOpenAiTypeField>> {

    public AiLlmEndpointParamsOpenAiTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiLlmEndpointParamsOpenAiTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiLlmEndpointParamsOpenAiTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiLlmEndpointParamsOpenAiTypeField>(value));
    }
  }

  public static class AiLlmEndpointParamsOpenAiTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiLlmEndpointParamsOpenAiTypeField>> {

    public AiLlmEndpointParamsOpenAiTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiLlmEndpointParamsOpenAiTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
