package com.box.sdkgen.managers.skills;

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

public enum UpdateAllSkillCardsOnFileRequestBodyStatusField implements Valuable {
  INVOKED("invoked"),
  PROCESSING("processing"),
  SUCCESS("success"),
  TRANSIENT_FAILURE("transient_failure"),
  PERMANENT_FAILURE("permanent_failure");

  private final String value;

  UpdateAllSkillCardsOnFileRequestBodyStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateAllSkillCardsOnFileRequestBodyStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField>> {

    public UpdateAllSkillCardsOnFileRequestBodyStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateAllSkillCardsOnFileRequestBodyStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField>(value));
    }
  }

  public static class UpdateAllSkillCardsOnFileRequestBodyStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField>> {

    public UpdateAllSkillCardsOnFileRequestBodyStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
