package com.box.sdkgen.schemas.v2025r0.fileversionbasev2025r0;

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

public enum FileVersionBaseV2025R0TypeField implements Valuable {
  FILE_VERSION("file_version");

  private final String value;

  FileVersionBaseV2025R0TypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileVersionBaseV2025R0TypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileVersionBaseV2025R0TypeField>> {

    public FileVersionBaseV2025R0TypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileVersionBaseV2025R0TypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileVersionBaseV2025R0TypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileVersionBaseV2025R0TypeField>(value));
    }
  }

  public static class FileVersionBaseV2025R0TypeFieldSerializer
      extends JsonSerializer<EnumWrapper<FileVersionBaseV2025R0TypeField>> {

    public FileVersionBaseV2025R0TypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileVersionBaseV2025R0TypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
