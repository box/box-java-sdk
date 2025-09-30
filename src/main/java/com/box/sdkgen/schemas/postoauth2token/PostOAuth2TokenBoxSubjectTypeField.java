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

public enum PostOAuth2TokenBoxSubjectTypeField implements Valuable {
  ENTERPRISE("enterprise"),
  USER("user");

  private final String value;

  PostOAuth2TokenBoxSubjectTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class PostOAuth2TokenBoxSubjectTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<PostOAuth2TokenBoxSubjectTypeField>> {

    public PostOAuth2TokenBoxSubjectTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<PostOAuth2TokenBoxSubjectTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(PostOAuth2TokenBoxSubjectTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<PostOAuth2TokenBoxSubjectTypeField>(value));
    }
  }

  public static class PostOAuth2TokenBoxSubjectTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<PostOAuth2TokenBoxSubjectTypeField>> {

    public PostOAuth2TokenBoxSubjectTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<PostOAuth2TokenBoxSubjectTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
