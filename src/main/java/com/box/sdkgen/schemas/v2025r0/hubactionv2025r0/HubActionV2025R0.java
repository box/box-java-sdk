package com.box.sdkgen.schemas.v2025r0.hubactionv2025r0;

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

public enum HubActionV2025R0 implements Valuable {
  ADD("add"),
  REMOVE("remove");

  private final String value;

  HubActionV2025R0(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class HubActionV2025R0Deserializer
      extends JsonDeserializer<EnumWrapper<HubActionV2025R0>> {

    public HubActionV2025R0Deserializer() {
      super();
    }

    @Override
    public EnumWrapper<HubActionV2025R0> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(HubActionV2025R0.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<HubActionV2025R0>(value));
    }
  }

  public static class HubActionV2025R0Serializer
      extends JsonSerializer<EnumWrapper<HubActionV2025R0>> {

    public HubActionV2025R0Serializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<HubActionV2025R0> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
