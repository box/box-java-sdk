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

public enum UpdateClassificationRequestBodyOpField implements Valuable {
  EDITENUMOPTION("editEnumOption");

  private final String value;

  UpdateClassificationRequestBodyOpField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateClassificationRequestBodyOpFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateClassificationRequestBodyOpField>> {

    public UpdateClassificationRequestBodyOpFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateClassificationRequestBodyOpField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateClassificationRequestBodyOpField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateClassificationRequestBodyOpField>(value));
    }
  }

  public static class UpdateClassificationRequestBodyOpFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateClassificationRequestBodyOpField>> {

    public UpdateClassificationRequestBodyOpFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateClassificationRequestBodyOpField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
