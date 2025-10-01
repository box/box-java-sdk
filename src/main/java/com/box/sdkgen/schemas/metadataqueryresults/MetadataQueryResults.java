package com.box.sdkgen.schemas.metadataqueryresults;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadataqueryresultitem.MetadataQueryResultItem;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A page of files and folders that matched the metadata query. */
@JsonFilter("nullablePropertyFilter")
public class MetadataQueryResults extends SerializableObject {

  /**
   * The mini representation of the files and folders that match the search terms.
   *
   * <p>By default, this endpoint returns only the most basic info about the items. To get
   * additional fields for each item, including any of the metadata, use the `fields` attribute in
   * the query.
   */
  protected List<MetadataQueryResultItem> entries;

  /**
   * The limit that was used for this search. This will be the same as the `limit` query parameter
   * unless that value exceeded the maximum value allowed.
   */
  protected Long limit;

  /** The marker for the start of the next page of results. */
  @JsonProperty("next_marker")
  protected String nextMarker;

  public MetadataQueryResults() {
    super();
  }

  protected MetadataQueryResults(Builder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<MetadataQueryResultItem> getEntries() {
    return entries;
  }

  public Long getLimit() {
    return limit;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataQueryResults casted = (MetadataQueryResults) o;
    return Objects.equals(entries, casted.entries)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries, limit, nextMarker);
  }

  @Override
  public String toString() {
    return "MetadataQueryResults{"
        + "entries='"
        + entries
        + '\''
        + ", "
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<MetadataQueryResultItem> entries;

    protected Long limit;

    protected String nextMarker;

    public Builder entries(List<MetadataQueryResultItem> entries) {
      this.entries = entries;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public MetadataQueryResults build() {
      return new MetadataQueryResults(this);
    }
  }
}
