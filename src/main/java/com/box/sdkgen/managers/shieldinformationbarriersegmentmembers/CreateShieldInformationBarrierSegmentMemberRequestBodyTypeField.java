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

public enum CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField implements Valuable {
  SHIELD_INFORMATION_BARRIER_SEGMENT_MEMBER("shield_information_barrier_segment_member");

  private final String value;

  CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CreateShieldInformationBarrierSegmentMemberRequestBodyTypeFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField>> {

    public CreateShieldInformationBarrierSegmentMemberRequestBodyTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField>(
                  value));
    }
  }

  public static class CreateShieldInformationBarrierSegmentMemberRequestBodyTypeFieldSerializer
      extends JsonSerializer<
          EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField>> {

    public CreateShieldInformationBarrierSegmentMemberRequestBodyTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CreateShieldInformationBarrierSegmentMemberRequestBodyTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
