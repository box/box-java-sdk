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

public enum KeywordSkillCardSkillTypeField implements Valuable {
  SERVICE("service");

  private final String value;

  KeywordSkillCardSkillTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class KeywordSkillCardSkillTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<KeywordSkillCardSkillTypeField>> {

    public KeywordSkillCardSkillTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<KeywordSkillCardSkillTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(KeywordSkillCardSkillTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<KeywordSkillCardSkillTypeField>(value));
    }
  }

  public static class KeywordSkillCardSkillTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<KeywordSkillCardSkillTypeField>> {

    public KeywordSkillCardSkillTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<KeywordSkillCardSkillTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
