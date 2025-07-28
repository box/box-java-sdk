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

public enum MetadataCascadePolicyOwnerEnterpriseTypeField implements Valuable {
  ENTERPRISE("enterprise");

  private final String value;

  MetadataCascadePolicyOwnerEnterpriseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class MetadataCascadePolicyOwnerEnterpriseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField>> {

    public MetadataCascadePolicyOwnerEnterpriseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(MetadataCascadePolicyOwnerEnterpriseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField>(value));
    }
  }

  public static class MetadataCascadePolicyOwnerEnterpriseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField>> {

    public MetadataCascadePolicyOwnerEnterpriseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
