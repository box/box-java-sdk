package com.box.sdkgen.schemas.userbase;

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

public enum UserBaseTypeField implements Valuable {
  USER("user");

  private final String value;

  UserBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UserBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UserBaseTypeField>> {

    public UserBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UserBaseTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UserBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UserBaseTypeField>(value));
    }
  }

  public static class UserBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<UserBaseTypeField>> {

    public UserBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UserBaseTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
