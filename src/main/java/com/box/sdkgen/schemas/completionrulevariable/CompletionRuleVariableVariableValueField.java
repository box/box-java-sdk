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

public enum CompletionRuleVariableVariableValueField implements Valuable {
  ALL_ASSIGNEES("all_assignees"),
  ANY_ASSIGNEES("any_assignees");

  private final String value;

  CompletionRuleVariableVariableValueField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CompletionRuleVariableVariableValueFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CompletionRuleVariableVariableValueField>> {

    public CompletionRuleVariableVariableValueFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CompletionRuleVariableVariableValueField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CompletionRuleVariableVariableValueField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CompletionRuleVariableVariableValueField>(value));
    }
  }

  public static class CompletionRuleVariableVariableValueFieldSerializer
      extends JsonSerializer<EnumWrapper<CompletionRuleVariableVariableValueField>> {

    public CompletionRuleVariableVariableValueFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CompletionRuleVariableVariableValueField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
