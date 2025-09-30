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

public enum CreateUserRequestBodyRoleField implements Valuable {
  COADMIN("coadmin"),
  USER("user");

  private final String value;

  CreateUserRequestBodyRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateUserRequestBodyRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateUserRequestBodyRoleField>> {

    public CreateUserRequestBodyRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateUserRequestBodyRoleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateUserRequestBodyRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateUserRequestBodyRoleField>(value));
    }
  }

  public static class CreateUserRequestBodyRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateUserRequestBodyRoleField>> {

    public CreateUserRequestBodyRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateUserRequestBodyRoleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
