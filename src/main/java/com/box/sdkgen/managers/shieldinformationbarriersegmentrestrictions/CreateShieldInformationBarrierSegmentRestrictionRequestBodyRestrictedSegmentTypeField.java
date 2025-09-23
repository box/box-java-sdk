package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

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

public enum CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField
    implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT("shield_information_barrier_segment");

  private final String value;

  CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField(
      String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static
  class CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>> {

    public
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<
            CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>
        deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField
                  .values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<
                  CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>(
                  value));
    }
  }

  public static
  class CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>> {

    public
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<
                CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField>
            value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
