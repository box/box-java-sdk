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

public enum TranscriptSkillCardInvocationTypeField implements Valuable {
  SKILL_INVOCATION("skill_invocation");

  private final String value;

  TranscriptSkillCardInvocationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TranscriptSkillCardInvocationTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TranscriptSkillCardInvocationTypeField>> {

    public TranscriptSkillCardInvocationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TranscriptSkillCardInvocationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TranscriptSkillCardInvocationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TranscriptSkillCardInvocationTypeField>(value));
    }
  }

  public static class TranscriptSkillCardInvocationTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TranscriptSkillCardInvocationTypeField>> {

    public TranscriptSkillCardInvocationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TranscriptSkillCardInvocationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
