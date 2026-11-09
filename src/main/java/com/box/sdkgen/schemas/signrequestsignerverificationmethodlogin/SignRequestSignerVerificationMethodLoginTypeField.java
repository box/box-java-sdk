package com.box.sdkgen.schemas.signrequestsignerverificationmethodlogin;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum SignRequestSignerVerificationMethodLoginTypeField implements Valuable {
  LOGIN("login");

  private final String value;

  SignRequestSignerVerificationMethodLoginTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class SignRequestSignerVerificationMethodLoginTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField>> {

    public SignRequestSignerVerificationMethodLoginTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestSignerVerificationMethodLoginTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField>(value));
    }
  }

  public static class SignRequestSignerVerificationMethodLoginTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField>> {

    public SignRequestSignerVerificationMethodLoginTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerVerificationMethodLoginTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
