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

public enum PostOAuth2TokenActorTokenTypeField implements Valuable {
  URN_IETF_PARAMS_OAUTH_TOKEN_TYPE_ID_TOKEN("urn:ietf:params:oauth:token-type:id_token");

  private final String value;

  PostOAuth2TokenActorTokenTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class PostOAuth2TokenActorTokenTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<PostOAuth2TokenActorTokenTypeField>> {

    public PostOAuth2TokenActorTokenTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<PostOAuth2TokenActorTokenTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(PostOAuth2TokenActorTokenTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<PostOAuth2TokenActorTokenTypeField>(value));
    }
  }

  public static class PostOAuth2TokenActorTokenTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<PostOAuth2TokenActorTokenTypeField>> {

    public PostOAuth2TokenActorTokenTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<PostOAuth2TokenActorTokenTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
