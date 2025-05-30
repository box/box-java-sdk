package com.box.sdkgen.schemas.metadatatemplate;

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

public enum MetadataTemplateTypeField implements Valuable {
  METADATA_TEMPLATE("metadata_template");

  private final String value;

  MetadataTemplateTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class MetadataTemplateTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<MetadataTemplateTypeField>> {

    public MetadataTemplateTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<MetadataTemplateTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(MetadataTemplateTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<MetadataTemplateTypeField>(value));
    }
  }

  public static class MetadataTemplateTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<MetadataTemplateTypeField>> {

    public MetadataTemplateTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<MetadataTemplateTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
