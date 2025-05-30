package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

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
    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField
    implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT("shield_information_barrier_segment");

  private final String value;

  CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField(
      String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static
  class CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<
              CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>> {

    public
    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<
            CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>
        deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(
              CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField
                  .values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<
                  CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>(
                  value));
    }
  }

  public static
  class CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<
              CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>> {

    public
    CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<
                CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField>
            value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
