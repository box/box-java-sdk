package com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestpageselectionv2026r0;

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

public enum DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField implements Valuable {
  PAGE_SELECTION("page_selection");

  private final String value;

  DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField>> {

    public DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField>(value));
    }
  }

  public static class DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField>> {

    public DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<DocumentSplitCreateRequestPageSelectionV2026R0SplitTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
