package com.box.sdkgen.schemas.statusskillcard;

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

public enum StatusSkillCardTypeField implements Valuable {
  SKILL_CARD("skill_card");

  private final String value;

  StatusSkillCardTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class StatusSkillCardTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<StatusSkillCardTypeField>> {

    public StatusSkillCardTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<StatusSkillCardTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(StatusSkillCardTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<StatusSkillCardTypeField>(value));
    }
  }

  public static class StatusSkillCardTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<StatusSkillCardTypeField>> {

    public StatusSkillCardTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<StatusSkillCardTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
