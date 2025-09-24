package com.box.sdkgen.managers.events;

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

public enum GetEventStreamQueryParamsStreamTypeField implements Valuable {
  ALL("all"),
  CHANGES("changes"),
  SYNC("sync"),
  ADMIN_LOGS("admin_logs"),
  ADMIN_LOGS_STREAMING("admin_logs_streaming");

  private final String value;

  GetEventStreamQueryParamsStreamTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetEventStreamQueryParamsStreamTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetEventStreamQueryParamsStreamTypeField>> {

    public GetEventStreamQueryParamsStreamTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetEventStreamQueryParamsStreamTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetEventStreamQueryParamsStreamTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetEventStreamQueryParamsStreamTypeField>(value));
    }
  }

  public static class GetEventStreamQueryParamsStreamTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GetEventStreamQueryParamsStreamTypeField>> {

    public GetEventStreamQueryParamsStreamTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetEventStreamQueryParamsStreamTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
