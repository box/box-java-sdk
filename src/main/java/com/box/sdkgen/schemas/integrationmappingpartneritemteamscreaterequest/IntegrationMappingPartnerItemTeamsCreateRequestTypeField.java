package com.box.sdkgen.schemas.integrationmappingpartneritemteamscreaterequest;

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

public enum IntegrationMappingPartnerItemTeamsCreateRequestTypeField implements Valuable {
  CHANNEL("channel"),
  TEAM("team");

  private final String value;

  IntegrationMappingPartnerItemTeamsCreateRequestTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class IntegrationMappingPartnerItemTeamsCreateRequestTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<IntegrationMappingPartnerItemTeamsCreateRequestTypeField>> {

    public IntegrationMappingPartnerItemTeamsCreateRequestTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<IntegrationMappingPartnerItemTeamsCreateRequestTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(IntegrationMappingPartnerItemTeamsCreateRequestTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<IntegrationMappingPartnerItemTeamsCreateRequestTypeField>(value));
    }
  }

  public static class IntegrationMappingPartnerItemTeamsCreateRequestTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<IntegrationMappingPartnerItemTeamsCreateRequestTypeField>> {

    public IntegrationMappingPartnerItemTeamsCreateRequestTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<IntegrationMappingPartnerItemTeamsCreateRequestTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
