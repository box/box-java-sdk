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

public enum SignTemplateAdditionalInfoRequiredSignersField implements Valuable {
  EMAIL("email");

  private final String value;

  SignTemplateAdditionalInfoRequiredSignersField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignTemplateAdditionalInfoRequiredSignersFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>> {

    public SignTemplateAdditionalInfoRequiredSignersFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignTemplateAdditionalInfoRequiredSignersField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>(value));
    }
  }

  public static class SignTemplateAdditionalInfoRequiredSignersFieldSerializer
      extends JsonSerializer<EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField>> {

    public SignTemplateAdditionalInfoRequiredSignersFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignTemplateAdditionalInfoRequiredSignersField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
