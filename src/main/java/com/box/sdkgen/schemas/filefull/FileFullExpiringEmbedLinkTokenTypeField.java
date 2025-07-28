package com.box.sdkgen.schemas.filefull;

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

public enum FileFullExpiringEmbedLinkTokenTypeField implements Valuable {
  BEARER("bearer");

  private final String value;

  FileFullExpiringEmbedLinkTokenTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileFullExpiringEmbedLinkTokenTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField>> {

    public FileFullExpiringEmbedLinkTokenTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileFullExpiringEmbedLinkTokenTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField>(value));
    }
  }

  public static class FileFullExpiringEmbedLinkTokenTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField>> {

    public FileFullExpiringEmbedLinkTokenTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
