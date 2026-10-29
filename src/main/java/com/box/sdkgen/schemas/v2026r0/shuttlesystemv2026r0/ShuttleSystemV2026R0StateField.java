package com.box.sdkgen.schemas.v2026r0.shuttlesystemv2026r0;

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

public enum ShuttleSystemV2026R0StateField implements Valuable {
  CONNECTED("connected"),
  DISCONNECTED("disconnected"),
  UNKNOWN("unknown");

  private final String value;

  ShuttleSystemV2026R0StateField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleSystemV2026R0StateFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleSystemV2026R0StateField>> {

    public ShuttleSystemV2026R0StateFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleSystemV2026R0StateField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleSystemV2026R0StateField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleSystemV2026R0StateField>(value));
    }
  }

  public static class ShuttleSystemV2026R0StateFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleSystemV2026R0StateField>> {

    public ShuttleSystemV2026R0StateFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleSystemV2026R0StateField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
