package com.box.sdkgen.schemas.v2026r0.notesconvertrequestbodyv2026r0;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum NotesConvertRequestBodyV2026R0ContentFormatField implements Valuable {
  MARKDOWN("markdown");

  private final String value;

  NotesConvertRequestBodyV2026R0ContentFormatField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class NotesConvertRequestBodyV2026R0ContentFormatFieldDeserializer
      extends JsonDeserializer<EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField>> {

    public NotesConvertRequestBodyV2026R0ContentFormatFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(NotesConvertRequestBodyV2026R0ContentFormatField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField>(value));
    }
  }

  public static class NotesConvertRequestBodyV2026R0ContentFormatFieldSerializer
      extends JsonSerializer<EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField>> {

    public NotesConvertRequestBodyV2026R0ContentFormatFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
