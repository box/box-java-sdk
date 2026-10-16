package com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestv2026r0;

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

public enum DocumentSplitCreateRequestV2026R0InputSourceField implements Valuable {
  API("api"),
  BOX("box"),
  RELAY("relay"),
  SALESFORCE("salesforce");

  private final String value;

  DocumentSplitCreateRequestV2026R0InputSourceField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class DocumentSplitCreateRequestV2026R0InputSourceFieldDeserializer
      extends JsonDeserializer<EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>> {

    public DocumentSplitCreateRequestV2026R0InputSourceFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(DocumentSplitCreateRequestV2026R0InputSourceField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>(value));
    }
  }

  public static class DocumentSplitCreateRequestV2026R0InputSourceFieldSerializer
      extends JsonSerializer<EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField>> {

    public DocumentSplitCreateRequestV2026R0InputSourceFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<DocumentSplitCreateRequestV2026R0InputSourceField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
