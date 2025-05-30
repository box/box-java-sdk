package com.box.sdkgen.managers.metadatacascadepolicies;

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

public enum CreateMetadataCascadePolicyRequestBodyScopeField implements Valuable {
  GLOBAL("global"),
  ENTERPRISE("enterprise");

  private final String value;

  CreateMetadataCascadePolicyRequestBodyScopeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateMetadataCascadePolicyRequestBodyScopeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField>> {

    public CreateMetadataCascadePolicyRequestBodyScopeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateMetadataCascadePolicyRequestBodyScopeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField>(value));
    }
  }

  public static class CreateMetadataCascadePolicyRequestBodyScopeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField>> {

    public CreateMetadataCascadePolicyRequestBodyScopeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateMetadataCascadePolicyRequestBodyScopeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
