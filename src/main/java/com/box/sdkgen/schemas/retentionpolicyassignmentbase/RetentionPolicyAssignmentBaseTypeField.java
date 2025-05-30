package com.box.sdkgen.schemas.retentionpolicyassignmentbase;

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

public enum RetentionPolicyAssignmentBaseTypeField implements Valuable {
  RETENTION_POLICY_ASSIGNMENT("retention_policy_assignment");

  private final String value;

  RetentionPolicyAssignmentBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class RetentionPolicyAssignmentBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<RetentionPolicyAssignmentBaseTypeField>> {

    public RetentionPolicyAssignmentBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<RetentionPolicyAssignmentBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(RetentionPolicyAssignmentBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<RetentionPolicyAssignmentBaseTypeField>(value));
    }
  }

  public static class RetentionPolicyAssignmentBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<RetentionPolicyAssignmentBaseTypeField>> {

    public RetentionPolicyAssignmentBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<RetentionPolicyAssignmentBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
