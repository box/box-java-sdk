package com.box.sdkgen.schemas.devicepinner;

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

public enum DevicePinnerTypeField implements Valuable {
  DEVICE_PINNER("device_pinner");

  private final String value;

  DevicePinnerTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class DevicePinnerTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<DevicePinnerTypeField>> {

    public DevicePinnerTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<DevicePinnerTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(DevicePinnerTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<DevicePinnerTypeField>(value));
    }
  }

  public static class DevicePinnerTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<DevicePinnerTypeField>> {

    public DevicePinnerTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<DevicePinnerTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
