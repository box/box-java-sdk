package com.box.sdkgen.schemas.integrationmappingpartneritemteams;

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

public enum IntegrationMappingPartnerItemTeamsTypeField implements Valuable {
  CHANNEL("channel"),
  TEAM("team");

  private final String value;

  IntegrationMappingPartnerItemTeamsTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class IntegrationMappingPartnerItemTeamsTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<IntegrationMappingPartnerItemTeamsTypeField>> {

    public IntegrationMappingPartnerItemTeamsTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<IntegrationMappingPartnerItemTeamsTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(IntegrationMappingPartnerItemTeamsTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<IntegrationMappingPartnerItemTeamsTypeField>(value));
    }
  }

  public static class IntegrationMappingPartnerItemTeamsTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<IntegrationMappingPartnerItemTeamsTypeField>> {

    public IntegrationMappingPartnerItemTeamsTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<IntegrationMappingPartnerItemTeamsTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
