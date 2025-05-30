package com.box.sdkgen.managers.termsofserviceuserstatuses;

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

public enum CreateTermsOfServiceStatusForUserRequestBodyTosTypeField implements Valuable {
  TERMS_OF_SERVICE("terms_of_service");

  private final String value;

  CreateTermsOfServiceStatusForUserRequestBodyTosTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateTermsOfServiceStatusForUserRequestBodyTosTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyTosTypeField>> {

    public CreateTermsOfServiceStatusForUserRequestBodyTosTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyTosTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateTermsOfServiceStatusForUserRequestBodyTosTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyTosTypeField>(value));
    }
  }

  public static class CreateTermsOfServiceStatusForUserRequestBodyTosTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyTosTypeField>> {

    public CreateTermsOfServiceStatusForUserRequestBodyTosTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyTosTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
