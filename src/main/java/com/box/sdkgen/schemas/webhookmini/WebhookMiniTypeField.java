package com.box.sdkgen.schemas.webhookmini;

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

public enum WebhookMiniTypeField implements Valuable {
  WEBHOOK("webhook");

  private final String value;

  WebhookMiniTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WebhookMiniTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WebhookMiniTypeField>> {

    public WebhookMiniTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WebhookMiniTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WebhookMiniTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WebhookMiniTypeField>(value));
    }
  }

  public static class WebhookMiniTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WebhookMiniTypeField>> {

    public WebhookMiniTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WebhookMiniTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
