package com.box.sdkgen.schemas.shieldinformationbarrierbase;

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

public enum ShieldInformationBarrierBaseTypeField implements Valuable {
  SHIELD_INFORMATION_BARRIER("shield_information_barrier");

  private final String value;

  ShieldInformationBarrierBaseTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ShieldInformationBarrierBaseTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ShieldInformationBarrierBaseTypeField>> {

    public ShieldInformationBarrierBaseTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ShieldInformationBarrierBaseTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ShieldInformationBarrierBaseTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ShieldInformationBarrierBaseTypeField>(value));
    }
  }

  public static class ShieldInformationBarrierBaseTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<ShieldInformationBarrierBaseTypeField>> {

    public ShieldInformationBarrierBaseTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ShieldInformationBarrierBaseTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
