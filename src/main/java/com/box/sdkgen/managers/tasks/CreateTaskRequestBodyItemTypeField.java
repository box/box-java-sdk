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

public enum CreateTaskRequestBodyItemTypeField implements Valuable {
  FILE("file");

  private final String value;

  CreateTaskRequestBodyItemTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateTaskRequestBodyItemTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateTaskRequestBodyItemTypeField>> {

    public CreateTaskRequestBodyItemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateTaskRequestBodyItemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateTaskRequestBodyItemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateTaskRequestBodyItemTypeField>(value));
    }
  }

  public static class CreateTaskRequestBodyItemTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateTaskRequestBodyItemTypeField>> {

    public CreateTaskRequestBodyItemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateTaskRequestBodyItemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
