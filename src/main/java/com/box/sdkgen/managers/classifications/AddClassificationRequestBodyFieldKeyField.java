package com.box.sdkgen.managers.classifications;

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

public enum AddClassificationRequestBodyFieldKeyField implements Valuable {
  BOX__SECURITY__CLASSIFICATION__KEY("Box__Security__Classification__Key");

  private final String value;

  AddClassificationRequestBodyFieldKeyField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AddClassificationRequestBodyFieldKeyFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AddClassificationRequestBodyFieldKeyField>> {

    public AddClassificationRequestBodyFieldKeyFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AddClassificationRequestBodyFieldKeyField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AddClassificationRequestBodyFieldKeyField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AddClassificationRequestBodyFieldKeyField>(value));
    }
  }

  public static class AddClassificationRequestBodyFieldKeyFieldSerializer
      extends JsonSerializer<EnumWrapper<AddClassificationRequestBodyFieldKeyField>> {

    public AddClassificationRequestBodyFieldKeyFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AddClassificationRequestBodyFieldKeyField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
