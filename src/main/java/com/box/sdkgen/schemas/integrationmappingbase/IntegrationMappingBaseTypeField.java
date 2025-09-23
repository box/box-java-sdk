package com.box.sdkgen.schemas.integrationmappingbase;

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

public enum IntegrationMappingBaseTypeField implements Valuable {
  INTEGRATION_MAPPING("integration_mapping");

  private final String value;

  IntegrationMappingBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class IntegrationMappingBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<IntegrationMappingBaseTypeField>> {

    public IntegrationMappingBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<IntegrationMappingBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(IntegrationMappingBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<IntegrationMappingBaseTypeField>(value));
    }
  }

  public static class IntegrationMappingBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<IntegrationMappingBaseTypeField>> {

    public IntegrationMappingBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<IntegrationMappingBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
