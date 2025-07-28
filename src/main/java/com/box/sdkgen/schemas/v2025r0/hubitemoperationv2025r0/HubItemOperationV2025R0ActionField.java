package com.box.sdkgen.schemas.v2025r0.hubitemoperationv2025r0;

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

public enum HubItemOperationV2025R0ActionField implements Valuable {
  ADD("add"),
  REMOVE("remove");

  private final String value;

  HubItemOperationV2025R0ActionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class HubItemOperationV2025R0ActionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<HubItemOperationV2025R0ActionField>> {

    public HubItemOperationV2025R0ActionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<HubItemOperationV2025R0ActionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(HubItemOperationV2025R0ActionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<HubItemOperationV2025R0ActionField>(value));
    }
  }

  public static class HubItemOperationV2025R0ActionFieldSerializer
      extends JsonSerializer<EnumWrapper<HubItemOperationV2025R0ActionField>> {

    public HubItemOperationV2025R0ActionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<HubItemOperationV2025R0ActionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
