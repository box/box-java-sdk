package com.box.sdkgen.schemas.v2026r0.shuttlejobscheduleupdaterequestv2026r0;

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

public enum ShuttleJobScheduleUpdateRequestV2026R0RunModeField implements Valuable {
  TRANSFER("transfer"),
  SIMULATION("simulation"),
  ANALYSIS("analysis");

  private final String value;

  ShuttleJobScheduleUpdateRequestV2026R0RunModeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleJobScheduleUpdateRequestV2026R0RunModeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField>> {

    public ShuttleJobScheduleUpdateRequestV2026R0RunModeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleJobScheduleUpdateRequestV2026R0RunModeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField>(value));
    }
  }

  public static class ShuttleJobScheduleUpdateRequestV2026R0RunModeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField>> {

    public ShuttleJobScheduleUpdateRequestV2026R0RunModeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
