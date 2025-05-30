package com.box.sdkgen.schemas.integrationmappingteams;

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

public enum IntegrationMappingTeamsIntegrationTypeField implements Valuable {
  TEAMS("teams");

  private final String value;

  IntegrationMappingTeamsIntegrationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class IntegrationMappingTeamsIntegrationTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<IntegrationMappingTeamsIntegrationTypeField>> {

    public IntegrationMappingTeamsIntegrationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<IntegrationMappingTeamsIntegrationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(IntegrationMappingTeamsIntegrationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<IntegrationMappingTeamsIntegrationTypeField>(value));
    }
  }

  public static class IntegrationMappingTeamsIntegrationTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<IntegrationMappingTeamsIntegrationTypeField>> {

    public IntegrationMappingTeamsIntegrationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<IntegrationMappingTeamsIntegrationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
