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

public enum
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField
    implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT("shield_information_barrier_segment");

  private final String value;

  CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField(
      String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static
  class CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>> {

    public
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<
            CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>
        deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField
                  .values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<
                  CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>(
                  value));
    }
  }

  public static
  class CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>> {

    public
    CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<
                CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField>
            value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
