package com.box.sdkgen.schemas.folderlocks;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.folderlock.FolderLock;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** A list of folder locks. */
@JsonFilter("nullablePropertyFilter")
public class FolderLocks extends SerializableObject {

  /** A list of folder locks. */
  protected List<FolderLock> entries;

  /**
   * The limit that was used for these entries. This will be the same as the `limit` query parameter
   * unless that value exceeded the maximum value allowed. The maximum value varies by API.
   */
  protected String limit;

  /** The marker for the start of the next page of results. */
  @JsonProperty("next_marker")
  @Nullable
  protected String nextMarker;

  public FolderLocks() {
    super();
  }

  protected FolderLocks(Builder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<FolderLock> getEntries() {
    return entries;
  }

  public String getLimit() {
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
    FolderLocks casted = (FolderLocks) o;
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
    return "FolderLocks{"
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

    protected List<FolderLock> entries;

    protected String limit;

    protected String nextMarker;

    public Builder entries(List<FolderLock> entries) {
      this.entries = entries;
      return this;
    }

    public Builder limit(String limit) {
      this.limit = limit;
      return this;
    }

    public Builder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      this.markNullableFieldAsSet("next_marker");
      return this;
    }

    public FolderLocks build() {
      return new FolderLocks(this);
    }
  }
}
