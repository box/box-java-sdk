package com.box.sdkgen.managers.taskassignments;

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

public enum UpdateTaskAssignmentByIdRequestBodyResolutionStateField implements Valuable {
  COMPLETED("completed"),
  INCOMPLETE("incomplete"),
  APPROVED("approved"),
  REJECTED("rejected");

  private final String value;

  UpdateTaskAssignmentByIdRequestBodyResolutionStateField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateTaskAssignmentByIdRequestBodyResolutionStateFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<UpdateTaskAssignmentByIdRequestBodyResolutionStateField>> {

    public UpdateTaskAssignmentByIdRequestBodyResolutionStateFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateTaskAssignmentByIdRequestBodyResolutionStateField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateTaskAssignmentByIdRequestBodyResolutionStateField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateTaskAssignmentByIdRequestBodyResolutionStateField>(value));
    }
  }

  public static class UpdateTaskAssignmentByIdRequestBodyResolutionStateFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateTaskAssignmentByIdRequestBodyResolutionStateField>> {

    public UpdateTaskAssignmentByIdRequestBodyResolutionStateFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateTaskAssignmentByIdRequestBodyResolutionStateField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
