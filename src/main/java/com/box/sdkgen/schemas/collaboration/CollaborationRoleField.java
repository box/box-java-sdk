package com.box.sdkgen.schemas.collaboration;

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

public enum CollaborationRoleField implements Valuable {
  EDITOR("editor"),
  VIEWER("viewer"),
  PREVIEWER("previewer"),
  UPLOADER("uploader"),
  PREVIEWER_UPLOADER("previewer uploader"),
  VIEWER_UPLOADER("viewer uploader"),
  CO_OWNER("co-owner"),
  OWNER("owner");

  private final String value;

  CollaborationRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollaborationRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollaborationRoleField>> {

    public CollaborationRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollaborationRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollaborationRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollaborationRoleField>(value));
    }
  }

  public static class CollaborationRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<CollaborationRoleField>> {

    public CollaborationRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollaborationRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
