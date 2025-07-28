package com.box.sdkgen.schemas.metadatacascadepolicy;

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

public enum MetadataCascadePolicyTypeField implements Valuable {
  METADATA_CASCADE_POLICY("metadata_cascade_policy");

  private final String value;

  MetadataCascadePolicyTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class MetadataCascadePolicyTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<MetadataCascadePolicyTypeField>> {

    public MetadataCascadePolicyTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<MetadataCascadePolicyTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(MetadataCascadePolicyTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<MetadataCascadePolicyTypeField>(value));
    }
  }

  public static class MetadataCascadePolicyTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<MetadataCascadePolicyTypeField>> {

    public MetadataCascadePolicyTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<MetadataCascadePolicyTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
