package com.box.sdkgen.schemas.trashweblink;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TrashWebLinkPathCollectionField extends SerializableObject {

  /** The number of folders in this list. */
  @JsonProperty("total_count")
  protected final long totalCount;

  /** Array of folders for this item's path collection. */
  protected final List<TrashWebLinkPathCollectionEntriesField> entries;

  public TrashWebLinkPathCollectionField(
      @JsonProperty("total_count") long totalCount,
      @JsonProperty("entries") List<TrashWebLinkPathCollectionEntriesField> entries) {
    super();
    this.totalCount = totalCount;
    this.entries = entries;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public List<TrashWebLinkPathCollectionEntriesField> getEntries() {
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
    TrashWebLinkPathCollectionField casted = (TrashWebLinkPathCollectionField) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "TrashWebLinkPathCollectionField{"
        + "totalCount='"
        + totalCount
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }
}
