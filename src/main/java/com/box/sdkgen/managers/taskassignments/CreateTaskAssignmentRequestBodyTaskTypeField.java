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

public enum CreateTaskAssignmentRequestBodyTaskTypeField implements Valuable {
  TASK("task");

  private final String value;

  CreateTaskAssignmentRequestBodyTaskTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateTaskAssignmentRequestBodyTaskTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField>> {

    public CreateTaskAssignmentRequestBodyTaskTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateTaskAssignmentRequestBodyTaskTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField>(value));
    }
  }

  public static class CreateTaskAssignmentRequestBodyTaskTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField>> {

    public CreateTaskAssignmentRequestBodyTaskTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
