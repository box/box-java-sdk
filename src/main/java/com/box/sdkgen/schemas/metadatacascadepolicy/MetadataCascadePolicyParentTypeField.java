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

public enum MetadataCascadePolicyParentTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  MetadataCascadePolicyParentTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class MetadataCascadePolicyParentTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<MetadataCascadePolicyParentTypeField>> {

    public MetadataCascadePolicyParentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<MetadataCascadePolicyParentTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(MetadataCascadePolicyParentTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<MetadataCascadePolicyParentTypeField>(value));
    }
  }

  public static class MetadataCascadePolicyParentTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<MetadataCascadePolicyParentTypeField>> {

    public MetadataCascadePolicyParentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<MetadataCascadePolicyParentTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
