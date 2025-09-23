package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionbase;

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

public enum ShieldInformationBarrierSegmentRestrictionBaseTypeField implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT_RESTRICTION("shield_information_barrier_segment_restriction");

  private final String value;

  ShieldInformationBarrierSegmentRestrictionBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ShieldInformationBarrierSegmentRestrictionBaseTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField>> {

    public ShieldInformationBarrierSegmentRestrictionBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShieldInformationBarrierSegmentRestrictionBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField>(value));
    }
  }

  public static class ShieldInformationBarrierSegmentRestrictionBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField>> {

    public ShieldInformationBarrierSegmentRestrictionBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShieldInformationBarrierSegmentRestrictionBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
