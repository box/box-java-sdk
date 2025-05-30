package com.box.sdkgen.schemas.v2025r0.docgenbatchcreaterequestv2025r0;

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

public enum DocGenBatchCreateRequestV2025R0DestinationFolderTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  DocGenBatchCreateRequestV2025R0DestinationFolderTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class DocGenBatchCreateRequestV2025R0DestinationFolderTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField>> {

    public DocGenBatchCreateRequestV2025R0DestinationFolderTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(DocGenBatchCreateRequestV2025R0DestinationFolderTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField>(value));
    }
  }

  public static class DocGenBatchCreateRequestV2025R0DestinationFolderTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField>> {

    public DocGenBatchCreateRequestV2025R0DestinationFolderTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<DocGenBatchCreateRequestV2025R0DestinationFolderTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
