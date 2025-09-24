package com.box.sdkgen.schemas.folderfull;

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

public enum FolderFullAllowedInviteeRolesField implements Valuable {
  EDITOR("editor"),
  VIEWER("viewer"),
  PREVIEWER("previewer"),
  UPLOADER("uploader"),
  PREVIEWER_UPLOADER("previewer uploader"),
  VIEWER_UPLOADER("viewer uploader"),
  CO_OWNER("co-owner");

  private final String value;

  FolderFullAllowedInviteeRolesField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class FolderFullAllowedInviteeRolesFieldDeserializer
      extends JsonDeserializer<EnumWrapper<FolderFullAllowedInviteeRolesField>> {

    public FolderFullAllowedInviteeRolesFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<FolderFullAllowedInviteeRolesField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(FolderFullAllowedInviteeRolesField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<FolderFullAllowedInviteeRolesField>(value));
    }
  }

  public static class FolderFullAllowedInviteeRolesFieldSerializer
      extends JsonSerializer<EnumWrapper<FolderFullAllowedInviteeRolesField>> {

    public FolderFullAllowedInviteeRolesFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<FolderFullAllowedInviteeRolesField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
