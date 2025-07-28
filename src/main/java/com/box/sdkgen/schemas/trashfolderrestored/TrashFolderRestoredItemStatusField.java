package com.box.sdkgen.schemas.trashfolderrestored;

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

public enum TrashFolderRestoredItemStatusField implements Valuable {
  ACTIVE("active"),
  TRASHED("trashed"),
  DELETED("deleted");

  private final String value;

  TrashFolderRestoredItemStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrashFolderRestoredItemStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrashFolderRestoredItemStatusField>> {

    public TrashFolderRestoredItemStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrashFolderRestoredItemStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrashFolderRestoredItemStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrashFolderRestoredItemStatusField>(value));
    }
  }

  public static class TrashFolderRestoredItemStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<TrashFolderRestoredItemStatusField>> {

    public TrashFolderRestoredItemStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrashFolderRestoredItemStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
