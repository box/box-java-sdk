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

public enum GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField implements Valuable {
  FILE("file"),
  FILE_VERSION("file_version"),
  FOLDER("folder"),
  USER("user"),
  OWNERSHIP("ownership"),
  INTERACTIONS("interactions");

  private final String value;

  GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField>> {

    public GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField>(value));
    }
  }

  public static class GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField>> {

    public GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetLegalHoldPolicyAssignmentsQueryParamsAssignToTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
