package com.box.sdkgen.schemas.v2026r0.documentsplitv2026r0;

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

public enum DocumentSplitV2026R0StatusField implements Valuable {
  SUBMITTED("submitted"),
  COMPLETED("completed"),
  FAILED("failed"),
  COMPLETED_WITH_ERROR("completed_with_error");

  private final String value;

  DocumentSplitV2026R0StatusField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class DocumentSplitV2026R0StatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<DocumentSplitV2026R0StatusField>> {

    public DocumentSplitV2026R0StatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<DocumentSplitV2026R0StatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(DocumentSplitV2026R0StatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<DocumentSplitV2026R0StatusField>(value));
    }
  }

  public static class DocumentSplitV2026R0StatusFieldSerializer
      extends JsonSerializer<EnumWrapper<DocumentSplitV2026R0StatusField>> {

    public DocumentSplitV2026R0StatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<DocumentSplitV2026R0StatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
