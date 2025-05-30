package com.box.sdkgen.managers.retentionpolicyassignments;

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

public enum CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField implements Valuable {
  ENTERPRISE("enterprise"),
  FOLDER("folder"),
  METADATA_TEMPLATE("metadata_template");

  private final String value;

  CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateRetentionPolicyAssignmentRequestBodyAssignToTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField>> {

    public CreateRetentionPolicyAssignmentRequestBodyAssignToTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField>(value));
    }
  }

  public static class CreateRetentionPolicyAssignmentRequestBodyAssignToTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField>> {

    public CreateRetentionPolicyAssignmentRequestBodyAssignToTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateRetentionPolicyAssignmentRequestBodyAssignToTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
