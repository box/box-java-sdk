package com.box.sdkgen.schemas.shieldinformationbarriersegment;

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

public enum ShieldInformationBarrierSegmentTypeField implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT("shield_information_barrier_segment");

  private final String value;

  ShieldInformationBarrierSegmentTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ShieldInformationBarrierSegmentTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShieldInformationBarrierSegmentTypeField>> {

    public ShieldInformationBarrierSegmentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShieldInformationBarrierSegmentTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShieldInformationBarrierSegmentTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShieldInformationBarrierSegmentTypeField>(value));
    }
  }

  public static class ShieldInformationBarrierSegmentTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShieldInformationBarrierSegmentTypeField>> {

    public ShieldInformationBarrierSegmentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShieldInformationBarrierSegmentTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
