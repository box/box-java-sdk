package com.box.sdkgen.schemas.keywordskillcard;

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

public enum KeywordSkillCardInvocationTypeField implements Valuable {
  SKILL_INVOCATION("skill_invocation");

  private final String value;

  KeywordSkillCardInvocationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class KeywordSkillCardInvocationTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<KeywordSkillCardInvocationTypeField>> {

    public KeywordSkillCardInvocationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<KeywordSkillCardInvocationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(KeywordSkillCardInvocationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<KeywordSkillCardInvocationTypeField>(value));
    }
  }

  public static class KeywordSkillCardInvocationTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<KeywordSkillCardInvocationTypeField>> {

    public KeywordSkillCardInvocationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<KeywordSkillCardInvocationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
