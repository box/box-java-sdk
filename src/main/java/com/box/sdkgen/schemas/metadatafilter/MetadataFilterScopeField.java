package com.box.sdkgen.schemas.metadatafilter;

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

public enum MetadataFilterScopeField implements Valuable {
  GLOBAL("global"),
  ENTERPRISE("enterprise"),
  ENTERPRISE__ENTERPRISE_ID_("enterprise_{enterprise_id}");

  private final String value;

  MetadataFilterScopeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class MetadataFilterScopeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<MetadataFilterScopeField>> {

    public MetadataFilterScopeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<MetadataFilterScopeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(MetadataFilterScopeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<MetadataFilterScopeField>(value));
    }
  }

  public static class MetadataFilterScopeFieldSerializer
      extends JsonSerializer<EnumWrapper<MetadataFilterScopeField>> {

    public MetadataFilterScopeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<MetadataFilterScopeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
