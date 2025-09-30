package com.box.sdkgen.schemas.trashfile;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TrashFilePathCollectionField extends SerializableObject {

  @JsonProperty("total_count")
  protected final long totalCount;

  protected final List<TrashFilePathCollectionEntriesField> entries;

  public TrashFilePathCollectionField(
      @JsonProperty("total_count") long totalCount,
      @JsonProperty("entries") List<TrashFilePathCollectionEntriesField> entries) {
    super();
    this.totalCount = totalCount;
    this.entries = entries;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public List<TrashFilePathCollectionEntriesField> getEntries() {
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
    TrashFilePathCollectionField casted = (TrashFilePathCollectionField) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "TrashFilePathCollectionField{"
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
