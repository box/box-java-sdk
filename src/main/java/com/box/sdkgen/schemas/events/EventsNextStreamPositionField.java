package com.box.sdkgen.schemas.events;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(
    using = EventsNextStreamPositionField.EventsNextStreamPositionFieldDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class EventsNextStreamPositionField extends OneOfTwo<String, Long> {

  public EventsNextStreamPositionField(String string) {
    super(string, null);
  }

  public EventsNextStreamPositionField(Long longNumber) {
    super(null, longNumber);
  }

  public boolean isString() {
    return value0 != null;
  }

  public String getString() {
    return value0;
  }

  public boolean isLongNumber() {
    return value1 != null;
  }

  public Long getLongNumber() {
    return value1;
  }

  static class EventsNextStreamPositionFieldDeserializer
      extends JsonDeserializer<EventsNextStreamPositionField> {

    public EventsNextStreamPositionFieldDeserializer() {
      super();
    }

    @Override
    public EventsNextStreamPositionField deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      try {
        return new EventsNextStreamPositionField(
            OneOfTwo.OBJECT_MAPPER.convertValue(node, Long.class));
      } catch (Exception ignored) {
      }
      try {
        return new EventsNextStreamPositionField(
            OneOfTwo.OBJECT_MAPPER.convertValue(node, String.class));
      } catch (Exception ignored) {
      }
      throw new JsonMappingException(jp, "Unable to deserialize EventsNextStreamPositionField");
    }
  }
}
