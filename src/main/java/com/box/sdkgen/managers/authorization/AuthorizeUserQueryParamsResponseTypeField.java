package com.box.sdkgen.managers.authorization;

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

public enum AuthorizeUserQueryParamsResponseTypeField implements Valuable {
  CODE("code");

  private final String value;

  AuthorizeUserQueryParamsResponseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AuthorizeUserQueryParamsResponseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AuthorizeUserQueryParamsResponseTypeField>> {

    public AuthorizeUserQueryParamsResponseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AuthorizeUserQueryParamsResponseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AuthorizeUserQueryParamsResponseTypeField>(value));
    }
  }

  public static class AuthorizeUserQueryParamsResponseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AuthorizeUserQueryParamsResponseTypeField>> {

    public AuthorizeUserQueryParamsResponseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
