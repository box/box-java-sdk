package com.box.sdkgen.schemas.fileversionlegalhold;

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

public enum FileVersionLegalHoldTypeField implements Valuable {
  FILE_VERSION_LEGAL_HOLD("file_version_legal_hold");

  private final String value;

  FileVersionLegalHoldTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileVersionLegalHoldTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileVersionLegalHoldTypeField>> {

    public FileVersionLegalHoldTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileVersionLegalHoldTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileVersionLegalHoldTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileVersionLegalHoldTypeField>(value));
    }
  }

  public static class FileVersionLegalHoldTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<FileVersionLegalHoldTypeField>> {

    public FileVersionLegalHoldTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileVersionLegalHoldTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
