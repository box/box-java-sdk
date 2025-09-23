package com.box.sdkgen.managers.workflows;

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

public enum StartWorkflowRequestBodyTypeField implements Valuable {
  WORKFLOW_PARAMETERS("workflow_parameters");

  private final String value;

  StartWorkflowRequestBodyTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class StartWorkflowRequestBodyTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<StartWorkflowRequestBodyTypeField>> {

    public StartWorkflowRequestBodyTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<StartWorkflowRequestBodyTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(StartWorkflowRequestBodyTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<StartWorkflowRequestBodyTypeField>(value));
    }
  }

  public static class StartWorkflowRequestBodyTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<StartWorkflowRequestBodyTypeField>> {

    public StartWorkflowRequestBodyTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<StartWorkflowRequestBodyTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
