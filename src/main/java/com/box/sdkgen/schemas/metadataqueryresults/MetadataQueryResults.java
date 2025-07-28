package com.box.sdkgen.schemas.metadataqueryresults;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filefullorfolderfull.FileFullOrFolderFull;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataQueryResults extends SerializableObject {

  protected List<FileFullOrFolderFull> entries;

  protected Long limit;

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

  public List<FileFullOrFolderFull> getEntries() {
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

    protected List<FileFullOrFolderFull> entries;

    protected Long limit;

    protected String nextMarker;

    public Builder entries(List<FileFullOrFolderFull> entries) {
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
