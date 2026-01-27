package com.box.sdkgen.managers.legalholdpolicyassignments;

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

public enum CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField implements Valuable {
  FILE("file"),
  FILE_VERSION("file_version"),
  FOLDER("folder"),
  USER("user"),
  OWNERSHIP("ownership"),
  INTERACTIONS("interactions");

  private final String value;

  CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField>> {

    public CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField>(value));
    }
  }

  public static class CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField>> {

    public CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
