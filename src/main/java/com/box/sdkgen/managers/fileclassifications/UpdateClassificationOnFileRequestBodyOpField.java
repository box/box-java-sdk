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

public enum UpdateClassificationOnFileRequestBodyOpField implements Valuable {
  REPLACE("replace");

  private final String value;

  UpdateClassificationOnFileRequestBodyOpField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateClassificationOnFileRequestBodyOpFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateClassificationOnFileRequestBodyOpField>> {

    public UpdateClassificationOnFileRequestBodyOpFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateClassificationOnFileRequestBodyOpField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateClassificationOnFileRequestBodyOpField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateClassificationOnFileRequestBodyOpField>(value));
    }
  }

  public static class UpdateClassificationOnFileRequestBodyOpFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateClassificationOnFileRequestBodyOpField>> {

    public UpdateClassificationOnFileRequestBodyOpFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateClassificationOnFileRequestBodyOpField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
