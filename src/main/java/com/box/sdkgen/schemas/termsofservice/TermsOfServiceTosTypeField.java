package com.box.sdkgen.schemas.termsofservice;

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

public enum TermsOfServiceTosTypeField implements Valuable {
  MANAGED("managed"),
  EXTERNAL("external");

  private final String value;

  TermsOfServiceTosTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TermsOfServiceTosTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TermsOfServiceTosTypeField>> {

    public TermsOfServiceTosTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TermsOfServiceTosTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TermsOfServiceTosTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TermsOfServiceTosTypeField>(value));
    }
  }

  public static class TermsOfServiceTosTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TermsOfServiceTosTypeField>> {

    public TermsOfServiceTosTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TermsOfServiceTosTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
