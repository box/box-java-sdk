package com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictionmini;

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

public enum ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField
    implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT("shield_information_barrier_segment");

  private final String value;

  ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField(
      String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static
  class ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<
              ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>> {

    public
    ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<
            ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>
        deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(
              ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField
                  .values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<
                  ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>(
                  value));
    }
  }

  public static
  class ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<
              ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>> {

    public
    ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<
                ShieldInformationBarrierSegmentRestrictionMiniShieldInformationBarrierSegmentTypeField>
            value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
