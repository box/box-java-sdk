package com.box.sdkgen.schemas.devicepinners;

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

public enum DevicePinnersOrderDirectionField implements Valuable {
  ASC("asc"),
  DESC("desc");

  private final String value;

  DevicePinnersOrderDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class DevicePinnersOrderDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<DevicePinnersOrderDirectionField>> {

    public DevicePinnersOrderDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<DevicePinnersOrderDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(DevicePinnersOrderDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<DevicePinnersOrderDirectionField>(value));
    }
  }

  public static class DevicePinnersOrderDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<DevicePinnersOrderDirectionField>> {

    public DevicePinnersOrderDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<DevicePinnersOrderDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
