package com.box.sdkgen.schemas.transcriptskillcard;

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

public enum TranscriptSkillCardTypeField implements Valuable {
  SKILL_CARD("skill_card");

  private final String value;

  TranscriptSkillCardTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TranscriptSkillCardTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TranscriptSkillCardTypeField>> {

    public TranscriptSkillCardTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TranscriptSkillCardTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TranscriptSkillCardTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TranscriptSkillCardTypeField>(value));
    }
  }

  public static class TranscriptSkillCardTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TranscriptSkillCardTypeField>> {

    public TranscriptSkillCardTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TranscriptSkillCardTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
