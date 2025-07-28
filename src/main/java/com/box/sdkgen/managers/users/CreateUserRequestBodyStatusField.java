package com.box.sdkgen.managers.users;

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

public enum CreateUserRequestBodyStatusField implements Valuable {
  ACTIVE("active"),
  INACTIVE("inactive"),
  CANNOT_DELETE_EDIT("cannot_delete_edit"),
  CANNOT_DELETE_EDIT_UPLOAD("cannot_delete_edit_upload");

  private final String value;

  CreateUserRequestBodyStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateUserRequestBodyStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateUserRequestBodyStatusField>> {

    public CreateUserRequestBodyStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateUserRequestBodyStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateUserRequestBodyStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateUserRequestBodyStatusField>(value));
    }
  }

  public static class CreateUserRequestBodyStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateUserRequestBodyStatusField>> {

    public CreateUserRequestBodyStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateUserRequestBodyStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
