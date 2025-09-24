package com.box.sdkgen.managers.shieldinformationbarriers;

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

public enum UpdateShieldInformationBarrierStatusRequestBodyStatusField implements Valuable {
  PENDING("pending"),
  DISABLED("disabled");

  private final String value;

  UpdateShieldInformationBarrierStatusRequestBodyStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class UpdateShieldInformationBarrierStatusRequestBodyStatusFieldDeserializer
      extends JsonDeserializer<
          EnumWrapper<UpdateShieldInformationBarrierStatusRequestBodyStatusField>> {

    public UpdateShieldInformationBarrierStatusRequestBodyStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<UpdateShieldInformationBarrierStatusRequestBodyStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(UpdateShieldInformationBarrierStatusRequestBodyStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(
              new EnumWrapper<UpdateShieldInformationBarrierStatusRequestBodyStatusField>(value));
    }
  }

  public static class UpdateShieldInformationBarrierStatusRequestBodyStatusFieldSerializer
      extends JsonSerializer<
          EnumWrapper<UpdateShieldInformationBarrierStatusRequestBodyStatusField>> {

    public UpdateShieldInformationBarrierStatusRequestBodyStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<UpdateShieldInformationBarrierStatusRequestBodyStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
