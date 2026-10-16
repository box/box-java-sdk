package com.box.sdkgen.schemas.sharedlinkotpallowlistentrywritable;

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

public enum SharedLinkOtpAllowlistEntryWritableTypeField implements Valuable {
  EMAIL("email"),
  DOMAIN("domain");

  private final String value;

  SharedLinkOtpAllowlistEntryWritableTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class SharedLinkOtpAllowlistEntryWritableTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SharedLinkOtpAllowlistEntryWritableTypeField>> {

    public SharedLinkOtpAllowlistEntryWritableTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SharedLinkOtpAllowlistEntryWritableTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SharedLinkOtpAllowlistEntryWritableTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SharedLinkOtpAllowlistEntryWritableTypeField>(value));
    }
  }

  public static class SharedLinkOtpAllowlistEntryWritableTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SharedLinkOtpAllowlistEntryWritableTypeField>> {

    public SharedLinkOtpAllowlistEntryWritableTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SharedLinkOtpAllowlistEntryWritableTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
