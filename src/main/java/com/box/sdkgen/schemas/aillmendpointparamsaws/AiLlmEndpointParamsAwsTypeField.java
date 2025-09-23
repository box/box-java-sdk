package com.box.sdkgen.schemas.aillmendpointparamsaws;

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

public enum AiLlmEndpointParamsAwsTypeField implements Valuable {
  AWS_PARAMS("aws_params");

  private final String value;

  AiLlmEndpointParamsAwsTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiLlmEndpointParamsAwsTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiLlmEndpointParamsAwsTypeField>> {

    public AiLlmEndpointParamsAwsTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiLlmEndpointParamsAwsTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiLlmEndpointParamsAwsTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiLlmEndpointParamsAwsTypeField>(value));
    }
  }

  public static class AiLlmEndpointParamsAwsTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiLlmEndpointParamsAwsTypeField>> {

    public AiLlmEndpointParamsAwsTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiLlmEndpointParamsAwsTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
