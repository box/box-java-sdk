package com.box.sdkgen.schemas.sharedlinkotpwritableopen;

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

public enum SharedLinkOtpWritableOpenModeField implements Valuable {
  OPEN("open");

  private final String value;

  SharedLinkOtpWritableOpenModeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class SharedLinkOtpWritableOpenModeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SharedLinkOtpWritableOpenModeField>> {

    public SharedLinkOtpWritableOpenModeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SharedLinkOtpWritableOpenModeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SharedLinkOtpWritableOpenModeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SharedLinkOtpWritableOpenModeField>(value));
    }
  }

  public static class SharedLinkOtpWritableOpenModeFieldSerializer
      extends JsonSerializer<EnumWrapper<SharedLinkOtpWritableOpenModeField>> {

    public SharedLinkOtpWritableOpenModeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SharedLinkOtpWritableOpenModeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
