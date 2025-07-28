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

public enum CollectionNameField implements Valuable {
  FAVORITES("Favorites");

  private final String value;

  CollectionNameField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class CollectionNameFieldDeserializer
      extends JsonDeserializer<EnumWrapper<CollectionNameField>> {

    public CollectionNameFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<CollectionNameField> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(CollectionNameField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<CollectionNameField>(value));
    }
  }

  public static class CollectionNameFieldSerializer
      extends JsonSerializer<EnumWrapper<CollectionNameField>> {

    public CollectionNameFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<CollectionNameField> value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
