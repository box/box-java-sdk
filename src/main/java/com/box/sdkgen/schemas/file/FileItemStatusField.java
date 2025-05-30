package com.box.sdkgen.schemas.file;

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

public enum FileItemStatusField implements Valuable {
  ACTIVE("active"),
  TRASHED("trashed"),
  DELETED("deleted");

  private final String value;

  FileItemStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileItemStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileItemStatusField>> {

    public FileItemStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileItemStatusField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileItemStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileItemStatusField>(value));
    }
  }

  public static class FileItemStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<FileItemStatusField>> {

    public FileItemStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileItemStatusField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
