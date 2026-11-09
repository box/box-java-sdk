package com.box.sdkgen.schemas.signrequestsignerverificationmethodcacpiv;

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

public enum SignRequestSignerVerificationMethodCacPivTypeField implements Valuable {
  CAC_PIV("cac_piv");

  private final String value;

  SignRequestSignerVerificationMethodCacPivTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class SignRequestSignerVerificationMethodCacPivTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField>> {

    public SignRequestSignerVerificationMethodCacPivTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestSignerVerificationMethodCacPivTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField>(value));
    }
  }

  public static class SignRequestSignerVerificationMethodCacPivTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField>> {

    public SignRequestSignerVerificationMethodCacPivTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerVerificationMethodCacPivTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
