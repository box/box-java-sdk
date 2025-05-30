package com.box.sdkgen.managers.usercollaborations;

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

public enum CreateCollaborationRequestBodyRoleField implements Valuable {
  EDITOR("editor"),
  VIEWER("viewer"),
  PREVIEWER("previewer"),
  UPLOADER("uploader"),
  PREVIEWER_UPLOADER("previewer uploader"),
  VIEWER_UPLOADER("viewer uploader"),
  CO_OWNER("co-owner");

  private final String value;

  CreateCollaborationRequestBodyRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateCollaborationRequestBodyRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateCollaborationRequestBodyRoleField>> {

    public CreateCollaborationRequestBodyRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateCollaborationRequestBodyRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateCollaborationRequestBodyRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateCollaborationRequestBodyRoleField>(value));
    }
  }

  public static class CreateCollaborationRequestBodyRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateCollaborationRequestBodyRoleField>> {

    public CreateCollaborationRequestBodyRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateCollaborationRequestBodyRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
