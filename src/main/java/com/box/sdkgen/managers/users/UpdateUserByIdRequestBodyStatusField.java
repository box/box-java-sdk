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

public enum UpdateUserByIdRequestBodyStatusField implements Valuable {
  ACTIVE("active"),
  INACTIVE("inactive"),
  CANNOT_DELETE_EDIT("cannot_delete_edit"),
  CANNOT_DELETE_EDIT_UPLOAD("cannot_delete_edit_upload");

  private final String value;

  UpdateUserByIdRequestBodyStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateUserByIdRequestBodyStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateUserByIdRequestBodyStatusField>> {

    public UpdateUserByIdRequestBodyStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateUserByIdRequestBodyStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateUserByIdRequestBodyStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateUserByIdRequestBodyStatusField>(value));
    }
  }

  public static class UpdateUserByIdRequestBodyStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateUserByIdRequestBodyStatusField>> {

    public UpdateUserByIdRequestBodyStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateUserByIdRequestBodyStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
