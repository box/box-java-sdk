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

public enum UpdateBoxSkillCardsOnFileRequestBodyOpField implements Valuable {
  REPLACE("replace");

  private final String value;

  UpdateBoxSkillCardsOnFileRequestBodyOpField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateBoxSkillCardsOnFileRequestBodyOpFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField>> {

    public UpdateBoxSkillCardsOnFileRequestBodyOpFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateBoxSkillCardsOnFileRequestBodyOpField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField>(value));
    }
  }

  public static class UpdateBoxSkillCardsOnFileRequestBodyOpFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField>> {

    public UpdateBoxSkillCardsOnFileRequestBodyOpFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateBoxSkillCardsOnFileRequestBodyOpField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
