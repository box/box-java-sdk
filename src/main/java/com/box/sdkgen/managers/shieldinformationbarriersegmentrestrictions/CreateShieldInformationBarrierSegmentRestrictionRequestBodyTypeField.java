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

public enum CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField
    implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT_RESTRICTION("shield_information_barrier_segment_restriction");

  private final String value;

  CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static
  class CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField>> {

    public CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField>
        deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(
              CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField>(
                  value));
    }
  }

  public static class CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField>> {

    public CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
