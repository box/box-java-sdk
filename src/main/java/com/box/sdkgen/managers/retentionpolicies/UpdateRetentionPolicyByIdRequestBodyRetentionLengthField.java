package com.box.sdkgen.managers.retentionpolicies;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(
    using =
        UpdateRetentionPolicyByIdRequestBodyRetentionLengthField
            .UpdateRetentionPolicyByIdRequestBodyRetentionLengthFieldDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class UpdateRetentionPolicyByIdRequestBodyRetentionLengthField
    extends OneOfTwo<String, Integer> {

  public UpdateRetentionPolicyByIdRequestBodyRetentionLengthField(String string) {
    super(string, null);
  }

  public UpdateRetentionPolicyByIdRequestBodyRetentionLengthField(Integer integerNumber) {
    super(null, integerNumber);
  }

  public boolean isString() {
    return value0 != null;
  }

  public String getString() {
    return value0;
  }

  public boolean isIntegerNumber() {
    return value1 != null;
  }

  public Integer getIntegerNumber() {
    return value1;
  }

  static class UpdateRetentionPolicyByIdRequestBodyRetentionLengthFieldDeserializer
      extends JsonDeserializer<UpdateRetentionPolicyByIdRequestBodyRetentionLengthField> {

    public UpdateRetentionPolicyByIdRequestBodyRetentionLengthFieldDeserializer() {
      super();
    }

    @Override
    public UpdateRetentionPolicyByIdRequestBodyRetentionLengthField deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      if (node.isInt()) {
        return new UpdateRetentionPolicyByIdRequestBodyRetentionLengthField(
            OneOfTwo.OBJECT_MAPPER.convertValue(node, Integer.class));
      }
      try {
        return new UpdateRetentionPolicyByIdRequestBodyRetentionLengthField(
            OneOfTwo.OBJECT_MAPPER.convertValue(node, String.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize UpdateRetentionPolicyByIdRequestBodyRetentionLengthField");
    }
  }
}
