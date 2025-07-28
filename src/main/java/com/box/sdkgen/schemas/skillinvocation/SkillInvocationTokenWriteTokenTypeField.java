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

public enum SkillInvocationTokenWriteTokenTypeField implements Valuable {
  BEARER("bearer");

  private final String value;

  SkillInvocationTokenWriteTokenTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SkillInvocationTokenWriteTokenTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SkillInvocationTokenWriteTokenTypeField>> {

    public SkillInvocationTokenWriteTokenTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SkillInvocationTokenWriteTokenTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SkillInvocationTokenWriteTokenTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SkillInvocationTokenWriteTokenTypeField>(value));
    }
  }

  public static class SkillInvocationTokenWriteTokenTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SkillInvocationTokenWriteTokenTypeField>> {

    public SkillInvocationTokenWriteTokenTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SkillInvocationTokenWriteTokenTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
