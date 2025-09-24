package com.box.sdkgen.managers.comments;

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

public enum CreateCommentRequestBodyItemTypeField implements Valuable {
  FILE("file"),
  COMMENT("comment");

  private final String value;

  CreateCommentRequestBodyItemTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateCommentRequestBodyItemTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateCommentRequestBodyItemTypeField>> {

    public CreateCommentRequestBodyItemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateCommentRequestBodyItemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateCommentRequestBodyItemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateCommentRequestBodyItemTypeField>(value));
    }
  }

  public static class CreateCommentRequestBodyItemTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateCommentRequestBodyItemTypeField>> {

    public CreateCommentRequestBodyItemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateCommentRequestBodyItemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
