package com.box.sdkgen.managers.webhooks;

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

public enum CreateWebhookRequestBodyTargetTypeField implements Valuable {
  FILE("file"),
  FOLDER("folder");

  private final String value;

  CreateWebhookRequestBodyTargetTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateWebhookRequestBodyTargetTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CreateWebhookRequestBodyTargetTypeField>> {

    public CreateWebhookRequestBodyTargetTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateWebhookRequestBodyTargetTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateWebhookRequestBodyTargetTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CreateWebhookRequestBodyTargetTypeField>(value));
    }
  }

  public static class CreateWebhookRequestBodyTargetTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CreateWebhookRequestBodyTargetTypeField>> {

    public CreateWebhookRequestBodyTargetTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateWebhookRequestBodyTargetTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
