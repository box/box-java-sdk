package com.box.sdkgen.schemas.retentionpolicyassignment;

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

public enum RetentionPolicyAssignmentTypeField implements Valuable {
  RETENTION_POLICY_ASSIGNMENT("retention_policy_assignment");

  private final String value;

  RetentionPolicyAssignmentTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class RetentionPolicyAssignmentTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<RetentionPolicyAssignmentTypeField>> {

    public RetentionPolicyAssignmentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<RetentionPolicyAssignmentTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(RetentionPolicyAssignmentTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<RetentionPolicyAssignmentTypeField>(value));
    }
  }

  public static class RetentionPolicyAssignmentTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<RetentionPolicyAssignmentTypeField>> {

    public RetentionPolicyAssignmentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<RetentionPolicyAssignmentTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
