package com.box.sdkgen.schemas.retentionpolicy;

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

public enum RetentionPolicyStatusField implements Valuable {
  ACTIVE("active"),
  RETIRED("retired");

  private final String value;

  RetentionPolicyStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class RetentionPolicyStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<RetentionPolicyStatusField>> {

    public RetentionPolicyStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<RetentionPolicyStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(RetentionPolicyStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<RetentionPolicyStatusField>(value));
    }
  }

  public static class RetentionPolicyStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<RetentionPolicyStatusField>> {

    public RetentionPolicyStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<RetentionPolicyStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
