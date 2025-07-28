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

public enum UpdateTermsOfServiceByIdRequestBodyStatusField implements Valuable {
  ENABLED("enabled"),
  DISABLED("disabled");

  private final String value;

  UpdateTermsOfServiceByIdRequestBodyStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateTermsOfServiceByIdRequestBodyStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateTermsOfServiceByIdRequestBodyStatusField>> {

    public UpdateTermsOfServiceByIdRequestBodyStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateTermsOfServiceByIdRequestBodyStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateTermsOfServiceByIdRequestBodyStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateTermsOfServiceByIdRequestBodyStatusField>(value));
    }
  }

  public static class UpdateTermsOfServiceByIdRequestBodyStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateTermsOfServiceByIdRequestBodyStatusField>> {

    public UpdateTermsOfServiceByIdRequestBodyStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateTermsOfServiceByIdRequestBodyStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
