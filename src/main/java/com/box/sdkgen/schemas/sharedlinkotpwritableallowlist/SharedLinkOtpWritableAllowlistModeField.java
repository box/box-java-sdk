package com.box.sdkgen.schemas.sharedlinkotpwritableallowlist;

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

public enum SharedLinkOtpWritableAllowlistModeField implements Valuable {
  ALLOWLIST("allowlist");

  private final String value;

  SharedLinkOtpWritableAllowlistModeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class SharedLinkOtpWritableAllowlistModeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SharedLinkOtpWritableAllowlistModeField>> {

    public SharedLinkOtpWritableAllowlistModeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SharedLinkOtpWritableAllowlistModeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SharedLinkOtpWritableAllowlistModeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SharedLinkOtpWritableAllowlistModeField>(value));
    }
  }

  public static class SharedLinkOtpWritableAllowlistModeFieldSerializer
      extends JsonSerializer<EnumWrapper<SharedLinkOtpWritableAllowlistModeField>> {

    public SharedLinkOtpWritableAllowlistModeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SharedLinkOtpWritableAllowlistModeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
