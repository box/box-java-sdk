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

public enum WorkflowFlowsOutcomesIfRejectedTypeField implements Valuable {
  OUTCOME("outcome");

  private final String value;

  WorkflowFlowsOutcomesIfRejectedTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WorkflowFlowsOutcomesIfRejectedTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField>> {

    public WorkflowFlowsOutcomesIfRejectedTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WorkflowFlowsOutcomesIfRejectedTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField>(value));
    }
  }

  public static class WorkflowFlowsOutcomesIfRejectedTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField>> {

    public WorkflowFlowsOutcomesIfRejectedTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
