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

public enum CollectionTypeField implements Valuable {
  COLLECTION("collection");

  private final String value;

  CollectionTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollectionTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollectionTypeField>> {

    public CollectionTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollectionTypeField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollectionTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollectionTypeField>(value));
    }
  }

  public static class CollectionTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<CollectionTypeField>> {

    public CollectionTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollectionTypeField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
