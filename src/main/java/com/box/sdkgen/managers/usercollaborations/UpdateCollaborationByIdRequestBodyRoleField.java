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

public enum UpdateCollaborationByIdRequestBodyRoleField implements Valuable {
  EDITOR("editor"),
  VIEWER("viewer"),
  PREVIEWER("previewer"),
  UPLOADER("uploader"),
  PREVIEWER_UPLOADER("previewer uploader"),
  VIEWER_UPLOADER("viewer uploader"),
  CO_OWNER("co-owner"),
  OWNER("owner");

  private final String value;

  UpdateCollaborationByIdRequestBodyRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateCollaborationByIdRequestBodyRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField>> {

    public UpdateCollaborationByIdRequestBodyRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateCollaborationByIdRequestBodyRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField>(value));
    }
  }

  public static class UpdateCollaborationByIdRequestBodyRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField>> {

    public UpdateCollaborationByIdRequestBodyRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
