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

public enum StatusSkillCardStatusCodeField implements Valuable {
  INVOKED("invoked"),
  PROCESSING("processing"),
  SUCCESS("success"),
  TRANSIENT_FAILURE("transient_failure"),
  PERMANENT_FAILURE("permanent_failure");

  private final String value;

  StatusSkillCardStatusCodeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class StatusSkillCardStatusCodeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<StatusSkillCardStatusCodeField>> {

    public StatusSkillCardStatusCodeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<StatusSkillCardStatusCodeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(StatusSkillCardStatusCodeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<StatusSkillCardStatusCodeField>(value));
    }
  }

  public static class StatusSkillCardStatusCodeFieldSerializer
      extends JsonSerializer<EnumWrapper<StatusSkillCardStatusCodeField>> {

    public StatusSkillCardStatusCodeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<StatusSkillCardStatusCodeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
