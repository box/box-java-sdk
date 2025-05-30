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

public enum WorkflowFlowsTriggerTypeField implements Valuable {
  TRIGGER("trigger");

  private final String value;

  WorkflowFlowsTriggerTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WorkflowFlowsTriggerTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WorkflowFlowsTriggerTypeField>> {

    public WorkflowFlowsTriggerTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WorkflowFlowsTriggerTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WorkflowFlowsTriggerTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WorkflowFlowsTriggerTypeField>(value));
    }
  }

  public static class WorkflowFlowsTriggerTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WorkflowFlowsTriggerTypeField>> {

    public WorkflowFlowsTriggerTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WorkflowFlowsTriggerTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
