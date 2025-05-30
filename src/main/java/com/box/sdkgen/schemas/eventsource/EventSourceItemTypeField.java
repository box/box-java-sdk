package com.box.sdkgen.schemas.eventsource;

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

public enum EventSourceItemTypeField implements Valuable {
  FILE("file"),
  FOLDER("folder");

  private final String value;

  EventSourceItemTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class EventSourceItemTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<EventSourceItemTypeField>> {

    public EventSourceItemTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<EventSourceItemTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(EventSourceItemTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<EventSourceItemTypeField>(value));
    }
  }

  public static class EventSourceItemTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<EventSourceItemTypeField>> {

    public EventSourceItemTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<EventSourceItemTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
