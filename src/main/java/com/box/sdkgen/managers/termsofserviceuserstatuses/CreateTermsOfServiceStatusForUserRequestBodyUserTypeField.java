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

public enum CreateTermsOfServiceStatusForUserRequestBodyUserTypeField implements Valuable {
  USER("user");

  private final String value;

  CreateTermsOfServiceStatusForUserRequestBodyUserTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateTermsOfServiceStatusForUserRequestBodyUserTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField>> {

    public CreateTermsOfServiceStatusForUserRequestBodyUserTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateTermsOfServiceStatusForUserRequestBodyUserTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField>(value));
    }
  }

  public static class CreateTermsOfServiceStatusForUserRequestBodyUserTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField>> {

    public CreateTermsOfServiceStatusForUserRequestBodyUserTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateTermsOfServiceStatusForUserRequestBodyUserTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
