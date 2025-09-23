package com.box.sdkgen.managers.tasks;

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

public enum UpdateTaskByIdRequestBodyCompletionRuleField implements Valuable {
  ALL_ASSIGNEES("all_assignees"),
  ANY_ASSIGNEE("any_assignee");

  private final String value;

  UpdateTaskByIdRequestBodyCompletionRuleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateTaskByIdRequestBodyCompletionRuleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField>> {

    public UpdateTaskByIdRequestBodyCompletionRuleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateTaskByIdRequestBodyCompletionRuleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField>(value));
    }
  }

  public static class UpdateTaskByIdRequestBodyCompletionRuleFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField>> {

    public UpdateTaskByIdRequestBodyCompletionRuleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateTaskByIdRequestBodyCompletionRuleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
