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

public enum GetFileThumbnailByIdExtension implements Valuable {
  PNG("png"),
  JPG("jpg");

  private final String value;

  GetFileThumbnailByIdExtension(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetFileThumbnailByIdExtensionDeserializer
      extends JsonDeserializer<EnumWrapper<GetFileThumbnailByIdExtension>> {

    public GetFileThumbnailByIdExtensionDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetFileThumbnailByIdExtension> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetFileThumbnailByIdExtension.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetFileThumbnailByIdExtension>(value));
    }
  }

  public static class GetFileThumbnailByIdExtensionSerializer
      extends JsonSerializer<EnumWrapper<GetFileThumbnailByIdExtension>> {

    public GetFileThumbnailByIdExtensionSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetFileThumbnailByIdExtension> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
