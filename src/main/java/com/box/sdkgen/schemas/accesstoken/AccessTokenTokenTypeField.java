package com.box.sdkgen.schemas.accesstoken;

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

public enum AccessTokenTokenTypeField implements Valuable {
  BEARER("bearer");

  private final String value;

  AccessTokenTokenTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AccessTokenTokenTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AccessTokenTokenTypeField>> {

    public AccessTokenTokenTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AccessTokenTokenTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AccessTokenTokenTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AccessTokenTokenTypeField>(value));
    }
  }

  public static class AccessTokenTokenTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AccessTokenTokenTypeField>> {

    public AccessTokenTokenTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AccessTokenTokenTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
