package com.box.sdkgen.schemas.aillmendpointparamsgoogle;

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

public enum AiLlmEndpointParamsGoogleTypeField implements Valuable {
  GOOGLE_PARAMS("google_params");

  private final String value;

  AiLlmEndpointParamsGoogleTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiLlmEndpointParamsGoogleTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiLlmEndpointParamsGoogleTypeField>> {

    public AiLlmEndpointParamsGoogleTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiLlmEndpointParamsGoogleTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiLlmEndpointParamsGoogleTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiLlmEndpointParamsGoogleTypeField>(value));
    }
  }

  public static class AiLlmEndpointParamsGoogleTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiLlmEndpointParamsGoogleTypeField>> {

    public AiLlmEndpointParamsGoogleTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiLlmEndpointParamsGoogleTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
