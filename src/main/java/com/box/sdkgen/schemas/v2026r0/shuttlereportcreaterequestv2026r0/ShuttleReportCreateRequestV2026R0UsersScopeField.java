package com.box.sdkgen.schemas.v2026r0.shuttlereportcreaterequestv2026r0;

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

public enum ShuttleReportCreateRequestV2026R0UsersScopeField implements Valuable {
  MIGRATED("migrated"),
  ALL("all");

  private final String value;

  ShuttleReportCreateRequestV2026R0UsersScopeField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleReportCreateRequestV2026R0UsersScopeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField>> {

    public ShuttleReportCreateRequestV2026R0UsersScopeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleReportCreateRequestV2026R0UsersScopeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField>(value));
    }
  }

  public static class ShuttleReportCreateRequestV2026R0UsersScopeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField>> {

    public ShuttleReportCreateRequestV2026R0UsersScopeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleReportCreateRequestV2026R0UsersScopeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
