package com.box.sdkgen.schemas.v2026r0.shuttlejobstartrequestv2026r0;

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

public enum ShuttleJobStartRequestV2026R0ModeField implements Valuable {
  TRANSFER("transfer"),
  SIMULATION("simulation"),
  SCAN("scan"),
  ANALYSIS("analysis");

  private final String value;

  ShuttleJobStartRequestV2026R0ModeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleJobStartRequestV2026R0ModeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleJobStartRequestV2026R0ModeField>> {

    public ShuttleJobStartRequestV2026R0ModeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleJobStartRequestV2026R0ModeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleJobStartRequestV2026R0ModeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleJobStartRequestV2026R0ModeField>(value));
    }
  }

  public static class ShuttleJobStartRequestV2026R0ModeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleJobStartRequestV2026R0ModeField>> {

    public ShuttleJobStartRequestV2026R0ModeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleJobStartRequestV2026R0ModeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
