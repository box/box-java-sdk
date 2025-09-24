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

public enum CreateCollaborationRequestBodyAccessibleByTypeField implements Valuable {
  USER("user"),
  GROUP("group");

  private final String value;

  CreateCollaborationRequestBodyAccessibleByTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateCollaborationRequestBodyAccessibleByTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField>> {

    public CreateCollaborationRequestBodyAccessibleByTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateCollaborationRequestBodyAccessibleByTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField>(value));
    }
  }

  public static class CreateCollaborationRequestBodyAccessibleByTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField>> {

    public CreateCollaborationRequestBodyAccessibleByTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateCollaborationRequestBodyAccessibleByTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
