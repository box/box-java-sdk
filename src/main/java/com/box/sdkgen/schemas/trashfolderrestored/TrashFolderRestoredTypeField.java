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

public enum TrashFolderRestoredTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  TrashFolderRestoredTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrashFolderRestoredTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrashFolderRestoredTypeField>> {

    public TrashFolderRestoredTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrashFolderRestoredTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrashFolderRestoredTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrashFolderRestoredTypeField>(value));
    }
  }

  public static class TrashFolderRestoredTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TrashFolderRestoredTypeField>> {

    public TrashFolderRestoredTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrashFolderRestoredTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
