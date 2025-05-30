package com.box.sdkgen.schemas.trashfilerestored;

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

public enum TrashFileRestoredItemStatusField implements Valuable {
  ACTIVE("active"),
  TRASHED("trashed"),
  DELETED("deleted");

  private final String value;

  TrashFileRestoredItemStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrashFileRestoredItemStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrashFileRestoredItemStatusField>> {

    public TrashFileRestoredItemStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrashFileRestoredItemStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrashFileRestoredItemStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrashFileRestoredItemStatusField>(value));
    }
  }

  public static class TrashFileRestoredItemStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<TrashFileRestoredItemStatusField>> {

    public TrashFileRestoredItemStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrashFileRestoredItemStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
