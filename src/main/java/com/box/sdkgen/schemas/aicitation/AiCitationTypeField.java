package com.box.sdkgen.schemas.aicitation;

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

public enum AiCitationTypeField implements Valuable {
  FILE("file");

  private final String value;

  AiCitationTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiCitationTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiCitationTypeField>> {

    public AiCitationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiCitationTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiCitationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiCitationTypeField>(value));
    }
  }

  public static class AiCitationTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AiCitationTypeField>> {

    public AiCitationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiCitationTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
