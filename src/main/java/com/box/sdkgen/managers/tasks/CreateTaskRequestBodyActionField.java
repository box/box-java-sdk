package com.box.sdkgen.managers.tasks;

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

public enum CreateTaskRequestBodyActionField implements Valuable {
  REVIEW("review"),
  COMPLETE("complete");

  private final String value;

  CreateTaskRequestBodyActionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateTaskRequestBodyActionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateTaskRequestBodyActionField>> {

    public CreateTaskRequestBodyActionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateTaskRequestBodyActionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateTaskRequestBodyActionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateTaskRequestBodyActionField>(value));
    }
  }

  public static class CreateTaskRequestBodyActionFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateTaskRequestBodyActionField>> {

    public CreateTaskRequestBodyActionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateTaskRequestBodyActionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
