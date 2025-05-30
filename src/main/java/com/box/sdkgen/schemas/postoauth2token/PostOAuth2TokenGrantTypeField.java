package com.box.sdkgen.schemas.postoauth2token;

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

public enum PostOAuth2TokenGrantTypeField implements Valuable {
  AUTHORIZATION_CODE("authorization_code"),
  REFRESH_TOKEN("refresh_token"),
  CLIENT_CREDENTIALS("client_credentials"),
  URN_IETF_PARAMS_OAUTH_GRANT_TYPE_JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer"),
  URN_IETF_PARAMS_OAUTH_GRANT_TYPE_TOKEN_EXCHANGE(
      "urn:ietf:params:oauth:grant-type:token-exchange");

  private final String value;

  PostOAuth2TokenGrantTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class PostOAuth2TokenGrantTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<PostOAuth2TokenGrantTypeField>> {

    public PostOAuth2TokenGrantTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<PostOAuth2TokenGrantTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(PostOAuth2TokenGrantTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<PostOAuth2TokenGrantTypeField>(value));
    }
  }

  public static class PostOAuth2TokenGrantTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<PostOAuth2TokenGrantTypeField>> {

    public PostOAuth2TokenGrantTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<PostOAuth2TokenGrantTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
