package com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestmanualv2026r0;

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

public enum DocumentSplitCreateRequestManualV2026R0VersionField implements Valuable {
  V_1("v_1");

  private final String value;

  DocumentSplitCreateRequestManualV2026R0VersionField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class DocumentSplitCreateRequestManualV2026R0VersionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField>> {

    public DocumentSplitCreateRequestManualV2026R0VersionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(DocumentSplitCreateRequestManualV2026R0VersionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField>(value));
    }
  }

  public static class DocumentSplitCreateRequestManualV2026R0VersionFieldSerializer
      extends JsonSerializer<EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField>> {

    public DocumentSplitCreateRequestManualV2026R0VersionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<DocumentSplitCreateRequestManualV2026R0VersionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
