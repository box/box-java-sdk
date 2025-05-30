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

public enum FileSharedLinkEffectiveAccessField implements Valuable {
  OPEN("open"),
  COMPANY("company"),
  COLLABORATORS("collaborators");

  private final String value;

  FileSharedLinkEffectiveAccessField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileSharedLinkEffectiveAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileSharedLinkEffectiveAccessField>> {

    public FileSharedLinkEffectiveAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileSharedLinkEffectiveAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileSharedLinkEffectiveAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileSharedLinkEffectiveAccessField>(value));
    }
  }

  public static class FileSharedLinkEffectiveAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<FileSharedLinkEffectiveAccessField>> {

    public FileSharedLinkEffectiveAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileSharedLinkEffectiveAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
