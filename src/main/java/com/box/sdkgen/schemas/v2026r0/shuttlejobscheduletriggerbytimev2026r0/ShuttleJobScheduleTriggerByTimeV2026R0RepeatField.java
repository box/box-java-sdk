package com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbytimev2026r0;

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

public enum ShuttleJobScheduleTriggerByTimeV2026R0RepeatField implements Valuable {
  NONE("none"),
  DAILY("daily"),
  WEEKLY("weekly"),
  WEEKDAYS("weekdays"),
  WEEKENDS("weekends");

  private final String value;

  ShuttleJobScheduleTriggerByTimeV2026R0RepeatField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleJobScheduleTriggerByTimeV2026R0RepeatFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField>> {

    public ShuttleJobScheduleTriggerByTimeV2026R0RepeatFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleJobScheduleTriggerByTimeV2026R0RepeatField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField>(value));
    }
  }

  public static class ShuttleJobScheduleTriggerByTimeV2026R0RepeatFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField>> {

    public ShuttleJobScheduleTriggerByTimeV2026R0RepeatFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
