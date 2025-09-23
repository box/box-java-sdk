package com.box.sdkgen.schemas.taskassignment;

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

public enum TaskAssignmentResolutionStateField implements Valuable {
  COMPLETED("completed"),
  INCOMPLETE("incomplete"),
  APPROVED("approved"),
  REJECTED("rejected");

  private final String value;

  TaskAssignmentResolutionStateField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TaskAssignmentResolutionStateFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TaskAssignmentResolutionStateField>> {

    public TaskAssignmentResolutionStateFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TaskAssignmentResolutionStateField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TaskAssignmentResolutionStateField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TaskAssignmentResolutionStateField>(value));
    }
  }

  public static class TaskAssignmentResolutionStateFieldSerializer
      extends JsonSerializer<EnumWrapper<TaskAssignmentResolutionStateField>> {

    public TaskAssignmentResolutionStateFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TaskAssignmentResolutionStateField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
