package com.box.sdkgen.schemas.workflowmini;

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

public enum WorkflowMiniTypeField implements Valuable {
  WORKFLOW("workflow");

  private final String value;

  WorkflowMiniTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WorkflowMiniTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WorkflowMiniTypeField>> {

    public WorkflowMiniTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WorkflowMiniTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WorkflowMiniTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WorkflowMiniTypeField>(value));
    }
  }

  public static class WorkflowMiniTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WorkflowMiniTypeField>> {

    public WorkflowMiniTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WorkflowMiniTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
