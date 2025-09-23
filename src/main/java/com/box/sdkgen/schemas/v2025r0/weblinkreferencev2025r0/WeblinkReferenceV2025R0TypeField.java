package com.box.sdkgen.schemas.v2025r0.weblinkreferencev2025r0;

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

public enum WeblinkReferenceV2025R0TypeField implements Valuable {
  WEBLINK("weblink");

  private final String value;

  WeblinkReferenceV2025R0TypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WeblinkReferenceV2025R0TypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WeblinkReferenceV2025R0TypeField>> {

    public WeblinkReferenceV2025R0TypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WeblinkReferenceV2025R0TypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WeblinkReferenceV2025R0TypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WeblinkReferenceV2025R0TypeField>(value));
    }
  }

  public static class WeblinkReferenceV2025R0TypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WeblinkReferenceV2025R0TypeField>> {

    public WeblinkReferenceV2025R0TypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WeblinkReferenceV2025R0TypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
