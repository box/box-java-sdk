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

public enum StatusSkillCardInvocationTypeField implements Valuable {
  SKILL_INVOCATION("skill_invocation");

  private final String value;

  StatusSkillCardInvocationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class StatusSkillCardInvocationTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<StatusSkillCardInvocationTypeField>> {

    public StatusSkillCardInvocationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<StatusSkillCardInvocationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(StatusSkillCardInvocationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<StatusSkillCardInvocationTypeField>(value));
    }
  }

  public static class StatusSkillCardInvocationTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<StatusSkillCardInvocationTypeField>> {

    public StatusSkillCardInvocationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<StatusSkillCardInvocationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
