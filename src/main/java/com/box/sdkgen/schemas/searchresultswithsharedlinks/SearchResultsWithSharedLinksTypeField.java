package com.box.sdkgen.schemas.searchresultswithsharedlinks;

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

public enum SearchResultsWithSharedLinksTypeField implements Valuable {
  SEARCH_RESULTS_WITH_SHARED_LINKS("search_results_with_shared_links");

  private final String value;

  SearchResultsWithSharedLinksTypeField(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static class SearchResultsWithSharedLinksTypeFieldDeserializer
      extends JsonDeserializer<EnumWrapper<SearchResultsWithSharedLinksTypeField>> {

    public SearchResultsWithSharedLinksTypeFieldDeserializer() {
      super();
    }

    @Override
    public EnumWrapper<SearchResultsWithSharedLinksTypeField> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      return Arrays.stream(SearchResultsWithSharedLinksTypeField.values())
          .filter((v) -> v.getValue().equalsIgnoreCase(value))
          .findFirst()
          .map(EnumWrapper::new)
          .orElse(new EnumWrapper<SearchResultsWithSharedLinksTypeField>(value));
    }
  }

  public static class SearchResultsWithSharedLinksTypeFieldSerializer
      extends JsonSerializer<EnumWrapper<SearchResultsWithSharedLinksTypeField>> {

    public SearchResultsWithSharedLinksTypeFieldSerializer() {
      super();
    }

    @Override
    public void serialize(
        EnumWrapper<SearchResultsWithSharedLinksTypeField> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeString(value.getStringValue());
    }
  }
}
