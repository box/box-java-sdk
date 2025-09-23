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

public enum UpdateClassificationRequestBodyFieldKeyField implements Valuable {
  BOX__SECURITY__CLASSIFICATION__KEY("Box__Security__Classification__Key");

  private final String value;

  UpdateClassificationRequestBodyFieldKeyField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateClassificationRequestBodyFieldKeyFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateClassificationRequestBodyFieldKeyField>> {

    public UpdateClassificationRequestBodyFieldKeyFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateClassificationRequestBodyFieldKeyField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateClassificationRequestBodyFieldKeyField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateClassificationRequestBodyFieldKeyField>(value));
    }
  }

  public static class UpdateClassificationRequestBodyFieldKeyFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateClassificationRequestBodyFieldKeyField>> {

    public UpdateClassificationRequestBodyFieldKeyFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateClassificationRequestBodyFieldKeyField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
