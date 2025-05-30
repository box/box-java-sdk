package com.box.sdkgen.managers.integrationmappings;

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

public enum GetTeamsIntegrationMappingQueryParamsBoxItemTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  GetTeamsIntegrationMappingQueryParamsBoxItemTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetTeamsIntegrationMappingQueryParamsBoxItemTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField>> {

    public GetTeamsIntegrationMappingQueryParamsBoxItemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetTeamsIntegrationMappingQueryParamsBoxItemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField>(value));
    }
  }

  public static class GetTeamsIntegrationMappingQueryParamsBoxItemTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField>> {

    public GetTeamsIntegrationMappingQueryParamsBoxItemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
