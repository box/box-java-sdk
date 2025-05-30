package com.box.sdkgen.managers.fileclassifications;

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

public enum UpdateClassificationOnFileRequestBodyPathField implements Valuable {
  _BOX__SECURITY__CLASSIFICATION__KEY("/Box__Security__Classification__Key");

  private final String value;

  UpdateClassificationOnFileRequestBodyPathField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateClassificationOnFileRequestBodyPathFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateClassificationOnFileRequestBodyPathField>> {

    public UpdateClassificationOnFileRequestBodyPathFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateClassificationOnFileRequestBodyPathField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateClassificationOnFileRequestBodyPathField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateClassificationOnFileRequestBodyPathField>(value));
    }
  }

  public static class UpdateClassificationOnFileRequestBodyPathFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateClassificationOnFileRequestBodyPathField>> {

    public UpdateClassificationOnFileRequestBodyPathFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateClassificationOnFileRequestBodyPathField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
