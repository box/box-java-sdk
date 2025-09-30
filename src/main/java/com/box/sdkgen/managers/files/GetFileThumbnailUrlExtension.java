package com.box.sdkgen.managers.files;

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

public enum GetFileThumbnailUrlExtension implements Valuable {
  PNG("png"),
  JPG("jpg");

  private final String value;

  GetFileThumbnailUrlExtension(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetFileThumbnailUrlExtensionDeserializer
      extends JsonDeserializer<EnumWrapper<GetFileThumbnailUrlExtension>> {

    public GetFileThumbnailUrlExtensionDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetFileThumbnailUrlExtension> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetFileThumbnailUrlExtension.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetFileThumbnailUrlExtension>(value));
    }
  }

  public static class GetFileThumbnailUrlExtensionSerializer
      extends JsonSerializer<EnumWrapper<GetFileThumbnailUrlExtension>> {

    public GetFileThumbnailUrlExtensionSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetFileThumbnailUrlExtension> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
