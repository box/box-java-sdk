package com.box.sdkgen.schemas.v2026r0.automateworkflowactionv2026r0;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum AutomateWorkflowActionV2026R0TypeField implements Valuable {
  WORKFLOW_ACTION("workflow_action");

  private final String value;

  AutomateWorkflowActionV2026R0TypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class AutomateWorkflowActionV2026R0TypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AutomateWorkflowActionV2026R0TypeField>> {

    public AutomateWorkflowActionV2026R0TypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AutomateWorkflowActionV2026R0TypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AutomateWorkflowActionV2026R0TypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AutomateWorkflowActionV2026R0TypeField>(value));
    }
  }

  public static class AutomateWorkflowActionV2026R0TypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AutomateWorkflowActionV2026R0TypeField>> {

    public AutomateWorkflowActionV2026R0TypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AutomateWorkflowActionV2026R0TypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
