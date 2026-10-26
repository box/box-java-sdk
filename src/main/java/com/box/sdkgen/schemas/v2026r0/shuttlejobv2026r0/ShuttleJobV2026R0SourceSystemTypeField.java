package com.box.sdkgen.schemas.v2026r0.shuttlejobv2026r0;

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

public enum ShuttleJobV2026R0SourceSystemTypeField implements Valuable {
  SHUTTLE_SYSTEM("shuttle_system");

  private final String value;

  ShuttleJobV2026R0SourceSystemTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleJobV2026R0SourceSystemTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField>> {

    public ShuttleJobV2026R0SourceSystemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleJobV2026R0SourceSystemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField>(value));
    }
  }

  public static class ShuttleJobV2026R0SourceSystemTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField>> {

    public ShuttleJobV2026R0SourceSystemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleJobV2026R0SourceSystemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
