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
        CreateRetentionPolicyRequestBodyRetentionLengthField
            .CreateRetentionPolicyRequestBodyRetentionLengthFieldDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class CreateRetentionPolicyRequestBodyRetentionLengthField
    extends OneOfTwo<String, Integer> {

  public CreateRetentionPolicyRequestBodyRetentionLengthField(String string) {
    super(string, null);
  }

  public CreateRetentionPolicyRequestBodyRetentionLengthField(Integer integerNumber) {
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

  static class CreateRetentionPolicyRequestBodyRetentionLengthFieldDeserializer
      extends JsonDeserializer<CreateRetentionPolicyRequestBodyRetentionLengthField> {

    public CreateRetentionPolicyRequestBodyRetentionLengthFieldDeserializer() {
      super();
    }

    @Override
    public CreateRetentionPolicyRequestBodyRetentionLengthField deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      if (node.isInt()) {
        return new CreateRetentionPolicyRequestBodyRetentionLengthField(
            OneOfTwo.OBJECT_MAPPER.convertValue(node, Integer.class));
      }
      try {
        return new CreateRetentionPolicyRequestBodyRetentionLengthField(
            OneOfTwo.OBJECT_MAPPER.convertValue(node, String.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize CreateRetentionPolicyRequestBodyRetentionLengthField");
    }
  }
}
