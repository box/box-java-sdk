package com.box.sdkgen.schemas.filefull;

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

public enum FileFullAllowedSharedLinkAccessLevelsField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  FileFullAllowedSharedLinkAccessLevelsField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class FileFullAllowedSharedLinkAccessLevelsFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileFullAllowedSharedLinkAccessLevelsField>> {

    public FileFullAllowedSharedLinkAccessLevelsFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileFullAllowedSharedLinkAccessLevelsField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileFullAllowedSharedLinkAccessLevelsField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileFullAllowedSharedLinkAccessLevelsField>(value));
    }
  }

  public static class FileFullAllowedSharedLinkAccessLevelsFieldSerializer
      extends JsonSerializer<EnumWrapper<FileFullAllowedSharedLinkAccessLevelsField>> {

    public FileFullAllowedSharedLinkAccessLevelsFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileFullAllowedSharedLinkAccessLevelsField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
