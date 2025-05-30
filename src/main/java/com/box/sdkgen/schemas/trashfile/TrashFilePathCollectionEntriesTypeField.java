package com.box.sdkgen.schemas.trashfile;

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

public enum TrashFilePathCollectionEntriesTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  TrashFilePathCollectionEntriesTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrashFilePathCollectionEntriesTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrashFilePathCollectionEntriesTypeField>> {

    public TrashFilePathCollectionEntriesTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrashFilePathCollectionEntriesTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrashFilePathCollectionEntriesTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrashFilePathCollectionEntriesTypeField>(value));
    }
  }

  public static class TrashFilePathCollectionEntriesTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TrashFilePathCollectionEntriesTypeField>> {

    public TrashFilePathCollectionEntriesTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrashFilePathCollectionEntriesTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
