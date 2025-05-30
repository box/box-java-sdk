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

public enum WorkflowFlowsTriggerScopeObjectTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  WorkflowFlowsTriggerScopeObjectTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WorkflowFlowsTriggerScopeObjectTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField>> {

    public WorkflowFlowsTriggerScopeObjectTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WorkflowFlowsTriggerScopeObjectTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField>(value));
    }
  }

  public static class WorkflowFlowsTriggerScopeObjectTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField>> {

    public WorkflowFlowsTriggerScopeObjectTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
