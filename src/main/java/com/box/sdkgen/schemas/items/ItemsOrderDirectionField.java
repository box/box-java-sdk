package com.box.sdkgen.schemas.items;

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

public enum ItemsOrderDirectionField implements Valuable {
  ASC("ASC"),
  DESC("DESC");

  private final String value;

  ItemsOrderDirectionField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class ItemsOrderDirectionFieldDeserializer
      extends JsonDeserializer<EnumWrapper<ItemsOrderDirectionField>> {

    public ItemsOrderDirectionFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<ItemsOrderDirectionField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(ItemsOrderDirectionField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<ItemsOrderDirectionField>(value));
    }
  }

  public static class ItemsOrderDirectionFieldSerializer
      extends JsonSerializer<EnumWrapper<ItemsOrderDirectionField>> {

    public ItemsOrderDirectionFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<ItemsOrderDirectionField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
