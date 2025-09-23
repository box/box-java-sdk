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

public enum ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField
    implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT("shield_information_barrier_segment");

  private final String value;

  ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static
  class ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>> {

    public ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>
        deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(
              ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<
                  ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>(value));
    }
  }

  public static
  class ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField>> {

    public ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShieldInformationBarrierSegmentRestrictionMiniRestrictedSegmentTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
