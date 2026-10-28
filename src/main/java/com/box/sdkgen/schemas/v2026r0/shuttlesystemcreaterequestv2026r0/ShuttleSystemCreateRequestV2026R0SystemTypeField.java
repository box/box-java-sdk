package com.box.sdkgen.schemas.v2026r0.shuttlesystemcreaterequestv2026r0;

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

public enum ShuttleSystemCreateRequestV2026R0SystemTypeField implements Valuable {
  WINDOWS("windows");

  private final String value;

  ShuttleSystemCreateRequestV2026R0SystemTypeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleSystemCreateRequestV2026R0SystemTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField>> {

    public ShuttleSystemCreateRequestV2026R0SystemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleSystemCreateRequestV2026R0SystemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField>(value));
    }
  }

  public static class ShuttleSystemCreateRequestV2026R0SystemTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField>> {

    public ShuttleSystemCreateRequestV2026R0SystemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleSystemCreateRequestV2026R0SystemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
