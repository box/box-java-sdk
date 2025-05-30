package com.box.sdkgen.schemas.emailalias;

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

public enum EmailAliasTypeField implements Valuable {
  EMAIL_ALIAS("email_alias");

  private final String value;

  EmailAliasTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class EmailAliasTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<EmailAliasTypeField>> {

    public EmailAliasTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<EmailAliasTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(EmailAliasTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<EmailAliasTypeField>(value));
    }
  }

  public static class EmailAliasTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<EmailAliasTypeField>> {

    public EmailAliasTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<EmailAliasTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
