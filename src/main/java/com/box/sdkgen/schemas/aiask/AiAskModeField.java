package com.box.sdkgen.schemas.aiask;

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

public enum AiAskModeField implements Valuable {
  MULTIPLE_ITEM_QA("multiple_item_qa"),
  SINGLE_ITEM_QA("single_item_qa");

  private final String value;

  AiAskModeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AiAskModeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AiAskModeField>> {

    public AiAskModeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AiAskModeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AiAskModeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AiAskModeField>(value));
    }
  }

  public static class AiAskModeFieldSerializer extends JsonSerializer<EnumWrapper<AiAskModeField>> {

    public AiAskModeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AiAskModeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
