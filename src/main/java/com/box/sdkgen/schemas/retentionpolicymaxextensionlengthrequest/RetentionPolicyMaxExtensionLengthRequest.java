package com.box.sdkgen.schemas.retentionpolicymaxextensionlengthrequest;

import com.box.sdkgen.internal.OneOfThree;
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
        RetentionPolicyMaxExtensionLengthRequest
            .RetentionPolicyMaxExtensionLengthRequestDeserializer.class)
@JsonSerialize(using = OneOfThree.OneOfThreeSerializer.class)
public class RetentionPolicyMaxExtensionLengthRequest
    extends OneOfThree<RetentionPolicyMaxExtensionLengthRequestEnum, String, Integer> {

  public RetentionPolicyMaxExtensionLengthRequest(
      RetentionPolicyMaxExtensionLengthRequestEnum retentionPolicyMaxExtensionLengthRequestEnum) {
    super(retentionPolicyMaxExtensionLengthRequestEnum, null, null);
  }

  public RetentionPolicyMaxExtensionLengthRequest(String string) {
    super(null, string, null);
  }

  public RetentionPolicyMaxExtensionLengthRequest(Integer integerNumber) {
    super(null, null, integerNumber);
  }

  public boolean isRetentionPolicyMaxExtensionLengthRequestEnum() {
    return value0 != null;
  }

  public RetentionPolicyMaxExtensionLengthRequestEnum
      getRetentionPolicyMaxExtensionLengthRequestEnum() {
    return value0;
  }

  public boolean isString() {
    return value1 != null;
  }

  public String getString() {
    return value1;
  }

  public boolean isIntegerNumber() {
    return value2 != null;
  }

  public Integer getIntegerNumber() {
    return value2;
  }

  static class RetentionPolicyMaxExtensionLengthRequestDeserializer
      extends JsonDeserializer<RetentionPolicyMaxExtensionLengthRequest> {

    public RetentionPolicyMaxExtensionLengthRequestDeserializer() {
      super();
    }

    @Override
    public RetentionPolicyMaxExtensionLengthRequest deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      if (node.isInt()) {
        return new RetentionPolicyMaxExtensionLengthRequest(
            OneOfThree.OBJECT_MAPPER.convertValue(node, Integer.class));
      }
      try {
        return new RetentionPolicyMaxExtensionLengthRequest(
            OneOfThree.OBJECT_MAPPER.convertValue(
                node, RetentionPolicyMaxExtensionLengthRequestEnum.class));
      } catch (Exception ignored) {
      }
      try {
        return new RetentionPolicyMaxExtensionLengthRequest(
            OneOfThree.OBJECT_MAPPER.convertValue(node, String.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize RetentionPolicyMaxExtensionLengthRequest");
    }
  }
}
