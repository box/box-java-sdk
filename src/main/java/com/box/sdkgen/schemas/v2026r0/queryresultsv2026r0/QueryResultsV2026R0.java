package com.box.sdkgen.schemas.v2026r0.queryresultsv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.queryresultentryv2026r0.QueryResultEntryV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A paginated list of items matching the query, using milestone-based marker pagination. */
@JsonFilter("nullablePropertyFilter")
public class QueryResultsV2026R0 extends SerializableObject {

  /** The list of items matching the query predicate. */
  protected final List<QueryResultEntryV2026R0> entries;

  /**
   * The marker for the start of the next page of results. When `null`, there are no further results
   * available.
   */
  @JsonProperty("next_marker")
  @Nullable
  protected String nextMarker;

  /**
   * The limit that was used for this request. This will be the same as the limit query parameter
   * unless that value exceeded the maximum value allowed.
   */
  protected final int limit;

  public QueryResultsV2026R0(
      @JsonProperty("entries") List<QueryResultEntryV2026R0> entries,
      @JsonProperty("limit") int limit) {
    super();
    this.entries = entries;
    this.limit = limit;
  }

  protected QueryResultsV2026R0(Builder builder) {
    super();
    this.entries = builder.entries;
    this.nextMarker = builder.nextMarker;
    this.limit = builder.limit;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<QueryResultEntryV2026R0> getEntries() {
    return entries;
  }

  public String getNextMarker() {
    return nextMarker;
  }

  public int getLimit() {
    return limit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryResultsV2026R0 casted = (QueryResultsV2026R0) o;
    return Objects.equals(entries, casted.entries)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(limit, casted.limit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries, nextMarker, limit);
  }

  @Override
  public String toString() {
    return "QueryResultsV2026R0{"
        + "entries='"
        + entries
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + ", "
        + "limit='"
        + limit
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final List<QueryResultEntryV2026R0> entries;

    protected String nextMarker;

    protected final int limit;

    public Builder(List<QueryResultEntryV2026R0> entries, int limit) {
      super();
      this.entries = entries;
      this.limit = limit;
    }

    public Builder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      this.markNullableFieldAsSet("next_marker");
      return this;
    }

    public QueryResultsV2026R0 build() {
      return new QueryResultsV2026R0(this);
    }
  }
}
