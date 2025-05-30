package com.box.sdkgen.schemas.trashweblink;

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

public enum TrashWebLinkPathCollectionEntriesTypeField implements Valuable {
  FOLDER("folder");

  private final String value;

  TrashWebLinkPathCollectionEntriesTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class TrashWebLinkPathCollectionEntriesTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<TrashWebLinkPathCollectionEntriesTypeField>> {

    public TrashWebLinkPathCollectionEntriesTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<TrashWebLinkPathCollectionEntriesTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(TrashWebLinkPathCollectionEntriesTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<TrashWebLinkPathCollectionEntriesTypeField>(value));
    }
  }

  public static class TrashWebLinkPathCollectionEntriesTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<TrashWebLinkPathCollectionEntriesTypeField>> {

    public TrashWebLinkPathCollectionEntriesTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<TrashWebLinkPathCollectionEntriesTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
