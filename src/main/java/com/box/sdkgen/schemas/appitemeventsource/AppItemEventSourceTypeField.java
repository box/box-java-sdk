package com.box.sdkgen.schemas.appitemeventsource;

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

public enum AppItemEventSourceTypeField implements Valuable {
  APP_ITEM("app_item");

  private final String value;

  AppItemEventSourceTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class AppItemEventSourceTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<AppItemEventSourceTypeField>> {

    public AppItemEventSourceTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<AppItemEventSourceTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(AppItemEventSourceTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<AppItemEventSourceTypeField>(value));
    }
  }

  public static class AppItemEventSourceTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<AppItemEventSourceTypeField>> {

    public AppItemEventSourceTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<AppItemEventSourceTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
