package com.box.sdkgen.schemas.integrationmapping;

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

public enum IntegrationMappingIntegrationTypeField implements Valuable {
  SLACK("slack");

  private final String value;

  IntegrationMappingIntegrationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class IntegrationMappingIntegrationTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<IntegrationMappingIntegrationTypeField>> {

    public IntegrationMappingIntegrationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<IntegrationMappingIntegrationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(IntegrationMappingIntegrationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<IntegrationMappingIntegrationTypeField>(value));
    }
  }

  public static class IntegrationMappingIntegrationTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<IntegrationMappingIntegrationTypeField>> {

    public IntegrationMappingIntegrationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<IntegrationMappingIntegrationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
