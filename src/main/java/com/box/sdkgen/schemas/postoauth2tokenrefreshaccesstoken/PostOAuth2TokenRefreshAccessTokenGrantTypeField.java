package com.box.sdkgen.schemas.postoauth2tokenrefreshaccesstoken;

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

public enum PostOAuth2TokenRefreshAccessTokenGrantTypeField implements Valuable {
  REFRESH_TOKEN("refresh_token");

  private final String value;

  PostOAuth2TokenRefreshAccessTokenGrantTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class PostOAuth2TokenRefreshAccessTokenGrantTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField>> {

    public PostOAuth2TokenRefreshAccessTokenGrantTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(PostOAuth2TokenRefreshAccessTokenGrantTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField>(value));
    }
  }

  public static class PostOAuth2TokenRefreshAccessTokenGrantTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField>> {

    public PostOAuth2TokenRefreshAccessTokenGrantTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
