package com.box.sdkgen.parameters.v2026r0.boxversionheaderv2026r0;

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

public enum BoxVersionHeaderV2026R0 implements Valuable {
  _2026_0("2026.0");

  private final String value;

  BoxVersionHeaderV2026R0(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class BoxVersionHeaderV2026R0Deserializer
      extends JsonDeserializer<EnumWrapper<BoxVersionHeaderV2026R0>> {

    public BoxVersionHeaderV2026R0Deserializer() {
      super();
    }

    @Override
    public EnumWrapper<BoxVersionHeaderV2026R0> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(BoxVersionHeaderV2026R0.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<BoxVersionHeaderV2026R0>(value));
    }
  }

  public static class BoxVersionHeaderV2026R0Serializer
      extends JsonSerializer<EnumWrapper<BoxVersionHeaderV2026R0>> {

    public BoxVersionHeaderV2026R0Serializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<BoxVersionHeaderV2026R0> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
