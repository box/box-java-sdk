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

public enum StartWorkflowRequestBodyFolderTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  StartWorkflowRequestBodyFolderTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class StartWorkflowRequestBodyFolderTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<StartWorkflowRequestBodyFolderTypeField>> {

    public StartWorkflowRequestBodyFolderTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<StartWorkflowRequestBodyFolderTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(StartWorkflowRequestBodyFolderTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<StartWorkflowRequestBodyFolderTypeField>(value));
    }
  }

  public static class StartWorkflowRequestBodyFolderTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<StartWorkflowRequestBodyFolderTypeField>> {

    public StartWorkflowRequestBodyFolderTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<StartWorkflowRequestBodyFolderTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
