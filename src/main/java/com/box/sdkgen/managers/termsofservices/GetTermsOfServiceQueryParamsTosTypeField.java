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

public enum GetTermsOfServiceQueryParamsTosTypeField implements Valuable {
  EXTERNAL("external"),
  MANAGED("managed");

  private final String value;

  GetTermsOfServiceQueryParamsTosTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetTermsOfServiceQueryParamsTosTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField>> {

    public GetTermsOfServiceQueryParamsTosTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetTermsOfServiceQueryParamsTosTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField>(value));
    }
  }

  public static class GetTermsOfServiceQueryParamsTosTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField>> {

    public GetTermsOfServiceQueryParamsTosTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
