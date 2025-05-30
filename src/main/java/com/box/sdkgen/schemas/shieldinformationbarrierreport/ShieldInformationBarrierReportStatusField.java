package com.box.sdkgen.schemas.shieldinformationbarrierreport;

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

public enum ShieldInformationBarrierReportStatusField implements Valuable {
  PENDING("pending"),
  ERROR("error"),
  DONE("done"),
  CANCELLED("cancelled");

  private final String value;

  ShieldInformationBarrierReportStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ShieldInformationBarrierReportStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShieldInformationBarrierReportStatusField>> {

    public ShieldInformationBarrierReportStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShieldInformationBarrierReportStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShieldInformationBarrierReportStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShieldInformationBarrierReportStatusField>(value));
    }
  }

  public static class ShieldInformationBarrierReportStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<ShieldInformationBarrierReportStatusField>> {

    public ShieldInformationBarrierReportStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShieldInformationBarrierReportStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
