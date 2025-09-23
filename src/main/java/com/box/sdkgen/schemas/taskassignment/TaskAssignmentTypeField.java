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

public enum TaskAssignmentTypeField implements Valuable {
  TASK_ASSIGNMENT("task_assignment");

  private final String value;

  TaskAssignmentTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TaskAssignmentTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TaskAssignmentTypeField>> {

    public TaskAssignmentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TaskAssignmentTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TaskAssignmentTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TaskAssignmentTypeField>(value));
    }
  }

  public static class TaskAssignmentTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TaskAssignmentTypeField>> {

    public TaskAssignmentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TaskAssignmentTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
