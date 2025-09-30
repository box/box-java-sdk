package com.box.sdkgen.managers.folderwatermarks;

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

public enum UpdateFolderWatermarkRequestBodyWatermarkImprintField implements Valuable {
  DEFAULT("default");

  private final String value;

  UpdateFolderWatermarkRequestBodyWatermarkImprintField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateFolderWatermarkRequestBodyWatermarkImprintFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField>> {

    public UpdateFolderWatermarkRequestBodyWatermarkImprintFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateFolderWatermarkRequestBodyWatermarkImprintField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField>(value));
    }
  }

  public static class UpdateFolderWatermarkRequestBodyWatermarkImprintFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField>> {

    public UpdateFolderWatermarkRequestBodyWatermarkImprintFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateFolderWatermarkRequestBodyWatermarkImprintField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
