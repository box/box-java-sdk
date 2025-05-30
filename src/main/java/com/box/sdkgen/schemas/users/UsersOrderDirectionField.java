package com.box.sdkgen.schemas.users;

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

public enum UsersOrderDirectionField implements Valuable {
  ASC("ASC"),
  DESC("DESC");

  private final String value;

  UsersOrderDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UsersOrderDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UsersOrderDirectionField>> {

    public UsersOrderDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UsersOrderDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UsersOrderDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UsersOrderDirectionField>(value));
    }
  }

  public static class UsersOrderDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<UsersOrderDirectionField>> {

    public UsersOrderDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UsersOrderDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
