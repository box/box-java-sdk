package com.box.sdkgen.schemas.sharedlinkotpallowlistentry;

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

public enum SharedLinkOtpAllowlistEntryTypeField implements Valuable {
  EMAIL("email"),
  DOMAIN("domain");

  private final String value;

  SharedLinkOtpAllowlistEntryTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class SharedLinkOtpAllowlistEntryTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SharedLinkOtpAllowlistEntryTypeField>> {

    public SharedLinkOtpAllowlistEntryTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SharedLinkOtpAllowlistEntryTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SharedLinkOtpAllowlistEntryTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SharedLinkOtpAllowlistEntryTypeField>(value));
    }
  }

  public static class SharedLinkOtpAllowlistEntryTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SharedLinkOtpAllowlistEntryTypeField>> {

    public SharedLinkOtpAllowlistEntryTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SharedLinkOtpAllowlistEntryTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
