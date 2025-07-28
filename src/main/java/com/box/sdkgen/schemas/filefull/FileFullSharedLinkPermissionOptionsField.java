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

public enum FileFullSharedLinkPermissionOptionsField implements Valuable {
  CAN_PREVIEW("can_preview"),
  CAN_DOWNLOAD("can_download"),
  CAN_EDIT("can_edit");

  private final String value;

  FileFullSharedLinkPermissionOptionsField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileFullSharedLinkPermissionOptionsFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileFullSharedLinkPermissionOptionsField>> {

    public FileFullSharedLinkPermissionOptionsFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileFullSharedLinkPermissionOptionsField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileFullSharedLinkPermissionOptionsField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileFullSharedLinkPermissionOptionsField>(value));
    }
  }

  public static class FileFullSharedLinkPermissionOptionsFieldSerializer
      extends JsonSerializer<EnumWrapper<FileFullSharedLinkPermissionOptionsField>> {

    public FileFullSharedLinkPermissionOptionsFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileFullSharedLinkPermissionOptionsField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
