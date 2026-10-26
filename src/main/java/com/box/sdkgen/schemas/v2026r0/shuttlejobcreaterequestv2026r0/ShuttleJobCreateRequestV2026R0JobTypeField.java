package com.box.sdkgen.schemas.v2026r0.shuttlejobcreaterequestv2026r0;

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

public enum ShuttleJobCreateRequestV2026R0JobTypeField implements Valuable {
  TRANSFER("transfer"),
  ANALYSIS("analysis");

  private final String value;

  ShuttleJobCreateRequestV2026R0JobTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleJobCreateRequestV2026R0JobTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField>> {

    public ShuttleJobCreateRequestV2026R0JobTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleJobCreateRequestV2026R0JobTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField>(value));
    }
  }

  public static class ShuttleJobCreateRequestV2026R0JobTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField>> {

    public ShuttleJobCreateRequestV2026R0JobTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleJobCreateRequestV2026R0JobTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
