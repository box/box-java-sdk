package com.box.sdkgen.schemas.skillinvocation;

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

public enum SkillInvocationTokenReadTokenTypeField implements Valuable {
  BEARER("bearer");

  private final String value;

  SkillInvocationTokenReadTokenTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SkillInvocationTokenReadTokenTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SkillInvocationTokenReadTokenTypeField>> {

    public SkillInvocationTokenReadTokenTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SkillInvocationTokenReadTokenTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SkillInvocationTokenReadTokenTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SkillInvocationTokenReadTokenTypeField>(value));
    }
  }

  public static class SkillInvocationTokenReadTokenTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SkillInvocationTokenReadTokenTypeField>> {

    public SkillInvocationTokenReadTokenTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SkillInvocationTokenReadTokenTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
