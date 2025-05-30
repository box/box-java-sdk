package com.box.sdkgen.schemas.collection;

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

public enum CollectionCollectionTypeField implements Valuable {
  FAVORITES("favorites");

  private final String value;

  CollectionCollectionTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollectionCollectionTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollectionCollectionTypeField>> {

    public CollectionCollectionTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollectionCollectionTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollectionCollectionTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollectionCollectionTypeField>(value));
    }
  }

  public static class CollectionCollectionTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CollectionCollectionTypeField>> {

    public CollectionCollectionTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollectionCollectionTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
