package com.box.sdkgen.schemas.trashfilerestored;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TrashFileRestoredPathCollectionField extends SerializableObject {

  /** The number of folders in this list. */
  @JsonProperty("total_count")
  protected final long totalCount;

  /** The parent folders for this item. */
  protected final List<FolderMini> entries;

  public TrashFileRestoredPathCollectionField(
      @JsonProperty("total_count") long totalCount,
      @JsonProperty("entries") List<FolderMini> entries) {
    super();
    this.totalCount = totalCount;
    this.entries = entries;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public List<FolderMini> getEntries() {
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
    TrashFileRestoredPathCollectionField casted = (TrashFileRestoredPathCollectionField) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "TrashFileRestoredPathCollectionField{"
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
