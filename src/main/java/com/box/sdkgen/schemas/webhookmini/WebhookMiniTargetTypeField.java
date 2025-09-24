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

public enum WebhookMiniTargetTypeField implements Valuable {
  FILE("file"),
  FOLDER("folder");

  private final String value;

  WebhookMiniTargetTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class WebhookMiniTargetTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<WebhookMiniTargetTypeField>> {

    public WebhookMiniTargetTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<WebhookMiniTargetTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(WebhookMiniTargetTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<WebhookMiniTargetTypeField>(value));
    }
  }

  public static class WebhookMiniTargetTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<WebhookMiniTargetTypeField>> {

    public WebhookMiniTargetTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<WebhookMiniTargetTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
