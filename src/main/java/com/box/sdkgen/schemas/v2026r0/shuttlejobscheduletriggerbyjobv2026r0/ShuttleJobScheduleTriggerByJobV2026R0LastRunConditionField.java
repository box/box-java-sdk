package com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbyjobv2026r0;

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

public enum ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField implements Valuable {
  ALWAYS("always"),
  ON_SUCCESS("on_success"),
  ON_FINISH("on_finish");

  private final String value;

  ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField>> {

    public ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField>(value));
    }
  }

  public static class ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionFieldSerializer
      extends JsonSerializer<
          EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField>> {

    public ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
