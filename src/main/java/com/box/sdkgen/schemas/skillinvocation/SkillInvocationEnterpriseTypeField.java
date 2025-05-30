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

public enum SkillInvocationEnterpriseTypeField implements Valuable {
  ENTERPRISE("enterprise");

  private final String value;

  SkillInvocationEnterpriseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SkillInvocationEnterpriseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SkillInvocationEnterpriseTypeField>> {

    public SkillInvocationEnterpriseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SkillInvocationEnterpriseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SkillInvocationEnterpriseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SkillInvocationEnterpriseTypeField>(value));
    }
  }

  public static class SkillInvocationEnterpriseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SkillInvocationEnterpriseTypeField>> {

    public SkillInvocationEnterpriseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SkillInvocationEnterpriseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
