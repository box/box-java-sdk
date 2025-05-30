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

public enum GetSlackIntegrationMappingQueryParamsPartnerItemTypeField implements Valuable {
  CHANNEL("channel");

  private final String value;

  GetSlackIntegrationMappingQueryParamsPartnerItemTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetSlackIntegrationMappingQueryParamsPartnerItemTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField>> {

    public GetSlackIntegrationMappingQueryParamsPartnerItemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetSlackIntegrationMappingQueryParamsPartnerItemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField>(value));
    }
  }

  public static class GetSlackIntegrationMappingQueryParamsPartnerItemTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField>> {

    public GetSlackIntegrationMappingQueryParamsPartnerItemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
