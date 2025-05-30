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

public enum CompletionRuleVariableTypeField implements Valuable {
  VARIABLE("variable");

  private final String value;

  CompletionRuleVariableTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CompletionRuleVariableTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CompletionRuleVariableTypeField>> {

    public CompletionRuleVariableTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CompletionRuleVariableTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CompletionRuleVariableTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CompletionRuleVariableTypeField>(value));
    }
  }

  public static class CompletionRuleVariableTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CompletionRuleVariableTypeField>> {

    public CompletionRuleVariableTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CompletionRuleVariableTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
