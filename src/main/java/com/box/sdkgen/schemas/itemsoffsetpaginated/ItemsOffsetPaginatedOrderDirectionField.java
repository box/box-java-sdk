package com.box.sdkgen.schemas.itemsoffsetpaginated;

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

public enum ItemsOffsetPaginatedOrderDirectionField implements Valuable {
  ASC("ASC"),
  DESC("DESC");

  private final String value;

  ItemsOffsetPaginatedOrderDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ItemsOffsetPaginatedOrderDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ItemsOffsetPaginatedOrderDirectionField>> {

    public ItemsOffsetPaginatedOrderDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ItemsOffsetPaginatedOrderDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ItemsOffsetPaginatedOrderDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ItemsOffsetPaginatedOrderDirectionField>(value));
    }
  }

  public static class ItemsOffsetPaginatedOrderDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<ItemsOffsetPaginatedOrderDirectionField>> {

    public ItemsOffsetPaginatedOrderDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ItemsOffsetPaginatedOrderDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
