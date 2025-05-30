package com.box.sdkgen.schemas.v2025r0.docgentagv2025r0;

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

public enum DocGenTagV2025R0TagTypeField implements Valuable {
  TEXT("text"),
  ARITHMETIC("arithmetic"),
  CONDITIONAL("conditional"),
  FOR_LOOP("for-loop"),
  TABLE_LOOP("table-loop"),
  IMAGE("image");

  private final String value;

  DocGenTagV2025R0TagTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class DocGenTagV2025R0TagTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<DocGenTagV2025R0TagTypeField>> {

    public DocGenTagV2025R0TagTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<DocGenTagV2025R0TagTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(DocGenTagV2025R0TagTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<DocGenTagV2025R0TagTypeField>(value));
    }
  }

  public static class DocGenTagV2025R0TagTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<DocGenTagV2025R0TagTypeField>> {

    public DocGenTagV2025R0TagTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<DocGenTagV2025R0TagTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
