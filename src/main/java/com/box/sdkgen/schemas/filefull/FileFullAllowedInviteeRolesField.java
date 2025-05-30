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

public enum FileFullAllowedInviteeRolesField implements Valuable {
  EDITOR("editor"),
  VIEWER("viewer"),
  PREVIEWER("previewer"),
  UPLOADER("uploader"),
  PREVIEWER_UPLOADER("previewer uploader"),
  VIEWER_UPLOADER("viewer uploader"),
  CO_OWNER("co-owner");

  private final String value;

  FileFullAllowedInviteeRolesField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FileFullAllowedInviteeRolesFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FileFullAllowedInviteeRolesField>> {

    public FileFullAllowedInviteeRolesFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FileFullAllowedInviteeRolesField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FileFullAllowedInviteeRolesField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FileFullAllowedInviteeRolesField>(value));
    }
  }

  public static class FileFullAllowedInviteeRolesFieldSerializer
      extends JsonSerializer<EnumWrapper<FileFullAllowedInviteeRolesField>> {

    public FileFullAllowedInviteeRolesFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FileFullAllowedInviteeRolesField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
