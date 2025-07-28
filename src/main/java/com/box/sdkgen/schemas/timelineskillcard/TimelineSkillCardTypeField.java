package com.box.sdkgen.schemas.timelineskillcard;

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

public enum TimelineSkillCardTypeField implements Valuable {
  SKILL_CARD("skill_card");

  private final String value;

  TimelineSkillCardTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TimelineSkillCardTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TimelineSkillCardTypeField>> {

    public TimelineSkillCardTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TimelineSkillCardTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TimelineSkillCardTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TimelineSkillCardTypeField>(value));
    }
  }

  public static class TimelineSkillCardTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TimelineSkillCardTypeField>> {

    public TimelineSkillCardTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TimelineSkillCardTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
