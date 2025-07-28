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

public enum UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField implements Valuable {
  FILE_VERSION("file_version");

  private final String value;

  UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField>> {

    public UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField>(value));
    }
  }

  public static class UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField>> {

    public UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateAllSkillCardsOnFileRequestBodyFileVersionTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
