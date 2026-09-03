package com.box.sdkgen.schemas.signrequestsignerinputzipjpvalidation;

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

public enum SignRequestSignerInputZipjpValidationValidationTypeField implements Valuable {
  ZIP_JP("zip_jp");

  private final String value;

  SignRequestSignerInputZipjpValidationValidationTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class SignRequestSignerInputZipjpValidationValidationTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField>> {

    public SignRequestSignerInputZipjpValidationValidationTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestSignerInputZipjpValidationValidationTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField>(value));
    }
  }

  public static class SignRequestSignerInputZipjpValidationValidationTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField>> {

    public SignRequestSignerInputZipjpValidationValidationTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestSignerInputZipjpValidationValidationTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
