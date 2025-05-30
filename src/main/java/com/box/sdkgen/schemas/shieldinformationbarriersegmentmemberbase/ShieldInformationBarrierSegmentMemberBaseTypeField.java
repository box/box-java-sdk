package com.box.sdkgen.schemas.shieldinformationbarriersegmentmemberbase;

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

public enum ShieldInformationBarrierSegmentMemberBaseTypeField implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT_MEMBER("shield_information_barrier_segment_member");

  private final String value;

  ShieldInformationBarrierSegmentMemberBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ShieldInformationBarrierSegmentMemberBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField>> {

    public ShieldInformationBarrierSegmentMemberBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShieldInformationBarrierSegmentMemberBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField>(value));
    }
  }

  public static class ShieldInformationBarrierSegmentMemberBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField>> {

    public ShieldInformationBarrierSegmentMemberBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShieldInformationBarrierSegmentMemberBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
