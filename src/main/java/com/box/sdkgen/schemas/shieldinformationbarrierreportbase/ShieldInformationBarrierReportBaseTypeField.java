package com.box.sdkgen.schemas.shieldinformationbarrierreportbase;

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

public enum ShieldInformationBarrierReportBaseTypeField implements Valuable {
  SHIELD_INFORMATION_BARRIER_REPORT("shield_information_barrier_report");

  private final String value;

  ShieldInformationBarrierReportBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ShieldInformationBarrierReportBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShieldInformationBarrierReportBaseTypeField>> {

    public ShieldInformationBarrierReportBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShieldInformationBarrierReportBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShieldInformationBarrierReportBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShieldInformationBarrierReportBaseTypeField>(value));
    }
  }

  public static class ShieldInformationBarrierReportBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShieldInformationBarrierReportBaseTypeField>> {

    public ShieldInformationBarrierReportBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShieldInformationBarrierReportBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
