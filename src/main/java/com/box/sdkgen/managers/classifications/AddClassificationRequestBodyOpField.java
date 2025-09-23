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

public enum AddClassificationRequestBodyOpField implements Valuable {
  ADDENUMOPTION("addEnumOption");

  private final String value;

  AddClassificationRequestBodyOpField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AddClassificationRequestBodyOpFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AddClassificationRequestBodyOpField>> {

    public AddClassificationRequestBodyOpFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AddClassificationRequestBodyOpField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AddClassificationRequestBodyOpField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AddClassificationRequestBodyOpField>(value));
    }
  }

  public static class AddClassificationRequestBodyOpFieldSerializer
      extends JsonSerializer<EnumWrapper<AddClassificationRequestBodyOpField>> {

    public AddClassificationRequestBodyOpFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AddClassificationRequestBodyOpField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
