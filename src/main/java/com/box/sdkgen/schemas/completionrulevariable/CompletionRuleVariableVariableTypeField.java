package com.box.sdkgen.schemas.completionrulevariable;

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

public enum CompletionRuleVariableVariableTypeField implements Valuable {
  TASK_COMPLETION_RULE("task_completion_rule");

  private final String value;

  CompletionRuleVariableVariableTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CompletionRuleVariableVariableTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CompletionRuleVariableVariableTypeField>> {

    public CompletionRuleVariableVariableTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CompletionRuleVariableVariableTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CompletionRuleVariableVariableTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CompletionRuleVariableVariableTypeField>(value));
    }
  }

  public static class CompletionRuleVariableVariableTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CompletionRuleVariableVariableTypeField>> {

    public CompletionRuleVariableVariableTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CompletionRuleVariableVariableTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
