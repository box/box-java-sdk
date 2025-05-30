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

public enum StatusSkillCardSkillTypeField implements Valuable {
  SERVICE("service");

  private final String value;

  StatusSkillCardSkillTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class StatusSkillCardSkillTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<StatusSkillCardSkillTypeField>> {

    public StatusSkillCardSkillTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<StatusSkillCardSkillTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(StatusSkillCardSkillTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<StatusSkillCardSkillTypeField>(value));
    }
  }

  public static class StatusSkillCardSkillTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<StatusSkillCardSkillTypeField>> {

    public StatusSkillCardSkillTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<StatusSkillCardSkillTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
