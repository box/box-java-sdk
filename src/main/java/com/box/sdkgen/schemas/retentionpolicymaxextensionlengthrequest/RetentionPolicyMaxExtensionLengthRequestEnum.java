package com.box.sdkgen.schemas.retentionpolicymaxextensionlengthrequest;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum RetentionPolicyMaxExtensionLengthRequestEnum implements Valuable {
  NONE("none");

  private final String value;

  RetentionPolicyMaxExtensionLengthRequestEnum(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class RetentionPolicyMaxExtensionLengthRequestEnumDeserializer
      extends JsonDeserializer<EnumWrapper<RetentionPolicyMaxExtensionLengthRequestEnum>> {

    public RetentionPolicyMaxExtensionLengthRequestEnumDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<RetentionPolicyMaxExtensionLengthRequestEnum> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(RetentionPolicyMaxExtensionLengthRequestEnum.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<RetentionPolicyMaxExtensionLengthRequestEnum>(value));
    }
  }

  public static class RetentionPolicyMaxExtensionLengthRequestEnumSerializer
      extends JsonSerializer<EnumWrapper<RetentionPolicyMaxExtensionLengthRequestEnum>> {

    public RetentionPolicyMaxExtensionLengthRequestEnumSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<RetentionPolicyMaxExtensionLengthRequestEnum> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
