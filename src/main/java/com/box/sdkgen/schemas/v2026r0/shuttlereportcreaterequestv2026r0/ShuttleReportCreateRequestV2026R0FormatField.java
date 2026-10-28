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

public enum ShuttleReportCreateRequestV2026R0FormatField implements Valuable {
  XLSX("xlsx"),
  CSV("csv");

  private final String value;

  ShuttleReportCreateRequestV2026R0FormatField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class ShuttleReportCreateRequestV2026R0FormatFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField>> {

    public ShuttleReportCreateRequestV2026R0FormatFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShuttleReportCreateRequestV2026R0FormatField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField>(value));
    }
  }

  public static class ShuttleReportCreateRequestV2026R0FormatFieldSerializer
      extends JsonSerializer<EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField>> {

    public ShuttleReportCreateRequestV2026R0FormatFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShuttleReportCreateRequestV2026R0FormatField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
