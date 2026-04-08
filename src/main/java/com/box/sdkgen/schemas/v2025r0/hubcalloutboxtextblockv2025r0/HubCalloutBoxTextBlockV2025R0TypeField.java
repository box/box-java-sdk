package com.box.sdkgen.schemas.v2025r0.hubcalloutboxtextblockv2025r0;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum HubCalloutBoxTextBlockV2025R0TypeField implements Valuable {
  CALLOUT_BOX("callout_box");

  private final String value;

  HubCalloutBoxTextBlockV2025R0TypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class HubCalloutBoxTextBlockV2025R0TypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<HubCalloutBoxTextBlockV2025R0TypeField>> {

    public HubCalloutBoxTextBlockV2025R0TypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<HubCalloutBoxTextBlockV2025R0TypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(HubCalloutBoxTextBlockV2025R0TypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<HubCalloutBoxTextBlockV2025R0TypeField>(value));
    }
  }

  public static class HubCalloutBoxTextBlockV2025R0TypeFieldSerializer
      extends JsonSerializer<EnumWrapper<HubCalloutBoxTextBlockV2025R0TypeField>> {

    public HubCalloutBoxTextBlockV2025R0TypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<HubCalloutBoxTextBlockV2025R0TypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
