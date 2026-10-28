package com.box.sdkgen.schemas.sharedlinkotpwritablenone;

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

public enum SharedLinkOtpWritableNoneModeField implements Valuable {
  NONE("none");

  private final String value;

  SharedLinkOtpWritableNoneModeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class SharedLinkOtpWritableNoneModeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SharedLinkOtpWritableNoneModeField>> {

    public SharedLinkOtpWritableNoneModeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SharedLinkOtpWritableNoneModeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SharedLinkOtpWritableNoneModeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SharedLinkOtpWritableNoneModeField>(value));
    }
  }

  public static class SharedLinkOtpWritableNoneModeFieldSerializer
      extends JsonSerializer<EnumWrapper<SharedLinkOtpWritableNoneModeField>> {

    public SharedLinkOtpWritableNoneModeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SharedLinkOtpWritableNoneModeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
