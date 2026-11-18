package com.box.sdkgen.schemas.v2026r0.aisessionv2026r0;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum AiSessionV2026R0SourceField implements Valuable {
  API("api");

  private final String value;

  AiSessionV2026R0SourceField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class AiSessionV2026R0SourceFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiSessionV2026R0SourceField>> {

    public AiSessionV2026R0SourceFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiSessionV2026R0SourceField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiSessionV2026R0SourceField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiSessionV2026R0SourceField>(value));
    }
  }

  public static class AiSessionV2026R0SourceFieldSerializer
      extends JsonSerializer<EnumWrapper<AiSessionV2026R0SourceField>> {

    public AiSessionV2026R0SourceFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiSessionV2026R0SourceField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
