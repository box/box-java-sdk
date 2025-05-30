package com.box.sdkgen.schemas.folderlocks;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.folderlock.FolderLock;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class FolderLocks extends SerializableObject {

  protected List<FolderLock> entries;

  protected String limit;

  @JsonProperty("next_marker")
  protected String nextMarker;

  public FolderLocks() {
    super();
  }

  protected FolderLocks(FolderLocksBuilder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
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

  public static class FolderLocksBuilder {

    protected List<FolderLock> entries;

    protected String limit;

    protected String nextMarker;

    public FolderLocksBuilder entries(List<FolderLock> entries) {
      this.entries = entries;
      return this;
    }

    public FolderLocksBuilder limit(String limit) {
      this.limit = limit;
      return this;
    }

    public FolderLocksBuilder nextMarker(String nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public FolderLocks build() {
      return new FolderLocks(this);
    }
  }
}
