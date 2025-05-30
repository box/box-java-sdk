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

public enum TaskActionField implements Valuable {
  REVIEW("review"),
  COMPLETE("complete");

  private final String value;

  TaskActionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TaskActionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TaskActionField>> {

    public TaskActionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TaskActionField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TaskActionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TaskActionField>(value));
    }
  }

  public static class TaskActionFieldSerializer
      extends JsonSerializer<EnumWrapper<TaskActionField>> {

    public TaskActionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TaskActionField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
