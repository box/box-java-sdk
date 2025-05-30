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

public enum CreateTaskRequestBodyCompletionRuleField implements Valuable {
  ALL_ASSIGNEES("all_assignees"),
  ANY_ASSIGNEE("any_assignee");

  private final String value;

  CreateTaskRequestBodyCompletionRuleField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateTaskRequestBodyCompletionRuleFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateTaskRequestBodyCompletionRuleField>> {

    public CreateTaskRequestBodyCompletionRuleFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateTaskRequestBodyCompletionRuleField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateTaskRequestBodyCompletionRuleField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateTaskRequestBodyCompletionRuleField>(value));
    }
  }

  public static class CreateTaskRequestBodyCompletionRuleFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateTaskRequestBodyCompletionRuleField>> {

    public CreateTaskRequestBodyCompletionRuleFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateTaskRequestBodyCompletionRuleField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
