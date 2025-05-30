package com.box.sdkgen.schemas.termsofserviceuserstatus;

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

public enum TermsOfServiceUserStatusTypeField implements Valuable {
  TERMS_OF_SERVICE_USER_STATUS("terms_of_service_user_status");

  private final String value;

  TermsOfServiceUserStatusTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TermsOfServiceUserStatusTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TermsOfServiceUserStatusTypeField>> {

    public TermsOfServiceUserStatusTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TermsOfServiceUserStatusTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TermsOfServiceUserStatusTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TermsOfServiceUserStatusTypeField>(value));
    }
  }

  public static class TermsOfServiceUserStatusTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TermsOfServiceUserStatusTypeField>> {

    public TermsOfServiceUserStatusTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TermsOfServiceUserStatusTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
