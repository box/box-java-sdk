package com.box.sdkgen.managers.termsofservices;

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

public enum CreateTermsOfServiceRequestBodyStatusField implements Valuable {
  ENABLED("enabled"),
  DISABLED("disabled");

  private final String value;

  CreateTermsOfServiceRequestBodyStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateTermsOfServiceRequestBodyStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateTermsOfServiceRequestBodyStatusField>> {

    public CreateTermsOfServiceRequestBodyStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateTermsOfServiceRequestBodyStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateTermsOfServiceRequestBodyStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateTermsOfServiceRequestBodyStatusField>(value));
    }
  }

  public static class CreateTermsOfServiceRequestBodyStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateTermsOfServiceRequestBodyStatusField>> {

    public CreateTermsOfServiceRequestBodyStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateTermsOfServiceRequestBodyStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
