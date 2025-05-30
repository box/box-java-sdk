package com.box.sdkgen.schemas.aiextractstructured;

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

public enum AiExtractStructuredMetadataTemplateTypeField implements Valuable {
  METADATA_TEMPLATE("metadata_template");

  private final String value;

  AiExtractStructuredMetadataTemplateTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiExtractStructuredMetadataTemplateTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiExtractStructuredMetadataTemplateTypeField>> {

    public AiExtractStructuredMetadataTemplateTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiExtractStructuredMetadataTemplateTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiExtractStructuredMetadataTemplateTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiExtractStructuredMetadataTemplateTypeField>(value));
    }
  }

  public static class AiExtractStructuredMetadataTemplateTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiExtractStructuredMetadataTemplateTypeField>> {

    public AiExtractStructuredMetadataTemplateTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiExtractStructuredMetadataTemplateTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
