package com.box.sdkgen.networking.fetchoptions;

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

public enum ResponseFormat implements Valuable {
  JSON("json"),
  BINARY("binary"),
  NO_CONTENT("no_content");

  private final String value;

  ResponseFormat(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ResponseFormatDeserializer
      extends JsonDeserializer<EnumWrapper<ResponseFormat>> {

    public ResponseFormatDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ResponseFormat> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ResponseFormat.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ResponseFormat>(value));
    }
  }

  public static class ResponseFormatSerializer extends JsonSerializer<EnumWrapper<ResponseFormat>> {

    public ResponseFormatSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ResponseFormat> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
