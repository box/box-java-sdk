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

public enum GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField implements Valuable {
  CHANNEL("channel"),
  TEAM("team");

  private final String value;

  GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetTeamsIntegrationMappingQueryParamsPartnerItemTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField>> {

    public GetTeamsIntegrationMappingQueryParamsPartnerItemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField>(value));
    }
  }

  public static class GetTeamsIntegrationMappingQueryParamsPartnerItemTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField>> {

    public GetTeamsIntegrationMappingQueryParamsPartnerItemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
