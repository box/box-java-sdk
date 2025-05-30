package com.box.sdkgen.schemas.trackingcode;

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

public enum TrackingCodeTypeField implements Valuable {
  TRACKING_CODE("tracking_code");

  private final String value;

  TrackingCodeTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrackingCodeTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrackingCodeTypeField>> {

    public TrackingCodeTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrackingCodeTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrackingCodeTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrackingCodeTypeField>(value));
    }
  }

  public static class TrackingCodeTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TrackingCodeTypeField>> {

    public TrackingCodeTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrackingCodeTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
