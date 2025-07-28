package com.box.sdkgen.schemas.trashweblinkrestored;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TrashWebLinkRestoredPathCollectionField extends SerializableObject {

  @JsonProperty("total_count")
  protected final long totalCount;

  protected final List<FolderMini> entries;

  public TrashWebLinkRestoredPathCollectionField(
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
    TrashWebLinkRestoredPathCollectionField casted = (TrashWebLinkRestoredPathCollectionField) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "TrashWebLinkRestoredPathCollectionField{"
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
