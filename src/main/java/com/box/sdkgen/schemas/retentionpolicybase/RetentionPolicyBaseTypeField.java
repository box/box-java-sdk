package com.box.sdkgen.schemas.retentionpolicybase;

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

public enum RetentionPolicyBaseTypeField implements Valuable {
  RETENTION_POLICY("retention_policy");

  private final String value;

  RetentionPolicyBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class RetentionPolicyBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<RetentionPolicyBaseTypeField>> {

    public RetentionPolicyBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<RetentionPolicyBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(RetentionPolicyBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<RetentionPolicyBaseTypeField>(value));
    }
  }

  public static class RetentionPolicyBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<RetentionPolicyBaseTypeField>> {

    public RetentionPolicyBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<RetentionPolicyBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
