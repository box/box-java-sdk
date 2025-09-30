package com.box.sdkgen.schemas.aillmendpointparamsibm;

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

public enum AiLlmEndpointParamsIbmTypeField implements Valuable {
  IBM_PARAMS("ibm_params");

  private final String value;

  AiLlmEndpointParamsIbmTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiLlmEndpointParamsIbmTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiLlmEndpointParamsIbmTypeField>> {

    public AiLlmEndpointParamsIbmTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiLlmEndpointParamsIbmTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiLlmEndpointParamsIbmTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiLlmEndpointParamsIbmTypeField>(value));
    }
  }

  public static class AiLlmEndpointParamsIbmTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiLlmEndpointParamsIbmTypeField>> {

    public AiLlmEndpointParamsIbmTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiLlmEndpointParamsIbmTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
