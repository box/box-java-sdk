package com.box.sdkgen.schemas.trashfolder;

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

public enum TrashFolderItemStatusField implements Valuable {
  ACTIVE("active"),
  TRASHED("trashed"),
  DELETED("deleted");

  private final String value;

  TrashFolderItemStatusField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrashFolderItemStatusFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrashFolderItemStatusField>> {

    public TrashFolderItemStatusFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrashFolderItemStatusField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrashFolderItemStatusField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrashFolderItemStatusField>(value));
    }
  }

  public static class TrashFolderItemStatusFieldSerializer
      extends JsonSerializer<EnumWrapper<TrashFolderItemStatusField>> {

    public TrashFolderItemStatusFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrashFolderItemStatusField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
