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

public enum TimelineSkillCardSkillCardTypeField implements Valuable {
  TIMELINE("timeline");

  private final String value;

  TimelineSkillCardSkillCardTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TimelineSkillCardSkillCardTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TimelineSkillCardSkillCardTypeField>> {

    public TimelineSkillCardSkillCardTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TimelineSkillCardSkillCardTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TimelineSkillCardSkillCardTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TimelineSkillCardSkillCardTypeField>(value));
    }
  }

  public static class TimelineSkillCardSkillCardTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TimelineSkillCardSkillCardTypeField>> {

    public TimelineSkillCardSkillCardTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TimelineSkillCardSkillCardTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
