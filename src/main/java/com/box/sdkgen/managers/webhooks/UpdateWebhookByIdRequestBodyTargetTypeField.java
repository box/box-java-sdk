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

public enum UpdateWebhookByIdRequestBodyTargetTypeField implements Valuable {
  FILE("file"),
  FOLDER("folder");

  private final String value;

  UpdateWebhookByIdRequestBodyTargetTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateWebhookByIdRequestBodyTargetTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField>> {

    public UpdateWebhookByIdRequestBodyTargetTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateWebhookByIdRequestBodyTargetTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField>(value));
    }
  }

  public static class UpdateWebhookByIdRequestBodyTargetTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField>> {

    public UpdateWebhookByIdRequestBodyTargetTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateWebhookByIdRequestBodyTargetTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
