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

public enum AccessTokenIssuedTokenTypeField implements Valuable {
  URN_IETF_PARAMS_OAUTH_TOKEN_TYPE_ACCESS_TOKEN("urn:ietf:params:oauth:token-type:access_token");

  private final String value;

  AccessTokenIssuedTokenTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AccessTokenIssuedTokenTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AccessTokenIssuedTokenTypeField>> {

    public AccessTokenIssuedTokenTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AccessTokenIssuedTokenTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AccessTokenIssuedTokenTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AccessTokenIssuedTokenTypeField>(value));
    }
  }

  public static class AccessTokenIssuedTokenTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AccessTokenIssuedTokenTypeField>> {

    public AccessTokenIssuedTokenTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AccessTokenIssuedTokenTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
