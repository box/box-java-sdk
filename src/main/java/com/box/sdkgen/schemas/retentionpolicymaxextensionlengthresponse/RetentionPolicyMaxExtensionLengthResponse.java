package com.box.sdkgen.schemas.retentionpolicymaxextensionlengthresponse;

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
        RetentionPolicyMaxExtensionLengthResponse
            .RetentionPolicyMaxExtensionLengthResponseDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class RetentionPolicyMaxExtensionLengthResponse
    extends OneOfTwo<RetentionPolicyMaxExtensionLengthResponseEnum, String> {

  public RetentionPolicyMaxExtensionLengthResponse(
      RetentionPolicyMaxExtensionLengthResponseEnum retentionPolicyMaxExtensionLengthResponseEnum) {
    super(retentionPolicyMaxExtensionLengthResponseEnum, null);
  }

  public RetentionPolicyMaxExtensionLengthResponse(String string) {
    super(null, string);
  }

  public boolean isRetentionPolicyMaxExtensionLengthResponseEnum() {
    return value0 != null;
  }

  public RetentionPolicyMaxExtensionLengthResponseEnum
      getRetentionPolicyMaxExtensionLengthResponseEnum() {
    return value0;
  }

  public boolean isString() {
    return value1 != null;
  }

  public String getString() {
    return value1;
  }

  static class RetentionPolicyMaxExtensionLengthResponseDeserializer
      extends JsonDeserializer<RetentionPolicyMaxExtensionLengthResponse> {

    public RetentionPolicyMaxExtensionLengthResponseDeserializer() {
      super();
    }

    @Override
    public RetentionPolicyMaxExtensionLengthResponse deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      try {
        return new RetentionPolicyMaxExtensionLengthResponse(
            OneOfTwo.OBJECT_MAPPER.convertValue(
                node, RetentionPolicyMaxExtensionLengthResponseEnum.class));
      } catch (Exception ignored) {
      }
      try {
        return new RetentionPolicyMaxExtensionLengthResponse(
            OneOfTwo.OBJECT_MAPPER.convertValue(node, String.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize RetentionPolicyMaxExtensionLengthResponse");
    }
  }
}
