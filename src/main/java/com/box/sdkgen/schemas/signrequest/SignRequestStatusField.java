package com.box.sdkgen.schemas.signrequest;

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

public enum SignRequestStatusField implements Valuable {
  CONVERTING("converting"),
  CREATED("created"),
  SENT("sent"),
  VIEWED("viewed"),
  SIGNED("signed"),
  CANCELLED("cancelled"),
  DECLINED("declined"),
  ERROR_CONVERTING("error_converting"),
  ERROR_SENDING("error_sending"),
  EXPIRED("expired"),
  FINALIZING("finalizing"),
  ERROR_FINALIZING("error_finalizing");

  private final String value;

  SignRequestStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SignRequestStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SignRequestStatusField>> {

    public SignRequestStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SignRequestStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SignRequestStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SignRequestStatusField>(value));
    }
  }

  public static class SignRequestStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<SignRequestStatusField>> {

    public SignRequestStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SignRequestStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
