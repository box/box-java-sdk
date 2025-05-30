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

public enum FileSharedLinkAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  FileSharedLinkAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileSharedLinkAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileSharedLinkAccessField>> {

    public FileSharedLinkAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileSharedLinkAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileSharedLinkAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileSharedLinkAccessField>(value));
    }
  }

  public static class FileSharedLinkAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<FileSharedLinkAccessField>> {

    public FileSharedLinkAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileSharedLinkAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
