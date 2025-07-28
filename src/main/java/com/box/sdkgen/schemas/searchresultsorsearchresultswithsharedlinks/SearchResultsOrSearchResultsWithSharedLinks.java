package com.box.sdkgen.schemas.searchresultsorsearchresultswithsharedlinks;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.searchresults.SearchResults;
import com.box.sdkgen.schemas.searchresultswithsharedlinks.SearchResultsWithSharedLinks;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(
    using =
        SearchResultsOrSearchResultsWithSharedLinks
            .SearchResultsOrSearchResultsWithSharedLinksDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class SearchResultsOrSearchResultsWithSharedLinks
    extends OneOfTwo<SearchResults, SearchResultsWithSharedLinks> {

  protected final Long totalCount;

  protected final Long limit;

  protected final Long offset;

  protected final String type;

  public SearchResultsOrSearchResultsWithSharedLinks(SearchResults searchResults) {
    super(searchResults, null);
    this.totalCount = searchResults.getTotalCount();
    this.limit = searchResults.getLimit();
    this.offset = searchResults.getOffset();
    this.type = EnumWrapper.convertToString(searchResults.getType());
  }

  public SearchResultsOrSearchResultsWithSharedLinks(
      SearchResultsWithSharedLinks searchResultsWithSharedLinks) {
    super(null, searchResultsWithSharedLinks);
    this.totalCount = searchResultsWithSharedLinks.getTotalCount();
    this.limit = searchResultsWithSharedLinks.getLimit();
    this.offset = searchResultsWithSharedLinks.getOffset();
    this.type = EnumWrapper.convertToString(searchResultsWithSharedLinks.getType());
  }

  public boolean isSearchResults() {
    return value0 != null;
  }

  public SearchResults getSearchResults() {
    return value0;
  }

  public boolean isSearchResultsWithSharedLinks() {
    return value1 != null;
  }

  public SearchResultsWithSharedLinks getSearchResultsWithSharedLinks() {
    return value1;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public long getLimit() {
    return limit;
  }

  public long getOffset() {
    return offset;
  }

  public String getType() {
    return type;
  }

  static class SearchResultsOrSearchResultsWithSharedLinksDeserializer
      extends JsonDeserializer<SearchResultsOrSearchResultsWithSharedLinks> {

    public SearchResultsOrSearchResultsWithSharedLinksDeserializer() {
      super();
    }

    @Override
    public SearchResultsOrSearchResultsWithSharedLinks deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "search_results_items":
            return new SearchResultsOrSearchResultsWithSharedLinks(
                JsonManager.deserialize(node, SearchResults.class));
          case "search_results_with_shared_links":
            return new SearchResultsOrSearchResultsWithSharedLinks(
                JsonManager.deserialize(node, SearchResultsWithSharedLinks.class));
        }
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize SearchResultsOrSearchResultsWithSharedLinks");
    }
  }
}
