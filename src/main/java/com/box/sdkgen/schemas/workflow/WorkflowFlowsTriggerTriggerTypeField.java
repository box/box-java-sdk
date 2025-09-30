package com.box.sdkgen.schemas.workflow;

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

public enum WorkflowFlowsTriggerTriggerTypeField implements Valuable {
  WORKFLOW_MANUAL_START("WORKFLOW_MANUAL_START");

  private final String value;

  WorkflowFlowsTriggerTriggerTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WorkflowFlowsTriggerTriggerTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WorkflowFlowsTriggerTriggerTypeField>> {

    public WorkflowFlowsTriggerTriggerTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WorkflowFlowsTriggerTriggerTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WorkflowFlowsTriggerTriggerTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WorkflowFlowsTriggerTriggerTypeField>(value));
    }
  }

  public static class WorkflowFlowsTriggerTriggerTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WorkflowFlowsTriggerTriggerTypeField>> {

    public WorkflowFlowsTriggerTriggerTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WorkflowFlowsTriggerTriggerTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
