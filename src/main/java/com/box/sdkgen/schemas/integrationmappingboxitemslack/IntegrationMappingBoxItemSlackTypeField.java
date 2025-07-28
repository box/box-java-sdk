package com.box.sdkgen.schemas.integrationmappingboxitemslack;

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

public enum IntegrationMappingBoxItemSlackTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  IntegrationMappingBoxItemSlackTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class IntegrationMappingBoxItemSlackTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<IntegrationMappingBoxItemSlackTypeField>> {

    public IntegrationMappingBoxItemSlackTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<IntegrationMappingBoxItemSlackTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(IntegrationMappingBoxItemSlackTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<IntegrationMappingBoxItemSlackTypeField>(value));
    }
  }

  public static class IntegrationMappingBoxItemSlackTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<IntegrationMappingBoxItemSlackTypeField>> {

    public IntegrationMappingBoxItemSlackTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<IntegrationMappingBoxItemSlackTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
