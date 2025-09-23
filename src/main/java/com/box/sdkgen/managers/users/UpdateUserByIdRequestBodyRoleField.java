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

public enum UpdateUserByIdRequestBodyRoleField implements Valuable {
  COADMIN("coadmin"),
  USER("user");

  private final String value;

  UpdateUserByIdRequestBodyRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateUserByIdRequestBodyRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateUserByIdRequestBodyRoleField>> {

    public UpdateUserByIdRequestBodyRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateUserByIdRequestBodyRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateUserByIdRequestBodyRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateUserByIdRequestBodyRoleField>(value));
    }
  }

  public static class UpdateUserByIdRequestBodyRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateUserByIdRequestBodyRoleField>> {

    public UpdateUserByIdRequestBodyRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateUserByIdRequestBodyRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
