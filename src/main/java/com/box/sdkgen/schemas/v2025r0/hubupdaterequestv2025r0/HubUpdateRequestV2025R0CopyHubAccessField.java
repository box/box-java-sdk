package com.box.sdkgen.schemas.v2025r0.hubupdaterequestv2025r0;

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

public enum HubUpdateRequestV2025R0CopyHubAccessField implements Valuable {
  ALL("all"),
  COMPANY("company"),
  NONE("none");

  private final String value;

  HubUpdateRequestV2025R0CopyHubAccessField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class HubUpdateRequestV2025R0CopyHubAccessFieldDeserializer
      extends JsonDeserializer<EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField>> {

    public HubUpdateRequestV2025R0CopyHubAccessFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(HubUpdateRequestV2025R0CopyHubAccessField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField>(value));
    }
  }

  public static class HubUpdateRequestV2025R0CopyHubAccessFieldSerializer
      extends JsonSerializer<EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField>> {

    public HubUpdateRequestV2025R0CopyHubAccessFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<HubUpdateRequestV2025R0CopyHubAccessField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
