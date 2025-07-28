package com.box.sdkgen.schemas.trashfolder;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TrashFolderPathCollectionField extends SerializableObject {

  @JsonProperty("total_count")
  protected final long totalCount;

  protected final List<TrashFolderPathCollectionEntriesField> entries;

  public TrashFolderPathCollectionField(
      @JsonProperty("total_count") long totalCount,
      @JsonProperty("entries") List<TrashFolderPathCollectionEntriesField> entries) {
    super();
    this.totalCount = totalCount;
    this.entries = entries;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public List<TrashFolderPathCollectionEntriesField> getEntries() {
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
    TrashFolderPathCollectionField casted = (TrashFolderPathCollectionField) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "TrashFolderPathCollectionField{"
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
