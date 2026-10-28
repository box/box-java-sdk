package com.box.sdkgen.schemas.v2026r0.shuttlejobschedulecreaterequestv2026r0;

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

public enum ShuttleJobScheduleCreateRequestV2026R0RunModeField implements Valuable {
  TRANSFER("transfer"),
  SIMULATION("simulation"),
  ANALYSIS("analysis");

  private final String value;

  ShuttleJobScheduleCreateRequestV2026R0RunModeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleJobScheduleCreateRequestV2026R0RunModeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField>> {

    public ShuttleJobScheduleCreateRequestV2026R0RunModeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleJobScheduleCreateRequestV2026R0RunModeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField>(value));
    }
  }

  public static class ShuttleJobScheduleCreateRequestV2026R0RunModeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField>> {

    public ShuttleJobScheduleCreateRequestV2026R0RunModeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
