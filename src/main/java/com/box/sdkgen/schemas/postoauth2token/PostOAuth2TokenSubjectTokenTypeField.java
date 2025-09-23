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

public enum PostOAuth2TokenSubjectTokenTypeField implements Valuable {
  URN_IETF_PARAMS_OAUTH_TOKEN_TYPE_ACCESS_TOKEN("urn:ietf:params:oauth:token-type:access_token");

  private final String value;

  PostOAuth2TokenSubjectTokenTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class PostOAuth2TokenSubjectTokenTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<PostOAuth2TokenSubjectTokenTypeField>> {

    public PostOAuth2TokenSubjectTokenTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<PostOAuth2TokenSubjectTokenTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(PostOAuth2TokenSubjectTokenTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<PostOAuth2TokenSubjectTokenTypeField>(value));
    }
  }

  public static class PostOAuth2TokenSubjectTokenTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<PostOAuth2TokenSubjectTokenTypeField>> {

    public PostOAuth2TokenSubjectTokenTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<PostOAuth2TokenSubjectTokenTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
