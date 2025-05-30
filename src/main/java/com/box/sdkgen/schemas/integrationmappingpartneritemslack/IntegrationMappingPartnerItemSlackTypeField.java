package com.box.sdkgen.schemas.integrationmappingpartneritemslack;

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

public enum IntegrationMappingPartnerItemSlackTypeField implements Valuable {
  CHANNEL("channel");

  private final String value;

  IntegrationMappingPartnerItemSlackTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class IntegrationMappingPartnerItemSlackTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<IntegrationMappingPartnerItemSlackTypeField>> {

    public IntegrationMappingPartnerItemSlackTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<IntegrationMappingPartnerItemSlackTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(IntegrationMappingPartnerItemSlackTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<IntegrationMappingPartnerItemSlackTypeField>(value));
    }
  }

  public static class IntegrationMappingPartnerItemSlackTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<IntegrationMappingPartnerItemSlackTypeField>> {

    public IntegrationMappingPartnerItemSlackTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<IntegrationMappingPartnerItemSlackTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
