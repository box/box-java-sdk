package com.box.sdkgen.managers.search;

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

public enum SearchForContentQueryParamsTrashContentField implements Valuable {
  NON_TRASHED_ONLY("non_trashed_only"),
  TRASHED_ONLY("trashed_only"),
  ALL_ITEMS("all_items");

  private final String value;

  SearchForContentQueryParamsTrashContentField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SearchForContentQueryParamsTrashContentFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SearchForContentQueryParamsTrashContentField>> {

    public SearchForContentQueryParamsTrashContentFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SearchForContentQueryParamsTrashContentField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SearchForContentQueryParamsTrashContentField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SearchForContentQueryParamsTrashContentField>(value));
    }
  }

  public static class SearchForContentQueryParamsTrashContentFieldSerializer
      extends JsonSerializer<EnumWrapper<SearchForContentQueryParamsTrashContentField>> {

    public SearchForContentQueryParamsTrashContentFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SearchForContentQueryParamsTrashContentField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
