package com.box.sdkgen.managers.filewatermarks;

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

public enum UpdateFileWatermarkRequestBodyWatermarkImprintField implements Valuable {
  DEFAULT("default");

  private final String value;

  UpdateFileWatermarkRequestBodyWatermarkImprintField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateFileWatermarkRequestBodyWatermarkImprintFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField>> {

    public UpdateFileWatermarkRequestBodyWatermarkImprintFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateFileWatermarkRequestBodyWatermarkImprintField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField>(value));
    }
  }

  public static class UpdateFileWatermarkRequestBodyWatermarkImprintFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField>> {

    public UpdateFileWatermarkRequestBodyWatermarkImprintFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateFileWatermarkRequestBodyWatermarkImprintField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
