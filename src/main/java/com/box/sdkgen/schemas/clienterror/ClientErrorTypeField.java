package com.box.sdkgen.schemas.clienterror;

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

public enum ClientErrorTypeField implements Valuable {
  ERROR("error");

  private final String value;

  ClientErrorTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ClientErrorTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ClientErrorTypeField>> {

    public ClientErrorTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ClientErrorTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ClientErrorTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ClientErrorTypeField>(value));
    }
  }

  public static class ClientErrorTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<ClientErrorTypeField>> {

    public ClientErrorTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ClientErrorTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
