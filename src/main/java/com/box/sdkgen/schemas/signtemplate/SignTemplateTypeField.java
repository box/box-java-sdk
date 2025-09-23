package com.box.sdkgen.schemas.signtemplate;

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

public enum SignTemplateTypeField implements Valuable {
  SIGN_TEMPLATE("sign-template");

  private final String value;

  SignTemplateTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignTemplateTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignTemplateTypeField>> {

    public SignTemplateTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignTemplateTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignTemplateTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignTemplateTypeField>(value));
    }
  }

  public static class SignTemplateTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SignTemplateTypeField>> {

    public SignTemplateTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignTemplateTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
