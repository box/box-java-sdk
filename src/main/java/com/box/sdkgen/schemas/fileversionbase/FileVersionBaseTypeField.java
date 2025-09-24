package com.box.sdkgen.schemas.fileversionbase;

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

public enum FileVersionBaseTypeField implements Valuable {
  FILE_VERSION("file_version");

  private final String value;

  FileVersionBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileVersionBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileVersionBaseTypeField>> {

    public FileVersionBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileVersionBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileVersionBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileVersionBaseTypeField>(value));
    }
  }

  public static class FileVersionBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<FileVersionBaseTypeField>> {

    public FileVersionBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileVersionBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
