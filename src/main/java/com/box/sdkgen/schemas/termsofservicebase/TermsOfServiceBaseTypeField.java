package com.box.sdkgen.schemas.termsofservicebase;

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

public enum TermsOfServiceBaseTypeField implements Valuable {
  TERMS_OF_SERVICE("terms_of_service");

  private final String value;

  TermsOfServiceBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TermsOfServiceBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TermsOfServiceBaseTypeField>> {

    public TermsOfServiceBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TermsOfServiceBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TermsOfServiceBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TermsOfServiceBaseTypeField>(value));
    }
  }

  public static class TermsOfServiceBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TermsOfServiceBaseTypeField>> {

    public TermsOfServiceBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TermsOfServiceBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
