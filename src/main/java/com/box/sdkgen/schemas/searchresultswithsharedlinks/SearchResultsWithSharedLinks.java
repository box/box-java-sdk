package com.box.sdkgen.schemas.searchresultswithsharedlinks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.searchresultwithsharedlink.SearchResultWithSharedLink;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SearchResultsWithSharedLinks extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected Long limit;

  protected Long offset;

  @JsonDeserialize(
      using =
          SearchResultsWithSharedLinksTypeField.SearchResultsWithSharedLinksTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          SearchResultsWithSharedLinksTypeField.SearchResultsWithSharedLinksTypeFieldSerializer
              .class)
  protected EnumWrapper<SearchResultsWithSharedLinksTypeField> type;

  protected List<SearchResultWithSharedLink> entries;

  public SearchResultsWithSharedLinks() {
    super();
    this.type =
        new EnumWrapper<SearchResultsWithSharedLinksTypeField>(
            SearchResultsWithSharedLinksTypeField.SEARCH_RESULTS_WITH_SHARED_LINKS);
  }

  protected SearchResultsWithSharedLinks(Builder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.limit = builder.limit;
    this.offset = builder.offset;
    this.type = builder.type;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public Long getLimit() {
    return limit;
  }

  public Long getOffset() {
    return offset;
  }

  public EnumWrapper<SearchResultsWithSharedLinksTypeField> getType() {
    return type;
  }

  public List<SearchResultWithSharedLink> getEntries() {
    return entries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchResultsWithSharedLinks casted = (SearchResultsWithSharedLinks) o;
    return Objects.equals(totalCount, casted.totalCount)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(offset, casted.offset)
        && Objects.equals(type, casted.type)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, limit, offset, type, entries);
  }

  @Override
  public String toString() {
    return "SearchResultsWithSharedLinks{"
        + "totalCount='"
        + totalCount
        + '\''
        + ", "
        + "limit='"
        + limit
        + '\''
        + ", "
        + "offset='"
        + offset
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long totalCount;

    protected Long limit;

    protected Long offset;

    protected EnumWrapper<SearchResultsWithSharedLinksTypeField> type;

    protected List<SearchResultWithSharedLink> entries;

    public Builder() {
      super();
      this.type =
          new EnumWrapper<SearchResultsWithSharedLinksTypeField>(
              SearchResultsWithSharedLinksTypeField.SEARCH_RESULTS_WITH_SHARED_LINKS);
    }

    public Builder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public Builder type(SearchResultsWithSharedLinksTypeField type) {
      this.type = new EnumWrapper<SearchResultsWithSharedLinksTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<SearchResultsWithSharedLinksTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder entries(List<SearchResultWithSharedLink> entries) {
      this.entries = entries;
      return this;
    }

    public SearchResultsWithSharedLinks build() {
      return new SearchResultsWithSharedLinks(this);
    }
  }
}
