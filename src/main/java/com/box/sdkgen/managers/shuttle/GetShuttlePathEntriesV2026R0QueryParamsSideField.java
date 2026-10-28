package com.box.sdkgen.managers.shuttle;

import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public enum GetShuttlePathEntriesV2026R0QueryParamsSideField implements Valuable {
  SOURCE("source"),
  TARGET("target");

  private final String value;

  GetShuttlePathEntriesV2026R0QueryParamsSideField(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static class GetShuttlePathEntriesV2026R0QueryParamsSideFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField>> {

    public GetShuttlePathEntriesV2026R0QueryParamsSideFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetShuttlePathEntriesV2026R0QueryParamsSideField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField>(value));
    }
  }

  public static class GetShuttlePathEntriesV2026R0QueryParamsSideFieldSerializer
      extends JsonSerializer<EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField>> {

    public GetShuttlePathEntriesV2026R0QueryParamsSideFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
