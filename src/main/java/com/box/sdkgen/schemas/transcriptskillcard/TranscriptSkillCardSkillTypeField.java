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

public enum TranscriptSkillCardSkillTypeField implements Valuable {
  SERVICE("service");

  private final String value;

  TranscriptSkillCardSkillTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TranscriptSkillCardSkillTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TranscriptSkillCardSkillTypeField>> {

    public TranscriptSkillCardSkillTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TranscriptSkillCardSkillTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TranscriptSkillCardSkillTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TranscriptSkillCardSkillTypeField>(value));
    }
  }

  public static class TranscriptSkillCardSkillTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TranscriptSkillCardSkillTypeField>> {

    public TranscriptSkillCardSkillTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TranscriptSkillCardSkillTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
