package com.box.sdkgen.schemas.userfull;

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

public enum UserFullRoleField implements Valuable {
  ADMIN("admin"),
  COADMIN("coadmin"),
  USER("user");

  private final String value;

  UserFullRoleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UserFullRoleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UserFullRoleField>> {

    public UserFullRoleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UserFullRoleField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UserFullRoleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UserFullRoleField>(value));
    }
  }

  public static class UserFullRoleFieldSerializer
      extends JsonSerializer<EnumWrapper<UserFullRoleField>> {

    public UserFullRoleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UserFullRoleField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
