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

public enum KeywordSkillCardTypeField implements Valuable {
  SKILL_CARD("skill_card");

  private final String value;

  KeywordSkillCardTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class KeywordSkillCardTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<KeywordSkillCardTypeField>> {

    public KeywordSkillCardTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<KeywordSkillCardTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(KeywordSkillCardTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<KeywordSkillCardTypeField>(value));
    }
  }

  public static class KeywordSkillCardTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<KeywordSkillCardTypeField>> {

    public KeywordSkillCardTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<KeywordSkillCardTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
