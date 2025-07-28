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

public enum CreateCollaborationRequestBodyItemTypeField implements Valuable {
  FILE("file"),
  FOLDER("folder");

  private final String value;

  CreateCollaborationRequestBodyItemTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateCollaborationRequestBodyItemTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateCollaborationRequestBodyItemTypeField>> {

    public CreateCollaborationRequestBodyItemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateCollaborationRequestBodyItemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateCollaborationRequestBodyItemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateCollaborationRequestBodyItemTypeField>(value));
    }
  }

  public static class CreateCollaborationRequestBodyItemTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateCollaborationRequestBodyItemTypeField>> {

    public CreateCollaborationRequestBodyItemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateCollaborationRequestBodyItemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
