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

public enum WorkflowFlowsTriggerScopeTypeField implements Valuable {
  TRIGGER_SCOPE("trigger_scope");

  private final String value;

  WorkflowFlowsTriggerScopeTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WorkflowFlowsTriggerScopeTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WorkflowFlowsTriggerScopeTypeField>> {

    public WorkflowFlowsTriggerScopeTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WorkflowFlowsTriggerScopeTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WorkflowFlowsTriggerScopeTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WorkflowFlowsTriggerScopeTypeField>(value));
    }
  }

  public static class WorkflowFlowsTriggerScopeTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WorkflowFlowsTriggerScopeTypeField>> {

    public WorkflowFlowsTriggerScopeTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WorkflowFlowsTriggerScopeTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
