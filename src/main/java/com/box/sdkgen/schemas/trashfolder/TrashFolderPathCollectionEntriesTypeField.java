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

public enum TrashFolderPathCollectionEntriesTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  TrashFolderPathCollectionEntriesTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrashFolderPathCollectionEntriesTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrashFolderPathCollectionEntriesTypeField>> {

    public TrashFolderPathCollectionEntriesTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrashFolderPathCollectionEntriesTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrashFolderPathCollectionEntriesTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrashFolderPathCollectionEntriesTypeField>(value));
    }
  }

  public static class TrashFolderPathCollectionEntriesTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TrashFolderPathCollectionEntriesTypeField>> {

    public TrashFolderPathCollectionEntriesTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrashFolderPathCollectionEntriesTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
