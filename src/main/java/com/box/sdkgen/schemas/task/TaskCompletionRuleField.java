package com.box.sdkgen.schemas.task;

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

public enum TaskCompletionRuleField implements Valuable {
  ALL_ASSIGNEES("all_assignees"),
  ANY_ASSIGNEE("any_assignee");

  private final String value;

  TaskCompletionRuleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TaskCompletionRuleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TaskCompletionRuleField>> {

    public TaskCompletionRuleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TaskCompletionRuleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TaskCompletionRuleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TaskCompletionRuleField>(value));
    }
  }

  public static class TaskCompletionRuleFieldSerializer
      extends JsonSerializer<EnumWrapper<TaskCompletionRuleField>> {

    public TaskCompletionRuleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TaskCompletionRuleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
