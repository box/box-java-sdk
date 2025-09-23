package com.box.sdkgen.schemas.searchresults;

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

public enum SearchResultsTypeField implements Valuable {
  SEARCH_RESULTS_ITEMS("search_results_items");

  private final String value;

  SearchResultsTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SearchResultsTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SearchResultsTypeField>> {

    public SearchResultsTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SearchResultsTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SearchResultsTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SearchResultsTypeField>(value));
    }
  }

  public static class SearchResultsTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SearchResultsTypeField>> {

    public SearchResultsTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SearchResultsTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
