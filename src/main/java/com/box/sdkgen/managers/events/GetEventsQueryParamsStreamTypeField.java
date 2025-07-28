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

public enum GetEventsQueryParamsStreamTypeField implements Valuable {
  ALL("all"),
  CHANGES("changes"),
  SYNC("sync"),
  ADMIN_LOGS("admin_logs"),
  ADMIN_LOGS_STREAMING("admin_logs_streaming");

  private final String value;

  GetEventsQueryParamsStreamTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class GetEventsQueryParamsStreamTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<GetEventsQueryParamsStreamTypeField>> {

    public GetEventsQueryParamsStreamTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<GetEventsQueryParamsStreamTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(GetEventsQueryParamsStreamTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<GetEventsQueryParamsStreamTypeField>(value));
    }
  }

  public static class GetEventsQueryParamsStreamTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<GetEventsQueryParamsStreamTypeField>> {

    public GetEventsQueryParamsStreamTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<GetEventsQueryParamsStreamTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
